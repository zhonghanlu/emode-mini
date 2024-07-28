package com.mini.base.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.common.enums.str.LoginOptType;
import com.mini.common.enums.str.YesOrNo;
import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 保存消息模板请求参数;
 *
 * @author : wsl
 * @date : 2024-7-18
 */
@Data
public class SaveMessageContent {

    /**
     * 消息发送者的id
     */
    @Parameter(description = "消息发送者的id")
    private Long sendId;

    /**
     * 消息的内容
     */
    @Parameter(description = "消息的内容")
    private String content;

    /**
     * 消息的类型
     */
    @Parameter(description = "消息的类型")
    private String type;


}