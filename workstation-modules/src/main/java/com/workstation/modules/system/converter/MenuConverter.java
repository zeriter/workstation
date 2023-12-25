package com.workstation.modules.system.converter;

import com.workstation.modules.system.domain.entity.Menu;
import com.workstation.modules.system.domain.form.MenuForm;
import com.workstation.modules.system.domain.result.MenuResult;
import org.mapstruct.Mapper;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单对象转换器
 * @date 2023/12/26 0:25 星期二
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {
    MenuResult entity2Vo(Menu entity);

    MenuForm entity2Form(Menu entity);

    Menu form2Entity(MenuForm menuForm);
}
