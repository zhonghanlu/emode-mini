package com.mini.base.mapperstruct;

import com.mini.base.entity.SysConfig;
import com.mini.base.model.dto.SysConfigDTO;
import com.mini.base.model.edit.SysConfigEdit;
import com.mini.base.model.request.SysConfigRequest;
import com.mini.base.model.vo.SysConfigVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/7/31 14:05
 */
@Mapper
public interface SysConfigStructMapper {

    SysConfigStructMapper INSTANCE = Mappers.getMapper(SysConfigStructMapper.class);

    /**
     * dto2entity
     */
    SysConfig dto2Entity(SysConfigDTO dto);

    /**
     * dto2vo
     */
    SysConfigVo dto2Vo(SysConfigDTO dto);

    /**
     * req2dto
     */
    SysConfigDTO req2Dto(SysConfigRequest req);

    /**
     * edit2dto
     */
    SysConfigDTO req2Edit(SysConfigEdit edit);
}
