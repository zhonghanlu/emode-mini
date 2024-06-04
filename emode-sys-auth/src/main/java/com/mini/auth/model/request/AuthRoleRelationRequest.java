package com.mini.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthRoleRelationRequest {

    /**
     * 角色名
     */
    @Schema(title = "角色名")
    private String roleName;
    /**
     * 角色码值
     */
    @Schema(title = "角色码值")
    private String roleCode;
    /**
     * 权限集合
     */
    @Schema(title = "权限集合")
    private List<AuthPermissionRequest> authPermissionRequestList;
}