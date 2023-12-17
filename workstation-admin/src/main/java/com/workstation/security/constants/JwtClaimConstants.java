package com.workstation.security.constants;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description JWT常量类
 * @date 2023/12/9 17:46 周六
 */
public interface JwtClaimConstants {

    /**
     * 用户ID
     */
    String USER_ID = "userId";

    /**
     * 用户名
     */
    String USERNAME = "username";

    /**
     * 部门ID
     */
    String DEPT_ID = "deptId";

    /**
     * 数据权限
     */
    String DATA_SCOPE = "dataScope";

    /**
     * 权限(角色Code)集合
     */
    String AUTHORITIES = "authorities";

}
