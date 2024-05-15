package com.mini.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhl
 * @create 2024/5/14 15:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthUserRequest {
    /**
     * 用户名
     */
    @Schema(title = "用户名")
    @NotEmpty(message = "用户名不可为空")
    private String username;
    /**
     * 密码
     */
    @Schema(title = "密码")
    @NotEmpty(message = "密码不可为空")
    private String password;
    /**
     * 昵称
     */
    @Schema(title = "昵称")
    @NotEmpty(message = "昵称不可为空")
    private String nickname;
    /**
     * 头像id
     */
    @Schema(title = "头像id")
    private Long avatar;
    /**
     * 手机号
     */
    @Schema(title = "手机号")
    @NotEmpty(message = "手机号不可为空")
    private String phone;
}
