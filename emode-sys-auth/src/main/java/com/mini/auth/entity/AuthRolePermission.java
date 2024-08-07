package com.mini.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.number.Delete;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限关联表;
 *
 * @author : zhl
 */
@Schema(description = "角色权限关联表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_role_permission")
public class AuthRolePermission {
    /**
     * 主键id
     */
    @Schema(name = "主键id")
    @TableId
    private Long id;
    /**
     * 角色id
     */
    @Schema(name = "角色id")
    private Long roleId;
    /**
     * 权限详情id
     */
    @Schema(name = "权限详情id")
    private Long permissionId;
    /**
     * 删除标识1未删除-1已经删除
     */
    @Schema(name = "删除标识1未删除-1已经删除")
    private Delete delFlag;

}