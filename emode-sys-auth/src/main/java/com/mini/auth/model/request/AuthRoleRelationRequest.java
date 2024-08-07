package com.mini.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Data
public class AuthRoleRelationRequest {

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不可为空")
    @Schema(title = "角色名")
    private String roleName;
    /**
     * 角色码值
     */
    @NotBlank(message = "角色码值不可为空")
    @Schema(title = "角色码值")
    private String roleCode;
    /**
     * 权限集合
     */
    @NotNull(message = "请关联权限信息")
    @Schema(title = "权限集合")
    private List<Long> authPermissionIdList;
}