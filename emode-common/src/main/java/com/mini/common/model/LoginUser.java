package com.mini.common.model;

import com.mini.common.enums.str.UserType;
import lombok.Data;

import java.util.Set;

/**
 * @author zhl
 * @create 2024/7/16 17:23
 */
@Data
public class LoginUser {

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 用户名
     */
    private String username;

    /**
     * 用户名
     */
    private UserType userType;

    /**
     * 菜单权限
     */
    private Set<String> menuPermission;

    /**
     * 角色权限
     */
    private Set<String> rolePermission;

}
