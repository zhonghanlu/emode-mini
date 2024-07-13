package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/5/16 10:57
 * 持续补充文件类型
 */
public enum FileType implements StringEnum {

    // jpg
    JPG("jpg"),
    // png
    PNG("png"),
    // txt
    TXT("txt"),
    // pdf
    PDF("pdf"),
    // doc
    DOC("doc"),
    // csv
    CSV("csv");

    private String value;

    FileType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FileType get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
