package com.mini.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "用户id")
    private Long id;

    /**
     * 角色id集合
     */
    @Schema(title = "角色id集合")
    private List<Long> roleIdList;

}
