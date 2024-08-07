package com.mini.biz.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.mapperstruct.SysNoticeStructMapper;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.model.query.SysNoticeQuery;
import com.mini.base.model.request.SysNoticeRequest;
import com.mini.base.model.vo.SysNoticeVo;
import com.mini.base.service.ISysNoticeService;
import com.mini.base.service.NoticeStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhl
 * @create 2024/8/7 15:13
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysNoticeBiz {

    private final ISysNoticeService sysNoticeService;

    /**
     * 查询广播站内信，最新一条
     */
    public SysNoticeVo selectLastBroadcastNotice() {
        return SysNoticeStructMapper.INSTANCE.dto2vo(sysNoticeService.selectLastBroadcastNotice());
    }

    /**
     * 分页
     */
    public IPage<SysNoticeVo> page(SysNoticeQuery query) {
        IPage<SysNoticeDTO> page = sysNoticeService.selectPage(query);
        return page.convert(SysNoticeStructMapper.INSTANCE::dto2vo);
    }

    /**
     * 发送消息
     */
    @Transactional(rollbackFor = Exception.class)
    public void send(SysNoticeRequest request) {
        NoticeStrategy.sendNotice(SysNoticeStructMapper.INSTANCE.request2Dto(request));
    }


}
