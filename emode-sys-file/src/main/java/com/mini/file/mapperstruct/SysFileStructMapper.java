package com.mini.file.mapperstruct;

import com.mini.file.entity.SysFile;
import com.mini.file.model.dto.SysFileDTO;
import com.mini.file.model.vo.SysFileVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/5/16 11:04
 */
@Mapper
public interface SysFileStructMapper {

    SysFileStructMapper INSTANCE = Mappers.getMapper(SysFileStructMapper.class);

    /**
     * dto2entity
     */
    SysFile dto2Entity(SysFileDTO dto);


    /**
     * entity2Dto
     */
    SysFileDTO entity2Dto(SysFile entity);

    /**
     * dto2Vo
     */
    SysFileVo dto2Vo(SysFileDTO dto);

}
