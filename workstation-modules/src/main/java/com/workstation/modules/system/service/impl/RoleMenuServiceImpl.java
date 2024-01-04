package com.workstation.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.modules.system.domain.bo.RolePermsBO;
import com.workstation.modules.system.domain.entity.RoleMenu;
import com.workstation.modules.system.mapper.RoleMenuMapper;
import com.workstation.modules.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户菜单关系实现类
 * @date 2023/12/31 16:55 星期日
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    @Override
    public List<Long> listMenuIdsByRoleId(Long roleId) {
        List<Long> menuIds = this.baseMapper.listMenuIdsByRoleId(roleId);
        return menuIds;
    }

    /**
     * 获取权限角色列表
     *
     * @return 权限角色列表
     */
    @Override
    public List<RolePermsBO> getRolePermsList(String roleCode) {
        List<RolePermsBO> rolePerms = this.baseMapper.getRolePermsList(roleCode);
        return rolePerms;
    }
}
