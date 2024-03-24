package com.workstation.modules.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.workstation.common.param.Option;
import com.workstation.common.param.R;
import com.workstation.modules.quartz.service.ITaskService;
import com.workstation.modules.system.domain.form.RoleForm;
import com.workstation.modules.system.domain.query.RolePageQuery;
import com.workstation.modules.system.domain.result.RolePageResult;
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
 * @description 任务管理
 * @date 2023/12/2 19:35 周六
 */
@Tag(name = "任务-任务管理")
@RestController
@RequestMapping("/system/task")
public class TaskController {
    @Resource
    private ITaskService taskService;

    @Operation(summary = "保存任务", description = "保存任务")
    @PostMapping
    public R<Boolean> save(@Valid @RequestBody RoleForm roleForm) {
        return R.data(Boolean.TRUE);
    }

    @Operation(summary = "删除任务", description = "删除任务")
    @DeleteMapping("/{ids}")
    public R<Boolean> delete(@Parameter(description = "删除任务，多个以英文逗号(,)分割") @PathVariable String ids) {
        return R.data(Boolean.TRUE);
    }

    @Operation(summary = "修改任务", description = "修改任务")
    @PutMapping("/{id}")
    public R<Boolean> edit(@Valid @RequestBody RoleForm roleForm) {
        return R.data(Boolean.TRUE);
    }

    @Operation(summary = "任务列表", description = "任务列表")
    @GetMapping("/page")
    public R<IPage<RolePageResult>> getRolePage(@ParameterObject RolePageQuery queryParams) {
        return R.success();
    }

    @Operation(summary = "任务详情", description = "任务详情")
    @GetMapping("/{roleId}/form")
    public R<RoleForm> detail(@Parameter(description = "任务ID") @PathVariable Long roleId) {
        return R.success();
    }

    @Operation(summary = "任务下拉列表", description = "任务下拉列表")
    @GetMapping("/options")
    public R<List<Option>> listRoleOptions() {
        return R.success();
    }

    @Operation(summary = "获取任务的菜单ID集合", description = "获取任务的菜单ID集合")
    @GetMapping("/{roleId}/menuIds")
    public R<List<Long>> getRoleMenuIds(@Parameter(description = "任务ID") @PathVariable Long roleId) {
        return R.success();
    }

    @Operation(summary = "分配菜单(包括按钮权限)给任务", description = "分配菜单(包括按钮权限)给任务")
    @PutMapping("/{roleId}/menus")
    public R<Boolean> assignMenusToRole(@PathVariable Long roleId, @RequestBody List<Long> menuIds) {
        return R.success();
    }
}
