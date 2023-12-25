package com.workstation.modules.system.controller;

import com.workstation.common.param.R;
import com.workstation.modules.system.domain.query.MenuQuery;
import com.workstation.modules.system.domain.result.MenuResult;
import com.workstation.modules.system.domain.result.RouteResult;
import com.workstation.modules.system.service.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
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
    private R<String> save() {
        return R.success();
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @DeleteMapping("/{userIds}")
    private R<String> delete() {
        return R.success();
    }

    @Operation(summary = "修改菜单", description = "修改菜单")
    @PutMapping
    private R<String> edit() {
        return R.success();
    }

    @Operation(summary = "菜单列表", description = "菜单列表")
    @GetMapping()
    private R<List<MenuResult>> menus(MenuQuery param) {
        return R.data(menuService.listMenus(param));
    }

    @Operation(summary = "菜单详情", description = "菜单详情")
    @GetMapping("/{userId}")
    private R<List<String>> detail(@PathVariable(value = "userId", required = false) Long userId) {
        return R.success();
    }

    @Operation(summary = "导入菜单", description = "导入菜单")
    @PostMapping("/import")
    private R<String> importUsers() {
        return R.success();
    }

    @Operation(summary = "导出菜单", description = "导出菜单")
    @GetMapping("/export")
    private R<List<String>> exportUsers() {
        return R.success();
    }

    @Operation(summary = "路由列表", description = "路由列表")
    @GetMapping("/routes")
    private R<List<RouteResult>> routes() {
        return R.data(menuService.listRoutes());
    }
}
