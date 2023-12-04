package com.workstation.common.constant;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 通用常量信息
 * @date 2023/12/4 20:16 周一
 */
public class Constants {
    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 自动识别json对象白名单配置（仅允许解析的包名，范围越小越安全）
     */
    public static final String[] JSON_WHITELIST_STR = {"org.springframework", "com.workstation"};

}