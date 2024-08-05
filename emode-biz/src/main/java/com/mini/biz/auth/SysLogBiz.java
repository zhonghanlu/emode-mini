package com.mini.biz.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.mapperstruct.SysLoginOptStructMapper;
import com.mini.base.mapperstruct.SysUserOptStructMapper;
import com.mini.base.model.dto.SysLoginOptInfoDTO;
import com.mini.base.model.dto.SysUserOptDTO;
import com.mini.base.model.query.SysLoginOptQuery;
import com.mini.base.model.query.SysUserOptQuery;
import com.mini.base.model.vo.SysLoginOptVo;
import com.mini.base.model.vo.SysUserOptVo;
import com.mini.base.service.ISysLoginOptService;
import com.mini.base.service.ISysUserOptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhl
 * @create 2024/7/18 17:09
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysLogBiz {

    private final ISysLoginOptService sysLoginOptService;

    private final ISysUserOptService userOptService;

    /**
     * 分页
     */
    public IPage<SysLoginOptVo> page(SysLoginOptQuery query) {
        IPage<SysLoginOptInfoDTO> page = sysLoginOptService.selectPage(query);
        return page.convert(SysLoginOptStructMapper.INSTANCE::dto2vo);
    }

    /**
     * 分页
     */
    public IPage<SysUserOptVo> page(SysUserOptQuery query) {
        IPage<SysUserOptDTO> page = userOptService.selectPage(query);
        return page.convert(SysUserOptStructMapper.INSTANCE::dto2vo);
    }

}
