package com.mini.base.mapperstruct;

import com.mini.base.entity.SysMessageContent;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.query.SaveMessageContent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/7/18 15:32
 */
@Mapper
public interface MessageContentMapper {

    MessageContentMapper INSTANCE = Mappers.getMapper(MessageContentMapper.class);


    /**
     * dtoInfo2vo
     */
    SysMessageContent dto2vo(SysLoginOptInfoDTO dto);

    /**
     * saveInfo2vo
     */
    SaveMessageContent save2vo(SysMessageContent saveMessageContent);
}
