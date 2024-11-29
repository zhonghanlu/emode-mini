package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/11/26 17:42
 */
public enum ClassType implements StringEnum {

    // 线上
    ONLINE("online"),
    // 线下
    OFFLINE("offline");

    private final String value;

    ClassType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ClassType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
