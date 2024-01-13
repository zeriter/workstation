package com.workstation.modules.system.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 部门查询对象
 * @date 2024/1/13 0:05 星期六
 */
@Schema(description = "部门分页查询对象")
public class DeptQuery {
    @Schema(description = "关键字(部门名称)")
    private String keywords;

    @Schema(description = "状态(1->正常；0->禁用)")
    private Integer status;

    public DeptQuery() {
    }

    public DeptQuery(String keywords, Integer status) {
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
