package com.workstation.modules.system.domain.result;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典分页对象
 * @date 2024/1/13 0:14 星期六
 */
@Schema(description = "字典分页对象")
public class DictPageResult {
    @Schema(description = "字典ID")
    private Long id;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典值")
    private String value;

    @Schema(description = "状态(1:启用;0:禁用)")
    private Integer status;

    public DictPageResult() {
    }

    public DictPageResult(Long id, String name, String value, Integer status) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
