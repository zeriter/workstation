package com.workstation.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workstation.common.annotation.DataPermission;
import com.workstation.modules.system.domain.bo.UserBO;
import com.workstation.modules.system.domain.bo.UserFormBO;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.query.UserPageQuery;
import com.workstation.modules.system.domain.result.UserExportResult;
import com.workstation.modules.system.domain.security.AuthInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户持久层
 * @date 2023/12/9 18:10 周六
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    AuthInfo getUserAuthInfo(@Param("username") String username);

    @DataPermission(deptAlias = "u")
    Page<UserBO> getUserPage(@Param("page") Page<UserBO> page, @Param("queryParams") UserPageQuery queryParams);

    UserFormBO getUserDetail(Long userId);

    @DataPermission(deptAlias = "u")
    List<UserExportResult> listExportUsers(UserPageQuery queryParams);
}
