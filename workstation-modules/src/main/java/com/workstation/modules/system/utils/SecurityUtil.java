package com.workstation.modules.system.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.workstation.common.constant.Constants;
import com.workstation.modules.system.domain.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 认证工具类
 * @date 2023/12/9 1:13 周六
 */
public class SecurityUtil {

    /**
     * 获取当前登录人信息
     *
     * @return
     */
    public static UserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (UserDetails) authentication.getPrincipal();
            }
        }
        return null;
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public static Long getUserId() {
        Long userId = Convert.toLong(getUser().getUserId());
        return userId;
    }

    /**
     * 获取部门ID
     *
     * @return
     */
    public static Long getDeptId() {
        Long userId = Convert.toLong(getUser().getDeptId());
        return userId;
    }

    /**
     * 获取数据权限范围
     *
     * @return DataScope
     */
    public static Integer getDataScope() {
        Integer dataScope = Convert.toInt(getUser().getDataScope());
        return dataScope;
    }


    /**
     * 获取用户角色集合
     *
     * @return
     */
    public static Set<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (CollectionUtil.isNotEmpty(authorities)) {
                Set<String> roles = authorities.stream().filter(item -> item.getAuthority().startsWith("ROLE_"))
                        .map(item -> StrUtil.removePrefix(item.getAuthority(), "ROLE_"))
                        .collect(Collectors.toSet());
                return roles;
            }
        }
        return Collections.EMPTY_SET;
    }

    /**
     * 获取用户权限集合
     *
     * @return
     */
    public static Set<String> getPerms() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (CollectionUtil.isNotEmpty(authorities)) {
                Set<String> perms = authorities.stream().filter(item -> !item.getAuthority().startsWith("ROLE_"))
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toSet());
                return perms;
            }
        }
        return Collections.EMPTY_SET;
    }

    /**
     * 是否超级管理员
     * <p>
     * 超级管理员忽视任何权限判断
     *
     * @return
     */
    public static boolean isRoot() {
        Set<String> roles = getRoles();

        return roles.contains(Constants.ROOT_ROLE_CODE);
    }


    /**
     * 是否拥有权限判断
     * <p>
     * 适用业务判断(接口权限判断适用Spring Security 自带注解 PreAuthorize 判断即可 )
     *
     * @return
     */
    public static boolean hasPerm(String perm) {

        if (isRoot()) {
            return true;
        }

        Set<String> perms = getPerms();

        boolean hasPerm = perms.stream().anyMatch(item -> PatternMatchUtils.simpleMatch(perm, item));
        return hasPerm;
    }

}
