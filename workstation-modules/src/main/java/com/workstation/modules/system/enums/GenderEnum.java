package com.workstation.modules.system.enums;

import com.workstation.common.enums.IBaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 性别枚举
 * @date 2024/1/12 0:21 星期五
 */
@Schema(enumAsRef = true)
public enum GenderEnum implements IBaseEnum<Integer> {
    MALE(1, "男"),
    FEMALE(2, "女");

    private final Integer value;

    private final String label;

    GenderEnum(Integer value, String label) {
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
