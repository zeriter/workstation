package com.workstation.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workstation.modules.system.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色持久层
 * @date 2023/12/9 18:10 周六
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(@Param("roles") Set<String> roles);
}
