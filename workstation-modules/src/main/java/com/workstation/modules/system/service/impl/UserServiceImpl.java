package com.workstation.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.common.constant.Constants;
import com.workstation.modules.system.converter.UserConverter;
import com.workstation.modules.system.domain.bo.UserBO;
import com.workstation.modules.system.domain.bo.UserFormBO;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.form.UserForm;
import com.workstation.modules.system.domain.query.UserPageQuery;
import com.workstation.modules.system.domain.result.UserExportResult;
import com.workstation.modules.system.domain.result.UserInfoResult;
import com.workstation.modules.system.domain.result.UserPageResult;
import com.workstation.modules.system.domain.security.AuthInfo;
import com.workstation.modules.system.mapper.UserMapper;
import com.workstation.modules.system.service.IMenuService;
import com.workstation.modules.system.service.IRoleService;
import com.workstation.modules.system.service.IUserRoleService;
import com.workstation.modules.system.service.IUserService;
import com.workstation.modules.system.utils.DateUtils;
import com.workstation.modules.system.utils.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户业务处理类
 * @date 2023/12/2 19:36 周六
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private IMenuService menuService;
    @Resource
    private IRoleService roleService;
    @Resource
    private UserConverter userConverter;
    @Resource
    private IUserRoleService userRoleService;

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
        UserInfoResult result = new UserInfoResult(user.getId(), user.getUsername(), user.getNickname(), user.getAvatar(), null, null);

        Set<String> roles = SecurityUtil.getRoles();
        result.setRoles(roles);
        // 用户权限集合
        if (CollectionUtil.isNotEmpty(roles)) {
            Set<String> perms = menuService.listRolePerms(roles);
            result.setPerms(perms);
        }
        return result;
    }

    @Override
    public IPage<UserPageResult> getUserPage(UserPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");

        // 查询数据
        Page<UserBO> userPage = this.baseMapper.getUserPage(page, queryParams);

        // 实体转换
        return userConverter.toPageVo(userPage);
    }

    @Override
    public Boolean saveUser(UserForm userForm) {
        String username = userForm.getUsername();
        long count = this.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        Assert.isTrue(count == 0, "用户名已存在");
        // 实体转换 form->entity
        User entity = userConverter.form2Entity(userForm);
        // 设置默认加密密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String defaultEncryptPwd = passwordEncoder.encode(Constants.DEFAULT_PASSWORD);
        entity.setPassword(defaultEncryptPwd);
        // 新增用户
        boolean result = this.save(entity);
        if (result) {
            // 保存用户角色
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }
        return result;
    }

    @Override
    public UserForm getUserFormData(Long userId) {
        UserFormBO userFormBO = this.baseMapper.getUserDetail(userId);
        // 实体转换po->form
        UserForm userForm = userConverter.bo2Form(userFormBO);
        return userForm;
    }

    @Override
    @Transactional
    public Boolean updateUser(Long userId, UserForm userForm) {
        String username = userForm.getUsername();

        long count = this.count(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .ne(User::getId, userId)
        );
        Assert.isTrue(count == 0, "用户名已存在");

        // form -> entity
        User entity = userConverter.form2Entity(userForm);

        // 修改用户
        boolean result = this.updateById(entity);

        if (result) {
            // 保存用户角色
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }
        return result;
    }

    @Override
    public Boolean deleteUsers(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的用户数据为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    @Override
    public Boolean updatePassword(Long userId, String password) {
        return this.update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, userId)
                .set(User::getPassword, new BCryptPasswordEncoder().encode(password))
        );
    }

    @Override
    public List<UserExportResult> listExportUsers(UserPageQuery queryParams) {
        List<UserExportResult> list = this.baseMapper.listExportUsers(queryParams);
        return list;
    }
}
