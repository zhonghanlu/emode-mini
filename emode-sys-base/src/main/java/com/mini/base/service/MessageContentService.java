package com.mini.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.TMessageContent;
import com.mini.base.model.dto.MessageContentRecordDTO;
import com.mini.base.model.dto.SysLoginOptDTO;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.dto.TMessageContentDTO;
import com.mini.base.model.query.BatchSaveMessageRecord;
import com.mini.base.model.query.MessageContentRecordQuery;
import com.mini.base.model.query.SaveMessageContent;
import com.mini.base.model.query.SysLoginOptQuery;

/**
 * (t_message_record)站内信消息模板表数据库访问层
 *
 * @author : wsl
 * @date : 2024年7月26日18:07:06
 */
public interface MessageContentService {

    /**
     * 新增消息模板
     */
    void insertMessageContent(SaveMessageContent saveMessageContent);

    /**
     * 新增消息模板
     */
    void batchSaveMessageRecord(BatchSaveMessageRecord batchSaveMessageRecord);


    /**
     * 查询消息模板
     */
    IPage<MessageContentRecordDTO> queryMessageContentRecord(MessageContentRecordQuery messageContentRecordQuery);


}