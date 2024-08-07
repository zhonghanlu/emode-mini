package com.mini.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.number.Delete;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Schema(description = "系统角色表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_role")
public class AuthRole {
    /**
     * 主键id
     */
    @Schema(name = "主键id")
    @TableId
    private Long id;
    /**
     * 角色名
     */
    @Schema(name = "角色名")
    private String roleName;
    /**
     * 角色码值
     */
    @Schema(name = "角色码值")
    private String roleCode;
    /**
     * 删除标识1未删除-1已删除
     */
    @Schema(name = "删除标识1未删除-1已删除")
    private Delete delFlag;
}