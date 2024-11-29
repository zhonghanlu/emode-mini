package com.mini.common.enums.str;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.StringEnum;

import java.util.Arrays;

/**
 * @author zhl
 * @create 2024/11/26 17:34
 */
public enum OrderStatus implements StringEnum {

    // 待支付
    TO_PAID("to_paid"),
    // 已支付
    HAVE_PAID("have_paid"),
    // 已过期
    EXPIRED_PAID("expired_paid"),
    // 支付错误
    ERROR_PAID("error_paid"),
    // 已核销
    CONSUME_PAID("consume_paid");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static OrderStatus get(String val) {
        return Arrays.stream(values()).filter(e -> e.getStringValue().equalsIgnoreCase(val)).findFirst().orElse(null);
    }

    @JsonValue
    @Override
    public String getStringValue() {
        return value;
    }

}
