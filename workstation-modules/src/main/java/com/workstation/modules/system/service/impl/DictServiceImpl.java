package com.workstation.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.common.param.Option;
import com.workstation.modules.system.domain.entity.Dict;
import com.workstation.modules.system.mapper.DictMapper;
import com.workstation.modules.system.service.IDictService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典业务处理类
 * @date 2024/1/11 23:11 星期四
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {
    @Override
    public List<Option> listDictOptions(String typeCode) {
        List<Dict> dictList = this.list(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getTypeCode, typeCode)
                .select(Dict::getValue, Dict::getName)
        );
        // 转换下拉数据
        List<Option> options = CollectionUtil.emptyIfNull(dictList)
                .stream()
                .map(dictItem -> new Option(dictItem.getValue(), dictItem.getName()))
                .collect(Collectors.toList());
        return options;
    }
}
