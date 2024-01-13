package com.workstation.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.workstation.common.param.Option;
import com.workstation.common.param.R;
import com.workstation.modules.system.domain.form.RoleForm;
import com.workstation.modules.system.domain.query.RolePageQuery;
import com.workstation.modules.system.domain.result.RolePageResult;
import com.workstation.modules.system.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户管理
 * @date 2023/12/2 19:35 周六
 */
@Tag(name = "系统-角色管理")
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @Operation(summary = "保存角色", description = "保存角色")
    @PostMapping
    public R<Boolean> save(@Valid @RequestBody RoleForm roleForm) {
        return R.data(roleService.saveRole(roleForm));
    }

    @Operation(summary = "删除角色", description = "删除角色")
    @DeleteMapping("/{ids}")
    public R<Boolean> delete(@Parameter(description = "删除角色，多个以英文逗号(,)分割") @PathVariable String ids) {
        return R.data(roleService.deleteRoles(ids));
    }

    @Operation(summary = "修改角色", description = "修改角色")
    @PutMapping("/{id}")
    public R<Boolean> edit(@Valid @RequestBody RoleForm roleForm) {
        return R.data(roleService.saveRole(roleForm));
    }

    @Operation(summary = "角色列表", description = "角色列表")
    @GetMapping("/page")
    public R<IPage<RolePageResult>> getRolePage(@ParameterObject RolePageQuery queryParams) {
        return R.data(roleService.getRolePage(queryParams));
    }

    @Operation(summary = "角色详情", description = "角色详情")
    @GetMapping("/{roleId}/form")
    public R<RoleForm> detail(@Parameter(description = "角色ID") @PathVariable Long roleId) {
        return R.data(roleService.getRoleForm(roleId));
    }

    @Operation(summary = "角色下拉列表", description = "角色下拉列表")
    @GetMapping("/options")
    public R<List<Option>> listRoleOptions() {
        return R.data(roleService.listRoleOptions());
    }

    @Operation(summary = "获取角色的菜单ID集合", description = "获取角色的菜单ID集合")
    @GetMapping("/{roleId}/menuIds")
    public R<List<Long>> getRoleMenuIds(@Parameter(description = "角色ID") @PathVariable Long roleId) {
        List<Long> menuIds = roleService.getRoleMenuIds(roleId);
        return R.data(menuIds);
    }

    @Operation(summary = "分配菜单(包括按钮权限)给角色", description = "分配菜单(包括按钮权限)给角色")
    @PutMapping("/{roleId}/menus")
    public R assignMenusToRole(@PathVariable Long roleId, @RequestBody List<Long> menuIds) {
        boolean result = roleService.assignMenusToRole(roleId, menuIds);
        return R.data(roleService.assignMenusToRole(roleId, menuIds));
    }
}
