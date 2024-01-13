package com.workstation.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workstation.modules.system.domain.entity.Dict;
import com.workstation.modules.system.domain.form.DictForm;
import com.workstation.modules.system.domain.result.DictPageResult;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典数据项转化器
 * @date 2024/1/13 0:17 星期六
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    Page<DictPageResult> entity2Page(Page<Dict> page);

    DictForm entity2Form(Dict entity);

    @InheritInverseConfiguration(name = "entity2Form")
    Dict form2Entity(DictForm entity);
}
