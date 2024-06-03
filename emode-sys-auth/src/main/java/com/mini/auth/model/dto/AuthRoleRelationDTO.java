package com.mini.auth.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthRoleRelationDTO {

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
     * 页面权限值
     */
    @Schema(name = "页面权限值")
    private String authMenu;

    /**
     * 操作权限值
     */
    @Schema(name = "操作权限值")
    private String authOpt;

    /**
     * 数据权限值
     */
    @Schema(name = "数据权限值")
    private String authData;
}