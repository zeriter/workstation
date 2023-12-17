package com.workstation.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.modules.system.domain.entity.Role;

import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色接口处理类
 * @date 2023/12/2 19:35 周六
 */
public interface IRoleService extends IService<Role> {
    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);


}
