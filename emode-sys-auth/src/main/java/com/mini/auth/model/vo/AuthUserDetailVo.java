package com.mini.auth.model.vo;

import com.mini.common.enums.str.Gender;
import com.mini.common.enums.str.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

/**
 * @author zhl
 * @create 2024/7/16 16:17
 */
@Data
public class AuthUserDetailVo {

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
     * 头像url
     */
    @Schema(title = "头像url")
    private String avatarUrl;
    /**
     * 用户类型
     */
    @Schema(title = "用户类型")
    private UserType userType;
    /**
     * 角色集合
     */
    @Schema(title = "角色权限")
    private Set<String> roleList;
    /**
     * 权限集合
     */
    @Schema(title = "菜单权限")
    private Set<String> permissionList;

}
