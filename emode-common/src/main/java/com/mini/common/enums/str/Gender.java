package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * 行别枚举
 */
public enum Gender implements StringEnum {

    /**
     * 男
     */
    MALE("male"),
    /**
     * 女
     */
    FEMALE("female"),
    /**
     * 未知
     */
    UNKNOWN("unknown");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Gender get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    public String chinese() {
        String value = "未知";
        if (Gender.FEMALE == this) {
            value = "女";
        } else if (Gender.MALE == this) {
            value = "男";
        }
        return value;
    }

    @JsonValue
    public String getStringValue() {
        return value;
    }
}