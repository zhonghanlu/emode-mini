package com.mini.base.model.dto;

import com.mini.base.model.request.SysSendRequest;
import com.mini.common.enums.str.MessageStatus;
import com.mini.common.enums.str.NoticeType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 站内信表;
 *
 * @author : zhl
 * @date : 2024-8-6
 */
@Data
public class SysNoticeDTO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 消息标头
     */
    private String title;
    /**
     * 消息的内容
     */
    private String content;
    /**
     * 消息发送者的id
     */
    private Long sendId;
    /**
     * 消息的类型
     */
    private NoticeType noticeType;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
    /**
     * 消息状态
     */
    private MessageStatus messageStatus;

    //////////////////////////////////////////

    /**
     * 消息发送者的id
     */
    private String sendName;
    /**
     * 发送对象参数
     */
    private List<SysSendRequest> sysSendRequestList;
}