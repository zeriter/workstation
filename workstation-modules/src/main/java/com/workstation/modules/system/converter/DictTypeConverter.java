package com.workstation.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workstation.modules.system.domain.entity.DictType;
import com.workstation.modules.system.domain.form.DictTypeForm;
import com.workstation.modules.system.domain.result.DictTypePageResult;
import org.mapstruct.Mapper;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典类型对象转化器
 * @date 2024/1/13 0:18 星期六
 */
@Mapper(componentModel = "spring")
public interface DictTypeConverter {

    Page<DictTypePageResult> entity2Page(Page<DictType> page);

    DictTypeForm entity2Form(DictType entity);

    DictType form2Entity(DictTypeForm entity);
}

