package com.mini.web.controller.mail;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.auth.model.vo.AuthRoleVo;
import com.mini.base.model.dto.MessageContentRecordDTO;
import com.mini.base.model.query.BatchSaveMessageRecord;
import com.mini.base.model.query.MessageContentRecordQuery;
import com.mini.base.model.query.SaveMessageContent;
import com.mini.base.service.MessageContentService;
import com.mini.biz.auth.SysRoleBiz;
import com.mini.common.utils.webmvc.Restful;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    /**
     * 消息模板服务层
     */
    private final MessageContentService messageContentService;


    @Operation(summary = "站内信发送")
    @PostMapping(path = "/mailSend")
    public Restful<Void> mailSend(@RequestBody @Valid BatchSaveMessageRecord batchSaveMessageRecord) {

        messageContentService.batchSaveMessageRecord(batchSaveMessageRecord);
        return Restful.SUCCESS().build();
    }


    @Operation(summary = "站内信接受")
    @PostMapping(path = "/mailAccept")
    public Restful<IPage<MessageContentRecordDTO>> mailAccept(@RequestBody @Valid MessageContentRecordQuery messageContentRecordQuery) {

        IPage<MessageContentRecordDTO> messageContentRecordDTOIPage = messageContentService.queryMessageContentRecord(messageContentRecordQuery);
        return Restful.OBJECT(messageContentRecordDTOIPage).build();
    }

    /**
     * 消息模板录入
     *
     * @return
     */
    @Operation(summary = "消息模板录入")
    @PostMapping(path = "/messageTemplateInput")
    public Restful<Void> messageTemplateInput(@RequestBody @Valid SaveMessageContent saveMessageContent) {
        messageContentService.insertMessageContent(saveMessageContent);
        return Restful.SUCCESS().build();
    }


}

