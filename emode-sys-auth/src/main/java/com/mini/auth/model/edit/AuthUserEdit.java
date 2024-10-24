package com.mini.auth.model.edit;

import com.mini.common.enums.str.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhl
 * @create 2024/5/14 15:34
 */
@Data
public class AuthUserEdit {
    /**
     * 用户表主键
     */
    @NotNull(message = "修改主键id不可为空")
    @Schema(title = "用户表主键")
    private Long id;
    /**
     * 用户名
     */
    @Schema(title = "用户名")
    private String username;
    /**
     * 昵称
     */
    @Schema(title = "昵称")
    private String nickname;
    /**
     * 性别
     */
    @Schema(title = "性别")
    private Gender sex;
    /**
     * 头像id
     */
    @Schema(title = "头像id")
    private Long avatar;
    /**
     * 手机号
     */
    @Schema(title = "手机号")
    private String phone;
}
