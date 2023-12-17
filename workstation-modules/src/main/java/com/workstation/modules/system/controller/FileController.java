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
@Tag(name = "系统-文件管理")
@RestController
@RequestMapping("/system/file")
public class FileController {

    @Operation(summary = "保存文件", description = "保存文件")
    @PostMapping
    private R<String> save() {
        return R.success();
    }

    @Operation(summary = "删除文件", description = "删除文件")
    @DeleteMapping("/{userIds}")
    private R<String> delete() {
        return R.success();
    }

    @Operation(summary = "修改文件", description = "修改文件")
    @PutMapping
    private R<String> edit() {
        return R.success();
    }

    @Operation(summary = "文件列表", description = "文件列表")
    @GetMapping("/list")
    private R<List<String>> users() {
        return R.success();
    }

    @Operation(summary = "文件详情", description = "文件详情")
    @GetMapping("/{userId}")
    private R<List<String>> detail(@PathVariable(value = "userId", required = false) Long userId) {
        return R.success();
    }

    @Operation(summary = "导入文件", description = "导入文件")
    @PostMapping("/import")
    private R<String> importUsers() {
        return R.success();
    }

    @Operation(summary = "导出文件", description = "导出文件")
    @GetMapping("/export")
    private R<List<String>> exportUsers() {
        return R.success();
    }
}
