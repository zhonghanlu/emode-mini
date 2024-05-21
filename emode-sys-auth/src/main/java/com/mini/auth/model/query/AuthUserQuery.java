package com.mini.auth.model.query;

import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhl
 * @create 2024/5/21 11:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthUserQuery extends PageQuery {

    /**
     * 用户名
     */
    @Parameter(description = "用户名")
    private String username;

}
