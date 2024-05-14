package com.mini.common.utils.webmvc;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.common.enums.number.Delete;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhl
 * mybatis-plus 通用类
 */
@Data
public class CommonEntity implements Serializable {

    /**
     * 删除标志（1代表存在 -1代表删除）
     */
    @Schema(name = "删除标识")
    private Delete delFlag;

    /**
     * 删除人
     */
    @Schema(name = "删除人", type = "Long")
    private Long delBy;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(name = "创建者", type = "Long")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "创建时间", type = "LocalDateTime")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(name = "更新者", type = "Long")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "更新时间", type = "LocalDateTime")
    private LocalDateTime updateTime;

}
