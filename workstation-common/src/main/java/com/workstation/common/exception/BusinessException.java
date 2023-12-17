package com.workstation.common.exception;

import com.workstation.common.param.IResultCode;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 自定义业务异常
 * @date 2023/12/9 17:32 周六
 */
public class BusinessException extends RuntimeException {

    public IResultCode resultCode;

    public BusinessException(IResultCode errorCode) {
        super(errorCode.getMessage());
        this.resultCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public IResultCode getResultCode() {
        return resultCode;
    }
}
