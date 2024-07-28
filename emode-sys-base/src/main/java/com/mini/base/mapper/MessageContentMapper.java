package com.mini.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysLoginOpt;
import com.mini.base.entity.TMessageContent;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.query.SysLoginOptQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * (t_message_record)站内信消息模板表数据库访问层
 *
 * @author : wsl
 * @date : 2024年7月26日18:07:06
 */
@Mapper
public interface MessageContentMapper extends BaseMapper<TMessageContent> {



}