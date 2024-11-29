package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/11/26 17:41
 */
public enum ProductShowStatus implements StringEnum {

    // 上架
    LISTING("listing"),
    // 下架
    DELIST("delint");

    private final String value;

    ProductShowStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ProductShowStatus get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
