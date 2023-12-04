package com.workstation.auth.controller;

import com.workstation.common.param.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 认证相关请求
 * @date 2023/12/2 19:28 周六
 */
@Tag(name = "认证-系统认证")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Operation(summary = "获取验证码", description = "获取验证码")
    @GetMapping("/captcha")
    public R<String> captcha() {
        return R.success("sdf");
    }

    @Operation(summary = "用户登录", description = "用户登录")
    @PostMapping("/login")
    public R<String> login() {
        return R.success("sdf");
    }

    @Operation(summary = "用户注销", description = "用户注销")
    @GetMapping("/logout")
    public R<String> logout() {
        return R.success("sdf");
    }
}
