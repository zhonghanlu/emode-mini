package com.mini.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mini.base.entity.SysUserNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhl
 * @create 2024/8/6 16:00
 */
@Mapper
public interface SysUserNoticeMapper extends BaseMapper<SysUserNotice> {

    int batchInsert(@Param("userNoticeList") List<SysUserNotice> userNoticeList);

}
