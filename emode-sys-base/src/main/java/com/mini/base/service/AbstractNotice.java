package com.mini.base.service;

import com.mini.base.model.request.SysSendRequest;
import com.mini.common.enums.number.Delete;
import com.mini.common.enums.str.YesOrNo;

import com.mini.base.entity.SysUserNotice;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.thread.NamedThreadFactory;
import com.mini.common.utils.webmvc.IDGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.mini.common.constant.NoticeConstant.THREAD_NOTICE_PREFIX;

/**
 * @author zhl
 * @create 2024/8/6 16:33
 */
@Component
public abstract class AbstractNotice {

    // 暂且写死线程池
    public final ExecutorService executorService = new ThreadPoolExecutor(5,
            10,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new NamedThreadFactory(THREAD_NOTICE_PREFIX));


    public List<SysUserNotice> convert(long noticeId, SysNoticeDTO sysNoticeDTO) {
        List<SysUserNotice> sysUserNoticeList = new ArrayList<>();

        List<SysSendRequest> sysSendRequestList = sysNoticeDTO.getSysSendRequestList();

        if (CollectionUtils.isEmpty(sysSendRequestList)) {
            throw new EModeServiceException("当前发送对象不可为空");
        }

        sysSendRequestList.forEach(info -> {
            SysUserNotice sysUserNotice = new SysUserNotice();
            sysUserNotice.setId(IDGenerator.next());
            sysUserNotice.setNoticeId(noticeId);
            sysUserNotice.setUserId(info.getUserId());
            sysUserNotice.setPhone(info.getPhone());
            sysUserNotice.setEmail(info.getEmail());
            sysUserNotice.setReadStatus(YesOrNo.NO);
            sysUserNotice.setDelFlag(Delete.NO);
            sysUserNoticeList.add(sysUserNotice);
        });
        return sysUserNoticeList;
    }

}
