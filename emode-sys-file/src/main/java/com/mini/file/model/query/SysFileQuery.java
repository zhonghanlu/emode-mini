package com.mini.file.model.query;

import com.mini.common.enums.str.Device;
import com.mini.common.utils.webmvc.PageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhl
 * @create 2024/10/23 14:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysFileQuery extends PageQuery {
    /**
     * 文件名
     */
    @Parameter(description = "文件名")
    private String fileName;

    /**
     * 文件存储路径
     */
    @Parameter(description = "文件存储路径")
    private String fileUrl;

    /**
     * 文件存储类型枚举值
     */
    @Parameter(description = "文件存储类型")
    private String fileType;

    /**
     * 文件存储端
     */
    @Parameter(description = "文件存储端")
    private Device fileDeviceBy;

    /**
     * 存储桶名称
     */
    @Parameter(description = "存储桶名称")
    private String bucketName;
}
