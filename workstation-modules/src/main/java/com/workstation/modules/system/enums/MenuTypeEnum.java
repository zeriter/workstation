package com.workstation.modules.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.workstation.common.enums.IBaseEnum;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 菜单类型枚举
 * @date 2023/12/10 17:02 周日
 */
public enum MenuTypeEnum implements IBaseEnum<Integer> {

    NULL(0, null),
    MENU(1, "菜单"),
    CATALOG(2, "目录"),
    EXTLINK(3, "外链"),
    BUTTON(4, "按钮");

    @EnumValue //  Mybatis-Plus 提供注解表示插入数据库时插入该值
    private final Integer value;

    // @JsonValue //  表示对枚举序列化时返回此字段
    private final String label;

    MenuTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
