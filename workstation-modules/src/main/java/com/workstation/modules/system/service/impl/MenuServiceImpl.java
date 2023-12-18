package com.workstation.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.common.constant.Constants;
import com.workstation.common.enums.StatusEnum;
import com.workstation.modules.system.domain.bo.RouteBO;
import com.workstation.modules.system.domain.entity.Menu;
import com.workstation.modules.system.domain.result.RouteResult;
import com.workstation.modules.system.enums.MenuTypeEnum;
import com.workstation.modules.system.mapper.MenuMapper;
import com.workstation.modules.system.service.IMenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单接口处理类
 * @date 2023/12/2 19:36 周六
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    /**
     * 根据角色查询菜单
     *
     * @param roles
     * @return
     */
    @Override
    public Set<String> listRolePerms(Set<String> roles) {
        return this.baseMapper.listRolePerms(roles);
    }

    /**
     * 查询路由
     *
     * @return
     */
    @Override
    @Cacheable(cacheNames = "menu", key = "'routes'")
    public List<RouteResult> listRoutes() {
        List<RouteBO> menuList = this.baseMapper.listRoutes();
        return buildRoutes(Constants.ROOT_NODE_ID, menuList);
    }

    /**
     * 递归生成菜单路由层级列表
     *
     * @param parentId 父级ID
     * @param menuList 菜单列表
     * @return 路由层级列表
     */
    private List<RouteResult> buildRoutes(Long parentId, List<RouteBO> menuList) {
        List<RouteResult> routeList = new ArrayList<>();
        for (RouteBO menu : menuList) {
            if (menu.getParentId().equals(parentId)) {
                RouteResult routeVO = toRouteVo(menu);
                List<RouteResult> children = buildRoutes(menu.getId(), menuList);
                if (!children.isEmpty()) {
                    routeVO.setChildren(children);
                }
                routeList.add(routeVO);
            }
        }

        return routeList;
    }

    /**
     * 根据RouteBO创建RouteVO
     */
    private RouteResult toRouteVo(RouteBO routeBO) {
        RouteResult routeVO = new RouteResult();
        String routeName = StringUtils.capitalize(StrUtil.toCamelCase(routeBO.getPath(), '-'));  // 路由 name 需要驼峰，首字母大写
        routeVO.setName(routeName); // 根据name路由跳转 this.$router.push({name:xxx})
        routeVO.setPath(routeBO.getPath()); // 根据path路由跳转 this.$router.push({path:xxx})
        routeVO.setRedirect(routeBO.getRedirect());
        routeVO.setComponent(routeBO.getComponent());

        RouteResult.Meta meta = new RouteResult.Meta();
        meta.setTitle(routeBO.getName());
        meta.setIcon(routeBO.getIcon());
        meta.setRoles(routeBO.getRoles());
        meta.setHidden(StatusEnum.DISABLE.getValue().equals(routeBO.getVisible()));
        // 【菜单】是否开启页面缓存
        if (MenuTypeEnum.MENU.equals(routeBO.getType())
                && ObjectUtil.equals(routeBO.getKeepAlive(), 1)) {
            meta.setKeepAlive(true);
        }
        // 【目录】只有一个子路由是否始终显示
        if (MenuTypeEnum.CATALOG.equals(routeBO.getType())
                && ObjectUtil.equals(routeBO.getAlwaysShow(), 1)) {
            meta.setAlwaysShow(true);
        }

        routeVO.setMeta(meta);
        return routeVO;
    }
}
