package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/7/16 16:21
 */
public enum UserQueryType implements StringEnum {
    // 用户基础信息
    BASE("base"),

    // ROLE角色信息
    ROLE("role"),

    // Permission权限信息
    PERMISSION("permission"),

    // ALL 全部信息
    ALL("all");

    private String value;

    UserQueryType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UserQueryType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }
}
