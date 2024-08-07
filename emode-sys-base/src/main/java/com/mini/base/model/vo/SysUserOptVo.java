package com.mini.base.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.common.enums.str.MethodType;
import com.mini.common.enums.str.YesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SysUserOptVo {
    /**
     * 主键id
     */
    @Schema(title = "主键id")
    private Long id;
    /**
     * 操作唯一id
     */
    @Schema(title = "操作唯一id")
    private String requestId;
    /**
     * 用户账户名
     */
    @Schema(title = "用户账户名")
    private String username;
    /**
     * 操作ip
     */
    @Schema(title = "操作ip")
    private String optIp;
    /**
     * 操作地址
     */
    @Schema(title = "操作地址")
    private String optAddress;
    /**
     * 浏览器类型
     */
    @Schema(title = "浏览器类型")
    private String browser;
    /**
     * 操作系统
     */
    @Schema(title = "操作系统")
    private String systemOs;
    /**
     * 操作方法类型
     */
    @Schema(title = "操作方法类型")
    private MethodType optMethod;
    /**
     * 请求地址
     */
    @Schema(title = "请求地址")
    private String optUrl;
    /**
     * 请求参数
     */
    @Schema(title = "请求参数")
    private String optBody;
    /**
     * 请求状态，yes成功，no失败
     */
    @Schema(title = "请求状态，yes成功，no失败")
    private YesOrNo optStatus;
    /**
     * 操作时间
     */
    @Schema(title = "操作时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime optTime;
}