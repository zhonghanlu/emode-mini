package com.mini.base.model.query;

import com.mini.common.enums.str.YesOrNo;
import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysConfigQuery extends PageQuery {
    /**
     * 配置名称
     */
    @Parameter(description = "配置名称")
    private String configName;
    /**
     * 配置key
     */
    @Parameter(description = "配置key")
    private String configKey;
    /**
     * 状态 yes no
     */
    @Parameter(description = "状态 yes no")
    private YesOrNo configStatus;
}