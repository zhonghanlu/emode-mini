package com.mini.common.utils.http;

import lombok.Data;

/**
 * @author zhl
 * @create 2024/5/20 15:56
 */
@Data
public class HttpResponse {

    private int code;

    private String msg;

    private Object data;

}