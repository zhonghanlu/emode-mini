package com.mini.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysMessageContent;
import com.mini.base.entity.SysMessageRecord;
import com.mini.base.mapper.SysMessageContentMapper;
import com.mini.base.mapper.SysMessageRecordMapper;
import com.mini.base.model.dto.MessageContentRecordDTO;
import com.mini.base.model.query.BatchSaveMessageRecord;
import com.mini.base.model.query.SaveMessageContent;
import com.mini.base.model.query.SysMessageContentRecordQuery;
import com.mini.base.service.ISysMessageContentService;
import com.mini.common.utils.webmvc.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.bean.BeanUtil.copyProperties;

/**
 * @author zhl
 * @create 2024/7/18 15:31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysMessageContentServiceImpl implements ISysMessageContentService {

    /**
     * 引入 站内信消息模板表数据库访问层
     */
    private final SysMessageContentMapper messageContentMapper;

    /**
     * 引入 站内信消息记录表数据库访问层
     */
    private final SysMessageRecordMapper messageRecordMapper;

    @Override
    public void insertMessageContent(SaveMessageContent saveMessageContent) {
        SysMessageContent tMessageContent = copyProperties(saveMessageContent, SysMessageContent.class);
        tMessageContent.setCId(IDGenerator.next());
        messageContentMapper.insert(tMessageContent);
    }

    @Override
    public void batchSaveMessageRecord(BatchSaveMessageRecord batchSaveMessageRecord) {

        List<Long> cIdList = batchSaveMessageRecord.getCIdList();
        if(!cIdList.isEmpty()){

            List<SysMessageRecord> tMessageRecords = new ArrayList<>();
            for (Long cid : cIdList) {
                SysMessageRecord tMessageRecord = new SysMessageRecord();
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
    public IPage<MessageContentRecordDTO> queryMessageContentRecord(SysMessageContentRecordQuery messageContentRecordQuery) {
        IPage<MessageContentRecordDTO> messageContentRecordDTOIPage = messageRecordMapper.selectPage(messageContentRecordQuery, messageContentRecordQuery.build());
        return messageContentRecordDTOIPage;
    }
}
