package com.mini.base.model.dto;

import com.mini.common.enums.str.LoginOptType;
import com.mini.common.enums.str.YesOrNo;
import lombok.Builder;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统登入登出记录;
 *
 * @author : zhl
 * @date : 2024-7-18
 */
@Data
@Builder
public class SysLoginOptDTO {
    /**
     * 用户账户名
     */
    private String username;
    /**
     * 操作是否成功
     */
    private YesOrNo status;
    /**
     * 操作类型，登入，登出
     */
    private LoginOptType optType;
    /**
     * 操作结果消息
     */
    private String optMsg;
    /**
     * request
     */
    private HttpServletRequest request;
}