package com.mini.file.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.str.Device;
import com.mini.common.model.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhl
 */
@Data
@Schema(description = "文件存储表")
@TableName("sys_file")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysFile extends CommonEntity implements Serializable {
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
     * 文件存储类型枚举值
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