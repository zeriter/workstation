package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.common.param.Option;
import com.workstation.modules.system.domain.entity.Dept;
import com.workstation.modules.system.domain.form.DeptForm;
import com.workstation.modules.system.domain.query.DeptQuery;
import com.workstation.modules.system.domain.result.DeptResult;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 部门业务处理类
 * @date 2024/1/4 22:41 星期四
 */
public interface IDeptService extends IService<Dept> {
    List<Option> listDeptOptions();

    List<DeptResult> listDepartments(DeptQuery queryParams);

    Long saveDept(DeptForm formData);

    DeptForm getDeptForm(Long deptId);

    Long updateDept(Long deptId, DeptForm formData);

    Boolean deleteByIds(String ids);
}
