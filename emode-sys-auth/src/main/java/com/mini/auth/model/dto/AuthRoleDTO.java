package com.mini.auth.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhl
 * @create 2024/7/15 16:21
 */
@Data
public class AuthRoleDTO {
    /**
     * 主键id
     */
    @Schema(name = "主键id")
    @TableId
    private Long id;
    /**
     * 角色名
     */
    @Schema(name = "角色名")
    private String roleName;
    /**
     * 角色码值
     */
    @Schema(name = "角色码值")
    private String roleCode;
}
