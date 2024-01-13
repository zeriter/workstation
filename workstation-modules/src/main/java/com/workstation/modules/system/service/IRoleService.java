package com.workstation.modules.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.common.param.Option;
import com.workstation.modules.system.domain.entity.Role;
import com.workstation.modules.system.domain.form.RoleForm;
import com.workstation.modules.system.domain.query.RolePageQuery;
import com.workstation.modules.system.domain.result.RolePageResult;

import java.util.List;
import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色业务处理类
 * @date 2023/12/2 19:35 周六
 */
public interface IRoleService extends IService<Role> {
    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);


    List<Option> listRoleOptions();

    IPage<RolePageResult> getRolePage(RolePageQuery queryParams);

    Boolean saveRole(RoleForm roleForm);

    RoleForm getRoleForm(Long roleId);

    Boolean deleteRoles(String ids);

    List<Long> getRoleMenuIds(Long roleId);

    boolean assignMenusToRole(Long roleId, List<Long> menuIds);
}
