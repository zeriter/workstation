package com.workstation.common.annotation;

import java.lang.annotation.*;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description MP数据权限注解
 * @date 2023/12/9 1:11 周六
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataPermission {

    /**
     * 数据权限
     */
    String deptAlias() default "";

    String deptIdColumnName() default "dept_id";

    String userAlias() default "";

    String userIdColumnName() default "create_by";

}
