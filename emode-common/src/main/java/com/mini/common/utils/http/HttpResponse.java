package com.mini.common.utils.http;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhl
 * @create 2024/5/20 15:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HttpResponse {

    private int code;

    private String msg;

    private Object data;

}