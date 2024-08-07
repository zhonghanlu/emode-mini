package com.mini.base.model.dto;

import com.mini.common.enums.str.YesOrNo;
import lombok.Data;

/**
 * 消息用户关联表;
 *
 * @author : zhl
 * @date : 2024-8-6
 */
@Data
public class SysUserNoticeDTO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 通知消息id
     */
    private Long noticeId;
    /**
     * 用户id，用于站内消息通知
     */
    private Long userId;
    /**
     * 用于手机号短信通知
     */
    private String phone;
    /**
     * 用于邮箱通知
     */
    private String email;
    /**
     * 是否已读 yes no
     */
    private YesOrNo readStatus;
}