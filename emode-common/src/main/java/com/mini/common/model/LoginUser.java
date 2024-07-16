package com.mini.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhl
 * @create 2024/7/16 17:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUser {

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 用户名
     */
    private String username;

}
