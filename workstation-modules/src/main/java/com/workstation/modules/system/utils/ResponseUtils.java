package com.workstation.modules.system.utils;

import cn.hutool.json.JSONUtil;
import com.workstation.common.param.R;
import com.workstation.common.param.ResultCode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 请求工具类
 * @date 2023/12/9 17:18 周六
 */
public class ResponseUtils {

    /**
     * 异常消息返回(适用过滤器中处理异常响应)
     *
     * @param response
     * @param resultCode
     */
    public static void writeErrMsg(HttpServletResponse response, ResultCode resultCode) throws IOException {
        switch (resultCode) {
            case ACCESS_UNAUTHORIZED:
            case TOKEN_INVALID:
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                break;
            case TOKEN_ACCESS_FORBIDDEN:
                response.setStatus(HttpStatus.FORBIDDEN.value());
                break;
            default:
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                break;
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSONUtil.toJsonStr(R.fail(resultCode)));
    }


}
