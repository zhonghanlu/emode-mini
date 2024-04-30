package com.mini.common.mybatis;

import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.Objects;

public class RequestStringEnumConverter<T extends StringEnum> implements Converter<String, T> {

    private final Class<T> enumType;

    public RequestStringEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(String source) {
        return Arrays.stream(enumType.getEnumConstants()).filter(t -> t.getStringValue().equalsIgnoreCase(source)).findFirst().orElse(null);
    }
}