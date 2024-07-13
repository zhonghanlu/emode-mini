package com.mini.common.enums.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author zhl
 */
public class RequestIntEnumConverterFactory implements ConverterFactory<Integer, IntEnum> {
    @Override
    public <T extends IntEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new RequestIntEnumConverter<>(targetType);
    }
}