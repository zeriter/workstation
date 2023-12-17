package com.workstation.auth.controller;

import cn.hutool.core.lang.Dict;
import com.workstation.auth.service.IAuthService;
import com.workstation.common.annotation.OpenAuth;
import com.workstation.common.constant.Constants;
import com.workstation.common.param.R;
import com.workstation.modules.system.domain.result.LoginResult;
import com.workstation.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 认证相关请求
 * @date 2023/12/2 19:28 周六
 */
@Tag(name = "认证-系统认证")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Resource
    private IAuthService authService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "获取验证码", description = "获取验证码")
    @GetMapping("/captcha")
    @OpenAuth
    public R<Dict> captcha() {
        return R.data(authService.captcha());
    }

    @Operation(summary = "用户登录", description = "用户登录")
    @PostMapping("/login")
    public R<LoginResult> login(@Parameter(description = "用户名", example = "admin") @RequestParam String username,
                                @Parameter(description = "密码", example = "123456") @RequestParam String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username.toLowerCase().trim(), password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String accessToken = jwtTokenProvider.createToken(authentication);
        return R.data(new LoginResult(accessToken, Constants.TOKEN_PREFIX, null, null));
    }

    @Operation(summary = "用户注销", description = "用户注销")
    @GetMapping("/logout")
    public R<String> logout() {
        return R.success("sdf");
    }
}
