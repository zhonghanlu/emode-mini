package com.mini.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 站内信消息内容表;
 *
 * @author : wsl
 * @date : 2024-7-17
 */

@Schema(description = "系统角色表")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_message_content")
public class TMessageContent {

    /**
     * 消息的id
     */
    @Schema(name = "消息的id")
    private Integer cId;
    /**
     * 消息发送者的id
     */
    @Schema(name = "消息发送者的id")
    private Integer sendId;
    /**
     * 消息的内容
     */
    @Schema(name = "消息的内容")
    private String content;
    /**
     * 消息的类型
     */
    @Schema(name = "消息的类型")
    private Integer type;
    /**
     * 消息发送的时间
     */
    @Schema(name = "消息发送的时间")
    private LocalDateTime createTime;

}
