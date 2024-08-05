package com.mini.web.controller.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.model.query.SysLoginOptQuery;
import com.mini.base.model.query.SysUserOptQuery;
import com.mini.base.model.vo.SysLoginOptVo;
import com.mini.base.model.vo.SysUserOptVo;
import com.mini.biz.auth.SysLogBiz;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhl
 * @create 2024/7/18 17:08
 */
@Tag(name = "日志记录中心", description = "日志记录中心")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-log")
public class LogController {


    private final SysLogBiz sysLogBiz;

    @Operation(summary = "登入登出日志分页")
    @GetMapping("/login-page")
    public Restful<IPage<SysLoginOptVo>> loginPage(@ParameterObject SysLoginOptQuery query) {
        return Restful.OBJECT(sysLogBiz.page(query)).build();
    }

    @Operation(summary = "用户操作日志分页")
    @GetMapping("/opt-page")
    public Restful<IPage<SysUserOptVo>> userOptPage(@ParameterObject SysUserOptQuery query) {
        return Restful.OBJECT(sysLogBiz.page(query)).build();
    }

}
