package com.mini.common.enums.number;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mini.common.enums.converter.IntEnum;

/**
 * @author zhl
 * 是否删除
 */
public enum Delete implements IntEnum {

    /**
     * 删除
     */
    YES(-1),

    /**
     * 未删除
     */
    NO(1);

    private int value;

    Delete(int value) {
        this.value = value;
    }

    @JsonValue
    public int getIntValue() {
        return value;
    }

}