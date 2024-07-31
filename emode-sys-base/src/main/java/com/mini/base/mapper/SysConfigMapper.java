package com.mini.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysConfig;
import com.mini.base.model.dto.SysConfigDTO;
import com.mini.base.model.query.SysConfigQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhl
 * @create 2024/7/31 14:04
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    IPage<SysConfigDTO> selectPage(@Param("query") SysConfigQuery query, IPage<SysConfigDTO> dtoiPage);

}
