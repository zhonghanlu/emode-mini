package com.mini.auth.model.edit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Data
public class AuthRoleRelationEdit {
    /**
     * 主键id
     */
    @NotNull(message = "修改主键id不可为空")
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
    private List<Long> authPermissionIdList;
}