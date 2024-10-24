package com.mini.file.model.dto;

import com.mini.common.enums.str.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysFileDTO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件存储路径
     */
    private String fileUrl;

    /**
     * 文件存储类型
     */
    private String fileType;

    /**
     * 文件存储端
     */
    private Device fileDeviceBy;

    /**
     * 存储桶名称
     */
    private String bucketName;
}