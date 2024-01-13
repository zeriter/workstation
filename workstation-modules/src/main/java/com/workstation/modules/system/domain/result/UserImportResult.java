package com.workstation.modules.system.domain.result;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description
 * @date 2024/1/13 14:55 星期六
 */
public class UserImportResult {
    @ExcelProperty(value = "用户名")
    private String username;

    @ExcelProperty(value = "昵称")
    private String nickname;

    @ExcelProperty(value = "性别")
    private String gender;

    @ExcelProperty(value = "手机号码")
    private String mobile;

    @ExcelProperty(value = "邮箱")
    private String email;

    @ExcelProperty("角色")
    private String roleCodes;

    public UserImportResult() {
    }

    public UserImportResult(String username, String nickname, String gender, String mobile, String email, String roleCodes) {
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.roleCodes = roleCodes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }
}
