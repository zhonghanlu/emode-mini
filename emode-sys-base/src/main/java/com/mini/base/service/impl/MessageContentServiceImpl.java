package com.mini.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysLoginOpt;
import com.mini.base.entity.TMessageContent;
import com.mini.base.entity.TMessageRecord;
import com.mini.base.mapper.MessageContentMapper;
import com.mini.base.mapper.MessageRecordMapper;
import com.mini.base.mapper.SysLoginOptMapper;
import com.mini.base.mapperstruct.SysLoginOptStructMapper;
import com.mini.base.model.dto.MessageContentRecordDTO;
import com.mini.base.model.dto.SysLoginOptDTO;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.query.BatchSaveMessageRecord;
import com.mini.base.model.query.MessageContentRecordQuery;
import com.mini.base.model.query.SaveMessageContent;
import com.mini.base.model.query.SysLoginOptQuery;
import com.mini.base.service.ISysLoginOptService;
import com.mini.base.service.MessageContentService;
import com.mini.common.utils.AddressByIpUtil;
import com.mini.common.utils.http.ServletUtil;
import com.mini.common.utils.webmvc.IDGenerator;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.bean.BeanUtil.*;

/**
 * @author zhl
 * @create 2024/7/18 15:31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageContentServiceImpl implements MessageContentService {

    /**
     * 引入 站内信消息模板表数据库访问层
     */
    private final MessageContentMapper messageContentMapper;

    /**
     * 引入 站内信消息记录表数据库访问层
     */
    private final MessageRecordMapper messageRecordMapper;

    @Override
    public void insertMessageContent(SaveMessageContent saveMessageContent) {
        TMessageContent tMessageContent = copyProperties(saveMessageContent, TMessageContent.class);
        tMessageContent.setCId(IDGenerator.next());
        messageContentMapper.insert(tMessageContent);
    }

    @Override
    public void batchSaveMessageRecord(BatchSaveMessageRecord batchSaveMessageRecord) {

        List<Long> cIdList = batchSaveMessageRecord.getCIdList();
        if(!cIdList.isEmpty()){

            List<TMessageRecord> tMessageRecords = new ArrayList<>();
            for (Long cid : cIdList) {
                TMessageRecord tMessageRecord = new TMessageRecord();
                tMessageRecord.setRId(IDGenerator.next());
                tMessageRecord.setRecId(batchSaveMessageRecord.getRecId());
                tMessageRecord.setCId(cid);
                tMessageRecord.setStatus("");
                tMessageRecords.add(tMessageRecord);
            }
            messageRecordMapper.batchInsert(tMessageRecords);
        }
    }


    @Override
    public IPage<MessageContentRecordDTO> queryMessageContentRecord(MessageContentRecordQuery messageContentRecordQuery) {
        IPage<MessageContentRecordDTO> messageContentRecordDTOIPage = messageRecordMapper.selectPage(messageContentRecordQuery, messageContentRecordQuery.build());
        return messageContentRecordDTOIPage;
    }
}
