package com.workstation.modules.system.domain.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 路由对象
 * @date 2023/12/18 23:25 星期一
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "路由对象")
public class RouteResult {
    @Schema(description = "路由路径", example = "user")
    private String path;

    @Schema(description = "组件路径", example = "system/user/index")
    private String component;

    @Schema(description = "跳转链接", example = "https://www.youlai.tech")
    private String redirect;

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "路由属性")
    private Meta meta;
    @Schema(description = "子路由列表")
    private List<RouteResult> children;

    public RouteResult() {
    }

    public RouteResult(String path, String component, String redirect, String name, Meta meta, List<RouteResult> children) {
        this.path = path;
        this.component = component;
        this.redirect = redirect;
        this.name = name;
        this.meta = meta;
        this.children = children;
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

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<RouteResult> getChildren() {
        return children;
    }

    public void setChildren(List<RouteResult> children) {
        this.children = children;
    }

    @Schema(description = "路由属性类型")

    public static class Meta {

        @Schema(description = "路由title")
        private String title;

        @Schema(description = "ICON")
        private String icon;

        @Schema(description = "是否隐藏(true-是 false-否)", example = "true")
        private Boolean hidden;

        @Schema(description = "拥有路由权限的角色编码", example = "['ADMIN','ROOT']")
        private List<String> roles;

        @Schema(description = "【菜单】是否开启页面缓存", example = "true")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean keepAlive;

        @Schema(description = "【目录】只有一个子路由是否始终显示", example = "true")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Boolean alwaysShow;

        public Meta() {
        }

        public Meta(String title, String icon, Boolean hidden, List<String> roles, Boolean keepAlive, Boolean alwaysShow) {
            this.title = title;
            this.icon = icon;
            this.hidden = hidden;
            this.roles = roles;
            this.keepAlive = keepAlive;
            this.alwaysShow = alwaysShow;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Boolean getHidden() {
            return hidden;
        }

        public void setHidden(Boolean hidden) {
            this.hidden = hidden;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public Boolean getKeepAlive() {
            return keepAlive;
        }

        public void setKeepAlive(Boolean keepAlive) {
            this.keepAlive = keepAlive;
        }

        public Boolean getAlwaysShow() {
            return alwaysShow;
        }

        public void setAlwaysShow(Boolean alwaysShow) {
            this.alwaysShow = alwaysShow;
        }
    }
}
