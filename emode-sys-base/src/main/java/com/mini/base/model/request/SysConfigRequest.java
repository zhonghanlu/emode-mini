package com.mini.base.model.request;

import com.mini.common.enums.str.YesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhl
 * @create 2024/7/31 14:41
 */
@Data
public class SysConfigRequest {
    /**
     * 配置名称
     */
    @NotEmpty(message = "配置名称不可为空")
    @Schema(title = "配置名称")
    private String configName;
    /**
     * 配置key
     */
    @NotEmpty(message = "配置key不可为空")
    @Schema(title = "配置key")
    private String configKey;
    /**
     * 配置实际值
     */
    @NotEmpty(message = "配置实际值不可为空")
    @Schema(title = "配置实际值")
    private String configValue;
    /**
     * 状态 yes no
     */
    @NotNull(message = "状态不可为空")
    @Schema(title = "状态 yes no")
    private YesOrNo configStatus;
}
