package com.workstation.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.common.constant.Constants;
import com.workstation.common.enums.StatusEnum;
import com.workstation.common.param.Option;
import com.workstation.modules.system.converter.DeptConverter;
import com.workstation.modules.system.domain.entity.Dept;
import com.workstation.modules.system.domain.form.DeptForm;
import com.workstation.modules.system.domain.query.DeptQuery;
import com.workstation.modules.system.domain.result.DeptResult;
import com.workstation.modules.system.mapper.DeptMapper;
import com.workstation.modules.system.service.IDeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 部门业务实现类
 * @date 2024/1/4 22:44 星期四
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Resource
    private DeptConverter deptConverter;


    @Override
    public List<Option> listDeptOptions() {
        List<Dept> deptList = this.list(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getStatus, StatusEnum.ENABLE.getValue())
                .select(Dept::getId, Dept::getParentId, Dept::getName)
                .orderByAsc(Dept::getSort)
        );
        if (CollectionUtil.isEmpty(deptList)) {
            return Collections.EMPTY_LIST;
        }

        Set<Long> deptIds = deptList.stream()
                .map(Dept::getId)
                .collect(Collectors.toSet());

        Set<Long> parentIds = deptList.stream()
                .map(Dept::getParentId)
                .collect(Collectors.toSet());

        List<Long> rootIds = CollectionUtil.subtractToList(parentIds, deptIds);

        // 递归生成部门树形列表
        return rootIds.stream()
                .flatMap(rootId -> recurDeptTreeOptions(rootId, deptList).stream())
                .toList();
    }

    @Override
    public List<DeptResult> listDepartments(DeptQuery queryParams) {
        // 查询参数
        String keywords = queryParams.getKeywords();
        Integer status = queryParams.getStatus();

        // 查询数据
        List<Dept> deptList = this.list(
                new LambdaQueryWrapper<Dept>()
                        .like(StrUtil.isNotBlank(keywords), Dept::getName, keywords)
                        .eq(status != null, Dept::getStatus, status)
                        .orderByAsc(Dept::getSort)
        );

        if (CollectionUtil.isEmpty(deptList)) {
            return Collections.EMPTY_LIST;
        }

        // 获取所有部门ID
        Set<Long> deptIds = deptList.stream()
                .map(Dept::getId)
                .collect(Collectors.toSet());
        // 获取父节点ID
        Set<Long> parentIds = deptList.stream()
                .map(Dept::getParentId)
                .collect(Collectors.toSet());
        // 获取根节点ID（递归的起点），即父节点ID中不包含在部门ID中的节点，注意这里不能拿顶级部门 O 作为根节点，因为部门筛选的时候 O 会被过滤掉
        List<Long> rootIds = CollectionUtil.subtractToList(parentIds, deptIds);

        // 递归生成部门树形列表
        return rootIds.stream()
                .flatMap(rootId -> recurDeptList(rootId, deptList).stream())
                .toList();
    }

    @Override
    public Long saveDept(DeptForm formData) {
        // 校验部门名称是否存在
        String name = formData.getName();
        long count = this.count(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getName, name)
        );
        Assert.isTrue(count == 0, "部门名称已存在");

        // form->entity
        Dept entity = deptConverter.form2Entity(formData);

        // 生成部门路径(tree_path)，格式：父节点tree_path + , + 父节点ID，用于删除部门时级联删除子部门
        String treePath = generateDeptTreePath(formData.getParentId());
        entity.setTreePath(treePath);

        // 保存部门并返回部门ID
        boolean result = this.save(entity);
        Assert.isTrue(result, "部门保存失败");

        return entity.getId();
    }

    @Override
    public DeptForm getDeptForm(Long deptId) {
        Dept entity = this.getOne(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getId, deptId)
                .select(
                        Dept::getId,
                        Dept::getName,
                        Dept::getParentId,
                        Dept::getStatus,
                        Dept::getSort
                ));

        return deptConverter.entity2Form(entity);
    }

    @Override
    public Long updateDept(Long deptId, DeptForm formData) {
        // 校验部门名称是否存在
        String name = formData.getName();
        long count = this.count(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getName, name)
                .ne(Dept::getId, deptId)
        );
        Assert.isTrue(count == 0, "部门名称已存在");

        // form->entity
        Dept entity = deptConverter.form2Entity(formData);
        entity.setId(deptId);

        // 生成部门路径(tree_path)，格式：父节点tree_path + , + 父节点ID，用于删除部门时级联删除子部门
        String treePath = generateDeptTreePath(formData.getParentId());
        entity.setTreePath(treePath);

        // 保存部门并返回部门ID
        boolean result = this.updateById(entity);
        Assert.isTrue(result, "部门更新失败");

        return entity.getId();
    }

    @Override
    public Boolean deleteByIds(String ids) {
        // 删除部门及子部门
        if (StrUtil.isNotBlank(ids)) {
            String[] menuIds = ids.split(",");
            for (String deptId : menuIds) {
                this.remove(new LambdaQueryWrapper<Dept>()
                        .eq(Dept::getId, deptId)
                        .or()
                        .apply("CONCAT (',',tree_path,',') LIKE CONCAT('%,',{0},',%')", deptId));
            }
        }
        return true;
    }

    private String generateDeptTreePath(Long parentId) {
        String treePath = null;
        if (Constants.ROOT_NODE_ID.equals(parentId)) {
            treePath = String.valueOf(parentId);
        } else {
            Dept parent = this.getById(parentId);
            if (parent != null) {
                treePath = parent.getTreePath() + "," + parent.getId();
            }
        }
        return treePath;
    }

    public List<DeptResult> recurDeptList(Long parentId, List<Dept> deptList) {
        return deptList.stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .map(dept -> {
                    DeptResult deptVO = deptConverter.entity2Vo(dept);
                    List<DeptResult> children = recurDeptList(dept.getId(), deptList);
                    deptVO.setChildren(children);
                    return deptVO;
                }).collect(Collectors.toList());
    }

    /**
     * 递归生成部门表格层级列表
     *
     * @param parentId 父ID
     * @param deptList 部门列表
     * @return 部门表格层级列表
     */
    public List<Option> recurDeptTreeOptions(Long parentId, List<Dept> deptList) {
        List<Option> list = CollectionUtil.emptyIfNull(deptList).stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .map(dept -> {
                    Option option = new Option(dept.getId(), dept.getName());
                    List<Option> children = recurDeptTreeOptions(dept.getId(), deptList);
                    if (CollectionUtil.isNotEmpty(children)) {
                        option.setChildren(children);
                    }
                    return option;
                })
                .collect(Collectors.toList());
        return list;
    }
}
