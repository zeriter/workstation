package com.workstation.common.annotation;

import java.lang.annotation.*;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 忽略认证接口注解
 * @date 2023/12/4 20:28 周一
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenAuth {
}
