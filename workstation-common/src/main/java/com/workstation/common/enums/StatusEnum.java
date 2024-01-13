package com.workstation.common.enums;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 状态枚举
 * @date 2023/12/18 23:38 星期一
 */
public enum StatusEnum implements IBaseEnum<Integer> {

    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final Integer value;

    private final String label;

    StatusEnum(Integer value, String label) {
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