package com.mini.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.entity.SysUserOpt;
import com.mini.base.mapper.SysUserOptMapper;
import com.mini.base.mapperstruct.SysUserOptStructMapper;
import com.mini.base.model.dto.SysUserOptDTO;
import com.mini.base.model.query.SysConfigQuery;
import com.mini.base.model.query.SysUserOptQuery;
import com.mini.base.service.ISysUserOptService;
import com.mini.common.utils.webmvc.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zhl
 * @create 2024/7/31 16:32
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserOptServiceImpl implements ISysUserOptService {

    private final SysUserOptMapper sysUserOptMapper;

    @Async
    @Override
    public void insertSysUserOpt(SysUserOptDTO dto) {
        dto.setId(IDGenerator.next());
        SysUserOpt sysUserOpt = SysUserOptStructMapper.INSTANCE.dto2Entity(dto);
        int b = sysUserOptMapper.insert(sysUserOpt);

        if (b <= 0) {
            log.error("操作日志记录失败");
        }
    }

    @Override
    public IPage<SysUserOptDTO> selectPage(SysUserOptQuery query) {
        return sysUserOptMapper.selectPage(query,query.build());
    }
}
