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
@Tag(name = "系统-字典管理")
@RestController
@RequestMapping("/system/dict")
public class DictController {

    @Operation(summary = "保存字典", description = "保存字典")
    @PostMapping
    private R<String> save() {
        return R.success();
    }

    @Operation(summary = "删除字典", description = "删除字典")
    @DeleteMapping("/{userIds}")
    private R<String> delete() {
        return R.success();
    }

    @Operation(summary = "修改字典", description = "修改字典")
    @PutMapping
    private R<String> edit() {
        return R.success();
    }

    @Operation(summary = "字典列表", description = "字典列表")
    @GetMapping("/list")
    private R<List<String>> users() {
        return R.success();
    }

    @Operation(summary = "字典详情", description = "字典详情")
    @GetMapping("/{userId}")
    private R<List<String>> detail(@PathVariable(value = "userId", required = false) Long userId) {
        return R.success();
    }

    @Operation(summary = "导入字典", description = "导入字典")
    @PostMapping("/import")
    private R<String> importUsers() {
        return R.success();
    }

    @Operation(summary = "导出字典", description = "导出字典")
    @GetMapping("/export")
    private R<List<String>> exportUsers() {
        return R.success();
    }
}
