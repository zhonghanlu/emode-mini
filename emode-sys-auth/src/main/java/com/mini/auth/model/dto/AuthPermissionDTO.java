package com.mini.auth.model.dto;

import com.mini.common.enums.str.MenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统权限详细记录表;
 *
 * @author : zhl
 */
@Data
public class AuthPermissionDTO {
    /**
     * 主键id
     */
    @Schema(name = "主键id")
    private Long id;

    /**
     * 父级id
     */
    @Schema(name = "父级id")
    private Long parentId;

    /**
     * 菜单名
     */
    @Schema(name = "菜单名")
    private String menuName;

    /**
     * 菜单path
     */
    @Schema(title = "菜单path")
    private String menuPath;

    /**
     * 菜单url
     */
    @Schema(name = "菜单url")
    private String menuUrl;

    /**
     * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
     */
    @Schema(name = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
    private String permissions;

    /**
     * 按钮类型 按钮 菜单
     */
    @Schema(name = "按钮类型：目录、按钮、菜单")
    private MenuType menuType;

    /**
     * 图标
     */
    @Schema(name = "图标")
    private String icon;

    /**
     * 排序
     */
    @Schema(name = "排序")
    private int sort;

}
