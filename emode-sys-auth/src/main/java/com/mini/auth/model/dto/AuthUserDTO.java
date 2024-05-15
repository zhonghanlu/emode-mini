package com.mini.auth.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mini.common.utils.webmvc.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhl
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthUserDTO extends CommonEntity {
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
}