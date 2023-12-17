package com.workstation.handler.mybatisplus;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.workstation.common.annotation.DataPermission;
import com.workstation.common.enums.IBaseEnum;
import com.workstation.enums.DataScopeEnum;
import com.workstation.modules.system.utils.SecurityUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;

import java.lang.reflect.Method;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description
 * @date 2023/12/9 1:09 周六
 */
public class MyDataPermissionHandler implements DataPermissionHandler {

    /**
     * 构建过滤条件
     *
     * @param where 当前查询条件
     * @return 构建后查询条件
     */
    public static Expression dataScopeFilter(String deptAlias, String deptIdColumnName, String userAlias, String userIdColumnName, Expression where) throws JSQLParserException {


        String deptColumnName = StrUtil.isNotBlank(deptAlias) ? (deptAlias + StringPool.DOT + deptIdColumnName) : deptIdColumnName;
        String userColumnName = StrUtil.isNotBlank(userAlias) ? (userAlias + StringPool.DOT + userIdColumnName) : userIdColumnName;

        // 获取当前用户的数据权限
        Integer dataScope = SecurityUtil.getDataScope();

        DataScopeEnum dataScopeEnum = IBaseEnum.getEnumByValue(dataScope, DataScopeEnum.class);

        Long deptId, userId;
        String appendSqlStr;
        switch (dataScopeEnum) {
            case ALL:
                return where;
            case DEPT:
                deptId = SecurityUtil.getDeptId();
                appendSqlStr = deptColumnName + StringPool.EQUALS + deptId;
                break;
            case SELF:
                userId = SecurityUtil.getUserId();
                appendSqlStr = userColumnName + StringPool.EQUALS + userId;
                break;
            // 默认部门及子部门数据权限
            default:
                deptId = SecurityUtil.getDeptId();
                appendSqlStr = deptColumnName + " IN ( SELECT id FROM sys_dept WHERE id = " + deptId + " OR FIND_IN_SET( " + deptId + " , tree_path ) )";
                break;
        }

        if (StrUtil.isBlank(appendSqlStr)) {
            return where;
        }

        Expression appendExpression = CCJSqlParserUtil.parseCondExpression(appendSqlStr);

        if (where == null) {
            return appendExpression;
        }

        return new AndExpression(where, appendExpression);
    }

    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        try {
            Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(StringPool.DOT)));
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(StringPool.DOT) + 1);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                DataPermission annotation = method.getAnnotation(DataPermission.class);
                // 如果没有注解或者是超级管理员，直接返回
                if (annotation == null || SecurityUtil.isRoot()) {
                    return where;
                }
                if (method.getName().equals(methodName) || (method.getName() + "_COUNT").equals(methodName)) {
                    return dataScopeFilter(annotation.deptAlias(), annotation.deptIdColumnName(), annotation.userAlias(), annotation.userIdColumnName(), where);
                }
            }
        } catch (ClassNotFoundException | JSQLParserException e) {
            throw new RuntimeException(e);
        }
        return where;
    }


}
