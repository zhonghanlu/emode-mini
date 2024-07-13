//package com.mini.core.jackson.converter;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//
//import java.io.IOException;
//
//public class GenericEnumDeserializer extends JsonDeserializer<Enum<?>> {
//
//    private final Class<? extends Enum<?>> enumType;
//
//    public GenericEnumDeserializer(Class<? extends Enum<?>> enumType) {
//        this.enumType = enumType;
//    }
//
//    @Override
//    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        String value = p.getText();
//        return Enum.valueOf(enumType, value);
//    }
//}
