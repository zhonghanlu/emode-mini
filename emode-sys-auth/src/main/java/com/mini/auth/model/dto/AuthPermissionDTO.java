package com.mini.auth.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限详细记录表;
 *
 * @author : zhl
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthPermissionDTO {
    /**
     * 主键id
     */
    @Schema(name = "主键id")
    private Long id;

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