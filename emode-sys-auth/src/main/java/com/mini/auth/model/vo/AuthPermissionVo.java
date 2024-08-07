package com.mini.auth.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mini.common.enums.str.MenuType;
import com.mini.common.utils.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhl
 * @create 2024/7/4 16:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthPermissionVo extends TreeNode<AuthPermissionVo> {

    /**
     * 主键id
     */
    @TableId
    @Schema(title = "主键id")
    private Long id;

    /**
     * 父级id
     */
    @Schema(title = "父级id")
    private Long parentId;

    /**
     * 菜单名
     */
    @Schema(title = "菜单名")
    private String menuName;

    /**
     * 菜单url
     */
    @Schema(title = "菜单url")
    private String menuUrl;

    /**
     * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
     */
    @Schema(title = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
    private String permissions;

    /**
     * 按钮类型 按钮 菜单
     */
    @Schema(title = "按钮类型：按钮、菜单")
    private MenuType menuType;

    /**
     * 图标
     */
    @Schema(title = "图标")
    private String icon;

    /**
     * 排序
     */
    @Schema(title = "排序")
    private int sort;
}
