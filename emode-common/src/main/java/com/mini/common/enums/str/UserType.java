package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/7/16 17:26
 */
public enum UserType implements StringEnum {

    // 小程序
    MINI("mini"),

    // PC
    PC("pc"),

    // MANAGER
    MANAGER("manager");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UserType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
