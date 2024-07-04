package com.mini.auth.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthRoleRelationVo {

    /**
     * 主键id
     */
    @Schema(title = "主键id")
    @TableId
    private Long id;
    /**
     * 角色名
     */
    @Schema(title = "角色名")
    private String roleName;
    /**
     * 角色码值
     */
    @Schema(title = "角色码值")
    private String roleCode;

}