package com.workstation.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workstation.modules.system.domain.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典持久层
 * @date 2024/1/11 23:00 星期四
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 统计角色下绑定的用户数量
     *
     * @param roleId 角色ID
     */
    int countUsersForRole(Long roleId);
}
