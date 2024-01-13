package com.workstation.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workstation.modules.system.domain.bo.UserBO;
import com.workstation.modules.system.domain.bo.UserFormBO;
import com.workstation.modules.system.domain.entity.User;
import com.workstation.modules.system.domain.form.UserForm;
import com.workstation.modules.system.domain.result.UserImportResult;
import com.workstation.modules.system.domain.result.UserPageResult;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户对象转换器
 * @date 2024/1/4 23:24 星期四
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    @Mappings({@Mapping(target = "genderLabel", expression = "java(com.workstation.common.enums.IBaseEnum.getLabelByValue(bo.getGender(), com.workstation.modules.system.enums.GenderEnum.class))")})
    UserPageResult toPageVo(UserBO bo);

    Page<UserPageResult> toPageVo(Page<UserBO> bo);

    @InheritInverseConfiguration(name = "entity2Form")
    User form2Entity(UserForm entity);

    UserForm bo2Form(UserFormBO userFormBO);

    UserForm entity2Form(User entity);

    User importVo2Entity(UserImportResult vo);
}
