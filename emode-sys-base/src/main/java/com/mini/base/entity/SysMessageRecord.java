package com.mini.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 站内信消息记录表;
 *
 * @author : wsl
 * @date : 2024-7-17
 */

@Schema(description = "站内信消息记录表")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_message_record")
public class SysMessageRecord {

    /**
     * 阅读记录的id
     */
    @TableId
    @Schema(name = "阅读记录的id")
    private Long rId;

    /**
     * 消息接收者的id
     */
    @Schema(name = "消息接收者的id")
    private Long recId;

    /**
     * 对应消息的id
     */
    @Schema(name = "消息的id")
    private Long cId;

    /**
     * 阅读记录的状态
     */
    @Schema(name = "阅读记录的状态")
    private String status;

}
