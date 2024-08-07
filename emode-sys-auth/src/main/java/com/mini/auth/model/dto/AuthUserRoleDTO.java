package com.mini.auth.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zhl
 * @create 2024/7/16 14:58
 */
@Data
public class AuthUserRoleDTO {

    /**
     * 用户表主键
     */
    private Long id;

    /**
     * 角色id集合
     */
    private List<Long> roleIdList;

}
