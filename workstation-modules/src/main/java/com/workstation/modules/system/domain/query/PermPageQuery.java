package com.workstation.modules.system.domain.query;

import com.workstation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 权限分页查询对象
 * @date 2024/1/13 0:09 星期六
 */
@Schema
public class PermPageQuery extends BasePageQuery {
    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "菜单ID")
    private Long menuId;

    public PermPageQuery() {
    }

    public PermPageQuery(int pageNum, int pageSize, String name, Long menuId) {
        super(pageNum, pageSize);
        this.name = name;
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
