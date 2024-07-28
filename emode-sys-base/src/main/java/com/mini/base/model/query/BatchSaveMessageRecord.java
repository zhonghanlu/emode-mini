package com.mini.base.model.query;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.List;


/**
 * 批量保存消息记录请求参数;
 *
 * @author : wsl
 * @date : 2024-7-18
 */
@Data
public class BatchSaveMessageRecord {

    /**
     * 消息发送者的id
     */
    @Parameter(description = "消息发送者的id")
    private Long RecId;

    /**
     * 消息的内容
     */
    @Parameter(description = "消息的内容")
    private List<Long> CIdList;

    /**
     * 消息的类型
     */
    @Parameter(description = "消息的类型")
    private String type;


}