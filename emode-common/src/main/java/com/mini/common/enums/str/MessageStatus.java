package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/8/7 14:36
 */
public enum MessageStatus implements StringEnum {

    // 广播 全部发送
    BROADCAST("broadcast"),
    // 独立发送
    ALONE("alone");

    private String value;

    MessageStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MessageStatus get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
