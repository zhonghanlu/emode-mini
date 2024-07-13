package com.mini.core.jackson.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author zhl
 * @description  自定义枚举序列化器
 */
public class GenericEnumSerializer extends JsonSerializer<Enum<?>> {

    @Override
    public void serialize(Enum<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeString(value.name());
        } else {
            gen.writeNull();
        }
    }
}
