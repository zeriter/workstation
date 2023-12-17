package com.workstation.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.modules.system.domain.entity.Role;
import com.workstation.modules.system.mapper.RoleMapper;
import com.workstation.modules.system.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 角色业务实现类
 *
 * @author haoxr
 * @since 2022/6/3
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 获取最大范围的数据权限
     *
     * @param roles 角色编码集合
     * @return {@link Integer} – 数据权限范围
     */
    @Override
    public Integer getMaximumDataScope(Set<String> roles) {
        Integer dataScope = this.baseMapper.getMaximumDataScope(roles);
        return dataScope;
    }

}
