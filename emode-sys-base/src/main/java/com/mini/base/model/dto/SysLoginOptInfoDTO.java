package com.mini.base.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mini.common.enums.str.LoginOptType;
import com.mini.common.enums.str.YesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhl
 * @create 2024/7/18 17:06
 */
@Data
public class SysLoginOptInfoDTO {

    /**
     * 主键id
     */
    @Schema(name = "主键id")
    @TableId
    private Long id;
    /**
     * 用户账户名
     */
    @Schema(name = "用户账户名")
    private String username;
    /**
     * 操作ip
     */
    @Schema(name = "操作ip")
    private String optIp;
    /**
     * 操作地址
     */
    @Schema(name = "操作地址")
    private String optAddress;
    /**
     * 浏览器类型
     */
    @Schema(name = "浏览器类型")
    private String browser;
    /**
     * 操作系统
     */
    @Schema(name = "操作系统")
    private String systemOs;
    /**
     * 操作是否成功
     */
    @Schema(name = "操作是否成功")
    private YesOrNo status;
    /**
     * 操作类型，登入，登出
     */
    @Schema(name = "操作类型，登入，登出")
    private LoginOptType optType;
    /**
     * 操作结果消息
     */
    @Schema(name = "操作结果消息")
    private String optMsg;
    /**
     * 操作时间
     */
    @Schema(name = "操作时间")
    private LocalDateTime optTime;

}
