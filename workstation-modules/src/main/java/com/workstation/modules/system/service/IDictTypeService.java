package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.modules.system.domain.entity.DictType;
import com.workstation.modules.system.domain.form.DictTypeForm;
import com.workstation.modules.system.domain.query.DictTypePageQuery;
import com.workstation.modules.system.domain.result.DictTypePageResult;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典详情业务处理类
 * @date 2024/1/11 23:08 星期四
 */
public interface IDictTypeService extends IService<DictType> {
    Page<DictTypePageResult> getDictTypePage(DictTypePageQuery queryParams);

    Boolean saveDictType(DictTypeForm dictTypeForm);

    DictTypeForm getDictTypeForm(Long id);

    Boolean updateDictType(Long id, DictTypeForm dictTypeForm);

    Boolean deleteDictTypes(String ids);
}
