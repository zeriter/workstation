package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.common.param.Option;
import com.workstation.modules.system.domain.entity.Dept;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 部门业务处理类
 * @date 2024/1/4 22:41 星期四
 */
public interface IDeptService extends IService<Dept> {
    List<Option> listDeptOptions();
}
