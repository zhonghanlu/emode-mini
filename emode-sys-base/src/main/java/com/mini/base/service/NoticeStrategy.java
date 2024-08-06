package com.mini.base.service;

import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.common.enums.str.NoticeType;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.bean.SpringBean;

import java.util.Objects;

import static com.mini.common.constant.NoticeConstant.NOTICE_STRATEGY_SUFFIX;

/**
 * @author zhl
 * @create 2024/8/6 16:15
 * 通知策略
 */
public interface NoticeStrategy {

    static void sendNotice(SysNoticeDTO sysNoticeDTO) {
        NoticeType noticeType = sysNoticeDTO.getNoticeType();

        if (Objects.isNull(noticeType)) {
            throw new EModeServiceException("当前类型不存在，请键入正确类型");
        }

        String beanName = noticeType.getStringValue() + NOTICE_STRATEGY_SUFFIX;

        NoticeStrategy noticeStrategy = SpringBean.getBean(beanName);
        noticeStrategy.send(sysNoticeDTO);
    }

    void send(SysNoticeDTO sysNoticeDTO);

}
