package com.mini.auth.model.request;

import com.mini.common.enums.str.MenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 系统权限详细记录表;
 *
 * @author : zhl
 */
@Data
public class AuthPermissionRequest {
    /**
     * 父级id
     */
    @NotNull(message = "父级id不可为空")
    @Schema(title = "父级id", description = "父级id")
    private Long parentId;

    /**
     * 菜单名
     */
    @Schema(title = "菜单名", description = "菜单名")
    private String menuName;

    /**
     * 菜单url
     */
    @Schema(title = "菜单url", description = "菜单url")
    private String menuUrl;

    /**
     * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
     */
    @Schema(title = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)", description = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
    private String permissions;

    /**
     * 类型 按钮 菜单
     */
    @NotNull(message = "类型不可为空")
    @Schema(title = "类型：按钮、菜单", description = "类型：按钮、菜单")
    private MenuType menuType;

    /**
     * 图标
     */
    @Schema(title = "图标", description = "图标")
    private String icon;

    /**
     * 排序
     */
    @Schema(title = "排序",description = "排序")
    private int sort;

}