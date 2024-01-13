package com.workstation.modules.system.plugin.easyexcel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.workstation.common.constant.Constants;
import com.workstation.common.enums.IBaseEnum;
import com.workstation.common.enums.StatusEnum;
import com.workstation.modules.system.converter.UserConverter;
import com.workstation.modules.system.domain.entity.Role;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.entity.UserRole;
import com.workstation.modules.system.domain.result.UserImportResult;
import com.workstation.modules.system.enums.GenderEnum;
import com.workstation.modules.system.service.IRoleService;
import com.workstation.modules.system.service.IUserRoleService;
import com.workstation.modules.system.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户导入监听器
 * @date 2024/1/13 14:53 星期六
 */
public class UserImportListener extends MyAnalysisEventListener<UserImportResult> {
    private final Long deptId;
    StringBuilder msg = new StringBuilder();
    private int validCount;
    private int invalidCount;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Resource
    private IUserService userService;
    @Resource
    private UserConverter userConverter;
    @Resource
    private IRoleService roleService;
    @Resource
    private IUserRoleService userRoleService;

    public UserImportListener(Long deptId) {
        this.deptId = deptId;
        this.userService = SpringUtil.getBean(IUserService.class);
        this.passwordEncoder = SpringUtil.getBean(PasswordEncoder.class);
        this.roleService = SpringUtil.getBean(IRoleService.class);
        this.userRoleService = SpringUtil.getBean(IUserRoleService.class);
        this.userConverter = SpringUtil.getBean(UserConverter.class);
    }

    @Override
    public void invoke(UserImportResult userImportResult, AnalysisContext analysisContext) {
        // 校验数据
        StringBuilder validationMsg = new StringBuilder();

        String username = userImportResult.getUsername();
        if (StrUtil.isBlank(username)) {
            validationMsg.append("用户名为空；");
        } else {
            long count = userService.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
            if (count > 0) {
                validationMsg.append("用户名已存在；");
            }
        }

        String nickname = userImportResult.getNickname();
        if (StrUtil.isBlank(nickname)) {
            validationMsg.append("用户昵称为空；");
        }

        String mobile = userImportResult.getMobile();
        if (StrUtil.isBlank(mobile)) {
            validationMsg.append("手机号码为空；");
        } else {
            if (!Validator.isMobile(mobile)) {
                validationMsg.append("手机号码不正确；");
            }
        }

        if (validationMsg.length() == 0) {
            // 校验通过，持久化至数据库
            User entity = userConverter.importVo2Entity(userImportResult);
            entity.setDeptId(deptId);   // 部门
            entity.setPassword(passwordEncoder.encode(Constants.DEFAULT_PASSWORD));   // 默认密码
            // 性别翻译
            String genderLabel = userImportResult.getGender();
            if (StrUtil.isNotBlank(genderLabel)) {
                Integer genderValue = (Integer) IBaseEnum.getValueByLabel(genderLabel, GenderEnum.class);
                entity.setGender(genderValue);
            }

            // 角色解析
            String roleCodes = userImportResult.getRoleCodes();
            List<Long> roleIds = null;
            if (StrUtil.isNotBlank(roleCodes)) {
                roleIds = roleService.list(
                                new LambdaQueryWrapper<Role>()
                                        .in(Role::getCode, roleCodes.split(","))
                                        .eq(Role::getStatus, StatusEnum.ENABLE.getValue())
                                        .select(Role::getId)
                        ).stream()
                        .map(role -> role.getId())
                        .collect(Collectors.toList());
            }


            boolean saveResult = userService.save(entity);
            if (saveResult) {
                validCount++;
                // 保存用户角色关联
                if (CollectionUtil.isNotEmpty(roleIds)) {
                    List<UserRole> userRoles = roleIds.stream()
                            .map(roleId -> new UserRole(entity.getId(), roleId))
                            .collect(Collectors.toList());
                    userRoleService.saveBatch(userRoles);
                }
            } else {
                invalidCount++;
                msg.append("第" + (validCount + invalidCount) + "行数据保存失败；<br/>");
            }
        } else {
            invalidCount++;
            msg.append("第" + (validCount + invalidCount) + "行数据校验失败：").append(validationMsg + "<br/>");
        }
    }


    /**
     * 所有数据解析完成会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    @Override
    public String getMsg() {
        // 总结信息
        String summaryMsg = StrUtil.format("导入用户结束：成功{}条，失败{}条；<br/>{}", validCount, invalidCount, msg);
        return summaryMsg;
    }
}
