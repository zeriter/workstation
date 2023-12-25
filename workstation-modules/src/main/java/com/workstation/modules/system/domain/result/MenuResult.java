package com.workstation.modules.system.domain.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.workstation.modules.system.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单返回类
 * @date 2023/12/26 0:14 星期二
 */
@Schema(description = "菜单视图对象")
public class MenuResult {
    @Schema(description = "菜单ID")
    private Long id;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单类型")
    private MenuTypeEnum type;

    @Schema(description = "路由路径")
    private String path;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "菜单排序(数字越小排名越靠前)")
    private Integer sort;

    @Schema(description = "菜单是否可见(1:显示;0:隐藏)")
    private Integer visible;

    @Schema(description = "ICON")
    private String icon;

    @Schema(description = "跳转路径")
    private String redirect;

    @Schema(description = "按钮权限标识")
    private String perm;

    @Schema(description = "子菜单")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<MenuResult> children;

    public MenuResult() {
    }

    public MenuResult(Long id, Long parentId, String name, MenuTypeEnum type, String path, String component, Integer sort, Integer visible, String icon, String redirect, String perm, List<MenuResult> children) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
        this.path = path;
        this.component = component;
        this.sort = sort;
        this.visible = visible;
        this.icon = icon;
        this.redirect = redirect;
        this.perm = perm;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuTypeEnum getType() {
        return type;
    }

    public void setType(MenuTypeEnum type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public List<MenuResult> getChildren() {
        return children;
    }

    public void setChildren(List<MenuResult> children) {
        this.children = children;
    }
}
