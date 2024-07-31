package com.mini.base.model.edit;

import com.mini.common.enums.str.YesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author zhl
 * @create 2024/7/31 14:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysConfigEdit {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不可为空")
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
