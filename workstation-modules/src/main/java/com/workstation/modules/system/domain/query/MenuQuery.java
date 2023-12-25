package com.workstation.modules.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单查询对象
 * @date 2023/12/26 0:15 星期二
 */
@Schema(description = "部门分页查询对象")
public class MenuQuery {
    @Schema(description = "关键字(菜单名称)")
    private String keywords;

    @Schema(description = "状态(1->显示；0->隐藏)")
    private Integer status;

    public MenuQuery() {
    }

    public MenuQuery(String keywords, Integer status) {
        this.keywords = keywords;
        this.status = status;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
