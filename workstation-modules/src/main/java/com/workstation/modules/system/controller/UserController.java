package com.workstation.modules.system.controller;

import com.workstation.common.param.R;
import com.workstation.modules.system.domain.result.UserInfoResult;
import com.workstation.modules.system.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "系统-用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Resource
    private IUserService userService;

    @Operation(summary = "保存用户", description = "保存用户")
    @PostMapping
    public R<String> save() {
        return R.success();
    }

    @Operation(summary = "删除用户", description = "删除用户")
    @DeleteMapping("/{userIds}")
    public R<String> delete() {
        return R.success();
    }

    @Operation(summary = "修改用户", description = "修改用户")
    @PutMapping
    public R<String> edit() {
        return R.success();
    }

    @Operation(summary = "用户列表", description = "用户列表")
    @GetMapping("/list")
    public R<List<String>> users() {
        return R.success();
    }

    @Operation(summary = "用户详情", description = "用户详情")
    @GetMapping("/info")
    public R<UserInfoResult> details() {
        return R.data(userService.details());
    }

    @Operation(summary = "导入用户", description = "导入用户")
    @PostMapping("/import")
    public R<String> importUsers() {
        return R.success();
    }

    @Operation(summary = "导出用户", description = "导出用户")
    @GetMapping("/export")
    public R<List<String>> exportUsers() {
        return R.success();
    }
}
