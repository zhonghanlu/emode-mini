package com.mini.base.mapperstruct;

import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.vo.SysLoginOptVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/7/18 15:32
 */
@Mapper
public interface SysLoginOptStructMapper {

    SysLoginOptStructMapper INSTANCE = Mappers.getMapper(SysLoginOptStructMapper.class);


    /**
     * dtoInfo2vo
     */
    SysLoginOptVo dto2vo(SysLoginOptInfoDTO dto);
}
