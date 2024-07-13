package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * 是或否
 */
public enum YesOrNo implements StringEnum {

    // 是
    YES("yes"),
    // 否
    NO("no"),

    /**
     * 忽略
     */
    IGNORE("ignore");

    private String value;

    YesOrNo(String value) {
        this.value = value;
    }

    @JsonCreator
    public static YesOrNo get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }


}