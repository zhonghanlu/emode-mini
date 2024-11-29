package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;
import lombok.Getter;

import java.util.Arrays;

/**
 * 课程类型
 * @author zhl
 * @create 2024-11-19 22:38
 */
@Getter
public enum CourseType implements StringEnum {
    // python
    PYTHON("python"),
    // c++
    CPP("cpp"),
    // scratch
    SCRATCH("scratch");

    private final String value;

    CourseType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CourseType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
