package com.mini.base.mapperstruct;

import com.mini.base.entity.SysUserNotice;
import com.mini.base.model.dto.SysUserNoticeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhl
 * @create 2024/8/6 16:04
 */
@Mapper
public interface SysUserNoticeStructMapper {

    SysUserNoticeStructMapper INSTANCE = Mappers.getMapper(SysUserNoticeStructMapper.class);

    /**
     * dto2entity list
     */
    List<SysUserNotice> dto2EntityList(List<SysUserNoticeDTO> userNoticeDTOList);
}
