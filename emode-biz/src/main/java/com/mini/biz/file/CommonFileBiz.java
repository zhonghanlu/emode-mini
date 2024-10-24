package com.mini.biz.file;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.common.constant.FileConstant;
import com.mini.common.enums.str.Device;
import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.DeviceUtil;
import com.mini.common.utils.file.FileUtil;
import com.mini.common.utils.minio.MinIoUtil;
import com.mini.file.mapperstruct.SysFileStructMapper;
import com.mini.file.model.dto.SysFileDTO;
import com.mini.file.model.query.SysFileQuery;
import com.mini.file.model.vo.SysFileVo;
import com.mini.file.service.ISysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Map;

/**
 * @author zhl
 * @create 2024/5/16 14:14
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CommonFileBiz {

    private final MinIoUtil minIoUtil;

    private final ISysFileService sysFileService;

    @Value("${minio.defaultBucketName}")
    private String minioDefaultBucketName;

    /**
     * 多文件上传
     */
    public void batchFileUpload(MultipartFile[] files) {
        if (files.length > 10) {
            throw new EModeServiceException("批量文件限制10");
        }
        Arrays.asList(files).parallelStream().forEach(this::singleFileUpload);
    }

    /**
     * 单文件上传
     */
    @Transactional(rollbackFor = Exception.class)
    public long singleFileUpload(MultipartFile file) {
        // 1.获取文件基础信息
        Map<String, String> fileBaseSource = FileUtil.getFileBaseSource(file);
        // 2.获取当前设备终端
        Device device = DeviceUtil.getDevice();
        // 3.上传文件至Minio
        String bucketUrl = minIoUtil.storageDefaultFileWithReturnUrl(file);
        // 4.入库
        SysFileDTO sysFileDTO = new SysFileDTO();
        sysFileDTO.setFileName(fileBaseSource.get(FileConstant.FILE_NAME));
        sysFileDTO.setFileUrl(bucketUrl);
        sysFileDTO.setFileType(fileBaseSource.get(FileConstant.FILE_TYPE));
        sysFileDTO.setFileDeviceBy(device);
        sysFileDTO.setBucketName(minioDefaultBucketName);
        return sysFileService.saveOrUpdate(sysFileDTO);
    }

    /**
     * 库内文件检索
     */
    public IPage<SysFileVo> page(SysFileQuery query) {
        IPage<SysFileDTO> page = sysFileService.page(query);
        return page.convert(SysFileStructMapper.INSTANCE::dto2Vo);
    }

    /**
     * 单文件删除
     */
    @Transactional(rollbackFor = Exception.class)
    public void del(long id) {
        // 取出当前对象
        SysFileDTO sysFileDTO = sysFileService.getById(id);
        // 执行库内删除
        sysFileService.del(id);
        // 执行minio删除
        minIoUtil.deleteFile(sysFileDTO.getBucketName(), sysFileDTO.getFileUrl());
    }
}
