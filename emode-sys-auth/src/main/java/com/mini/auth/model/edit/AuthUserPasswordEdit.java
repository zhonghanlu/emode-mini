package com.mini.auth.model.edit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhl
 * @create 2024/5/14 15:34
 */
@Data
public class AuthUserPasswordEdit {
    /**
     * 用户表主键
     */
    @NotNull(message = "修改主键id不可为空")
    @Schema(title = "用户表主键")
    private Long id;

    /**
     * 用户旧密码
     */
    @NotBlank(message = "旧密码不可为空")
    @Schema(title = "旧密码")
    private String oldPassword;

    /**
     * 用户新密码
     */
    @NotBlank(message = "新密码不可为空")
    @Schema(title = "新密码")
    private String newPassword;

    /**
     * 用户确认新密码
     */
    @NotBlank(message = "确认新密码不可为空")
    @Schema(title = "确认新密码")
    private String verifyPassword;


}
