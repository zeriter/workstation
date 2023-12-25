package com.workstation.modules.system.domain.form;

import com.workstation.modules.system.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单表单对象
 * @date 2023/12/26 0:29 星期二
 */
@Schema(description = "菜单表单对象")
public class MenuForm {
    @Schema(description = "菜单ID")
    private Long id;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)")
    private MenuTypeEnum type;

    @Schema(description = "路由路径")
    private String path;

    @Schema(description = "组件路径(vue页面完整路径，省略.vue后缀)")
    private String component;

    @Schema(description = "权限标识")
    private String perm;

    @Schema(description = "显示状态(1:显示;0:隐藏)")
    private Integer visible;

    @Schema(description = "排序(数字越小排名越靠前)")
    private Integer sort;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "跳转路径")
    private String redirect;

    @Schema(description = "【菜单】是否开启页面缓存", example = "1")
    private Integer keepAlive;

    @Schema(description = "【目录】只有一个子路由是否始终显示", example = "1")
    private Integer alwaysShow;

    public MenuForm() {
    }

    public MenuForm(Long id, Long parentId, String name, MenuTypeEnum type, String path, String component, String perm, Integer visible, Integer sort, String icon, String redirect, Integer keepAlive, Integer alwaysShow) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
        this.path = path;
        this.component = component;
        this.perm = perm;
        this.visible = visible;
        this.sort = sort;
        this.icon = icon;
        this.redirect = redirect;
        this.keepAlive = keepAlive;
        this.alwaysShow = alwaysShow;
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

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Integer getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Integer alwaysShow) {
        this.alwaysShow = alwaysShow;
    }
}
