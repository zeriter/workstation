package com.workstation.modules.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色菜单关系表
 * @date 2023/12/31 16:50 星期日
 */
@TableName(value = "sys_role_menu")
public class RoleMenu {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    public RoleMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
