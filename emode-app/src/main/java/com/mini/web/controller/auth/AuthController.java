package com.mini.web.controller.auth;

import com.mini.auth.model.dto.AuthUserDTO;
import com.mini.auth.mapperstruct.AuthUserStructMapper;
import com.mini.auth.model.edit.AuthUserEdit;
import com.mini.auth.model.request.AuthUserRequest;
import com.mini.biz.auth.AuthBiz;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhl
 * @create 2024/5/14 16:37
 */
@Tag(name = "鉴权中心", description = "鉴权中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthBiz authBiz;

    @Operation(summary = "新增")
    @PostMapping("/add")
    public Restful<?> add(@RequestBody @Validated AuthUserRequest request) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.request2dto(request);
        authBiz.add(authUserDTO);
        return Restful.SUCCESS().build();
    }

    @Operation(summary = "修改")
    @PostMapping("/update")
    public Restful<?> update(@RequestBody @Validated AuthUserEdit edit) {
        AuthUserDTO authUserDTO = AuthUserStructMapper.INSTANCE.request2dto(edit);
        authBiz.update(authUserDTO);
        return Restful.SUCCESS().build();
    }


}


