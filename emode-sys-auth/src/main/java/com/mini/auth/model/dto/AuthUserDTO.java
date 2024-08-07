package com.mini.auth.model.dto;

import com.mini.common.enums.str.UserType;
import lombok.Data;

/**
 * @author zhl
 */
@Data
public class AuthUserDTO  {
    /**
     * 用户表主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像id
     */
    private Long avatar;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户类型
     */
    private UserType userType;
}