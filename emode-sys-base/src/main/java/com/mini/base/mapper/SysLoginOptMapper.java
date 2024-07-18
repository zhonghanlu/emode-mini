package com.mini.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysLoginOpt;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.query.SysLoginOptQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ;(sys_login_opt)表数据库访问层
 *
 * @author : zhl
 * @date : 2024-7-18
 */
@Mapper
public interface SysLoginOptMapper extends BaseMapper<SysLoginOpt> {

    IPage<SysLoginOptInfoDTO> selectPage(@Param("query") SysLoginOptQuery query, IPage<SysLoginOptInfoDTO> page);

}