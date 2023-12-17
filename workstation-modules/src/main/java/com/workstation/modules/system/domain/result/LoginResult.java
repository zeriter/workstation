package com.workstation.modules.system.domain.result;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 登录返回参数
 * @date 2023/12/18 0:57 星期一
 */
@Schema(description = "登录响应对象")
public class LoginResult {
    @Schema(description = "访问token")
    private String accessToken;
    @Schema(description = "token 类型", example = "Bearer")
    private String tokenType;
    @Schema(description = "刷新token")
    private String refreshToken;
    @Schema(description = "过期时间(单位：毫秒)")
    private Long expires;

    public LoginResult() {
    }

    public LoginResult(String accessToken, String tokenType, String refreshToken, Long expires) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
