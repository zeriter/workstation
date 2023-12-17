package com.workstation.modules.system.domain.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 登录用户
 * @date 2023/12/9 16:50 周六
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private Long userId;

    private String username;

    private String password;

    private Boolean enabled;

    private Collection<SimpleGrantedAuthority> authorities;

    private Set<String> perms;

    private Long deptId;

    private Integer dataScope;

    public UserDetails() {
    }

    public UserDetails(AuthInfo user) {
        this.userId = user.getUserId();
        Set<String> roles = user.getRoles();
        Set<SimpleGrantedAuthority> authorities;
        if (CollectionUtil.isNotEmpty(roles)) {
            authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // 标识角色
                    .collect(Collectors.toSet());
        } else {
            authorities = Collections.EMPTY_SET;
        }
        this.authorities = authorities;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = ObjectUtil.equal(user.getStatus(), 1);
        this.perms = user.getPerms();
        this.deptId = user.getDeptId();
        this.dataScope = user.getDataScope();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Integer getDataScope() {
        return dataScope;
    }

    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
