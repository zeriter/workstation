package com.workstation.common.constant;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 通用常量信息
 * @date 2023/12/4 20:16 周一
 */
public class Constants {
    /**
     * 管理员
     */
    public static final String ROOT_ROLE_CODE = "ROOT";
    public static final String ADMIN_ROLE_CODE = "ADMIN";
    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";

    /**
     * 自动识别json对象白名单配置（仅允许解析的包名，范围越小越安全）
     */
    public static final String[] JSON_WHITELIST_STR = {"org.springframework", "com.workstation"};

    /**
     * captcha类型
     */
    public static final String CAPTCHA_MATH = "math";
    public static final String CAPTCHA_CHAR = "char";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 根节点ID
     */
    public static final Long ROOT_NODE_ID = 0L;

    /**
     * 系统默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

}