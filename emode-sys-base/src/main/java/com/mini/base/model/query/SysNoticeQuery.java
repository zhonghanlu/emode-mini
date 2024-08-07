package com.mini.base.model.query;

import com.mini.common.enums.str.MessageStatus;
import com.mini.common.enums.str.NoticeType;
import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhl
 * @create 2024/8/7 14:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysNoticeQuery extends PageQuery {

    /**
     * 消息标头
     */
    @Parameter(description = "消息标头")
    private String title;

    /**
     * 消息的类型
     */
    @Parameter(description = "消息的类型")
    private NoticeType noticeType;

    /**
     * 消息状态
     */
    @Parameter(description = "消息状态")
    private MessageStatus messageStatus;

}
