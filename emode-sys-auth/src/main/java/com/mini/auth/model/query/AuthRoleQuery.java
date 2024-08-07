package com.mini.auth.model.query;

import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthRoleQuery extends PageQuery {
    /**
     * 角色名
     */
    @Parameter(description = "角色名")
    private String roleName;
    /**
     * 角色码值
     */
    @Parameter(description = "角色码值")
    private String roleCode;
}