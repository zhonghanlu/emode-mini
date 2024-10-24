package com.mini.file.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mini.common.enums.str.Device;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhl
 */
@Data
public class SysFileVo {
    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId
    private Long id;

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

    /**
     * 存储桶名称
     */
    @Schema(description = "存储桶名称")
    private String bucketName;
}