package com.mini.base.mapperstruct;

import com.mini.base.entity.SysNotice;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.model.request.SysNoticeRequest;
import com.mini.base.model.vo.SysNoticeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhl
 * @create 2024/7/18 15:32
 */
@Mapper
public interface SysNoticeStructMapper {

    SysNoticeStructMapper INSTANCE = Mappers.getMapper(SysNoticeStructMapper.class);

    /**
     * dto2entity
     */
    SysNotice dto2Entity(SysNoticeDTO sysNoticeDTO);

    /**
     * entity2dto
     */
    SysNoticeDTO entity2Dto(SysNotice sysNotice);

    /**
     * req2dto
     */
    SysNoticeDTO request2Dto(SysNoticeRequest request);

    /**
     * dto2vo
     */
    SysNoticeVo dto2vo(SysNoticeDTO dto);

}
