package com.mini.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.base.model.dto.SysConfigDTO;
import com.mini.base.model.query.SysConfigQuery;

import java.util.Map;

/**
 * @author zhl
 * @create 2024/7/31 14:05
 */
public interface ISysConfigService {

    /**
     * 增
     */
    void insert(SysConfigDTO dto);

    /**
     * 刪
     */
    void del(long id);

    /**
     * 改
     */
    void update(SysConfigDTO dto);

    /**
     * 根据key查询
     */
    String searchByKey(String key);

    /**
     * 分页查询
     */
    IPage<SysConfigDTO> selectPage(SysConfigQuery query);

    /**
     * 所有参数 key val
     */
    Map<String, String> selectAllForMap();

}
