package com.workstation.modules.system.controller;

import com.workstation.common.param.Option;
import com.workstation.common.param.R;
import com.workstation.modules.system.domain.form.DeptForm;
import com.workstation.modules.system.domain.query.DeptQuery;
import com.workstation.modules.system.domain.result.DeptResult;
import com.workstation.modules.system.service.IDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户管理
 * @date 2023/12/2 19:35 周六
 */
@Tag(name = "系统-部门管理")
@RestController
@RequestMapping("/system/dept")
public class DeptController {
    @Resource
    private IDeptService deptService;

    @Operation(summary = "保存部门", description = "保存部门")
    @PostMapping
    public R<Long> save(@Valid @RequestBody DeptForm formData) {
        return R.data(deptService.saveDept(formData));
    }

    @Operation(summary = "删除部门", description = "删除部门")
    @DeleteMapping("/{ids}")
    public R<Boolean> delete(@Parameter(description = "部门ID，多个以英文逗号(,)分割") @PathVariable("ids") String ids) {
        return R.data(deptService.deleteByIds(ids));
    }

    @Operation(summary = "修改部门", description = "修改部门")
    @PutMapping("/{deptId}")
    public R<Long> edit(@PathVariable Long deptId, @Valid @RequestBody DeptForm formData) {
        return R.data(deptService.updateDept(deptId, formData));
    }

    @Operation(summary = "部门列表", description = "部门列表")
    @GetMapping
    public R<List<DeptResult>> depts(@ParameterObject DeptQuery queryParams) {
        return R.data(deptService.listDepartments(queryParams));
    }

    @Operation(summary = "部门详情", description = "部门详情")
    @GetMapping("/{deptId}/form")
    public R<DeptForm> detail(@Parameter(description = "部门ID") @PathVariable Long deptId) {
        return R.data(deptService.getDeptForm(deptId));
    }

    @Operation(summary = "部门下拉选项", description = "部门下拉选项")
    @GetMapping("/options")
    public R<List<Option>> listDeptOptions() {
        return R.data(deptService.listDeptOptions());
    }
}
