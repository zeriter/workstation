package com.workstation.modules.system.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 部门表单对象
 * @date 2024/1/13 0:00 星期六
 */
@Schema(description = "部门表单对象")
public class DeptForm {
    @Schema(description = "部门ID")
    private Long id;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "父部门ID")
    @NotNull(message = "父部门ID不能为空")
    private Long parentId;

    @Schema(description = "状态(1:启用;0:禁用)")
    private Integer status;

    @Schema(description = "排序(数字越小排名越靠前)")
    private Integer sort;

    public DeptForm() {
    }

    public DeptForm(Long id, String name, Long parentId, Integer status, Integer sort) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.status = status;
        this.sort = sort;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}

