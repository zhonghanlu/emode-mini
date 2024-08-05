package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/8/5 10:44
 */
public enum MethodType implements StringEnum {

    // GET
    GET("GET"),

    // POST
    POST("POST");

    private String value;

    MethodType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MethodType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }
}
