package com.mini.file.service;

import com.mini.file.model.dto.SysFileDTO;

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
     * 删除
     */
    void del(long id);

}
