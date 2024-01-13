package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.modules.system.domain.bo.RolePermsBO;
import com.workstation.modules.system.domain.entity.RoleMenu;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户菜单业务处理类
 * @date 2023/12/31 16:48 星期日
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    List<Long> listMenuIdsByRoleId(Long roleId);


    /**
     * 获取角色和权限的列表
     *
     * @return 角色权限的列表
     */
    List<RolePermsBO> getRolePermsList(String roleCode);
}
