package com.workstation.common.base;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 基础分页请求对象
 * @date 2024/1/4 23:17 星期四
 */
@Schema
public class BasePageQuery {
    @Schema(description = "页码", required = true, example = "1")
    private int pageNum = 1;

    @Schema(description = "每页记录数", required = true, example = "10")
    private int pageSize = 10;

    public BasePageQuery() {
    }

    public BasePageQuery(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
