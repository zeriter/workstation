package com.workstation.auth.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.workstation.auth.service.IAuthService;
import com.workstation.common.annotation.OpenAuth;
import com.workstation.common.constant.CacheConstants;
import com.workstation.common.constant.Constants;
import com.workstation.common.param.R;
import com.workstation.common.utils.RedisUtil;
import com.workstation.modules.system.domain.result.LoginResult;
import com.workstation.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private IAuthService authService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenProvider jwtTokenProvider;
    @Resource
    private RedisUtil redisUtil;

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
    @DeleteMapping("/logout")
    public R logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtTokenProvider.resolveToken(request);
        if (StrUtil.isNotBlank(token)) {
            Claims claims = jwtTokenProvider.getTokenClaims(token);
            String jti = claims.get("jti", String.class);
            Date expiration = claims.getExpiration();
            if (expiration != null) {
                long ttl = expiration.getTime() - System.currentTimeMillis();
                redisUtil.redisTemplate.opsForValue().set(CacheConstants.BLACKLIST_TOKEN_PREFIX + jti, null, ttl, TimeUnit.MILLISECONDS);
            } else {
                redisUtil.redisTemplate.opsForValue().set(CacheConstants.BLACKLIST_TOKEN_PREFIX + jti, null);
            }
        }
        SecurityContextHolder.clearContext();
        return R.success();
    }
}
