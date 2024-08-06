package com.mini.web.controller.base;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangSiLiang
 * @Date 2024/7/18 16:41
 **/
@Tag(name = "站内信接口开发")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {


//    @OptLog
//    @Operation(summary = "站内信发送")
//    @PostMapping(path = "/mailSend")
//    public Restful<Void> mailSend(@RequestBody @Valid ) {
//        return Restful.SUCCESS().build();
//    }

}

