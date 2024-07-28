package com.mini.base.model.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 查询消息内容记录请求参数;
 *
 * @author : wsl
 * @date : 2024-7-18
 */
@Data
public class MessageContentRecordDTO {

    /**
     * 消息记录id
     */
    @Schema(name = "消息记录id")
    private Long rId;

    /**
     * 消息接收者的id
     */
    @Schema(name = "消息接收者的id")
    private Long recId;

    /**
     * 消息的id
     */
    @Parameter(description = "消息的id")
    private Long cid;

    /**
     * 发送者id
     */
    @Parameter(description = "发送者id")
    private Long send_id;

    /**
     * 阅读记录的状态
     */
    @Schema(name = "阅读记录的状态")
    private String status;

    /**
     * 消息的类型
     */
    @Parameter(description = "消息的类型")
    private String type;

    /**
     * 消息的内容
     */
    @Parameter(description = "消息的内容")
    private String content;


    /**
     * 消息记录创建时间
     */
    @Parameter(description = "消息创建时间")
    private LocalDateTime creatTime;





}