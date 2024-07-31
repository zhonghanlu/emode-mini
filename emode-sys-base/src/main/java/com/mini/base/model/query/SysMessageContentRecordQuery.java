package com.mini.base.model.query;

import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;


/**
 * 查询消息内容记录请求参数;
 *
 * @author : wsl
 * @date : 2024-7-18
 */
@Data
public class SysMessageContentRecordQuery extends PageQuery {

    /**
     * 消息接收者的id
     */
    @Parameter(description = "消息接收者的id")
    private Long recId;

    /**
     * 消息的类型
     */
    @Parameter(description = "消息的类型")
    private String type;


    /**
     * 消息记录创建时间
     */
    @Parameter(description = "消息创建时间")
    private LocalDateTime creatTime;



}