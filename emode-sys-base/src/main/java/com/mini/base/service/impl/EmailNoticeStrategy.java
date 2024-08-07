package com.mini.base.service.impl;

import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.service.AbstractNotice;
import com.mini.base.service.NoticeStrategy;
import org.springframework.stereotype.Service;

/**
 * @author zhl
 * @create 2024/8/6 16:17
 * 邮箱策略具体实现
 */
@Service
public class EmailNoticeStrategy extends AbstractNotice implements NoticeStrategy {
    @Override
    public void send(SysNoticeDTO sysNoticeDTO) {

    }
}
