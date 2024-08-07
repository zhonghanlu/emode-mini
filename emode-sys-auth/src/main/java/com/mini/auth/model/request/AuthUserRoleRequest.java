package com.mini.auth.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhl
 * @create 2024/7/16 15:24
 */
@Data
public class AuthUserRoleRequest {

    /**
     * 用户表主键
     */
    @NotNull(message = "用户id不可为空")
    private Long id;

    /**
     * 角色id集合
     */
    private List<Long> roleIdList;

}
