package com.mini.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysNotice;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.model.query.SysNoticeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhl
 * @create 2024/8/6 15:59
 */
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    IPage<SysNoticeDTO> selectPage(@Param("query") SysNoticeQuery query, IPage<SysNoticeDTO> page);

}
