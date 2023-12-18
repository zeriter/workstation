package com.workstation.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workstation.modules.system.domain.bo.RouteBO;
import com.workstation.modules.system.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单持久层
 * @date 2023/12/9 18:10 周六
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    Set<String> listRolePerms(@Param("roles") Set<String> roles);

    List<RouteBO> listRoutes();
}
