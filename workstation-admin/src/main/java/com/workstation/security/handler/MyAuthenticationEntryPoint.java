package com.workstation.security.handler;

import com.workstation.common.param.ResultCode;
import com.workstation.modules.system.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 认证异常处理
 * @date 2023/12/9 17:11 周六
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int status = response.getStatus();
        if (status == HttpServletResponse.SC_NOT_FOUND) {
            // 资源不存在
            ResponseUtils.writeErrMsg(response, ResultCode.NOT_FOUND);
        } else {
            // 未认证或者token过期
            ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
        }
    }
}