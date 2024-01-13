package com.workstation.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.workstation.common.param.Option;
import com.workstation.modules.system.domain.entity.Dict;
import com.workstation.modules.system.domain.form.DictForm;
import com.workstation.modules.system.domain.query.DictPageQuery;
import com.workstation.modules.system.domain.result.DictPageResult;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典业务处理类
 * @date 2024/1/11 23:08 星期四
 */
public interface IDictService extends IService<Dict> {
    List<Option> listDictOptions(String typeCode);

    IPage<DictPageResult> getDictPage(DictPageQuery queryParams);

    Boolean saveDict(DictForm dictForm);

    DictForm getDictForm(Long id);

    Boolean updateDict(Long id, DictForm dictForm);

    Boolean deleteDict(String ids);
}
