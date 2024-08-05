package com.mini.base.mapperstruct;

import com.mini.base.entity.SysUserOpt;
import com.mini.base.model.dto.SysUserOptDTO;
import com.mini.base.model.vo.SysUserOptVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/7/31 16:28
 */
@Mapper
public interface SysUserOptStructMapper {

    SysUserOptStructMapper INSTANCE = Mappers.getMapper(SysUserOptStructMapper.class);

    /**
     * dto2entity
     */
    SysUserOpt dto2Entity(SysUserOptDTO dto);

    /**
     * dto2vo
     */
    SysUserOptVo dto2vo(SysUserOptDTO dto);
}
