package com.mini.base.service.impl;

import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.service.AbstractNotice;
import com.mini.base.service.NoticeStrategy;
import org.springframework.stereotype.Service;

/**
 * @author zhl
 * @create 2024/8/6 16:16
 * 短信通知策略实现
 */
@Service
public class MsgNoticeStrategy extends AbstractNotice implements NoticeStrategy {
    @Override
    public void send(SysNoticeDTO sysNoticeDTO) {

    }
}
