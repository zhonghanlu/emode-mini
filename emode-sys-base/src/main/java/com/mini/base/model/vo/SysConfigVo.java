package com.mini.base.model.vo;

import com.mini.common.enums.str.YesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SysConfigVo {
    /**
     * 主键id
     */
    @Schema(title = "主键id")
    private Long id;
    /**
     * 配置名称
     */
    @Schema(title = "配置名称")
    private String configName;
    /**
     * 配置key
     */
    @Schema(title = "配置key")
    private String configKey;
    /**
     * 配置实际值
     */
    @Schema(title = "配置实际值")
    private String configValue;
    /**
     * 状态 yes no
     */
    @Schema(title = "状态 yes no")
    private YesOrNo configStatus;
}