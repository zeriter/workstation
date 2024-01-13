package com.workstation.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workstation.common.param.Option;
import com.workstation.modules.system.domain.entity.Role;
import com.workstation.modules.system.domain.form.RoleForm;
import com.workstation.modules.system.domain.result.RolePageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色对象转换器
 * @date 2024/1/11 23:33 星期四
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {
    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(Role role);

    List<Option> entities2Options(List<Role> roles);

    Page<RolePageResult> entity2Page(Page<Role> rolePage);

    Role form2Entity(RoleForm roleForm);

}
