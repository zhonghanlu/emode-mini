package com.mini.web.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.edit.AuthRoleRelationEdit;
import com.mini.auth.model.query.AuthRoleQuery;
import com.mini.auth.model.request.AuthRoleRelationRequest;
import com.mini.auth.model.vo.AuthRoleDetailVo;
import com.mini.auth.model.vo.AuthRoleVo;
import com.mini.biz.auth.SysRoleBiz;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author zhl
 * @create 2024/6/4 17:04
 */
@Tag(name = "角色相关信息中心", description = "角色相关信息中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-role")
public class SysRoleController {

    private final SysRoleBiz sysRoleBiz;

    @Operation(summary = "角色分页")
    @GetMapping("/page")
    public Restful<IPage<AuthRoleVo>> rolePage(@ParameterObject AuthRoleQuery query) {
        return Restful.OBJECT(sysRoleBiz.page(query)).build();
    }

    @Operation(summary = "角色详情")
    @GetMapping("/detail/{roleId}")
    public Restful<AuthRoleDetailVo> getRoleById(@PathVariable("roleId") Long id) {
        return Restful.OBJECT(sysRoleBiz.getRoleById(id)).build();
    }

    @Operation(summary = "新增角色信息")
    @PostMapping("/add")
    public Restful<Void> insert(@RequestBody @Valid AuthRoleRelationRequest request) {
        sysRoleBiz.insert(request);
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "删除角色信息")
    @PostMapping("/del")
    public Restful<Void> del(long id) {
        sysRoleBiz.del(id);
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "修改角色信息")
    @PostMapping("/update")
    public Restful<Void> update(@RequestBody @Valid AuthRoleRelationEdit edit) {
        sysRoleBiz.update(edit);
        return Restful.SUCCESS().build();
    }

}
