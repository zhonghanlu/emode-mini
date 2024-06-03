package com.mini.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.number.Delete;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统权限详细记录表;
 *
 * @author : zhl
 */
@Schema(description = "系统权限详细记录表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("auth_permission")
public class AuthPermission {
    /**
     * 主键id
     */
    @TableId
    @Schema(name = "主键id")
    private Long id;

    /**
     * 页面权限值
     */
    @Schema(name = "页面权限值")
    private String authMenu;

    /**
     * 操作权限值
     */
    @Schema(name = "操作权限值")
    private String authOpt;

    /**
     * 数据权限值
     */
    @Schema(name = "数据权限值")
    private String authData;

    /**
     * 删除标识1未删除-1已删除
     */
    @Schema(name = "删除标识1未删除-1已删除")
    private Delete delFlag;

}