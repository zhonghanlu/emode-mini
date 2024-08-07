package com.mini.file.model.request;

import com.mini.common.enums.str.Device;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhl
 */
@Data
@Schema(description = "文件存储表")
public class SysFileRequest {
    /**
     * 文件名
     */
    @Schema(description = "文件名")
    private String fileName;

    /**
     * 文件存储路径
     */
    @Schema(description = "文件存储路径")
    private String fileUrl;

    /**
     * 文件存储类型
     */
    @Schema(description = "文件存储类型")
    private String fileType;

    /**
     * 文件存储端
     */
    @Schema(description = "文件存储端")
    private Device fileDeviceBy;
}