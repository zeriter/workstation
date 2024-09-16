package com.workstation.modules.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.workstation.common.param.R;
import com.workstation.modules.quartz.domain.form.QuartzForm;
import com.workstation.modules.quartz.domain.query.QuartzPageQuery;
import com.workstation.modules.quartz.domain.result.QuartzPageResult;
import com.workstation.modules.quartz.service.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 任务管理
 * @date 2023/12/2 19:35 周六
 */
@Tag(name = "任务-任务管理")
@RestController
@RequestMapping("/system/quartz")
public class TaskController {
    @Resource
    private ITaskService taskService;

    @Operation(summary = "保存任务", description = "保存任务")
    @PostMapping
    public R<Boolean> save(@Valid @RequestBody QuartzForm quartzForm) {
        return R.data(Boolean.TRUE);
    }

    @Operation(summary = "删除任务", description = "删除任务")
    @DeleteMapping("/{ids}")
    public R<Boolean> delete(@Parameter(description = "删除任务，多个以英文逗号(,)分割") @PathVariable String ids) {
        return R.data(Boolean.TRUE);
    }

    @Operation(summary = "修改任务", description = "修改任务")
    @PutMapping("/{id}")
    public R<Boolean> edit(@Valid @RequestBody QuartzForm quartzForm) {
        return R.data(Boolean.TRUE);
    }

    @Operation(summary = "任务列表", description = "任务列表")
    @GetMapping("/page")
    public R<IPage<QuartzPageResult>> getQuartzPage(@ParameterObject QuartzPageQuery queryParams) {
        return R.success();
    }

    @Operation(summary = "任务详情", description = "任务详情")
    @GetMapping("/{quartzId}/form")
    public R<QuartzForm> detail(@Parameter(description = "任务ID") @PathVariable Long quartzId) {
        return R.success();
    }

}
