package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/5/16 10:57
 */
public enum Device implements StringEnum {

    // pc
    PC("pc"),
    // mini_app
    MINI_APP("mini_app"),
    // app
    APP("app"),
    // pad
    PAD("pad"),
    // other
    OTHER("other");

    private String value;

    Device(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Device get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
