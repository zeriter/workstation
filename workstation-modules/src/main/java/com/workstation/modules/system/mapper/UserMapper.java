package com.workstation.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.security.AuthInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户持久层
 * @date 2023/12/9 18:10 周六
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    AuthInfo getUserAuthInfo(String username);
}
