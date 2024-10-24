package com.mini.auth.model.request;

import com.mini.common.enums.str.Device;
import com.mini.common.enums.str.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhl
 * @create 2024/7/17 15:15
 */
@Data
public class AuthLoginRequest {

    @NotBlank(message = "账户名不可为空")
    @Schema(title = "账户名")
    private String username;

    @NotBlank(message = "密码不可为空")
    @Schema(title = "密码")
    private String password;

    @Schema(title = "验证码uuid，验证码若关闭，可不传")
    private String uuid;

    @Schema(title = "验证码")
    private String code;

    @NotNull(message = "用户类型不可为空")
    @Schema(title = "用户类型小程序:mini,管理端：manager，电脑端：pc")
    private UserType userType;

    @NotNull(message = "设备类型不可为空")
    @Schema(title = "设备类型，pc,mini_app,other")
    private Device deviceBy;
}
