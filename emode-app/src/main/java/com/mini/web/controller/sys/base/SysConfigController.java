package com.mini.web.controller.sys.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.model.edit.SysConfigEdit;
import com.mini.base.model.query.SysConfigQuery;
import com.mini.base.model.request.SysConfigRequest;
import com.mini.base.model.vo.SysConfigVo;
import com.mini.biz.base.SysConfigBiz;
import com.mini.common.annotation.OptLog;
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
 * @create 2024/7/31 14:45
 */
@Tag(name = "参数配置", description = "参数配置")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-config")
public class SysConfigController {

    private final SysConfigBiz sysConfigBiz;

    @OptLog
    @Operation(summary = "参数新增")
    @PostMapping("/add")
    public Restful<Void> add(@RequestBody @Valid SysConfigRequest request) {
        sysConfigBiz.insert(request);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "删除参数信息")
    @PostMapping("/del")
    public Restful<Void> del(Long id) {
        sysConfigBiz.del(id);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "参数修改")
    @PostMapping("/update")
    public Restful<Void> update(@RequestBody @Valid SysConfigEdit edit) {
        sysConfigBiz.update(edit);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "参数列表分页")
    @GetMapping("/page")
    public Restful<IPage<SysConfigVo>> page(@ParameterObject SysConfigQuery query) {
        return Restful.OBJECT(sysConfigBiz.page(query)).build();
    }
}
