package com.mini.base.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.common.enums.str.LoginOptType;
import com.mini.common.enums.str.YesOrNo;
import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 系统登入登出记录;
 *
 * @author : zhl
 * @date : 2024-7-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLoginOptQuery extends PageQuery {

    /**
     * 用户账户名
     */
    @Parameter(description = "用户账户名")
    private String username;
    /**
     * 操作是否成功
     */
    @Parameter(description = "操作是否成功")
    private YesOrNo status;
    /**
     * 操作类型，登入，登出
     */
    @Parameter(description = "操作类型，登入，登出")
    private LoginOptType optType;
    /**
     * 操作时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Parameter(description = "操作时间开始")
    private LocalDateTime optTimeStart;
    /**
     * 操作时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Parameter(description = "操作时间结束")
    private LocalDateTime optTimeEnd;
}