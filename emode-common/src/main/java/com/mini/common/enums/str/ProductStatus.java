package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/11/26 17:42
 */
public enum ProductStatus implements StringEnum {

    // 暫未想起來
    TEST("test");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ProductStatus get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }
}
