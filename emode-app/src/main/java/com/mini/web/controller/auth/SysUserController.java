package com.mini.web.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.edit.AuthUserEdit;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.model.request.AuthUserRequest;
import com.mini.auth.model.request.AuthUserRoleRequest;
import com.mini.auth.model.vo.AuthUserDetailRouterVo;
import com.mini.auth.model.vo.AuthUserDetailVo;
import com.mini.auth.model.vo.AuthUserVo;
import com.mini.biz.auth.SysUserBiz;
import com.mini.common.annotation.OptLog;
import com.mini.common.enums.str.UserQueryType;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhl
 * @create 2024/5/14 16:37
 */
@Tag(name = "用户相关信息中心", description = "用户相关信息中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-user")
public class SysUserController {

    private final SysUserBiz sysUserBiz;

    @OptLog
    @Operation(summary = "用户新增")
    @PostMapping("/add")
    public Restful<Void> add(@RequestBody @Valid AuthUserRequest request) {
        sysUserBiz.add(request);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "用户修改")
    @PostMapping("/update")
    public Restful<Void> update(@RequestBody @Valid AuthUserEdit edit) {
        sysUserBiz.update(edit);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "用户分页")
    @GetMapping("/page")
    public Restful<IPage<AuthUserVo>> page(@ParameterObject AuthUserQuery query) {
        return Restful.OBJECT(sysUserBiz.page(query)).build();
    }

    @OptLog
    @Operation(summary = "用户角色关联")
    @PostMapping("/user-role-relation")
    public Restful<Void> relation(@RequestBody @Valid AuthUserRoleRequest request) {
        sysUserBiz.relationUserRole(request);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "用户详情-【废弃，暂不使用】")
    @GetMapping("/user-detail-type/{id}/{type}")
    public Restful<AuthUserDetailVo> getUserRolePermissionById(@PathVariable("id") long id,
                                                               @PathVariable("type") UserQueryType type) {
        return Restful.OBJECT(sysUserBiz.getUserRolePermissionById(id, type)).build();
    }

    @OptLog
    @Operation(summary = "获取用户基本信息")
    @GetMapping("/user-detail-base")
    public Restful<AuthUserDetailVo> getUserInfoBase() {
        return Restful.OBJECT(sysUserBiz.getUserInfoBase()).build();
    }

    @OptLog
    @Operation(summary = "获取用户基本路由信息")
    @GetMapping("/user-detail-router")
    public Restful<List<AuthUserDetailRouterVo>> getUserInfoRouter() {
        return Restful.OBJECT(sysUserBiz.getUserInfoRouter()).build();
    }


}


