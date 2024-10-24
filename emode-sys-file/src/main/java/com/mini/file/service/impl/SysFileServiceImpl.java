package com.mini.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mini.common.constant.LastSql;
import com.mini.common.enums.number.Delete;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.mybatis.CommonMybatisUtil;
import com.mini.common.utils.webmvc.IDGenerator;
import com.mini.file.entity.SysFile;
import com.mini.file.mapper.SysFileMapper;
import com.mini.file.mapperstruct.SysFileStructMapper;
import com.mini.file.model.dto.SysFileDTO;
import com.mini.file.model.query.SysFileQuery;
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
    public long saveOrUpdate(SysFileDTO dto) {
        LambdaQueryWrapper<SysFile> wrapper = Wrappers.lambdaQuery(SysFile.class);
        wrapper.eq(SysFile::getFileName, dto.getFileName())
                .eq(SysFile::getBucketName,dto.getBucketName())
                .eq(SysFile::getDelFlag, Delete.NO)
                .last(LastSql.LIMIT_ONE);
        SysFile sysFile = sysFileMapper.selectOne(wrapper);

        // 不存在新增
        if (Objects.isNull(sysFile)) {
            return this.insert(dto);
        }

        // 存在修改
        sysFileMapper.updateById(sysFile);
        return sysFile.getId();
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

    @Override
    public SysFileDTO getById(long id) {
        SysFile sysFile = CommonMybatisUtil.getById(id, sysFileMapper);
        return SysFileStructMapper.INSTANCE.entity2Dto(sysFile);
    }

    @Override
    public IPage<SysFileDTO> page(SysFileQuery query) {
        LambdaQueryWrapper<SysFile> wrapper = Wrappers.lambdaQuery(SysFile.class);
        wrapper.eq(SysFile::getDelFlag, Delete.NO);
        Page<SysFile> sysFilePage = sysFileMapper.selectPage(query.build(), wrapper);
        return sysFilePage.convert(SysFileStructMapper.INSTANCE::entity2Dto);
    }
}
