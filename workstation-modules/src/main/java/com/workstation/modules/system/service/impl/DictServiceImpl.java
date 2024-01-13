package com.workstation.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.common.param.Option;
import com.workstation.modules.system.converter.DictConverter;
import com.workstation.modules.system.domain.entity.Dict;
import com.workstation.modules.system.domain.form.DictForm;
import com.workstation.modules.system.domain.query.DictPageQuery;
import com.workstation.modules.system.domain.result.DictPageResult;
import com.workstation.modules.system.mapper.DictMapper;
import com.workstation.modules.system.service.IDictService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    @Resource
    private DictConverter dictConverter;
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

    @Override
    public IPage<DictPageResult> getDictPage(DictPageQuery queryParams) {
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();
        String typeCode = queryParams.getTypeCode();

        // 查询数据
        Page<Dict> dictItemPage = this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Dict>()
                        .like(StrUtil.isNotBlank(keywords), Dict::getName, keywords)
                        .eq(StrUtil.isNotBlank(typeCode), Dict::getTypeCode, typeCode)
                        .select(Dict::getId, Dict::getName, Dict::getValue, Dict::getStatus)
        );
        return dictConverter.entity2Page(dictItemPage);
    }

    @Override
    public Boolean saveDict(DictForm dictForm) {
        Dict entity = dictConverter.form2Entity(dictForm);
        return this.save(entity);
    }

    @Override
    public DictForm getDictForm(Long id) {
        // 获取entity
        Dict entity = this.getOne(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getId, id)
                .select(
                        Dict::getId,
                        Dict::getTypeCode,
                        Dict::getName,
                        Dict::getValue,
                        Dict::getStatus,
                        Dict::getSort,
                        Dict::getRemark
                ));
        Assert.isTrue(entity != null, "字典数据项不存在");

        // 实体转换
        DictForm dictForm = dictConverter.entity2Form(entity);
        return dictForm;
    }

    @Override
    public Boolean updateDict(Long id, DictForm dictForm) {
        Dict entity = dictConverter.form2Entity(dictForm);
        return this.updateById(entity);
    }

    @Override
    public Boolean deleteDict(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        //
        List<Long> ids = Arrays.asList(idsStr.split(","))
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }
}
