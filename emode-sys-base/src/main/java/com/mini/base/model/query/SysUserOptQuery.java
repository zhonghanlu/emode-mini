package com.mini.base.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.common.enums.str.MethodType;
import com.mini.common.enums.str.YesOrNo;
import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author zhl
 * @create 2024/7/31 16:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserOptQuery extends PageQuery {

    /**
     * 操作唯一id
     */
    @Parameter(description = "操作唯一id")
    private String requestId;
    /**
     * 用户账户名
     */
    @Parameter(description = "用户账户名")
    private String username;
    /**
     * 操作是否成功
     */
    @Parameter(description = "操作是否成功")
    private YesOrNo optStatus;
    /**
     * 操作方法类型
     */
    @Parameter(description = "操作方法类型")
    private MethodType optMethod;
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
