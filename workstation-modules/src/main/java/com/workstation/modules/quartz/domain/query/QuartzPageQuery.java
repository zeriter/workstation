package com.workstation.modules.quartz.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 任务分页查询对象
 * @date 2024/9/16 13:24 周一
 */
@Schema(description = "任务分页查询对象")
public class QuartzPageQuery {
    @Schema(description = "关键字(任务名称)")
    private String keywords;

    @Schema(description = "状态")
    private Integer status;

    public QuartzPageQuery() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
