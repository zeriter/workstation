package com.workstation.security.handler;

import com.workstation.common.param.ResultCode;
import com.workstation.modules.system.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 访问异常处理类
 * @date 2023/12/9 17:10 周六
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ResponseUtils.writeErrMsg(response, ResultCode.ACCESS_UNAUTHORIZED);
    }
}
