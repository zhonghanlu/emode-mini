package com.mini.auth.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhl
 * @create 2024/7/16 16:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthUserDetailVo {

    /**
     * 用户表主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像id
     */
    private String avatarUrl;
    /**
     * 角色集合
     */
    private List<AuthRoleVo> authRoleVoList;
    /**
     * 权限集合
     */
    private List<AuthPermissionVo> authPermissionVoList;

}
