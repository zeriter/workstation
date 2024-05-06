package com.workstation.generator.controller;

import com.workstation.common.param.R;
import com.workstation.generator.domain.query.DbQuery;
import com.workstation.generator.service.IGenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description @TODO
 * @date 2024/5/6 下午4:57 星期一
 **/
@Tag(name = "系统-代码生成")
@RestController
@RequestMapping("/tool/gen")
public class GenController {

    @Resource
    private IGenService genService;

    @Operation(summary = "部门列表", description = "部门列表")
    @GetMapping("/db/list")
    public R<Map<String, String>> dataList(DbQuery query) {
        return R.success();
    }
}
