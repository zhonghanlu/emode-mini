package com.mini.auth.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author zhl
 * @create 2024/7/15 15:42
 */
@Data
public class AuthRoleDetailVo {
    /**
     * 主键id
     */
    @Schema(title = "主键id")
    private Long id;
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
    private List<AuthPermissionVo> authPermissionVoList;

}
