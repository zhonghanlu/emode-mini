package com.mini.base.model.request;

import com.mini.common.enums.str.MessageStatus;
import com.mini.common.enums.str.NoticeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhl
 * @create 2024/8/7 15:20
 */
@Data
public class SysNoticeRequest {
    /**
     * 消息标头
     */
    @NotEmpty(message = "标题不可为空")
    @Schema(title = "标题")
    private String title;
    /**
     * 消息的内容
     */
    @NotEmpty(message = "内容不可为空")
    @Schema(title = "内容")
    private String content;
    /**
     * 消息的类型
     */
    @NotNull(message = "消息类型不可为空，本地：local 短信：msg 邮箱：email")
    @Schema(title = "本地：local 短信：msg 邮箱：email")
    private NoticeType noticeType;
    /**
     * 广播：broadcast 独立：alone
     */
    @NotNull(message = "消息形式不可为空，广播：broadcast 独立：alone")
    @Schema(title = "广播：broadcast 独立：alone")
    private MessageStatus messageStatus;

    /**
     * 发送对象参数
     */
    @Schema(title = "针对独立发送人群")
    private List<SysSendRequest> sysSendRequestList;

}
