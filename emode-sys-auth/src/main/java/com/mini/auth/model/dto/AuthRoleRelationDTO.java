package com.mini.auth.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 系统角色表;
 *
 * @author : zhl
 */
@Data
public class AuthRoleRelationDTO {

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
    /**
     * 权限idList集合
     */
    @Schema(name = "权限idList集合")
    private List<Long> authPermissionIdList;
    /**
     * 权限集合
     */
    @Schema(name = "权限集合")
    private List<AuthPermissionDTO> authPermissionDTOList;
}