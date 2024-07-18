package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/7/18 15:29
 */
public enum LoginOptType implements StringEnum {
    // login
    LOGIN("login"),
    // logout
    LOGOUT("logout");

    private String value;

    LoginOptType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static LoginOptType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }
}
