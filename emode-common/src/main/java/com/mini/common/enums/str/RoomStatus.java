package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/11/15 11:13
 */
public enum RoomStatus implements StringEnum {

    // 启用
    ENABLE("enable"),
    // 关闭
    DISABLE("disable");

    private final String value;

    RoomStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static RoomStatus get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }


}
