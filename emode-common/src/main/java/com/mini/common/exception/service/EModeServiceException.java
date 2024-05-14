package com.mini.common.exception.service;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhl
 */
@Setter
@Getter
public final class EModeServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     */
    private String detailMessage;

    public EModeServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public EModeServiceException(String message) {
        this.message = message;
    }

    public EModeServiceException(Integer code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }
}