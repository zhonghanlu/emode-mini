package com.mini.base.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mini.common.enums.str.MessageStatus;
import com.mini.common.enums.str.NoticeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhl
 * @create 2024/8/7 15:20
 */
@Data
public class SysNoticeVo {

    /**
     * 主键id
     */
    @Schema(title = "主键id")
    @TableId
    private Long id;
    /**
     * 消息标头
     */
    @Schema(title = "消息标头")
    private String title;
    /**
     * 消息的内容
     */
    @Schema(title = "消息的内容")
    private String content;
    /**
     * 消息发送者的id
     */
    @Schema(title = "消息发送人姓名")
    private String sendName;
    /**
     * 消息的类型
     */
    @Schema(title = "消息的类型")
    private NoticeType noticeType;
    /**
     * 发送时间
     */
    @Schema(title = "发送时间")
    private LocalDateTime sendTime;
    /**
     * 消息状态yes no
     */
    @Schema(title = "消息状态 广播 全部发送：broadcast 独立发送：alone")
    private MessageStatus messageStatus;

}
