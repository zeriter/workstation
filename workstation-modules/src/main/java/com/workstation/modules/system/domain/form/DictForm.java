package com.workstation.modules.system.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典表单对象
 * @date 2024/1/13 0:01 星期六
 */
@Schema(description = "字典表单对象")
public class DictForm {
    @Schema(description = "字典ID")
    private Long id;

    @Schema(description = "类型编码")
    private String typeCode;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典值")
    private String value;

    @Schema(description = "状态(1:启用;0:禁用)")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "字典备注")
    private String remark;

    public DictForm() {
    }

    public DictForm(Long id, String typeCode, String name, String value, Integer status, Integer sort, String remark) {
        this.id = id;
        this.typeCode = typeCode;
        this.name = name;
        this.value = value;
        this.status = status;
        this.sort = sort;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
