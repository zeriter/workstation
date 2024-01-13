package com.workstation.modules.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.common.constant.Constants;
import com.workstation.common.param.Option;
import com.workstation.modules.system.converter.RoleConverter;
import com.workstation.modules.system.domain.entity.Role;
import com.workstation.modules.system.domain.query.RolePageQuery;
import com.workstation.modules.system.domain.result.RolePageResult;
import com.workstation.modules.system.mapper.RoleMapper;
import com.workstation.modules.system.service.IRoleService;
import com.workstation.modules.system.utils.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色业务处理类
 * @date 2023/12/31 16:55 星期日
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleConverter roleConverter;

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

    @Override
    public List<Option> listRoleOptions() {
        List<Role> roleList = this.list(new LambdaQueryWrapper<Role>()
                .ne(!SecurityUtil.isRoot(), Role::getCode, Constants.ROOT_ROLE_CODE)
                .select(Role::getId, Role::getName)
                .orderByAsc(Role::getSort)
        );

        // 实体转换
        return roleConverter.entities2Options(roleList);
    }

    @Override
    public IPage<RolePageResult> getRolePage(RolePageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        // 查询数据
        Page<Role> rolePage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Role>()
                        .and(StrUtil.isNotBlank(keywords),
                                wrapper ->
                                        wrapper.like(StrUtil.isNotBlank(keywords), Role::getName, keywords)
                                                .or()
                                                .like(StrUtil.isNotBlank(keywords), Role::getCode, keywords)
                        )
                        .ne(!SecurityUtil.isRoot(), Role::getCode, Constants.ROOT_ROLE_CODE) // 非超级管理员不显示超级管理员角色
        );

        // 实体转换
        return roleConverter.entity2Page(rolePage);
    }

}
