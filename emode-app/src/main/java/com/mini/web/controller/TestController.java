package com.mini.web.controller;

import com.mini.common.constant.HttpStatus;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.RedisUtils;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(description = "测试t")
    @GetMapping("/t")
    public Restful<Object> test() {
        RedisUtils.setCacheObject("Test", "TEST");
        return Restful.SUCCESS().msg("ceshi").build();
    }
}
