package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/7/4 15:37
 */
public enum MenuType implements StringEnum {
    // Button
    BUTTON("Button"),
    // Menu
    MENU("Menu"),
    // Directory
    DIRECTORY("Directory");

    private String value;

    MenuType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MenuType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }
}
