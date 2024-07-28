package com.mini.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.TMessageRecord;
import com.mini.base.model.dto.MessageContentRecordDTO;
import com.mini.base.model.query.MessageContentRecordQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (t_message_record)站内信消息记录表数据库访问层
 *
 * @author : wsl
 * @date : 2024年7月26日18:07:06
 */
@Mapper
public interface MessageRecordMapper extends BaseMapper<TMessageRecord> {


    Long batchInsert(List<TMessageRecord> tMessageRecords);


    /**
     * 分页查询 消息记录
     */
    IPage<MessageContentRecordDTO> selectPage(@Param("query") MessageContentRecordQuery query, IPage<MessageContentRecordDTO> page);


}