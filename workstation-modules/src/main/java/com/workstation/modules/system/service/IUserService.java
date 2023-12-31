package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.result.UserInfoResult;
import com.workstation.modules.system.domain.security.AuthInfo;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户接口处理类
 * @date 2023/12/2 19:35 周六
 */
public interface IUserService extends IService<User> {
    AuthInfo getUserAuthInfo(String username);

    UserInfoResult details();
}
