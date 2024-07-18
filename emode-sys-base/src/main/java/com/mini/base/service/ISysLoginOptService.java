package com.mini.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.model.dto.SysLoginOptDTO;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.query.SysLoginOptQuery;

/**
 * ;(sys_login_opt)表服务接口
 *
 * @author : zhl
 * @date : 2024-7-18
 */
public interface ISysLoginOptService {

    /**
     * 异步添加
     */
    void addLoginOptInfo(SysLoginOptDTO dto);

    /**
     * 查分页
     */
    IPage<SysLoginOptInfoDTO> selectPage(SysLoginOptQuery query);

}