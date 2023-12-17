package com.workstation.modules.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.workstation.modules.system.enums.MenuTypeEnum;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description menu模型
 * @date 2023/12/9 18:09 周六
 */
@TableName(value = "sys_menu")
public class Menu extends BaseEntity {
    /**
     * 菜单ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
     */
    private MenuTypeEnum type;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;

    /**
     * 权限标识
     */
    private String perm;

    /**
     * 显示状态(1:显示;0:隐藏)
     */
    private Integer visible;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 跳转路径
     */
    private String redirect;

    /**
     * 父节点路径，以英文逗号(,)分割
     */
    private String treePath;

    /**
     * 【菜单】是否开启页面缓存(1:开启;0:关闭)
     */
    private Integer keepAlive;

    /**
     * 【目录】只有一个子路由是否始终显示(1:是 0:否)
     */
    private Integer alwaysShow;

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

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
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