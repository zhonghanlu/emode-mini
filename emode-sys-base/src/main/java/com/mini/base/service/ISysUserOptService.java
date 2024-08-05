package com.mini.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.model.dto.SysUserOptDTO;
import com.mini.base.model.query.SysUserOptQuery;

/**
 * @author zhl
 * @create 2024/7/31 16:30
 */
public interface ISysUserOptService {

    /**
     * 新增操作日志
     */
    void insertSysUserOpt(SysUserOptDTO dto);

    /**
     * 分页查询
     */
    IPage<SysUserOptDTO> selectPage(SysUserOptQuery query);


}
