package com.mini.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.str.YesOrNo;
import com.mini.common.model.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 全局参数配置表;
 *
 * @author zhl
 */
@Data
@TableName("sys_config")
@Schema(description = "全局参数配置表")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig extends CommonEntity {
    /**
     * 主键id
     */
    @Schema(title = "主键id")
    @TableId
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
    /**
     * 是否展示 yes no
     */
    @Schema(title = "是否展示 yes no")
    private YesOrNo showStatus;
}