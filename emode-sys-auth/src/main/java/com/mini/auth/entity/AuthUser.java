package com.mini.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.str.UserType;
import com.mini.common.model.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhl
 */
@Schema(description = "用户表")
@TableName("auth_user")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends CommonEntity {
    /**
     * 用户表主键
     */
    @Schema(name = "用户表主键")
    @TableId
    private Long id;
    /**
     * 用户名
     */
    @Schema(name = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(name = "密码")
    private String password;
    /**
     * 昵称
     */
    @Schema(name = "昵称")
    private String nickname;
    /**
     * 头像id
     */
    @Schema(name = "头像id")
    private Long avatar;
    /**
     * 手机号
     */
    @Schema(name = "手机号")
    private String phone;
    /**
     * 用户类型
     */
    @Schema(name = "用户类型")
    private UserType userType;
}