package com.mini.biz.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.mapperstruct.SysConfigStructMapper;
import com.mini.base.model.dto.SysConfigDTO;
import com.mini.base.model.edit.SysConfigEdit;
import com.mini.base.model.query.SysConfigQuery;
import com.mini.base.model.request.SysConfigRequest;
import com.mini.base.model.vo.SysConfigVo;
import com.mini.base.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author zhl
 * @create 2024/7/31 14:36
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysConfigBiz {

    private final ISysConfigService sysConfigService;

    /**
     * 新增
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysConfigRequest request) {
        SysConfigDTO sysConfigDTO = SysConfigStructMapper.INSTANCE.req2Dto(request);
        sysConfigService.insert(sysConfigDTO);
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    public void del(long id) {
        sysConfigService.del(id);
    }

    /**
     * 修改
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(SysConfigEdit edit) {
        SysConfigDTO sysConfigDTO = SysConfigStructMapper.INSTANCE.req2Edit(edit);
        sysConfigService.update(sysConfigDTO);
    }

    /**
     * 分页
     */
    public IPage<SysConfigVo> page(SysConfigQuery query) {
        IPage<SysConfigDTO> page = sysConfigService.selectPage(query);
        return page.convert(SysConfigStructMapper.INSTANCE::dto2Vo);
    }

    /**
     * 查询所有 key val
     */
    public Map<String, String> selectAllForMap() {
        return sysConfigService.selectAllForMap();
    }

}
