package com.mini.web.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.edit.AuthPermissionEdit;
import com.mini.auth.model.query.AuthPermissionQuery;
import com.mini.auth.model.request.AuthPermissionRequest;
import com.mini.auth.model.vo.AuthPermissionVo;
import com.mini.biz.auth.SysPermissionBiz;
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
 * @create 2024/7/4 16:51
 */
@Tag(name = "权限相关信息中心", description = "权限相关信息中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-permission")
public class SysPermissionController {

    private final SysPermissionBiz sysPermissionBiz;

    @Operation(summary = "权限分页")
    @GetMapping("/page")
    public Restful<IPage<AuthPermissionVo>> rolePage(@ParameterObject AuthPermissionQuery query) {
        return Restful.OBJECT(sysPermissionBiz.page(query)).build();
    }

    @Operation(summary = "新增权限信息")
    @PostMapping("/add")
    public Restful<Void> insert(@RequestBody @Valid AuthPermissionRequest request) {
        sysPermissionBiz.insert(request);
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "删除权限信息")
    @PostMapping("/del")
    public Restful<Void> del(long id) {
        sysPermissionBiz.del(id);
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "修改权限信息")
    @PostMapping("/update")
    public Restful<Void> update(@RequestBody @Valid AuthPermissionEdit edit) {
        sysPermissionBiz.update(edit);
        return Restful.SUCCESS().build();
    }


}
