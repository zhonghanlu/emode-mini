package com.mini.base.model.dto;

import com.mini.common.enums.str.MethodType;
import com.mini.common.enums.str.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserOptDTO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 操作唯一id
     */
    private String requestId;
    /**
     * 用户账户名
     */
    private String username;
    /**
     * 操作ip
     */
    private String optIp;
    /**
     * 操作地址
     */
    private String optAddress;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统
     */
    private String systemOs;
    /**
     * 操作方法类型
     */
    private MethodType optMethod;
    /**
     * 请求地址
     */
    private String optUrl;
    /**
     * 请求参数
     */
    private String optBody;
    /**
     * 请求状态，yes成功，no失败
     */
    private YesOrNo optStatus;
    /**
     * 操作时间
     */
    private LocalDateTime optTime;
}