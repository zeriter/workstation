package com.workstation.common.constant;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 缓存的key 常量
 * @date 2023/12/4 21:01 周一
 */
public class CacheConstants {
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 角色和权限缓存前缀
     */
    public static final String ROLE_PERMS_PREFIX = "role_perms:";

    /**
     * 黑名单Token缓存前缀
     */
    public static final String BLACKLIST_TOKEN_PREFIX = "blacklist_token:";
}
