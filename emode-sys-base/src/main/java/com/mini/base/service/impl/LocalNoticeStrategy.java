package com.mini.base.service.impl;

import com.mini.base.entity.SysNotice;
import com.mini.base.entity.SysUserNotice;
import com.mini.base.mapper.SysNoticeMapper;
import com.mini.base.mapper.SysUserNoticeMapper;
import com.mini.base.mapperstruct.SysNoticeStructMapper;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.service.NoticeAbstract;
import com.mini.base.service.NoticeStrategy;
import com.mini.common.enums.number.Delete;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.LoginUtils;
import com.mini.common.utils.webmvc.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhl
 * @create 2024/8/6 16:18
 * 本地站内信具体实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LocalNoticeStrategy extends NoticeAbstract implements NoticeStrategy {

    private final SysNoticeMapper sysNoticeMapper;

    private final SysUserNoticeMapper sysUserNoticeMapper;

    @Override
    public void send(SysNoticeDTO sysNoticeDTO) {
        // 存入消息通知表
        long noticeId = IDGenerator.next();
        SysNotice sysNotice = SysNoticeStructMapper.INSTANCE.dto2Entity(sysNoticeDTO);

        sysNotice.setId(noticeId);
        sysNotice.setSendId(LoginUtils.getUserId());
        sysNotice.setSendTime(LocalDateTime.now());
        sysNotice.setDelFlag(Delete.NO);

        int b1 = sysNoticeMapper.insert(sysNotice);

        if (b1 <= 0) {
            throw new EModeServiceException("本地站内信，消息创建失败");
        }

        // 异步存入关联表
        List<SysUserNotice> userNoticeList = super.convert(noticeId, sysNoticeDTO);
        if (CollectionUtils.isNotEmpty(userNoticeList)) {
            // 异步执行
            executorService.execute(() -> {
                int b = sysUserNoticeMapper.batchInsert(userNoticeList);

                if (b <= 0) {
                    throw new EModeServiceException("本地站内信，消息发送失败");
                }
            });
        }
    }
}
