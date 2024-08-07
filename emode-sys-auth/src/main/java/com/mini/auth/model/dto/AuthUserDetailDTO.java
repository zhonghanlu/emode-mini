package com.mini.auth.model.dto;

import com.mini.common.enums.str.UserType;
import lombok.Data;

import java.util.List;

/**
 * @author zhl
 * @create 2024/7/16 15:56
 */
@Data
public class AuthUserDetailDTO {

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
     * 用户类型
     */
    private UserType userType;
    /**
     * 角色集合
     */
    private List<AuthRoleDTO> authRoleDTOList;
    /**
     * 权限集合
     */
    private List<AuthPermissionDTO> authPermissionDTOList;

}
