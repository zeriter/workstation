package com.workstation.modules.system.service;

import com.workstation.modules.system.domain.result.RouteResult;

import java.util.List;
import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单接口处理类
 * @date 2023/12/2 19:35 周六
 */
public interface IMenuService {
    Set<String> listRolePerms(Set<String> roles);

    List<RouteResult> listRoutes();
}
