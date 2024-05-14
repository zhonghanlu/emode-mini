package com.mini.biz.vo.auth;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhl
 * @create 2024/5/14 15:34
 */
@Setter
@Getter
public class AuthUserVo {

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
