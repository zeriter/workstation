package com.workstation.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workstation.common.param.Option;
import com.workstation.common.param.R;
import com.workstation.modules.system.domain.form.DictForm;
import com.workstation.modules.system.domain.form.DictTypeForm;
import com.workstation.modules.system.domain.query.DictPageQuery;
import com.workstation.modules.system.domain.query.DictTypePageQuery;
import com.workstation.modules.system.domain.result.DictPageResult;
import com.workstation.modules.system.domain.result.DictTypePageResult;
import com.workstation.modules.system.service.IDictService;
import com.workstation.modules.system.service.IDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户管理
 * @date 2023/12/2 19:35 周六
 */
@Tag(name = "系统-字典管理")
@RestController
@RequestMapping("/system/dict")
public class DictController {
    @Resource
    private IDictService dictService;
    @Resource
    private IDictTypeService dictTypeService;
    @Operation(summary = "字典下拉列表", description = "字典下拉列表")
    @GetMapping("/{typeCode}/options")
    public R<List<Option>> listDictOptions(@Parameter(description = "字典类型编码") @PathVariable String typeCode) {
        return R.data(dictService.listDictOptions(typeCode));
    }

    @Operation(summary = "字典类型分页列表", description = "字典类型分页列表")
    @GetMapping("/types/page")
    public R<IPage<DictTypePageResult>> getDictTypePage(
            @ParameterObject DictTypePageQuery queryParams
    ) {
        Page<DictTypePageResult> result = dictTypeService.getDictTypePage(queryParams);
        return R.data(result);
    }

    @Operation(summary = "新增字典类型", description = "新增字典类型")
    @PostMapping("/types")
    public R<Boolean> saveDictType(@RequestBody DictTypeForm dictTypeForm) {
        return R.data(dictTypeService.saveDictType(dictTypeForm));
    }

    @Operation(summary = "字典类型表单数据", description = "字典类型表单数据")
    @GetMapping("/types/{id}/form")
    public R<DictTypeForm> getDictTypeForm(@Parameter(description = "字典ID") @PathVariable Long id) {
        return R.data(dictTypeService.getDictTypeForm(id));
    }

    @Operation(summary = "修改字典类型", description = "修改字典类型")
    @PutMapping("/types/{id}")
    public R<Boolean> updateDictType(@PathVariable Long id, @RequestBody DictTypeForm dictTypeForm) {
        return R.data(dictTypeService.updateDictType(id, dictTypeForm));
    }

    @Operation(summary = "删除字典类型", description = "删除字典类型")
    @DeleteMapping("/types/{ids}")
    public R<Boolean> deleteDictTypes(@Parameter(description = "字典类型ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        return R.data(dictTypeService.deleteDictTypes(ids));
    }

    @Operation(summary = "字典分页列表", description = "字典分页列表")
    @GetMapping("/page")
    public R<IPage<DictPageResult>> getDictPage(@ParameterObject DictPageQuery queryParams) {
        return R.data(dictService.getDictPage(queryParams));
    }

    @Operation(summary = "新增字典", description = "新增字典")
    @PostMapping
    public R<Boolean> saveDict(@RequestBody DictForm DictForm) {
        return R.data(dictService.saveDict(DictForm));
    }

    @Operation(summary = "字典数据表单数据", description = "字典数据表单数据")
    @GetMapping("/{id}/form")
    public R<DictForm> getDictForm(@Parameter(description = "字典ID") @PathVariable Long id) {
        DictForm formData = dictService.getDictForm(id);
        return R.data(formData);
    }

    @Operation(summary = "修改字典", description = "修改字典")
    @PutMapping("/{id}")
    public R<Boolean> updateDict(@PathVariable Long id, @RequestBody DictForm DictForm) {
        return R.data(dictService.updateDict(id, DictForm));
    }

    @Operation(summary = "删除字典", description = "删除字典")
    @DeleteMapping("/{ids}")
    public R<Boolean> deleteDict(@Parameter(description = "字典ID，多个以英文逗号(,)拼接") @PathVariable String ids) {
        return R.data(dictService.deleteDict(ids));
    }
}
