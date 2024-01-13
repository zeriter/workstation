package com.workstation.modules.system.controller;

import com.workstation.common.param.Option;
import com.workstation.common.param.R;
import com.workstation.modules.system.service.IDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
    @Resource
    private IDictService dictService;

    @Operation(summary = "保存字典", description = "保存字典")
    @PostMapping
    public R<String> save() {
        return R.success();
    }

    @Operation(summary = "删除字典", description = "删除字典")
    @DeleteMapping("/{userIds}")
    public R<String> delete() {
        return R.success();
    }

    @Operation(summary = "修改字典", description = "修改字典")
    @PutMapping
    public R<String> edit() {
        return R.success();
    }

    @Operation(summary = "字典列表", description = "字典列表")
    @GetMapping("/list")
    public R<List<String>> users() {
        return R.success();
    }

    @Operation(summary = "字典详情", description = "字典详情")
    @GetMapping("/{userId}")
    public R<List<String>> detail(@PathVariable(value = "userId", required = false) Long userId) {
        return R.success();
    }

    @Operation(summary = "导入字典", description = "导入字典")
    @PostMapping("/import")
    public R<String> importUsers() {
        return R.success();
    }

    @Operation(summary = "导出字典", description = "导出字典")
    @GetMapping("/export")
    public R<List<String>> exportUsers() {
        return R.success();
    }

    @Operation(summary = "字典下拉列表", description = "字典下拉列表")
    @GetMapping("/{typeCode}/options")
    public R<List<Option>> listDictOptions(@Parameter(description = "字典类型编码") @PathVariable String typeCode) {
        return R.data(dictService.listDictOptions(typeCode));
    }
}
