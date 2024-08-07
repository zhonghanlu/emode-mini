package com.mini.base.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.common.enums.str.LoginOptType;
import com.mini.common.enums.str.YesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 系统登入登出记录;
 *
 * @author : zhl
 * @date : 2024-7-18
 */
@Data
public class SysLoginOptVo {
    /**
     * 主键id
     */
    @Schema(title = "主键id")
    @TableId
    private Long id;
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
     * 操作是否成功
     */
    @Schema(title = "操作是否成功")
    private YesOrNo status;
    /**
     * 操作类型，登入，登出
     */
    @Schema(title = "操作类型，登入，登出")
    private LoginOptType optType;
    /**
     * 操作结果消息
     */
    @Schema(title = "操作结果消息")
    private String optMsg;
    /**
     * 操作时间
     */
    @Schema(title = "操作时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime optTime;
}