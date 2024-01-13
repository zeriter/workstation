package com.workstation.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.modules.system.converter.DictTypeConverter;
import com.workstation.modules.system.domain.entity.Dict;
import com.workstation.modules.system.domain.entity.DictType;
import com.workstation.modules.system.domain.form.DictTypeForm;
import com.workstation.modules.system.domain.query.DictTypePageQuery;
import com.workstation.modules.system.domain.result.DictTypePageResult;
import com.workstation.modules.system.mapper.DictTypeMapper;
import com.workstation.modules.system.service.IDictService;
import com.workstation.modules.system.service.IDictTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典详情业务处理类
 * @date 2024/1/11 23:11 星期四
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {
    @Resource
    private DictTypeConverter dictTypeConverter;
    @Resource
    private IDictService dictItemService;

    @Override
    public Page<DictTypePageResult> getDictTypePage(DictTypePageQuery queryParams) {
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        // 查询数据
        Page<DictType> dictTypePage = this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<DictType>()
                        .like(StrUtil.isNotBlank(keywords), DictType::getName, keywords)
                        .or()
                        .like(StrUtil.isNotBlank(keywords), DictType::getCode, keywords)
                        .select(
                                DictType::getId,
                                DictType::getName,
                                DictType::getCode,
                                DictType::getStatus,
                                DictType::getRemark
                        )
        );

        // 实体转换
        Page<DictTypePageResult> pageResult = dictTypeConverter.entity2Page(dictTypePage);
        return pageResult;
    }

    @Override
    public Boolean saveDictType(DictTypeForm dictTypeForm) {
        DictType entity = dictTypeConverter.form2Entity(dictTypeForm);
        return this.save(entity);
    }

    @Override
    public DictTypeForm getDictTypeForm(Long id) {
        DictType entity = this.getOne(new LambdaQueryWrapper<DictType>()
                .eq(DictType::getId, id)
                .select(
                        DictType::getId,
                        DictType::getName,
                        DictType::getCode,
                        DictType::getStatus,
                        DictType::getRemark
                ));
        Assert.isTrue(entity != null, "字典类型不存在");

        // 实体转换
        DictTypeForm dictTypeForm = dictTypeConverter.entity2Form(entity);
        return dictTypeForm;
    }

    @Override
    public Boolean updateDictType(Long id, DictTypeForm dictTypeForm) {
        DictType sysDictType = this.getById(id);
        Assert.isTrue(sysDictType != null, "字典类型不存在");

        DictType entity = dictTypeConverter.form2Entity(dictTypeForm);
        boolean result = this.updateById(entity);
        if (result) {
            // 字典类型code变化，同步修改字典项的类型code
            String oldCode = sysDictType.getCode();
            String newCode = dictTypeForm.getCode();
            if (!StrUtil.equals(oldCode, newCode)) {
                dictItemService.update(new LambdaUpdateWrapper<Dict>()
                        .eq(Dict::getTypeCode, oldCode)
                        .set(Dict::getTypeCode, newCode)
                );
            }
        }
        return result;
    }

    @Override
    @Transactional
    public Boolean deleteDictTypes(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");

        List ids = Arrays.asList(idsStr.split(","))
                .stream()
                .collect(Collectors.toList());

        // 删除字典数据项
        List<String> dictTypeCodes = this.list(new LambdaQueryWrapper<DictType>()
                        .in(DictType::getId, ids)
                        .select(DictType::getCode))
                .stream()
                .map(dictType -> dictType.getCode())
                .collect(Collectors.toList()
                );
        if (CollectionUtil.isNotEmpty(dictTypeCodes)) {
            dictItemService.remove(new LambdaQueryWrapper<Dict>()
                    .in(Dict::getTypeCode, dictTypeCodes));
        }
        // 删除字典类型
        boolean result = this.removeByIds(ids);
        return result;
    }
}
