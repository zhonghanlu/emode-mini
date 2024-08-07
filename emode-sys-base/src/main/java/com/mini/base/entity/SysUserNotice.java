package com.mini.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mini.common.enums.str.YesOrNo;
import com.mini.common.model.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 消息用户关联表;
 *
 * @author : zhl
 * @date : 2024-8-6
 */
@Data
@Schema(description = "消息用户关联表")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_notice")
public class SysUserNotice extends CommonEntity {
    /**
     * 主键id
     */
    @Schema(title = "主键id")
    @TableId
    private Long id;
    /**
     * 通知消息id
     */
    @Schema(title = "通知消息id")
    private Long noticeId;
    /**
     * 用户id，用于站内消息通知
     */
    @Schema(title = "用户id，用于站内消息通知")
    private Long userId;
    /**
     * 用于手机号短信通知
     */
    @Schema(title = "用于手机号短信通知")
    private String phone;
    /**
     * 用于邮箱通知
     */
    @Schema(title = "用于邮箱通知")
    private String email;
    /**
     * 是否已读 yes no
     */
    @Schema(title = "是否已读 yes no")
    private YesOrNo readStatus;
}