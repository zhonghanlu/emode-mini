package com.mini.common.enums.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author zhl
 */
@SuppressWarnings("unchecked")
public class RequestStringEnumConverterFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new RequestStringEnumConverter(targetType);
    }
}