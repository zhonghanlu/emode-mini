package com.mini.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.number.Delete;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色关联表;
 *
 * @author : zhl
 */
@Schema(description = "用户角色关联表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_user_role")
public class AuthUserRole {
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
     * 角色名
     */
    @Schema(name = "角色名")
    private String roleName;
    /**
     * 用户id
     */
    @Schema(name = "用户id")
    private Long userId;
    /**
     * 用户名
     */
    @Schema(name = "用户名")
    private String username;
    /**
     * 删除标识1未删除 -1 已删除
     */
    @Schema(name = "删除标识1未删除 -1 已删除")
    private Delete delFlag;

}