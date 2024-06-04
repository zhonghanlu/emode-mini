package com.mini.auth.model.edit;

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
public class AuthPermissionEdit {
    /**
     * 主键id
     */
    @Schema(title = "主键id")
    private Long id;
    /**
     * 页面权限值
     */
    @Schema(title = "页面权限值")
    private String authMenu;

    /**
     * 操作权限值
     */
    @Schema(title = "操作权限值")
    private String authOpt;

    /**
     * 数据权限值
     */
    @Schema(title = "数据权限值")
    private String authData;

}