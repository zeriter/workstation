package com.workstation.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.security.AuthInfo;
import com.workstation.modules.system.mapper.UserMapper;
import com.workstation.modules.system.service.IMenuService;
import com.workstation.modules.system.service.IRoleService;
import com.workstation.modules.system.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
