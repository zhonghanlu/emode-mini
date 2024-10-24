package com.mini.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.file.model.dto.SysFileDTO;
import com.mini.file.model.query.SysFileQuery;

/**
 * @author zhl
 * @create 2024/5/16 11:09
 */
public interface ISysFileService {

    /**
     * 新增
     */
    long insert(SysFileDTO dto);

    /**
     * 新增或修改
     */
    long saveOrUpdate(SysFileDTO dto);

    /**
     * 删除
     */
    void del(long id);

    /**
     * 根据id查询单对象
     */
    SysFileDTO getById(long id);

    /**
     * 分页
     */
    IPage<SysFileDTO> page(SysFileQuery query);
}
