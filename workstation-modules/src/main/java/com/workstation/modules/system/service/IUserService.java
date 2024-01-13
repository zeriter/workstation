package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.form.UserForm;
import com.workstation.modules.system.domain.query.UserPageQuery;
import com.workstation.modules.system.domain.result.UserExportResult;
import com.workstation.modules.system.domain.result.UserInfoResult;
import com.workstation.modules.system.domain.result.UserPageResult;
import com.workstation.modules.system.domain.security.AuthInfo;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户业务处理类
 * @date 2023/12/2 19:35 周六
 */
public interface IUserService extends IService<User> {
    AuthInfo getUserAuthInfo(String username);

    UserInfoResult details();

    IPage<UserPageResult> getUserPage(UserPageQuery queryParams);

    Boolean saveUser(UserForm userForm);

    UserForm getUserFormData(Long userId);

    Boolean updateUser(Long userId, UserForm userForm);

    Boolean deleteUsers(String ids);

    Boolean updatePassword(Long userId, String password);

    List<UserExportResult> listExportUsers(UserPageQuery queryParams);
}
