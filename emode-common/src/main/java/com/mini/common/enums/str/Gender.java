package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.mybatis.StringEnum;

import java.util.Arrays;

public enum Gender implements StringEnum {

    MALE("male"),
    FEMALE("female"),
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