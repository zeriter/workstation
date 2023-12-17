package com.workstation.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.modules.system.domain.entity.Menu;
import com.workstation.modules.system.mapper.MenuMapper;
import com.workstation.modules.system.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单接口处理类
 * @date 2023/12/2 19:36 周六
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public Set<String> listRolePerms(Set<String> roles) {
        return this.baseMapper.listRolePerms(roles);
    }
}
