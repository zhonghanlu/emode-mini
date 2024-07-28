/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云-rds
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : rm-bp1xd6sbag3rwsz46po.mysql.rds.aliyuncs.com:3306
 Source Schema         : emode

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 14/07/2024 02:30:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` bigint NOT NULL COMMENT '主键id',
  `parent_id` bigint DEFAULT NULL COMMENT '上级ID，一级菜单为0',
  `menu_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单URL',
  `permissions` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:list,sys:user:save)',
  `menu_type` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型：按钮、菜单',
  `icon` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `sort` int DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识1未删除-1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统权限详细记录表';

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` bigint NOT NULL COMMENT '主键id',
  `role_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `role_code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色码值',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识1未删除-1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission` (
  `id` bigint NOT NULL COMMENT '主键id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint DEFAULT NULL COMMENT '权限详情id',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识1未删除-1已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联表';

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` bigint NOT NULL COMMENT '用户表主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `avatar` bigint DEFAULT NULL COMMENT '头像id',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识1未删除-1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
BEGIN;
INSERT INTO `auth_user` (`id`, `username`, `password`, `nickname`, `avatar`, `phone`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`) VALUES (1623626795188256, 'admin', '5ccd168145095976bba93922d46e7b32f3bf74563da664d90c329471e43b909de788c85951bc5dd61e1ea2987dbe587d66b48863a3dadd89d1668ddb059184a9bef932ee8f41063513afccfda6517e6c15634ead98574b1820bdf965609cf1d61e141fe4b3e4', '超级管理员', 1, '15312665707', '2024-07-14 01:06:28', NULL, '2024-07-14 01:23:29', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` bigint NOT NULL COMMENT '主键id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `role_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识1未删除 -1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联表';

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint NOT NULL COMMENT '主键id',
  `file_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名',
  `file_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件存储路径',
  `file_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件存储类型枚举值',
  `file_device_by` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件存储端',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `del_flag` tinyint(1) DEFAULT '1' COMMENT '删除标识1未删除，-1已删除 默认值1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统文件表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;








-- ----------------------------
-- Table structure for t_message_content
-- ----------------------------
DROP TABLE IF EXISTS `t_message_content`;
CREATE TABLE `t_message_content` (
  `c_id` bigint NOT NULL COMMENT '消息的id',
  `send_id` bigint DEFAULT NULL COMMENT '消息发送者的id',
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息的内容',
  `type` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息的类型',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息发送的时间',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='站内信消息模版表';
-- ----------------------------
-- Records of t_message_content
-- ----------------------------
BEGIN;
COMMIT;




-- ----------------------------
-- Table structure for t_message_record
-- ----------------------------
DROP TABLE IF EXISTS `t_message_record`;
CREATE TABLE `t_message_record` (
  `r_id` bigint NOT NULL COMMENT '阅读记录的id',
  `rec_id` bigint DEFAULT NULL COMMENT '消息接收者的id',
  `c_id` bigint DEFAULT NULL COMMENT '对应消息的id',
  `status` varchar(10) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '阅读记录的状态',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='站内信记录表';

-- ----------------------------
-- Records of t_message_record
-- ----------------------------
BEGIN;
COMMIT;










