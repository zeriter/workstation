package com.workstation.modules.system.controller;

import com.workstation.common.param.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "保存角色", description = "保存角色")
    @PostMapping
    private R<String> save() {
        return R.success();
    }

    @Operation(summary = "删除角色", description = "删除角色")
    @DeleteMapping("/{userIds}")
    private R<String> delete() {
        return R.success();
    }

    @Operation(summary = "修改角色", description = "修改角色")
    @PutMapping
    private R<String> edit() {
        return R.success();
    }

    @Operation(summary = "角色列表", description = "角色列表")
    @GetMapping("/list")
    private R<List<String>> users() {
        return R.success();
    }

    @Operation(summary = "角色详情", description = "角色详情")
    @GetMapping("/{userId}")
    private R<List<String>> detail(@PathVariable(value = "userId", required = false) Long userId) {
        return R.success();
    }

    @Operation(summary = "导入角色", description = "导入角色")
    @PostMapping("/import")
    private R<String> importUsers() {
        return R.success();
    }

    @Operation(summary = "导出角色", description = "导出角色")
    @GetMapping("/export")
    private R<List<String>> exportUsers() {
        return R.success();
    }
}
