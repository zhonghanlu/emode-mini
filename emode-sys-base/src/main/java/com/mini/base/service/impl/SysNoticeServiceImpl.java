package com.mini.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mini.base.entity.SysNotice;
import com.mini.base.mapper.SysNoticeMapper;
import com.mini.base.mapperstruct.SysNoticeStructMapper;
import com.mini.base.model.dto.SysNoticeDTO;
import com.mini.base.model.query.SysNoticeQuery;
import com.mini.base.service.ISysNoticeService;
import com.mini.common.constant.LastSql;
import com.mini.common.enums.number.Delete;
import com.mini.common.enums.str.MessageStatus;
import com.mini.common.enums.str.NoticeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhl
 * @create 2024/8/6 16:19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysNoticeServiceImpl implements ISysNoticeService {

    private final SysNoticeMapper sysNoticeMapper;

    @Override
    public IPage<SysNoticeDTO> selectPage(SysNoticeQuery query) {
        return sysNoticeMapper.selectPage(query, query.build());
    }

    @Override
    public SysNoticeDTO selectLastBroadcastNotice() {
        LambdaQueryWrapper<SysNotice> wrapper = Wrappers.lambdaQuery(SysNotice.class);

        wrapper.eq(SysNotice::getNoticeType, NoticeType.LOCAL)
                .eq(SysNotice::getMessageStatus, MessageStatus.BROADCAST)
                .eq(SysNotice::getDelFlag, Delete.NO)
                .orderByDesc(SysNotice::getSendTime)
                .last(LastSql.LIMIT_ONE);
        return SysNoticeStructMapper.INSTANCE.entity2Dto(sysNoticeMapper.selectOne(wrapper));
    }
}
