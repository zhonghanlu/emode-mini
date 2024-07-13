package com.mini.common.enums.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;

/**
 * @author zhl
 */
public class RequestIntEnumConverter<T extends IntEnum> implements Converter<Integer, T> {

    private final Class<T> enumType;

    public RequestIntEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(Integer source) {
        return Arrays.stream(enumType.getEnumConstants()).filter(t -> t.getIntValue() == (source)).findFirst().orElse(null);
    }
}