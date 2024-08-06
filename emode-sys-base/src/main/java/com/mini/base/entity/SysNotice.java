package com.mini.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.str.NoticeType;
import com.mini.common.enums.str.YesOrNo;
import com.mini.common.model.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 站内信表;
 *
 * @author : zhl
 * @date : 2024-8-6
 */
@Data
@TableName("sys_notice")
@Schema(description = "站内信表")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysNotice extends CommonEntity {
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
    @Schema(title = "消息发送者的id")
    private Long sendId;
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
    @Schema(title = "消息状态yes no")
    private YesOrNo status;
}