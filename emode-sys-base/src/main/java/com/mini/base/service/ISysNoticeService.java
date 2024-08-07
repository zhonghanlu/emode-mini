package com.mini.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.model.query.SysNoticeQuery;

/**
 * @author zhl
 * @create 2024/8/6 16:19
 */
public interface ISysNoticeService {

    /**
     * 查询分页
     */
    IPage<SysNoticeDTO> selectPage(SysNoticeQuery query);

    /**
     * 根据当前人信息，查询最近广播通知
     */
    SysNoticeDTO selectLastBroadcastNotice();

}
