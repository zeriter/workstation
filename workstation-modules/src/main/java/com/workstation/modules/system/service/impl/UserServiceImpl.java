package com.workstation.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.common.constant.CacheConstants;
import com.workstation.common.utils.RedisUtil;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.result.UserInfoResult;
import com.workstation.modules.system.domain.security.AuthInfo;
import com.workstation.modules.system.mapper.UserMapper;
import com.workstation.modules.system.service.IMenuService;
import com.workstation.modules.system.service.IRoleService;
import com.workstation.modules.system.service.IUserService;
import com.workstation.modules.system.utils.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户接口处理类
 * @date 2023/12/2 19:36 周六
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private IMenuService menuService;
    @Resource
    private IRoleService roleService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    @Override
    public AuthInfo getUserAuthInfo(String username) {
        AuthInfo userAuthInfo = this.baseMapper.getUserAuthInfo(username);
        if (userAuthInfo != null) {
            Set<String> roles = userAuthInfo.getRoles();
            if (CollectionUtil.isNotEmpty(roles)) {
                Set<String> perms = menuService.listRolePerms(roles);
                userAuthInfo.setPerms(perms);
            }

            // 获取最大范围的数据权限
            Integer dataScope = roleService.getMaximumDataScope(roles);
            userAuthInfo.setDataScope(dataScope);
        }
        return userAuthInfo;
    }

    /**
     * 查询用户详情
     *
     * @return
     */
    @Override
    public UserInfoResult details() {
        String username = SecurityUtil.getUser().getUsername();
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).select(
                User::getId,
                User::getUsername,
                User::getNickname,
                User::getAvatar
        ));
        Set<String> roles = SecurityUtil.getRoles();
        // 用户权限集合
        Set<String> perms = new HashSet<>();
        if (CollectionUtil.isNotEmpty(roles)) {
            for (String role : roles) {
                Set<String> rolePerms = redisUtil.getCacheSet(CacheConstants.ROLE_PERMS_PREFIX + role);
                if (CollectionUtil.isNotEmpty(rolePerms)) {
                    perms.addAll(rolePerms);
                }
            }
        }
        return new UserInfoResult(user.getId(), user.getUsername(), user.getNickname(), user.getAvatar(), roles, perms);
    }
}
