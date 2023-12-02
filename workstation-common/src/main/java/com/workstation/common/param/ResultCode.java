package com.workstation.common.param;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 返回状态枚举
 * @date 2023/12/2 19:19 周六
 */
public enum ResultCode implements IResultCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 业务异常
     */
    FAILURE(400, "业务异常"),

    /**
     * 业务异常
     */
    UN_AUTHORIZED(401, "业务异常"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(404, "404 没找到请求"),

    /**
     * 消息不能读取
     */
    MSG_NOT_READABLE(400, "消息不能读取"),

    /**
     * 不支持当前请求方法
     */
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),

    /**
     * 不支持当前媒体类型
     */
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),

    /**
     * 请求被拒绝
     */
    REQ_REJECT(403, "请求被拒绝"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器异常"),

    /**
     * token无效或已过期
     */
    TOKEN_INVALID(500, "认证失败或已过期"),

    /**
     * token已被禁止访问
     */
    TOKEN_ACCESS_FORBIDDEN(500, "token已被禁止访问"),

    /**
     * 验证码失效
     */
    VERIFY_CODE_TIMEOUT(500, "验证码失效"),

    /**
     * 验证码错误
     */
    VERIFY_CODE_ERROR(500, "验证码错误"),
    /**
     * 访问未授权
     */
    ACCESS_UNAUTHORIZED(401, "访问未授权"),

    /**
     * 缺少必要请求参数
     */
    PARAM_MISS(400, "缺少必要的请求参数"),

    /**
     * 请求参数类型错误
     */
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),

    /**
     * 请求参数绑定错误
     */
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),

    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(400, "参数校验失败");

    final int code;
    final String message;

    ResultCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
