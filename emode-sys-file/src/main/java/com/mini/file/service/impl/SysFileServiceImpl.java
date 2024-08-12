package com.mini.file.service.impl;

import com.mini.common.enums.number.Delete;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.mybatis.CommonMybatisUtil;
import com.mini.common.utils.webmvc.IDGenerator;
import com.mini.file.entity.SysFile;
import com.mini.file.mapper.SysFileMapper;
import com.mini.file.mapperstruct.SysFileStructMapper;
import com.mini.file.model.dto.SysFileDTO;
import com.mini.file.service.ISysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhl
 * @create 2024/5/16 11:09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysFileServiceImpl implements ISysFileService {

    private final SysFileMapper sysFileMapper;

    @Override
    public long insert(SysFileDTO dto) {
        SysFile sysFile = SysFileStructMapper.INSTANCE.dto2Entity(dto);

        long next = IDGenerator.next();
        sysFile.setId(next);

        int b = sysFileMapper.insert(sysFile);
        if (b <= 0) {
            throw new EModeServiceException("新增失败");
        }
        return next;
    }

    @Override
    public void del(long id) {
        if (id <= 0) {
            throw new EModeServiceException("删除主键id有误，id:" + id);
        }

        SysFile sysFile = CommonMybatisUtil.getById(id, sysFileMapper);

        if (Objects.isNull(sysFile)) {
            throw new EModeServiceException("待删除数据不存在，id:" + id);
        }

        sysFile.setDelFlag(Delete.YES);

        int b = sysFileMapper.updateById(sysFile);

        if (b <= 0) {
            throw new EModeServiceException("删除异常，id:" + id);
        }

    }
}
