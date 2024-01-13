package com.workstation.modules.system.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.workstation.common.param.R;
import com.workstation.modules.system.domain.form.UserForm;
import com.workstation.modules.system.domain.query.UserPageQuery;
import com.workstation.modules.system.domain.result.UserExportResult;
import com.workstation.modules.system.domain.result.UserImportResult;
import com.workstation.modules.system.domain.result.UserInfoResult;
import com.workstation.modules.system.domain.result.UserPageResult;
import com.workstation.modules.system.plugin.easyexcel.UserImportListener;
import com.workstation.modules.system.service.IUserService;
import com.workstation.modules.system.utils.ExcelUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户管理
 * @date 2023/12/2 19:35 周六
 */
@Tag(name = "系统-用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Resource
    private IUserService userService;

    @Operation(summary = "保存用户", description = "保存用户")
    @PostMapping
    public R<Boolean> save(@RequestBody @Valid UserForm userForm) {
        return R.data(userService.saveUser(userForm));
    }

    @Operation(summary = "删除用户", description = "删除用户")
    @DeleteMapping("/{ids}")
    public R<Boolean> delete(@Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        return R.data(userService.deleteUsers(ids));
    }

    @Operation(summary = "修改用户", description = "修改用户")
    @PutMapping("/{userId}")
    public R<Boolean> edit(@Parameter(description = "用户ID") @PathVariable Long userId, @RequestBody @Validated UserForm userForm) {
        return R.data(userService.updateUser(userId, userForm));
    }

    @Operation(summary = "用户列表", description = "用户列表")
    @GetMapping("/page")
    public R<IPage<UserPageResult>> users(@ParameterObject UserPageQuery queryParams) {
        return R.data(userService.getUserPage(queryParams));
    }

    @Operation(summary = "用户详情", description = "用户详情")
    @GetMapping("/info")
    public R<UserInfoResult> details() {
        return R.data(userService.details());
    }

    @Operation(summary = "导入用户", description = "导入用户")
    @PostMapping("/_import")
    public R<String> importUsers(@Parameter(description = "部门ID") Long deptId, MultipartFile file) throws IOException {
        UserImportListener listener = new UserImportListener(deptId);
        return R.data(ExcelUtils.importExcel(file.getInputStream(), UserImportResult.class, listener));
    }

    @Operation(summary = "导出用户", description = "导出用户")
    @GetMapping("/_export")
    public void exportUsers(UserPageQuery queryParams, HttpServletResponse response) throws IOException {
        String fileName = "用户列表.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        List<UserExportResult> exportUserList = userService.listExportUsers(queryParams);
        EasyExcel.write(response.getOutputStream(), UserExportResult.class).sheet("用户列表")
                .doWrite(exportUserList);
    }

    @Operation(summary = "用户导入模板下载", description = "用户导入模板下载")
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String fileName = "用户导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        String fileClassPath = "excel-templates" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();

        excelWriter.finish();
    }

    @Operation(summary = "用户表单数据", description = "用户表单数据")
    @GetMapping("/{userId}/form")
    public R<UserForm> getUserForm(@Parameter(description = "用户ID") @PathVariable Long userId) {
        UserForm formData = userService.getUserFormData(userId);
        return R.data(formData);
    }

    @Operation(summary = "修改用户密码", description = "修改用户密码")
    @PatchMapping(value = "/{userId}/password")
    public R<Boolean> updatePassword(@Parameter(description = "用户ID") @PathVariable Long userId, @RequestParam String password) {
        return R.data(userService.updatePassword(userId, password));
    }
}
