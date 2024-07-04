package com.mini.auth.model.query;

import com.mini.common.enums.str.MenuType;
import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限查询
 *
 * @author zhl
 * @create 2024/7/4 15:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthPermissionQuery extends PageQuery {

    /**
     * 菜单名
     */
    @Parameter(description = "菜单名")
    private String menuName;

    /**
     * 菜单url
     */
    @Parameter(description = "菜单url")
    private String menuUrl;

    /**
     * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
     */
    @Parameter(description = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
    private String permissions;

    /**
     * 按钮类型 按钮 菜单
     */
    @Parameter(description = "按钮类型：按钮、菜单")
    private MenuType menuType;

}
