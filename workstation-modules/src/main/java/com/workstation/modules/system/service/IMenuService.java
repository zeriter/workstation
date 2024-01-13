package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.common.param.Option;
import com.workstation.modules.system.domain.entity.Menu;
import com.workstation.modules.system.domain.form.MenuForm;
import com.workstation.modules.system.domain.query.MenuQuery;
import com.workstation.modules.system.domain.result.MenuResult;
import com.workstation.modules.system.domain.result.RouteResult;

import java.util.List;
import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单业务处理类
 * @date 2023/12/2 19:35 周六
 */
public interface IMenuService extends IService<Menu> {
    Set<String> listRolePerms(Set<String> roles);

    List<RouteResult> listRoutes();

    List<MenuResult> listMenus(MenuQuery param);

    List<Option> listMenuOptions();

    Boolean saveMenu(MenuForm menuForm);

    MenuForm getMenuForm(Long id);

    Boolean deleteMenu(Long id);
}
