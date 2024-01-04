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
@Tag(name = "系统-部门管理")
@RestController
@RequestMapping("/system/dept")
public class DeptController {

    @Operation(summary = "保存部门", description = "保存部门")
    @PostMapping
    public R<String> save() {
        return R.success();
    }

    @Operation(summary = "删除部门", description = "删除部门")
    @DeleteMapping("/{userIds}")
    public R<String> delete() {
        return R.success();
    }

    @Operation(summary = "修改部门", description = "修改部门")
    @PutMapping
    public R<String> edit() {
        return R.success();
    }

    @Operation(summary = "部门列表", description = "部门列表")
    @GetMapping("/list")
    public R<List<String>> users() {
        return R.success();
    }

    @Operation(summary = "部门详情", description = "部门详情")
    @GetMapping("/{userId}")
    public R<List<String>> detail(@PathVariable(value = "userId", required = false) Long userId) {
        return R.success();
    }

    @Operation(summary = "导入部门", description = "导入部门")
    @PostMapping("/import")
    public R<String> importUsers() {
        return R.success();
    }

    @Operation(summary = "导出部门", description = "导出部门")
    @GetMapping("/export")
    public R<List<String>> exportUsers() {
        return R.success();
    }
}
