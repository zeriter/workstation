package com.workstation.modules.system.domain.bo;

import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色权限业务对象
 * @date 2023/12/31 16:54 星期日
 */
public class RolePermsBO {
    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 权限标识集合
     */
    private Set<String> perms;

    public RolePermsBO(String roleCode, Set<String> perms) {
        this.roleCode = roleCode;
        this.perms = perms;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }
}
