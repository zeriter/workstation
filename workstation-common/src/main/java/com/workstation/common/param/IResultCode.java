package com.workstation.common.param;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 返回状态
 * @date 2023/12/2 19:18 周六
 */
public interface IResultCode {
    /**
     * 获取统一信息
     */
    String getMessage();

    /**
     * 获取统一code
     */
    int getCode();
}
