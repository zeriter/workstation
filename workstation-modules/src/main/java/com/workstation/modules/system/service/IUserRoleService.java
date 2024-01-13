package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.modules.system.domain.entity.UserRole;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户角色业务处理类
 * @date 2024/1/11 23:08 星期四
 */
public interface IUserRoleService extends IService<UserRole> {
    boolean saveUserRoles(Long id, List<Long> roleIds);

    boolean isRoleAssignedToUser(Long roleId);
}
