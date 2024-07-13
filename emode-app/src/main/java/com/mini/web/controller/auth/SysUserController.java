package com.mini.web.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.model.edit.AuthUserEdit;
import com.mini.auth.model.query.AuthUserQuery;
import com.mini.auth.model.request.AuthUserRequest;
import com.mini.auth.model.vo.AuthUserVo;
import com.mini.biz.auth.SysUserBiz;
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
 * @create 2024/5/14 16:37
 */
@Tag(name = "用户相关信息中心", description = "用户相关信息中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-user")
public class SysUserController {

    private final SysUserBiz sysUserBiz;

    @Operation(summary = "用户新增")
    @PostMapping("/add")
    public Restful<Void> add(@RequestBody @Valid AuthUserRequest request) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.request2Dto(request);
        sysUserBiz.add(authUserDTO);
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "用户修改")
    @PostMapping("/update")
    public Restful<Void> update(@RequestBody @Valid AuthUserEdit edit) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.edit2Dto(edit);
        sysUserBiz.update(authUserDTO);
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "用户分页")
    @GetMapping("/page")
    public Restful<IPage<AuthUserVo>> page(@ParameterObject AuthUserQuery query) {
        return Restful.OBJECT(sysUserBiz.page(query)).build();
    }

}


