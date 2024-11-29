package com.mini.base.service.impl;

import com.mini.base.entity.SysNotice;
import com.mini.base.entity.SysUserNotice;
import com.mini.base.mapper.SysNoticeMapper;
import com.mini.base.mapper.SysUserNoticeMapper;
import com.mini.base.mapperstruct.SysNoticeStructMapper;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.service.AbstractNotice;
import com.mini.base.service.NoticeStrategy;
import com.mini.common.constant.ErrorCodeConstant;
import com.mini.common.enums.number.Delete;
import com.mini.common.enums.str.MessageStatus;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.LoginUtils;
import com.mini.common.utils.webmvc.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
public class LocalNoticeStrategy extends AbstractNotice implements NoticeStrategy {

    private final SysNoticeMapper sysNoticeMapper;

    private final SysUserNoticeMapper sysUserNoticeMapper;

    private final PlatformTransactionManager transactionManager;

    @Override
    public void send(SysNoticeDTO sysNoticeDTO) {
        // 存入消息通知表
        long noticeId = IDGenerator.next();
        SysNotice sysNotice = SysNoticeStructMapper.INSTANCE.dto2Entity(sysNoticeDTO);

        sysNotice.setId(noticeId);
        sysNotice.setSendId(LoginUtils.getUserId());
        sysNotice.setSendTime(LocalDateTime.now());
        sysNotice.setDelFlag(Delete.NO);

        // 广播发送
        if (MessageStatus.BROADCAST.equals(sysNoticeDTO.getMessageStatus())) {
            int b1 = sysNoticeMapper.insert(sysNotice);

            if (b1 <= 0) {
                throw new EModeServiceException(ErrorCodeConstant.DB_ERROR, "本地站内信，消息创建失败");
            }
            return;
        }

        // 异步存入关联表
        List<SysUserNotice> userNoticeList = super.convert(noticeId, sysNoticeDTO);

        if (CollectionUtils.isEmpty(userNoticeList) ||
                (1 == userNoticeList.size() && 0 == userNoticeList.get(0).getUserId())) {
            throw new EModeServiceException(ErrorCodeConstant.BUSINESS_ERROR, "非广播发送，请键入发送对象");
        }

        // 异步执行
        executorService.execute(() -> {
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                // 消息插入
                sysNoticeMapper.insert(sysNotice);

                int b = sysUserNoticeMapper.batchInsert(userNoticeList);

                if (b <= 0) {
                    throw new EModeServiceException(ErrorCodeConstant.DB_ERROR, "本地站内信，消息发送失败");
                }

                transactionManager.commit(status);
            } catch (EModeServiceException e) {
                transactionManager.rollback(status);
                log.error("异步消息发送失败", e);
            }
        });
    }
}
