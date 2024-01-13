package com.workstation.modules.system.controller;

import com.workstation.common.param.Option;
import com.workstation.common.param.R;
import com.workstation.modules.system.domain.form.MenuForm;
import com.workstation.modules.system.domain.query.MenuQuery;
import com.workstation.modules.system.domain.result.MenuResult;
import com.workstation.modules.system.domain.result.RouteResult;
import com.workstation.modules.system.service.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户管理
 * @date 2023/12/2 19:35 周六
 */
@Tag(name = "系统-菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;

    @Operation(summary = "保存菜单", description = "保存菜单")
    @PostMapping
    public R<Boolean> save(@RequestBody MenuForm menuForm) {
        return R.data(menuService.saveMenu(menuForm));
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@Parameter(description = "菜单ID，多个以英文(,)分割") @PathVariable("id") Long id) {
        return R.data(menuService.deleteMenu(id));
    }

    @Operation(summary = "修改菜单", description = "修改菜单")
    @PutMapping("/{id}")
    public R<Boolean> edit(@RequestBody MenuForm menuForm) {
        return R.data(menuService.saveMenu(menuForm));
    }

    @Operation(summary = "菜单列表", description = "菜单列表")
    @GetMapping()
    public R<List<MenuResult>> menus(MenuQuery param) {
        return R.data(menuService.listMenus(param));
    }

    @Operation(summary = "菜单详情", description = "菜单详情")
    @GetMapping("/{id}/form")
    public R<MenuForm> detail(@Parameter(description = "菜单ID") @PathVariable Long id) {
        return R.data(menuService.getMenuForm(id));
    }

    @Operation(summary = "路由列表", description = "路由列表")
    @GetMapping("/routes")
    public R<List<RouteResult>> routes() {
        return R.data(menuService.listRoutes());
    }

    @Operation(summary = "菜单下拉列表", description = "菜单下拉列表")
    @GetMapping("/options")
    public R<List<Option>> listMenuOptions() {
        List<Option> menus = menuService.listMenuOptions();
        return R.data(menus);
    }
}
