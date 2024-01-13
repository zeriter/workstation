package com.workstation.modules.system.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色表单对象
 * @date 2024/1/13 0:04 星期六
 */
@Schema(description = "角色表单对象")
public class RoleForm {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @Schema(description = "角色编码")
    @NotBlank(message = "角色编码不能为空")
    private String code;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "角色状态(1-正常；0-停用)")
    private Integer status;

    @Schema(description = "数据权限")
    private Integer dataScope;

    public RoleForm() {
    }

    public RoleForm(Long id, String name, String code, Integer sort, Integer status, Integer dataScope) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.sort = sort;
        this.status = status;
        this.dataScope = dataScope;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDataScope() {
        return dataScope;
    }

    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }
}
