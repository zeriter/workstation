package com.workstation.modules.system.converter;

import com.workstation.modules.system.domain.entity.Dept;
import com.workstation.modules.system.domain.form.DeptForm;
import com.workstation.modules.system.domain.result.DeptResult;
import org.mapstruct.Mapper;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 部门对象转化器
 * @date 2024/1/13 0:16 星期六
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm entity2Form(Dept entity);

    DeptResult entity2Vo(Dept entity);

    Dept form2Entity(DeptForm deptForm);

}
