package com.mini.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysUserOpt;
import com.mini.base.model.dto.SysUserOptDTO;
import com.mini.base.model.query.SysUserOptQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhl
 * @create 2024/7/31 16:27
 */
@Mapper
public interface SysUserOptMapper extends BaseMapper<SysUserOpt> {

    IPage<SysUserOptDTO> selectPage(@Param("query") SysUserOptQuery query, IPage<SysUserOptDTO> dtoiPage);

}
