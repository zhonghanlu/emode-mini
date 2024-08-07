package com.mini.base.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhl
 * @create 2024/8/6 17:31
 */
@Data
public class SysSendRequest {

    /**
     * 用户id，用于站内消息通知
     */
    @Schema(title = "用户id，用于站内消息通知")
    private Long userId;
    /**
     * 用于手机号短信通知
     */
    @Schema(title = "用于手机号短信通知")
    private String phone;
    /**
     * 用于邮箱通知
     */
    @Schema(title = "用于邮箱通知")
    private String email;

}
