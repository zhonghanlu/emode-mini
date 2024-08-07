package com.mini.web.controller.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.model.query.SysNoticeQuery;
import com.mini.base.model.request.SysNoticeRequest;
import com.mini.base.model.vo.SysNoticeVo;
import com.mini.biz.base.SysNoticeBiz;
import com.mini.common.annotation.OptLog;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author zhl
 * @Date 2024年8月7日15:23:17
 **/
@Tag(name = "站内信接口开发")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys-notice")
public class SysNoticeController {

    private final SysNoticeBiz sysNoticeBiz;

    @OptLog
    @Operation(summary = "站内信发送")
    @PostMapping(path = "/send")
    public Restful<Void> send(@RequestBody @Valid SysNoticeRequest request) {
        sysNoticeBiz.send(request);
        return Restful.SUCCESS().build();
    }

    @OptLog
    @Operation(summary = "分页查询")
    @GetMapping(path = "/page")
    public Restful<IPage<SysNoticeVo>> page(@ParameterObject SysNoticeQuery query) {
        return Restful.OBJECT(sysNoticeBiz.page(query)).build();
    }

    @OptLog
    @Operation(summary = "获取最新一条广播通知")
    @GetMapping(path = "/last-broad")
    public Restful<SysNoticeVo> broad() {
        return Restful.OBJECT(sysNoticeBiz.selectLastBroadcastNotice()).build();
    }

}

