package com.mini.base.model.dto;

import com.mini.common.enums.str.YesOrNo;
import lombok.Data;

@Data
public class SysConfigDTO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 配置名称
     */
    private String configName;
    /**
     * 配置key
     */
    private String configKey;
    /**
     * 配置实际值
     */
    private String configValue;
    /**
     * 状态 yes no
     */
    private YesOrNo configStatus;
    /**
     * 是否展示 yes no
     */
    private YesOrNo showStatus;
}