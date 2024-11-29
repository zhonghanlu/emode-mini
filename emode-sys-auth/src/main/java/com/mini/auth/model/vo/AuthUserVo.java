package com.mini.auth.model.vo;

import com.mini.common.enums.str.Gender;
import com.mini.common.enums.str.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhl
 * @create 2024/5/14 15:34
 */
@Data
public class AuthUserVo {

    /**
     * 用户表主键
     */
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
     * 头像Url
     */
    @Schema(title = "头像Url")
    private String avatarUrl;
    /**
     * 手机号
     */
    @Schema(title = "手机号")
    private String phone;
    /**
     * 用户类型
     */
    @Schema(title = "用户类型")
    private UserType userType;

}
