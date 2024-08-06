package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/8/6 15:46
 */
public enum NoticeType implements StringEnum {

    // 站内信
    LOCAL("Local"),

    // 短信
    MSG("Msg"),

    // 邮箱
    EMAIL("Email");

    private String value;

    NoticeType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static NoticeType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
