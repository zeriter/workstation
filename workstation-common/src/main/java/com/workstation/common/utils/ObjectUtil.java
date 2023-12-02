package com.workstation.common.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 对象工具类
 * @date 2023/12/2 19:20 周六
 */
public class ObjectUtil extends ObjectUtils {
    public ObjectUtil() {
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }
}
