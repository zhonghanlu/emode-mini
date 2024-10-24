/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯-mysql
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : 124.220.79.217:3306
 Source Schema         : emode

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 24/10/2024 14:10:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级ID，一级菜单为0',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `permissions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:list,sys:user:save)',
  `menu_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型：按钮、菜单',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识1未删除-1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限详细记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES (1641854439260192, 0, '测试修改首页', '/sys/index', 'sys.index,sys.update,sys.insert', 'Menu', '', 0, 1);
INSERT INTO `auth_permission` VALUES (1641854453940256, 0, '测试-首页', '/sys/index', 'sys.index,sys.update,sys.insert', 'Menu', '', 0, -1);
INSERT INTO `auth_permission` VALUES (1641860751687712, 1641854439260192, '测试-首页11', '/sys/index', 'sys.index,sys.update,sys.insert', 'Menu', '', 0, 1);

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色码值',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识1未删除-1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES (1641998456979488, '测试权限修改', 'test-update', 1);

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint NULL DEFAULT NULL COMMENT '权限详情id',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识1未删除-1已经删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE COMMENT '关联'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
INSERT INTO `auth_role_permission` VALUES (1641998456979520, 1641998456979488, 1641854439260192, -1);
INSERT INTO `auth_role_permission` VALUES (1641998456979552, 1641998456979488, 1641860751687712, -1);
INSERT INTO `auth_role_permission` VALUES (1641998691860512, 1641998456979488, 1641854439260192, -1);
INSERT INTO `auth_role_permission` VALUES (1641998727512096, 1641998456979488, 1641854439260192, 1);
INSERT INTO `auth_role_permission` VALUES (1641998727512128, 1641998456979488, 1641860751687712, 1);

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user`  (
  `id` bigint NOT NULL COMMENT '用户表主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别 male：男 female：女',
  `avatar` bigint NULL DEFAULT NULL COMMENT '头像id',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `user_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户类型，pc mini manager',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标识1未删除-1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES (1, 'admin', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '超级管理员', 'male', 2, '15312665707', 'manager', '2024-07-14 01:06:28', NULL, '2024-07-17 21:42:06', 1623626795188256, 1);
INSERT INTO `auth_user` VALUES (1641852256124960, 'tes001', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '测试一下修改啦', 'female', 2, '147258369', 'pc', '2024-10-22 15:09:25', 1623626795188256, '2024-10-22 15:18:00', 1623626795188256, 1);
INSERT INTO `auth_user` VALUES (1641858696478752, 'test002', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '测试性别', 'male', 0, '111111111', 'pc', '2024-10-22 16:00:35', 1641852256124960, '2024-10-22 16:00:35', 1641852256124960, 1);

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识1未删除 -1 已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE COMMENT '关联'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES (1641998964490272, 1641998456979488, '测试权限修改', 1641852256124960, 'tes001', -1);
INSERT INTO `auth_user_role` VALUES (1641998985461792, 1641998456979488, '测试权限修改', 1641852256124960, 'tes001', 1);

-- ----------------------------
-- Table structure for bm_org
-- ----------------------------
DROP TABLE IF EXISTS `bm_org`;
CREATE TABLE `bm_org`  (
  `id` bigint NOT NULL COMMENT '机构id',
  `org_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `org_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构地址',
  `org_longitude` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构经度',
  `org_latitude` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构纬度',
  `org_described` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '机构描述',
  `org_status` tinyint(1) NULL DEFAULT NULL COMMENT '机构状态',
  `org_headA` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构负责人 A',
  `org_headB` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构负责人B',
  `org_business_license` bigint NULL DEFAULT NULL COMMENT '机构营业执照',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识 1未删除，-1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置key',
  `config_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置实际值',
  `config_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态 yes no',
  `show_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否展示 yes no',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标识 1未删除，-1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '全局参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1626811498758176, 'bbb', 'bbb', 'ccc', 'yes', 'yes', '2024-07-31 14:56:12', 1623626795188256, '2024-07-31 14:56:12', 1623626795188256, -1);
INSERT INTO `sys_config` VALUES (1626812075474976, 'aaa3', 'aaa1', 'aaa', 'yes', 'yes', '2024-07-31 15:00:47', 1623626795188256, '2024-07-31 15:00:47', 1623626795188256, -1);
INSERT INTO `sys_config` VALUES (1626812083863584, 'aaa3', 'aaa2', 'aaa', 'yes', 'yes', '2024-07-31 15:00:51', 1623626795188256, '2024-07-31 15:00:51', 1623626795188256, 1);
INSERT INTO `sys_config` VALUES (1626812092252192, 'aaa3', 'aaa3', 'aaa', 'yes', 'no', '2024-07-31 15:00:55', 1623626795188256, '2024-07-31 15:00:55', 1623626795188256, 1);
INSERT INTO `sys_config` VALUES (1626945854898208, 'aaa3', 'aaa23', 'aaa', 'yes', 'yes', '2024-08-01 08:43:58', 1623626795188256, '2024-08-01 08:43:58', 1623626795188256, 1);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `file_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储路径',
  `file_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储类型枚举值',
  `file_device_by` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储端',
  `bucket_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '桶名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人id',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标识1未删除，-1已删除 默认值1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1642031082373152, '微信截图_20240807091511', '微信截图_20240807091511.png', 'image/png', 'pc', 'e-mode-bucket', '2024-10-23 14:50:36', 1, '2024-10-23 14:50:36', 1, -1);
INSERT INTO `sys_file` VALUES (1642031088664608, '微信截图_20240807091511', '微信截图_20240807091511.png', 'image/png', 'pc', 'e-mode-bucket', '2024-10-23 14:50:38', 1, '2024-10-23 14:50:38', 1, -1);
INSERT INTO `sys_file` VALUES (1642031092858912, '微信截图_20240807091511', '微信截图_20240807091511.png', 'image/png', 'pc', 'e-mode-bucket', '2024-10-23 14:50:41', 1, '2024-10-23 14:50:41', 1, -1);
INSERT INTO `sys_file` VALUES (1642032860758048, '微信截图_20240807091127', '微信截图_20240807091127.doc', 'application/msword', 'pc', 'e-mode-bucket', '2024-10-23 15:04:43', 1, '2024-10-23 15:04:43', 1, -1);
INSERT INTO `sys_file` VALUES (1642035767410720, '微信截图_20240807091511', '微信截图_20240807091511.png', 'image/png', 'pc', 'e-mode-bucket', '2024-10-23 15:27:49', 1, '2024-10-23 15:27:49', 1, -1);

-- ----------------------------
-- Table structure for sys_login_opt
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_opt`;
CREATE TABLE `sys_login_opt`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账户名',
  `opt_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作ip',
  `opt_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作地址',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `system_os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作是否成功',
  `opt_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型，登入，登出',
  `opt_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作结果消息',
  `opt_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统登入登出信息记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_login_opt
-- ----------------------------
INSERT INTO `sys_login_opt` VALUES (1641634313797664, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'no', 'login', 'ACCOUNT_PASSWORD_ERROR', '2024-10-21 10:17:21');
INSERT INTO `sys_login_opt` VALUES (1641634317991968, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'no', 'login', 'ACCOUNT_PASSWORD_ERROR', '2024-10-21 10:17:24');
INSERT INTO `sys_login_opt` VALUES (1641634320089120, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'no', 'login', 'ACCOUNT_PASSWORD_ERROR', '2024-10-21 10:17:25');
INSERT INTO `sys_login_opt` VALUES (1641634322186272, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'no', 'login', 'ACCOUNT_PASSWORD_ERROR', '2024-10-21 10:17:26');
INSERT INTO `sys_login_opt` VALUES (1641634322186304, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'no', 'login', 'ACCOUNT_PASSWORD_ERROR', '2024-10-21 10:17:26');
INSERT INTO `sys_login_opt` VALUES (1641634322186336, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'no', 'login', 'ACCOUNT_PASSWORD_ERROR', '2024-10-21 10:17:26');
INSERT INTO `sys_login_opt` VALUES (1641634324283424, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'no', 'login', 'ACCOUNT_PASSWORD_ERROR', '2024-10-21 10:17:26');
INSERT INTO `sys_login_opt` VALUES (1641634435432480, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 10:18:20');
INSERT INTO `sys_login_opt` VALUES (1641634680799264, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 10:20:17');
INSERT INTO `sys_login_opt` VALUES (1641721882476576, 'admin', '49.65.227.47', '中国|江苏省|南京市|电信', 'Chrome 12', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 21:53:18');
INSERT INTO `sys_login_opt` VALUES (1641721888768032, 'admin', '49.65.227.47', '中国|江苏省|南京市|电信', 'Chrome 12', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 21:53:20');
INSERT INTO `sys_login_opt` VALUES (1641721888768064, 'admin', '49.65.227.47', '中国|江苏省|南京市|电信', 'Chrome 12', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 21:53:21');
INSERT INTO `sys_login_opt` VALUES (1641721890865184, 'admin', '49.65.227.47', '中国|江苏省|南京市|电信', 'Chrome 12', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 21:53:21');
INSERT INTO `sys_login_opt` VALUES (1641721890865216, 'admin', '49.65.227.47', '中国|江苏省|南京市|电信', 'Chrome 12', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 21:53:21');
INSERT INTO `sys_login_opt` VALUES (1641721890865248, 'admin', '49.65.227.47', '中国|江苏省|南京市|电信', 'Chrome 12', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-21 21:53:22');
INSERT INTO `sys_login_opt` VALUES (1641811579764768, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 09:46:08');
INSERT INTO `sys_login_opt` VALUES (1641811720273952, 'admin', '121.225.46.115', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 09:47:15');
INSERT INTO `sys_login_opt` VALUES (1641812227784736, 'admin', '121.225.46.115', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 09:51:17');
INSERT INTO `sys_login_opt` VALUES (1641815665016864, 'admin', '121.225.46.115', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 10:18:36');
INSERT INTO `sys_login_opt` VALUES (1641815939743776, 'admin', '121.225.46.115', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 10:20:47');
INSERT INTO `sys_login_opt` VALUES (1641815939743808, 'admin', '121.225.46.115', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 10:20:48');
INSERT INTO `sys_login_opt` VALUES (1641815946035232, 'admin', '121.225.46.115', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 10:20:51');
INSERT INTO `sys_login_opt` VALUES (1641815952326688, 'admin', '121.225.46.115', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 10:20:54');
INSERT INTO `sys_login_opt` VALUES (1641848741298208, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:41:29');
INSERT INTO `sys_login_opt` VALUES (1641848833572896, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:42:12');
INSERT INTO `sys_login_opt` VALUES (1641849177505824, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:44:56');
INSERT INTO `sys_login_opt` VALUES (1641849179602976, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:44:57');
INSERT INTO `sys_login_opt` VALUES (1641849181700128, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:44:58');
INSERT INTO `sys_login_opt` VALUES (1641849181700160, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:44:59');
INSERT INTO `sys_login_opt` VALUES (1641849427066912, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:46:55');
INSERT INTO `sys_login_opt` VALUES (1641849548701728, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:47:54');
INSERT INTO `sys_login_opt` VALUES (1641849691308064, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 14:49:01');
INSERT INTO `sys_login_opt` VALUES (1641853847863328, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 15:22:03');
INSERT INTO `sys_login_opt` VALUES (1641854013538336, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 15:23:23');
INSERT INTO `sys_login_opt` VALUES (1641854015635488, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 15:23:24');
INSERT INTO `sys_login_opt` VALUES (1641854017732640, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 15:23:24');
INSERT INTO `sys_login_opt` VALUES (1641854017732672, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 15:23:25');
INSERT INTO `sys_login_opt` VALUES (1641854019829792, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 15:23:25');
INSERT INTO `sys_login_opt` VALUES (1641854019829824, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 15:23:26');
INSERT INTO `sys_login_opt` VALUES (1641870564261920, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:34:55');
INSERT INTO `sys_login_opt` VALUES (1641870572650528, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:34:59');
INSERT INTO `sys_login_opt` VALUES (1641870574747680, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:35:00');
INSERT INTO `sys_login_opt` VALUES (1641870576844832, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:35:01');
INSERT INTO `sys_login_opt` VALUES (1641870578941984, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:35:02');
INSERT INTO `sys_login_opt` VALUES (1641870581039136, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:35:03');
INSERT INTO `sys_login_opt` VALUES (1641870583136288, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:35:03');
INSERT INTO `sys_login_opt` VALUES (1641870585233440, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:35:04');
INSERT INTO `sys_login_opt` VALUES (1641870585233472, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:35:05');
INSERT INTO `sys_login_opt` VALUES (1641870876737568, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:23');
INSERT INTO `sys_login_opt` VALUES (1641870878834720, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:25');
INSERT INTO `sys_login_opt` VALUES (1641870880931872, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:25');
INSERT INTO `sys_login_opt` VALUES (1641870880931904, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:26');
INSERT INTO `sys_login_opt` VALUES (1641870883029024, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:26');
INSERT INTO `sys_login_opt` VALUES (1641870883029056, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:27');
INSERT INTO `sys_login_opt` VALUES (1641870885126176, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:27');
INSERT INTO `sys_login_opt` VALUES (1641870885126208, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:27');
INSERT INTO `sys_login_opt` VALUES (1641870887223328, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:37:29');
INSERT INTO `sys_login_opt` VALUES (1641871161950240, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:39:40');
INSERT INTO `sys_login_opt` VALUES (1641872176971808, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:47:44');
INSERT INTO `sys_login_opt` VALUES (1641872183263264, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 17:47:46');
INSERT INTO `sys_login_opt` VALUES (1641888654295072, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 19:58:41');
INSERT INTO `sys_login_opt` VALUES (1641890571092000, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:13:55');
INSERT INTO `sys_login_opt` VALUES (1641891422535712, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Unknown', 'Unknown', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:20:40');
INSERT INTO `sys_login_opt` VALUES (1641891430924320, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Unknown', 'Unknown', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:20:45');
INSERT INTO `sys_login_opt` VALUES (1641891793731616, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:23:38');
INSERT INTO `sys_login_opt` VALUES (1641891806314528, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:23:44');
INSERT INTO `sys_login_opt` VALUES (1641891808411680, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:23:45');
INSERT INTO `sys_login_opt` VALUES (1641892146053152, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:26:25');
INSERT INTO `sys_login_opt` VALUES (1641892305436704, 'admin', '36.113.45.192', '中国|江苏省|南京市|电信', 'Firefox 13', 'Mac OS X', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-22 20:27:42');
INSERT INTO `sys_login_opt` VALUES (1641998012383264, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 10:27:47');
INSERT INTO `sys_login_opt` VALUES (1641998117240864, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 10:28:36');
INSERT INTO `sys_login_opt` VALUES (1641998125629472, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 10:28:41');
INSERT INTO `sys_login_opt` VALUES (1641998127726624, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 10:28:42');
INSERT INTO `sys_login_opt` VALUES (1641998127726656, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 10:28:42');
INSERT INTO `sys_login_opt` VALUES (1641998291304480, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 10:30:00');
INSERT INTO `sys_login_opt` VALUES (1641999941763104, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 10:43:06');
INSERT INTO `sys_login_opt` VALUES (1642020498047008, 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 13:26:28');
INSERT INTO `sys_login_opt` VALUES (1642022479855648, 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 13:42:14');
INSERT INTO `sys_login_opt` VALUES (1642035610124320, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 15:26:34');
INSERT INTO `sys_login_opt` VALUES (1642035612221472, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 15:26:36');
INSERT INTO `sys_login_opt` VALUES (1642035614318624, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 15:26:36');
INSERT INTO `sys_login_opt` VALUES (1642035614318656, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 15:26:37');
INSERT INTO `sys_login_opt` VALUES (1642035729661984, 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'yes', 'login', 'LOGIN_SUCCESS', '2024-10-23 15:27:32');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息标头',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '消息的内容',
  `send_id` bigint NULL DEFAULT NULL COMMENT '消息发送者的id',
  `notice_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息的类型',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  `message_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息状态',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识1未删除 -1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站内信表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1642022033162272, '测试广播', '我是内容', 1641852256124960, 'local', '2024-10-23 13:38:40', 'broadcast', '2024-10-23 13:38:40', 1641852256124960, '2024-10-23 13:38:40', 1641852256124960, 1);
INSERT INTO `sys_notice` VALUES (1642022043648032, '测试广播2', '我是内容', 1641852256124960, 'local', '2024-10-23 13:38:46', 'broadcast', '2024-10-23 13:38:46', 1641852256124960, '2024-10-23 13:38:46', 1641852256124960, 1);
INSERT INTO `sys_notice` VALUES (1642022425329696, '测试广播2', '我是内容', 1641852256124960, 'local', '2024-10-23 13:41:48', 'alone', '2024-10-23 13:41:48', NULL, '2024-10-23 13:41:48', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_notice`;
CREATE TABLE `sys_user_notice`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `notice_id` bigint NULL DEFAULT NULL COMMENT '通知消息id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id，用于站内消息通知',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用于手机号短信通知',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用于邮箱通知',
  `read_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否已读 yes no',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识1未删除 -1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息用户关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_notice
-- ----------------------------
INSERT INTO `sys_user_notice` VALUES (1642022425329728, 1642022425329696, 1, '', '', 'no', '2024-10-23 13:41:48', NULL, '2024-10-23 13:41:48', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_opt
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_opt`;
CREATE TABLE `sys_user_opt`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `request_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作唯一id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账户名',
  `opt_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作ip',
  `opt_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作地址',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `system_os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `opt_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'get post',
  `opt_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `opt_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `opt_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求状态，yes成功，no失败',
  `opt_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_opt
-- ----------------------------
INSERT INTO `sys_user_opt` VALUES (1641849705988128, '19ef1302-8842-491e-9173-cafe7c39c399', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:49:08');
INSERT INTO `sys_user_opt` VALUES (1641849980715040, 'bf7bdfa9-7115-4605-9f86-61d9518b9070', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:51:20');
INSERT INTO `sys_user_opt` VALUES (1641849982812192, '541cdf05-e10a-4818-875c-2d19370e91b2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:51:20');
INSERT INTO `sys_user_opt` VALUES (1641849982812224, '5c31ae3f-e7c0-44ea-9e01-6698f9e47d18', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:51:21');
INSERT INTO `sys_user_opt` VALUES (1641849982812256, '8eb26ae5-6b91-4ae6-a784-dc62b8b31878', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:51:21');
INSERT INTO `sys_user_opt` VALUES (1641849984909344, '296e8a42-2d92-4227-84dc-9ef74a1186b1', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:51:21');
INSERT INTO `sys_user_opt` VALUES (1641849984909376, 'ab03f27b-2cf7-40f1-8b2d-81f72e1acc75', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:51:21');
INSERT INTO `sys_user_opt` VALUES (1641849984909408, '10a45fee-b472-49a3-8f76-defc4e40d408', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 14:51:22');
INSERT INTO `sys_user_opt` VALUES (1641850020560928, 'f2aaa8e5-47b3-4b03-9a7f-828ac2965669', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 14:51:39');
INSERT INTO `sys_user_opt` VALUES (1641850028949536, 'dcb5bf2f-ab70-4c8e-9ae8-12a0e3371bdb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 14:51:43');
INSERT INTO `sys_user_opt` VALUES (1641850031046688, 'f942cd34-cc12-4ea4-8a22-b434a06465e9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 14:51:43');
INSERT INTO `sys_user_opt` VALUES (1641850031046720, 'ff404a78-6b75-4225-9cbd-da5518d8a427', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 14:51:43');
INSERT INTO `sys_user_opt` VALUES (1641850031046752, '01766cd1-e2be-430c-bdad-0e158136674a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 14:51:44');
INSERT INTO `sys_user_opt` VALUES (1641850031046784, '3bd520ff-7fb4-46c8-b73c-b4b7b0e2a86b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 14:51:44');
INSERT INTO `sys_user_opt` VALUES (1641850031046816, '49062652-b59c-401c-9a5a-557c1be90a8a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 14:51:44');
INSERT INTO `sys_user_opt` VALUES (1641850307870752, 'b18dd04a-055a-4c3a-a770-86d526d4df7d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 14:53:55');
INSERT INTO `sys_user_opt` VALUES (1641850307870784, '20b70777-9e02-4058-b54b-9affc1246ba6', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 14:53:56');
INSERT INTO `sys_user_opt` VALUES (1641850309967904, '0c39d6cd-9035-495b-853c-8c3771c460c1', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 14:53:56');
INSERT INTO `sys_user_opt` VALUES (1641850309967936, '1dc894ee-581f-435c-8df6-5ec913a66571', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 14:53:57');
INSERT INTO `sys_user_opt` VALUES (1641850309967968, '38aa929f-d313-4efa-8b69-22bc3bb668b8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 14:53:57');
INSERT INTO `sys_user_opt` VALUES (1641850312065056, 'db78883a-cd16-4e13-83d5-f832bde26ad2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 14:53:57');
INSERT INTO `sys_user_opt` VALUES (1641850379173920, '374ca4f6-ae43-4296-b29d-84515caa68b9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":0,\"username\":\"\",\"password\":\"\",\"nickname\":\"\",\"avatar\":0,\"phone\":\"\"}', 'no', '2024-10-22 14:54:30');
INSERT INTO `sys_user_opt` VALUES (1641850381271072, '36fa11b9-cb14-49d6-9a0f-2074002f9e77', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":0,\"username\":\"\",\"password\":\"\",\"nickname\":\"\",\"avatar\":0,\"phone\":\"\"}', 'no', '2024-10-22 14:54:30');
INSERT INTO `sys_user_opt` VALUES (1641850381271104, 'ec6ccdef-7b62-48b7-b736-42d9d71db1bd', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":0,\"username\":\"\",\"password\":\"\",\"nickname\":\"\",\"avatar\":0,\"phone\":\"\"}', 'no', '2024-10-22 14:54:31');
INSERT INTO `sys_user_opt` VALUES (1641850383368224, '462050a7-b3c6-42f8-9eb8-164bc57b580d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":0,\"username\":\"\",\"password\":\"\",\"nickname\":\"\",\"avatar\":0,\"phone\":\"\"}', 'no', '2024-10-22 14:54:31');
INSERT INTO `sys_user_opt` VALUES (1641850383368256, 'a90a103e-4c4b-4e5f-9194-36f4ab5adf56', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":0,\"username\":\"\",\"password\":\"\",\"nickname\":\"\",\"avatar\":0,\"phone\":\"\"}', 'no', '2024-10-22 14:54:31');
INSERT INTO `sys_user_opt` VALUES (1641850387562528, '3bb18c92-bc73-4f55-98a2-3f644b863edc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'no', '2024-10-22 14:54:34');
INSERT INTO `sys_user_opt` VALUES (1641850836353056, '685e4408-a0f2-46c8-9dda-750be9136152', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'no', '2024-10-22 14:58:07');
INSERT INTO `sys_user_opt` VALUES (1641850926530592, '4ddca7e8-8915-4aa4-b630-c7f7c7486b95', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'no', '2024-10-22 14:58:50');
INSERT INTO `sys_user_opt` VALUES (1641851186577440, '45da248a-c570-4bbe-8d46-5b69ed1bd83a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:00:54');
INSERT INTO `sys_user_opt` VALUES (1641851201257504, '96232093-6814-4ebf-829d-e08d811da5c1', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:01:01');
INSERT INTO `sys_user_opt` VALUES (1641851201257536, '0a23186c-5279-4e34-a70e-f377e581a1ad', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:01:02');
INSERT INTO `sys_user_opt` VALUES (1641851201257568, 'a560aca1-d6ae-4885-8bf9-a2833b4880e6', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:01:02');
INSERT INTO `sys_user_opt` VALUES (1641851203354656, '5bf78407-cbeb-4452-b59d-63ef0dd0df82', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:01:02');
INSERT INTO `sys_user_opt` VALUES (1641851255783456, '9c1d1540-4893-459e-b4f7-6e5b9ef5efd0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'no', '2024-10-22 15:01:28');
INSERT INTO `sys_user_opt` VALUES (1641851702476832, 'ee1d295c-7e20-4872-ae00-5b66cca95090', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:05:00');
INSERT INTO `sys_user_opt` VALUES (1641851704573984, 'b1ada400-ce64-415d-8ded-2da5f2adcfc1', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:05:01');
INSERT INTO `sys_user_opt` VALUES (1641851704574016, '8f0021e7-9a30-4868-9c9d-a1b4b8b6d7cf', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:05:02');
INSERT INTO `sys_user_opt` VALUES (1641851706671136, '48bfb812-6a29-40cc-8da4-65eac566e271', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:05:02');
INSERT INTO `sys_user_opt` VALUES (1641851710865440, 'f9ffa646-2f20-4cee-9a7e-ef4cc751c33a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:05');
INSERT INTO `sys_user_opt` VALUES (1641851712962592, 'fbaf2941-cfd4-4a26-91bb-9dc139c0af6e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:06');
INSERT INTO `sys_user_opt` VALUES (1641851712962624, '7374cd85-8b00-4e7c-ac58-b14d3ab5f6b0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:06');
INSERT INTO `sys_user_opt` VALUES (1641851715059744, '65306605-6cbe-449d-b5b5-a8315f8a3f54', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:06');
INSERT INTO `sys_user_opt` VALUES (1641851715059776, '3901d3c9-dd7d-457f-baf7-8fe61f86bca7', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:07');
INSERT INTO `sys_user_opt` VALUES (1641851715059808, '5c7ae92d-54ec-4cba-8a42-c84bc171f75c', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:07');
INSERT INTO `sys_user_opt` VALUES (1641851717156896, '45e2a076-d07a-4f5f-af52-369f18cb1687', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:07');
INSERT INTO `sys_user_opt` VALUES (1641851717156928, '734a9449-af94-4677-8f4c-aa70fec31f11', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:07');
INSERT INTO `sys_user_opt` VALUES (1641851717156960, '850b3131-0b8d-499f-acf5-17d24b80f2d0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:08');
INSERT INTO `sys_user_opt` VALUES (1641851782168608, 'ab395113-ba15-41dc-bef4-f4ab050b255c', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:39');
INSERT INTO `sys_user_opt` VALUES (1641851782168640, 'f6528e83-0245-4758-95b1-c08c948faa65', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:39');
INSERT INTO `sys_user_opt` VALUES (1641851782168672, '25453d48-f80e-421e-933c-ab438e497d53', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:05:39');
INSERT INTO `sys_user_opt` VALUES (1641851790557216, '04956636-3ced-47fc-9264-7d5e306a51ef', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:42');
INSERT INTO `sys_user_opt` VALUES (1641851790557248, 'f404749b-32be-4122-8780-494cb3e891b8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:43');
INSERT INTO `sys_user_opt` VALUES (1641851792654368, '5b6305b8-c8a7-4b7b-8a65-2fbb34b1ec43', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:43');
INSERT INTO `sys_user_opt` VALUES (1641851792654400, 'a7b3c9c5-be75-4fec-af51-854c30fc14bc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:43');
INSERT INTO `sys_user_opt` VALUES (1641851792654432, 'c179e3a6-3180-4954-b6b9-40fd470c5a0f', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:43');
INSERT INTO `sys_user_opt` VALUES (1641851792654464, '5b74d2d0-a288-4e6e-81b2-71ad35059b43', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:44');
INSERT INTO `sys_user_opt` VALUES (1641851792654496, '4732e438-da78-47fa-8d0c-6ef023e161ff', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:44');
INSERT INTO `sys_user_opt` VALUES (1641851794751520, 'fcc7e56f-6378-4292-8b48-defd35dea4be', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:44');
INSERT INTO `sys_user_opt` VALUES (1641851794751552, 'b1f53ada-a84a-40ad-a6ea-157c48fa7745', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:44');
INSERT INTO `sys_user_opt` VALUES (1641851794751584, 'a08dcf16-bda3-44c9-816c-fdcc0aba1dd0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:44');
INSERT INTO `sys_user_opt` VALUES (1641851794751616, '82322361-2556-4660-8a9a-196d1b5f926b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:45');
INSERT INTO `sys_user_opt` VALUES (1641851807334432, '6120efe2-1a57-4542-8053-721fb30c1148', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:51');
INSERT INTO `sys_user_opt` VALUES (1641851809431584, '6e6e5ad2-8789-4650-a233-e906b224cd3e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:51');
INSERT INTO `sys_user_opt` VALUES (1641851809431616, '6bb5c509-8680-48ce-b80a-eb4805ab8fbc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:52');
INSERT INTO `sys_user_opt` VALUES (1641851809431648, '9e763b64-05f5-457e-83fb-a4fb4d6df81f', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:52');
INSERT INTO `sys_user_opt` VALUES (1641851811528736, '3d5494ec-8e93-40dd-9329-005f3ea48cce', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:52');
INSERT INTO `sys_user_opt` VALUES (1641851811528768, '727dcd38-8a56-4ee7-90bd-e2909b873b20', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:52');
INSERT INTO `sys_user_opt` VALUES (1641851811528800, '6a45f811-6c7a-4aaf-95cc-1c3bc6ddfd67', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:53');
INSERT INTO `sys_user_opt` VALUES (1641851811528832, 'c478cd94-1651-4027-b93f-0126b1488e81', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:53');
INSERT INTO `sys_user_opt` VALUES (1641851813625888, '1d284c70-6d17-4033-834b-023717251a80', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:53');
INSERT INTO `sys_user_opt` VALUES (1641851813625920, '0568ca2a-8caf-4fe5-882d-1a804122f764', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:05:53');
INSERT INTO `sys_user_opt` VALUES (1641851824111648, 'a73987fd-d341-4958-becf-10d98d4f7c83', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:59');
INSERT INTO `sys_user_opt` VALUES (1641851826208800, '25869d44-c7ff-48f8-a024-886e5946e6dd', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:05:59');
INSERT INTO `sys_user_opt` VALUES (1641851830403104, '6114a237-4e5f-41a9-83ef-2ace4b578330', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:01');
INSERT INTO `sys_user_opt` VALUES (1641851830403136, 'bfcc0856-0043-4a41-859f-9054af2cd16f', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:01');
INSERT INTO `sys_user_opt` VALUES (1641851830403168, '7bc8bea4-9285-470b-b8e3-260a534a648e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:01');
INSERT INTO `sys_user_opt` VALUES (1641851830403200, '0aa53c28-e1ea-4d03-8251-2cefed7fb20e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:02');
INSERT INTO `sys_user_opt` VALUES (1641851947843616, 'a3a90121-683f-4076-966b-716e023384bb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:57');
INSERT INTO `sys_user_opt` VALUES (1641851949940768, '9633110e-d6d1-4e96-a69a-cab5e5a23ed7', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:58');
INSERT INTO `sys_user_opt` VALUES (1641851949940800, '96a88d05-d405-4d06-8e10-e535f16fcf83', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:58');
INSERT INTO `sys_user_opt` VALUES (1641851949940832, '1a878dc7-55b2-41f2-b242-20627cc562eb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:59');
INSERT INTO `sys_user_opt` VALUES (1641851949940864, '0700f740-387e-4dab-9ac6-cb1e866c9df4', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:59');
INSERT INTO `sys_user_opt` VALUES (1641851949940896, '6a4d8c11-25da-4ea1-af77-66a16477af25', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:59');
INSERT INTO `sys_user_opt` VALUES (1641851952037920, 'f591dfd0-b536-4a25-805d-ff37a4d57001', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:06:59');
INSERT INTO `sys_user_opt` VALUES (1641851962523680, 'bd76b4ad-a06f-44e2-a6c5-4709c7321537', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:07:05');
INSERT INTO `sys_user_opt` VALUES (1641851964620832, 'c17b4186-6896-4ae5-b4b5-2aea01cca692', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:07:05');
INSERT INTO `sys_user_opt` VALUES (1641851964620864, 'acc8209a-f48b-4c93-8593-4bdfb75100d4', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/all', '{id=1623626795188256, type=all}', 'yes', '2024-10-22 15:07:05');
INSERT INTO `sys_user_opt` VALUES (1641851968815136, 'fbd070c7-be4b-40e6-a2b0-cb323973feaa', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:07:08');
INSERT INTO `sys_user_opt` VALUES (1641851970912288, '8aecbb66-e651-45f2-8212-d7f5aa9957d8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:07:08');
INSERT INTO `sys_user_opt` VALUES (1641851970912320, '854a8baa-551d-431d-a27d-71f46c042496', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:07:08');
INSERT INTO `sys_user_opt` VALUES (1641851970912352, '450010e7-216d-4ebb-bbd4-1bbd2fa40eb5', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:07:09');
INSERT INTO `sys_user_opt` VALUES (1641851970912384, '2e91b939-7344-4be1-9e4c-c38074b8f32a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/role', '{id=1623626795188256, type=role}', 'yes', '2024-10-22 15:07:09');
INSERT INTO `sys_user_opt` VALUES (1641851981398048, '8084bb00-342e-4cc2-b49c-70e1d5aabef6', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:07:14');
INSERT INTO `sys_user_opt` VALUES (1641851983495200, '9de9410e-905e-4655-8a92-f19b6915fcac', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:07:14');
INSERT INTO `sys_user_opt` VALUES (1641851983495232, 'e2c1957f-1bb7-43a3-9d4f-b6f47eee8d66', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:07:14');
INSERT INTO `sys_user_opt` VALUES (1641851987689504, '2aba582b-2ad7-4de9-aa46-5b78ec8b47f2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/16236267951882561/base', '{id=16236267951882561, type=base}', 'no', '2024-10-22 15:07:16');
INSERT INTO `sys_user_opt` VALUES (1641851987689536, '0a7781de-089d-4ec6-a125-3e71ef747524', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/16236267951882561/base', '{id=16236267951882561, type=base}', 'no', '2024-10-22 15:07:17');
INSERT INTO `sys_user_opt` VALUES (1641851987689568, 'fb28abd9-d330-48b4-8b45-c90f9d1a6606', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/16236267951882561/base', '{id=16236267951882561, type=base}', 'no', '2024-10-22 15:07:17');
INSERT INTO `sys_user_opt` VALUES (1641851989786656, '3762da6c-a10e-44d4-8d36-dcb6f7cc622f', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/16236267951882561/base', '{id=16236267951882561, type=base}', 'no', '2024-10-22 15:07:17');
INSERT INTO `sys_user_opt` VALUES (1641851989786688, 'ae14bc9a-d133-4271-8a33-7a44f98cd960', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/16236267951882561/base', '{id=16236267951882561, type=base}', 'no', '2024-10-22 15:07:17');
INSERT INTO `sys_user_opt` VALUES (1641851989786720, '397de9d3-9a51-46ce-a28b-9e1e32843345', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/16236267951882561/base', '{id=16236267951882561, type=base}', 'no', '2024-10-22 15:07:17');
INSERT INTO `sys_user_opt` VALUES (1641851993980960, 'afe138fb-c957-4fdb-a87d-6a484b82b8f2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:07:19');
INSERT INTO `sys_user_opt` VALUES (1641851993980992, '3dd9dd9b-e1aa-4816-a4a6-22660a21d992', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:07:20');
INSERT INTO `sys_user_opt` VALUES (1641851993981024, '32e42ec0-a218-42fe-9809-f26fd3bdb7e3', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:07:20');
INSERT INTO `sys_user_opt` VALUES (1641851996078112, 'ba67e26d-8392-4cbc-8a32-699034d319aa', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1623626795188256/base', '{id=1623626795188256, type=base}', 'yes', '2024-10-22 15:07:20');
INSERT INTO `sys_user_opt` VALUES (1641852103032864, '7dac64f6-1443-4046-999a-81e2640e0133', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:08:11');
INSERT INTO `sys_user_opt` VALUES (1641852103032896, 'cb0a3648-cde0-436e-8783-929dedd147f8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:08:12');
INSERT INTO `sys_user_opt` VALUES (1641852103032928, '5c518466-3d0c-4438-b598-6ccd7edc80cf', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:08:12');
INSERT INTO `sys_user_opt` VALUES (1641852105130016, 'f1dfb5de-e949-4b66-86fd-033c2fb2205d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:08:12');
INSERT INTO `sys_user_opt` VALUES (1641852105130048, 'a3bd2868-5134-4607-9972-64ab51f38825', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:08:13');
INSERT INTO `sys_user_opt` VALUES (1641852105130080, '2afae1c2-34b8-4b85-81b7-d78ac307d5c7', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:08:13');
INSERT INTO `sys_user_opt` VALUES (1641852256124992, '1a4c1983-4f6d-4ad7-9f14-16a2651a815b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'yes', '2024-10-22 15:09:25');
INSERT INTO `sys_user_opt` VALUES (1641852262416416, 'b428953e-69dc-4ac3-9c97-f0775e1dc922', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:27');
INSERT INTO `sys_user_opt` VALUES (1641852262416448, 'c73dae93-1a0a-4519-bfb3-fe208779cdc8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:28');
INSERT INTO `sys_user_opt` VALUES (1641852264513568, 'f89f71dd-2e6d-4a89-b99c-0013ac78c1c2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:28');
INSERT INTO `sys_user_opt` VALUES (1641852264513600, '0a3dac2f-28aa-4359-b070-9fec983f26e7', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:29');
INSERT INTO `sys_user_opt` VALUES (1641852266610720, '9442e3b2-377b-4db2-9747-d74be86c682d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:29');
INSERT INTO `sys_user_opt` VALUES (1641852266610752, 'f3500474-03f8-4926-a94e-21145d09c703', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:29');
INSERT INTO `sys_user_opt` VALUES (1641852266610784, '4a646d39-7498-4e79-b164-ed84e024f63e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:30');
INSERT INTO `sys_user_opt` VALUES (1641852268707872, '7307b51e-6a8b-412c-bad8-821a85969396', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:30');
INSERT INTO `sys_user_opt` VALUES (1641852268707904, '8d310613-b15a-471b-af3a-f2685ba1f8fc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test01\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试001\",\"avatar\":2,\"phone\":\"1452415\",\"userType\":\"pc\"}', 'no', '2024-10-22 15:09:30');
INSERT INTO `sys_user_opt` VALUES (1641852285485088, '65cef0a4-f0c1-4c86-a970-b01b3c3ba55e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:39');
INSERT INTO `sys_user_opt` VALUES (1641852300165152, '8113684a-7404-4dcd-81bf-a2677440a6bf', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:45');
INSERT INTO `sys_user_opt` VALUES (1641852302262304, '8f0e0305-813d-43fd-b1c7-daf1d86897e9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:47');
INSERT INTO `sys_user_opt` VALUES (1641852304359456, 'cbe3a69f-da77-4a2d-ac74-94da2a077eed', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:47');
INSERT INTO `sys_user_opt` VALUES (1641852304359488, '9c8f3211-b834-4e6d-851e-b2093b2a6928', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:47');
INSERT INTO `sys_user_opt` VALUES (1641852304359520, 'e999d85e-a528-41d4-b5e8-d040f57dd821', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:48');
INSERT INTO `sys_user_opt` VALUES (1641852304359552, 'b1f0aa57-6309-4da0-8c43-231135dbd133', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:48');
INSERT INTO `sys_user_opt` VALUES (1641852306456608, '34aebcc9-cc53-444b-8cb4-75d25f80f453', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:49');
INSERT INTO `sys_user_opt` VALUES (1641852308553760, 'f350b0af-ce35-4637-94e0-71dcb46eb60d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:49');
INSERT INTO `sys_user_opt` VALUES (1641852308553792, '597eea49-3eea-45a3-9dc7-b988b7ae0a6f', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:50');
INSERT INTO `sys_user_opt` VALUES (1641852310650912, 'd0542676-1917-47d5-b8d5-958e87c16db0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:51');
INSERT INTO `sys_user_opt` VALUES (1641852321136672, 'f7b5567a-8400-449c-ae93-cb021423d211', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:09:55');
INSERT INTO `sys_user_opt` VALUES (1641852340011040, '219f6acb-44dd-4eb5-83b3-8384149d5e9a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:10:05');
INSERT INTO `sys_user_opt` VALUES (1641852526657568, '82496c26-874d-4f49-850f-d65f6b825af3', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:11:34');
INSERT INTO `sys_user_opt` VALUES (1641852644098080, 'cf24be4a-ee11-4618-b71a-14c4999bc834', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:30');
INSERT INTO `sys_user_opt` VALUES (1641852646195232, 'bf188fd7-044e-4e0b-9d8c-e77051573a82', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:30');
INSERT INTO `sys_user_opt` VALUES (1641852646195264, '6f565602-d856-459d-8c26-6618d1c5fcbc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:31');
INSERT INTO `sys_user_opt` VALUES (1641852646195296, '2bf9b0c3-2607-4b12-b41d-d40f0b2de833', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:31');
INSERT INTO `sys_user_opt` VALUES (1641852648292384, '90ba8ee9-5775-4b7f-8473-cb9b0ab8b1bf', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:31');
INSERT INTO `sys_user_opt` VALUES (1641852652486688, '6ff15931-00b0-4f08-a894-14cf275a834e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:33');
INSERT INTO `sys_user_opt` VALUES (1641852652486720, '28b44bb6-34a4-4d9f-8685-f16d0897a61c', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:34');
INSERT INTO `sys_user_opt` VALUES (1641852654583840, 'e28f9a3c-46f7-45b2-b182-3c7483b16ba5', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:34');
INSERT INTO `sys_user_opt` VALUES (1641852654583872, '5b5df0ba-1f55-4abf-8bd5-ac7c9f930083', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:34');
INSERT INTO `sys_user_opt` VALUES (1641852654583904, 'dab03569-e474-4fe3-abc7-b3e91e6e59dc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:34');
INSERT INTO `sys_user_opt` VALUES (1641852658778144, 'd11acea4-4c2c-400d-b40a-a8a62ad8b598', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:37');
INSERT INTO `sys_user_opt` VALUES (1641852658778176, '00149397-51bb-4f60-9b5e-b2949aadad34', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:12:37');
INSERT INTO `sys_user_opt` VALUES (1641852665069600, 'f1444bb7-12ea-45ca-b633-27a24d30b58d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'no', '2024-10-22 15:12:39');
INSERT INTO `sys_user_opt` VALUES (1641852665069632, 'fd3a176e-77cc-4014-92df-79934d41dd56', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'no', '2024-10-22 15:12:39');
INSERT INTO `sys_user_opt` VALUES (1641852665069664, 'b4f6134c-d95c-4464-babe-6c615ed5151b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'no', '2024-10-22 15:12:40');
INSERT INTO `sys_user_opt` VALUES (1641852667166752, '73eb0519-e057-4e1e-b746-37981f67d18a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'no', '2024-10-22 15:12:40');
INSERT INTO `sys_user_opt` VALUES (1641852667166784, 'e69402a0-e7fb-4b89-850f-85c651177bd1', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'no', '2024-10-22 15:12:40');
INSERT INTO `sys_user_opt` VALUES (1641852692332576, 'e9e48189-1c23-42b9-93b2-8402f456907d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'no', '2024-10-22 15:12:53');
INSERT INTO `sys_user_opt` VALUES (1641852805578784, '9c03e717-ea69-4357-9341-a2ca5be805b9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:47');
INSERT INTO `sys_user_opt` VALUES (1641852818161696, '9d9fda47-53e8-4bab-8fad-991ee5d74912', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:53');
INSERT INTO `sys_user_opt` VALUES (1641852818161728, 'ada63ca6-80dc-4f24-b3b4-3a4e8c58e81a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:53');
INSERT INTO `sys_user_opt` VALUES (1641852820258848, 'f79ea234-7db2-4e41-82f9-7733fac2914d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:53');
INSERT INTO `sys_user_opt` VALUES (1641852824453152, '5a9b3c63-8b77-4084-9e57-e5af9c4e0c0d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:55');
INSERT INTO `sys_user_opt` VALUES (1641852824453184, '2051533f-bdef-4318-9144-52103305f7aa', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:55');
INSERT INTO `sys_user_opt` VALUES (1641852824453216, '0c2bc593-9e9a-4a96-8846-8d6b23bb9e1e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:56');
INSERT INTO `sys_user_opt` VALUES (1641852824453248, '5512bf22-9654-4bdd-b3b6-5a3a8391c473', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:56');
INSERT INTO `sys_user_opt` VALUES (1641852824453280, '8eb71eb0-971e-4284-afa2-b1522caf17f8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:56');
INSERT INTO `sys_user_opt` VALUES (1641852826550304, '3819ed16-0cc8-4d4b-a3ef-0c64093d7f23', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:56');
INSERT INTO `sys_user_opt` VALUES (1641852832841760, '95687f54-06f1-4841-b1aa-bd809a772acc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:13:59');
INSERT INTO `sys_user_opt` VALUES (1641852832841792, 'b8b3c23e-d8c7-48c6-b507-76375c57b910', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:00');
INSERT INTO `sys_user_opt` VALUES (1641852832841824, 'd861e6e9-dee4-4dce-96ba-89e88d90aae0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:00');
INSERT INTO `sys_user_opt` VALUES (1641852841230368, '3ace331e-fc2f-438a-8edf-eeed45d502f9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:03');
INSERT INTO `sys_user_opt` VALUES (1641852845424672, '683c0e69-baa9-4b07-84ff-a03f32693b8c', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:05');
INSERT INTO `sys_user_opt` VALUES (1641852849618976, 'bb594241-c478-47ab-86e3-97d790289c72', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:08');
INSERT INTO `sys_user_opt` VALUES (1641852851716128, 'd88d7d81-b7b5-46c2-be2b-ffbe00f39bd2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:08');
INSERT INTO `sys_user_opt` VALUES (1641852851716160, '16bf2b96-23fc-4119-bec7-2a73ae2465b6', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:08');
INSERT INTO `sys_user_opt` VALUES (1641852851716192, '34f8e1d5-f894-4515-9757-a3f15f772611', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:08');
INSERT INTO `sys_user_opt` VALUES (1641852851716224, '30aa731f-61ee-42cc-84f2-bb07a5e59166', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:09');
INSERT INTO `sys_user_opt` VALUES (1641852864299040, 'ac6186fe-56de-49b0-9082-b505e45161e2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:15');
INSERT INTO `sys_user_opt` VALUES (1641852927213600, '5e518c1a-2a03-4dc4-b50a-fc30dee4aefc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":\"\",\"password\":\"\",\"nickname\":\"测试一下修改啦\",\"avatar\":0,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:14:44');
INSERT INTO `sys_user_opt` VALUES (1641852931407904, '6b76a80a-1432-4848-8f23-dbc41e1f8c48', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":\"\",\"password\":\"\",\"nickname\":\"测试一下修改啦\",\"avatar\":0,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:14:46');
INSERT INTO `sys_user_opt` VALUES (1641852933505056, '1a06cde0-1184-42f3-a400-892d047fc42c', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":\"\",\"password\":\"\",\"nickname\":\"测试一下修改啦\",\"avatar\":0,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:14:47');
INSERT INTO `sys_user_opt` VALUES (1641852937699360, '1765d9a5-56b3-47be-b783-c6b807f8cee2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:50');
INSERT INTO `sys_user_opt` VALUES (1641852939796512, 'd3ac6099-a1d5-4632-85be-d14bd5ba2fe1', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:51');
INSERT INTO `sys_user_opt` VALUES (1641852939796544, '6f1f406f-6d18-404b-af5a-1ae536d9ca64', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:51');
INSERT INTO `sys_user_opt` VALUES (1641852939796576, '5ffa915e-0749-475a-902c-761093240f42', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:51');
INSERT INTO `sys_user_opt` VALUES (1641852941893664, '3dda1e02-0ff1-4317-8190-36b4b37282d5', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:51');
INSERT INTO `sys_user_opt` VALUES (1641852941893696, 'd71d6388-edff-4515-ba90-d1a44d3590b5', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:14:51');
INSERT INTO `sys_user_opt` VALUES (1641853243883552, 'd3da13af-0172-4ec2-998b-63251c432860', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":\"测试啦\",\"nickname\":\"测试一下修改啦\",\"avatar\":2,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:17:16');
INSERT INTO `sys_user_opt` VALUES (1641853248077856, '151c0d9d-5dbc-491f-8908-01c6b9a84f84', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":\"测试啦\",\"nickname\":\"测试一下修改啦\",\"avatar\":2,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:17:17');
INSERT INTO `sys_user_opt` VALUES (1641853250175008, '0d6d9654-c41e-4944-8776-2744ebf52e9e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":\"测试啦\",\"nickname\":\"测试一下修改啦\",\"avatar\":2,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:17:18');
INSERT INTO `sys_user_opt` VALUES (1641853258563616, 'b429b446-5d4c-4e6f-974d-1cea0c660e04', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":null,\"nickname\":\"测试一下修改啦\",\"avatar\":2,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:17:22');
INSERT INTO `sys_user_opt` VALUES (1641853262757920, '5c4943e1-7be2-4ab4-9c15-ec1e32fbc0f5', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":null,\"nickname\":\"测试一下修改啦11\",\"avatar\":2,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:17:25');
INSERT INTO `sys_user_opt` VALUES (1641853269049376, 'fd85543c-47a7-4ab9-81a3-b03c9321faa0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:17:28');
INSERT INTO `sys_user_opt` VALUES (1641853338255392, '8764554f-9da7-4a34-bea1-b6964278b66a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/update', '{\"id\":1641852256124960,\"username\":\"tes001\",\"nickname\":\"测试一下修改啦\",\"avatar\":2,\"phone\":\"147258369\"}', 'yes', '2024-10-22 15:18:00');
INSERT INTO `sys_user_opt` VALUES (1641853348741152, '466e73e5-e3c9-43b0-bce0-a7b023478720', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:18:05');
INSERT INTO `sys_user_opt` VALUES (1641853348741184, '547763c1-f3db-40cb-8260-867d00f3370a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:18:06');
INSERT INTO `sys_user_opt` VALUES (1641853350838304, '4f752741-0876-4aaf-a167-9e6a1f49c41d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:18:06');
INSERT INTO `sys_user_opt` VALUES (1641853350838336, '22d988b0-a929-4f87-b1be-2fac0182022d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:18:07');
INSERT INTO `sys_user_opt` VALUES (1641853369712672, 'f9824b74-82bb-4fbb-8770-85cd26a803cb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:18:15');
INSERT INTO `sys_user_opt` VALUES (1641853378101280, 'f972ee18-5cee-410d-bccc-17e4515cf9e1', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/role', '{id=1641852256124960, type=role}', 'yes', '2024-10-22 15:18:19');
INSERT INTO `sys_user_opt` VALUES (1641853388587040, '2c2e2923-75a8-403c-ad07-7dfc9bd2c3a3', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/all', '{id=1641852256124960, type=all}', 'yes', '2024-10-22 15:18:24');
INSERT INTO `sys_user_opt` VALUES (1641853455695904, '5e651f90-a810-4d99-a047-92e6e3ba82ab', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-captcha/captcha-image', '{}', 'yes', '2024-10-22 15:18:56');
INSERT INTO `sys_user_opt` VALUES (1641853864640544, '26bc8332-7311-4136-9d99-40ec2eb0edea', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:22:11');
INSERT INTO `sys_user_opt` VALUES (1641853864640576, '71bfebcb-6ce5-47a0-8c1a-55a368af546e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:22:12');
INSERT INTO `sys_user_opt` VALUES (1641853866737696, 'e1d24dd7-1e2d-4c33-a2b1-5a030d1adfc4', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:22:12');
INSERT INTO `sys_user_opt` VALUES (1641853866737728, 'a6d90db7-c13b-4a45-84c7-7e6016812fb5', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:22:13');
INSERT INTO `sys_user_opt` VALUES (1641853868834848, 'f14347e2-9c69-4f37-842d-05c33dbed3b5', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:22:13');
INSERT INTO `sys_user_opt` VALUES (1641853868834880, 'c30506f6-da97-443c-8e4a-3624913e46a0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:22:13');
INSERT INTO `sys_user_opt` VALUES (1641853877223456, '2b1db620-756c-4ca9-8851-43cac9a2f326', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:22:17');
INSERT INTO `sys_user_opt` VALUES (1641853919166496, 'c7b84d8f-e999-4e33-bdcb-f44d35e91a58', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-captcha/captcha-image', '{}', 'yes', '2024-10-22 15:22:37');
INSERT INTO `sys_user_opt` VALUES (1641854028218400, 'b0d3c054-8d3b-4b9d-b980-a3c2fc787a28', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:23:29');
INSERT INTO `sys_user_opt` VALUES (1641854028218432, '4dcc4b77-4993-4ab4-b8fb-ee04ab5ace09', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:23:30');
INSERT INTO `sys_user_opt` VALUES (1641854030315552, '67d751e3-b958-4f57-a7e8-0f323e107765', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:23:30');
INSERT INTO `sys_user_opt` VALUES (1641854030315584, '45be7c06-c671-4a9d-a333-9750f4c5dbe8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:23:30');
INSERT INTO `sys_user_opt` VALUES (1641854030315616, '9eb38c15-6296-4abb-81a6-058bbc7bc66b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:23:30');
INSERT INTO `sys_user_opt` VALUES (1641854030315648, '5c3bd1d2-d798-46b9-b4e8-63f9ec732bcf', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:23:31');
INSERT INTO `sys_user_opt` VALUES (1641854059675680, 'ffdd64a0-c1cf-45ed-bfdc-889a9cac52a7', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:23:44');
INSERT INTO `sys_user_opt` VALUES (1641854074355744, 'bb5e4561-47d3-40d1-a7a6-fe507965c29b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:23:52');
INSERT INTO `sys_user_opt` VALUES (1641854076452896, '1daebb4a-2c03-4780-9b4f-3a7198786408', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:23:52');
INSERT INTO `sys_user_opt` VALUES (1641854076452928, '03a4e833-a77f-4fb7-92a9-b94d5e1276ee', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:23:53');
INSERT INTO `sys_user_opt` VALUES (1641854076452960, '433c6444-acc4-4437-bcb3-0bf912d8e201', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:23:53');
INSERT INTO `sys_user_opt` VALUES (1641854078550048, '8384e240-87ba-4204-982e-94390ae34517', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 15:23:53');
INSERT INTO `sys_user_opt` VALUES (1641854091132960, 'dffc6d2b-70d8-4329-ba0f-0add856eb3a8', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:24:00');
INSERT INTO `sys_user_opt` VALUES (1641854093230112, '32542e22-b105-4813-be45-340dd62bcd7c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:24:00');
INSERT INTO `sys_user_opt` VALUES (1641854093230144, '9363cf97-b72d-485a-8d18-6cddc99430b1', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:24:01');
INSERT INTO `sys_user_opt` VALUES (1641854093230176, '386c7a13-a7e9-4ea0-81ec-255e2d0f3629', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:24:01');
INSERT INTO `sys_user_opt` VALUES (1641854095327264, '036748ab-73c3-4efd-b321-8628419cc2c8', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:24:01');
INSERT INTO `sys_user_opt` VALUES (1641854164533280, 'a97fa4ea-c7ac-4723-9d59-f3477f322a90', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:34');
INSERT INTO `sys_user_opt` VALUES (1641854164533312, '53908298-d465-44fe-990e-9af8a749885a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:35');
INSERT INTO `sys_user_opt` VALUES (1641854166630432, 'dad15313-9f83-453b-a2a3-c73f549e18e9', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:35');
INSERT INTO `sys_user_opt` VALUES (1641854166630464, '41fc3931-6c25-403e-aaa2-dab743dfee2b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:35');
INSERT INTO `sys_user_opt` VALUES (1641854166630496, '1a08afca-844f-403b-8287-906fb9f15037', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:35');
INSERT INTO `sys_user_opt` VALUES (1641854166630528, '4174c17a-6f2f-4ebd-b204-6a96bfaa94f0', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:36');
INSERT INTO `sys_user_opt` VALUES (1641854166630560, '4402260e-585d-41e3-8fb4-9fbd4a9f6e9d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:36');
INSERT INTO `sys_user_opt` VALUES (1641854168727584, '8885a3e3-c803-4c0d-9fd6-b498cdeeef6b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 15:24:36');
INSERT INTO `sys_user_opt` VALUES (1641854193893408, 'c65e176b-43dd-462c-ad11-41180928c64e', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:24:48');
INSERT INTO `sys_user_opt` VALUES (1641854193893440, '7310c61c-beeb-4eb8-9051-1e5440c5325c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:24:49');
INSERT INTO `sys_user_opt` VALUES (1641854193893472, '0af24462-2f74-4a62-9cc1-6ce5c2270759', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:24:49');
INSERT INTO `sys_user_opt` VALUES (1641854193893504, 'b2480449-3a90-498d-9244-e9d5b36b8905', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:24:49');
INSERT INTO `sys_user_opt` VALUES (1641854195990560, 'c11618f0-b2c9-412a-918a-9b1680978e64', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:24:49');
INSERT INTO `sys_user_opt` VALUES (1641854195990592, '52b7ee4e-491b-4e01-8a9a-4446252561fe', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:24:49');
INSERT INTO `sys_user_opt` VALUES (1641854195990624, '96fd56a4-d6e1-467e-a797-389b1fdd7ec0', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:24:49');
INSERT INTO `sys_user_opt` VALUES (1641854311333920, '10a980e8-f487-4de8-9ddf-42d8f23a9ad3', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:25:45');
INSERT INTO `sys_user_opt` VALUES (1641854313431072, '095c464d-20ab-47ce-b2e4-9ed5aa3bf9c4', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:25:45');
INSERT INTO `sys_user_opt` VALUES (1641854313431104, '2c61e6cd-3e8c-48a1-b274-99717a26a4e8', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:25:46');
INSERT INTO `sys_user_opt` VALUES (1641854313431136, '7f1af78c-1f69-4d60-92f1-9cbda1bb2ba3', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:25:46');
INSERT INTO `sys_user_opt` VALUES (1641854439260224, '348d2c72-eeb8-4918-a1c3-a07404a8d4ca', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'yes', '2024-10-22 15:26:46');
INSERT INTO `sys_user_opt` VALUES (1641854453940288, '76073de9-656c-4e62-a746-1d5cb07d4128', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'yes', '2024-10-22 15:26:53');
INSERT INTO `sys_user_opt` VALUES (1641854462328864, '593c6391-ee69-4238-bd78-a591a90cb6d2', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:26:57');
INSERT INTO `sys_user_opt` VALUES (1641854470717472, 'b1226dc0-5810-4140-aaa9-3b0a076f1d2e', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 15:27:00');
INSERT INTO `sys_user_opt` VALUES (1641854854496288, 'f2d95ef1-7e8c-4e89-b9e2-87c4d7f3d26f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:03');
INSERT INTO `sys_user_opt` VALUES (1641854856593440, '7f42f461-9431-4b7b-b3ea-3669a03ca3db', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:04');
INSERT INTO `sys_user_opt` VALUES (1641854856593472, '5fab4777-744f-422a-bdce-167436d3a856', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:05');
INSERT INTO `sys_user_opt` VALUES (1641854858690592, 'fb0f6c93-e020-4948-b4f2-7650ec7b8423', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:06');
INSERT INTO `sys_user_opt` VALUES (1641854860787744, '250f9e47-2a47-4c3c-8087-179b09c18c8d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:06');
INSERT INTO `sys_user_opt` VALUES (1641854860787776, '635774fb-05f7-49d3-8e21-0cc3b03b8077', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:06');
INSERT INTO `sys_user_opt` VALUES (1641854860787808, '9dc89e1f-214d-49d1-bd6b-2d513f51d920', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:07');
INSERT INTO `sys_user_opt` VALUES (1641854862884896, 'aa442873-3610-4674-b8f5-542f897c70ff', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:07');
INSERT INTO `sys_user_opt` VALUES (1641854862884928, '49c9656f-55a6-4854-9154-426655839173', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:07');
INSERT INTO `sys_user_opt` VALUES (1641854862884960, 'a35d1853-d82f-4f0d-834a-44d6f539cdce', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:08');
INSERT INTO `sys_user_opt` VALUES (1641854864982048, '132b1ce6-654f-44b8-b2ef-be1cc4cdcb02', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:08');
INSERT INTO `sys_user_opt` VALUES (1641854864982080, '443be98f-0e0c-4646-adad-49c7818f8c8d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:09');
INSERT INTO `sys_user_opt` VALUES (1641854867079200, '0bfb6ce3-23f8-47ce-a922-db97ffb46c63', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:09');
INSERT INTO `sys_user_opt` VALUES (1641854867079232, 'dc6a48e4-1b0b-4f78-8535-4ec2c0fab0eb', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":0,\"menuName\":\"测试-首页\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 15:30:09');
INSERT INTO `sys_user_opt` VALUES (1641857492713504, '0a2570f3-7610-4a98-85db-ef6de45b3fd8', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:01');
INSERT INTO `sys_user_opt` VALUES (1641857494810656, 'f2620bf3-fd59-4766-90fd-5d6128d5fdb3', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:02');
INSERT INTO `sys_user_opt` VALUES (1641857496907808, 'de1ac4f2-8541-4a05-84c6-d0dee0d374c2', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:03');
INSERT INTO `sys_user_opt` VALUES (1641857496907840, '01ffc0e4-e1db-4b3f-9430-31f0a97fd3fe', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:04');
INSERT INTO `sys_user_opt` VALUES (1641857574502432, '12cd163d-39ce-4852-80aa-50346f538262', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:40');
INSERT INTO `sys_user_opt` VALUES (1641857576599584, 'dd3e2c13-a140-43be-92c3-db8f327e4170', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:42');
INSERT INTO `sys_user_opt` VALUES (1641857578696736, '76b2b04a-d66e-43cb-a7b8-1ebd2dae3bea', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:43');
INSERT INTO `sys_user_opt` VALUES (1641857578696768, 'a00c40d2-7e30-4abe-b6f0-d4af29918d4c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:43');
INSERT INTO `sys_user_opt` VALUES (1641857578696800, 'c3bdc07d-c43a-4e2b-8256-1c8d041a8889', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:43');
INSERT INTO `sys_user_opt` VALUES (1641857580793888, 'e204c409-e8d5-421f-86ce-8127e9bbba6b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:43');
INSERT INTO `sys_user_opt` VALUES (1641857605959712, '1254e3bc-c30f-4238-a289-1fee383fbeba', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 15:51:55');
INSERT INTO `sys_user_opt` VALUES (1641857668874272, '6aa8a239-1a07-496e-a135-0a2308ebf1e6', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:52:26');
INSERT INTO `sys_user_opt` VALUES (1641857670971424, '88a82444-dd56-40c0-89fa-5f466f171987', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:52:26');
INSERT INTO `sys_user_opt` VALUES (1641857670971456, 'b81ea31d-ec0c-4ef7-9146-c5e80df7d9f4', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:52:27');
INSERT INTO `sys_user_opt` VALUES (1641857670971488, 'd4efcbdb-ef53-4e63-a850-a3f1db8b5759', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:52:27');
INSERT INTO `sys_user_opt` VALUES (1641857673068576, '94b14b36-9f4a-4a24-acdc-305583e9f8c6', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:52:27');
INSERT INTO `sys_user_opt` VALUES (1641857673068608, 'f0af0596-3b39-45b6-a56c-2a3255354ecc', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:52:27');
INSERT INTO `sys_user_opt` VALUES (1641857673068640, '3a7ea076-190e-43ba-85eb-b5cfdfcb3a8c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:52:28');
INSERT INTO `sys_user_opt` VALUES (1641857782120480, 'c0a02408-acc0-484d-94c0-c14805420d85', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:53:19');
INSERT INTO `sys_user_opt` VALUES (1641857784217632, '93e5eecc-96a6-4b1e-9e80-0043a1884ac6', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:53:20');
INSERT INTO `sys_user_opt` VALUES (1641857794703392, 'f1081c66-fd16-42b6-bfba-a1bd5e1639b2', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:53:25');
INSERT INTO `sys_user_opt` VALUES (1641857993932832, '79f6d1c9-5904-435c-b2d8-c94b63f0f9e8', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:00');
INSERT INTO `sys_user_opt` VALUES (1641858102984736, '81271c0a-eb20-47e6-b93b-5aa78ba38fd1', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:52');
INSERT INTO `sys_user_opt` VALUES (1641858109276192, '4aaaae08-031c-4a65-95dd-db20788ef51a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:56');
INSERT INTO `sys_user_opt` VALUES (1641858109276224, 'e2c09197-cbfd-4752-b64d-9f168088ba51', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:56');
INSERT INTO `sys_user_opt` VALUES (1641858111373344, '60b7b742-732c-4c74-903c-da2366f85de3', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:56');
INSERT INTO `sys_user_opt` VALUES (1641858111373376, '818082b9-77c4-44fd-81f0-33f14817a845', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:57');
INSERT INTO `sys_user_opt` VALUES (1641858111373408, '4921686e-55a2-4ffe-a24c-a803e6cdcd3d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:57');
INSERT INTO `sys_user_opt` VALUES (1641858113470496, 'dbe776e5-eae4-4136-8a91-5712f5807fed', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:57');
INSERT INTO `sys_user_opt` VALUES (1641858113470528, 'b9b293a3-79e1-4aa8-a335-d3d0a7d1730a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:55:58');
INSERT INTO `sys_user_opt` VALUES (1641858375614496, '8e562564-07a2-4753-b64c-509d4d6bc143', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 15:58:02');
INSERT INTO `sys_user_opt` VALUES (1641858696478784, '1b699fa4-c1c0-4738-b3cf-c090a971bcf6', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test002\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试性别\",\"sex\":\"male\",\"avatar\":0,\"phone\":\"111111111\",\"userType\":\"pc\"}', 'yes', '2024-10-22 16:00:35');
INSERT INTO `sys_user_opt` VALUES (1641858700673056, 'f6418fc5-d511-4928-85ab-1a909a1bebcc', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/add', '{\"username\":\"test002\",\"password\":\"574da0e6fc0e9903835c149fda037f9976351b71430b6cb881d8eb5bc2d40d454cf7aa934ae4ccc9e7f5b7ea71de8adac5b501548fea28308328a8ef38b5c3f9ac8ef076400d2d101bba241d7d17a70750b74a1d9df727ced0edc30ea15ceb40d7bb4da991a8\",\"nickname\":\"测试性别\",\"sex\":\"male\",\"avatar\":0,\"phone\":\"111111111\",\"userType\":\"pc\"}', 'no', '2024-10-22 16:00:38');
INSERT INTO `sys_user_opt` VALUES (1641858711158816, '07ed02cd-639d-4cf7-9564-5184d56d8324', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/page', '{}', 'yes', '2024-10-22 16:00:43');
INSERT INTO `sys_user_opt` VALUES (1641859841523744, '1fce0a0f-88c6-4a68-8528-767c26d18d80', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 16:09:42');
INSERT INTO `sys_user_opt` VALUES (1641859843620896, '0221dad0-e1e3-41cf-91fb-7cb6fb843f8c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 16:09:43');
INSERT INTO `sys_user_opt` VALUES (1641859845718048, '2f45cabd-cc9d-47b6-b6f8-e6be8bfaa3b1', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 16:09:43');
INSERT INTO `sys_user_opt` VALUES (1641859845718080, 'c44351ac-9da0-4105-b427-e5f402f986ba', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 16:09:43');
INSERT INTO `sys_user_opt` VALUES (1641859845718112, 'af7d33f2-7676-4ed8-b545-2c4155999041', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 16:09:44');
INSERT INTO `sys_user_opt` VALUES (1641859952672800, '430235a8-64aa-405b-8889-b27c2c7fa70e', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 16:10:34');
INSERT INTO `sys_user_opt` VALUES (1641860172873760, '949e024b-5f5f-4de2-b4a4-8cbb1e09ba6f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 16:12:19');
INSERT INTO `sys_user_opt` VALUES (1641860183359520, 'cd202057-dedc-4afd-8b4d-773778c9194e', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:12:24');
INSERT INTO `sys_user_opt` VALUES (1641860193845280, 'e963a27f-e23e-409b-8c55-30eebccc2023', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:12:30');
INSERT INTO `sys_user_opt` VALUES (1641860527292448, 'fb157ce1-5c19-4c92-903f-ee597b00da26', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:15:08');
INSERT INTO `sys_user_opt` VALUES (1641860529389600, 'ac0e6bb3-dc78-4f86-b5af-8b418265effe', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:15:09');
INSERT INTO `sys_user_opt` VALUES (1641860529389632, 'afc5939c-4fef-4d7d-a10d-1828c3027ecb', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:15:10');
INSERT INTO `sys_user_opt` VALUES (1641860529389664, 'b409c253-5f38-4b84-a494-c9bd55b32b22', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:15:10');
INSERT INTO `sys_user_opt` VALUES (1641860531486752, 'b513da3e-8da8-4700-8e91-76d28a57fa87', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:15:10');
INSERT INTO `sys_user_opt` VALUES (1641860680384544, '46e80df7-1dfa-4723-b4ea-cd01e3a3f6c4', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:16:22');
INSERT INTO `sys_user_opt` VALUES (1641860695064608, 'f9509b68-92b6-409b-aa78-3cb43e0e67b0', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854453940256', 'yes', '2024-10-22 16:16:29');
INSERT INTO `sys_user_opt` VALUES (1641860699258912, '0912b883-2659-457d-8e9e-154f2e2f3be9', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854453940256', 'no', '2024-10-22 16:16:31');
INSERT INTO `sys_user_opt` VALUES (1641860701356064, '56e07c15-a28b-4a7d-b20a-f3855e1d54e0', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854453940256', 'no', '2024-10-22 16:16:31');
INSERT INTO `sys_user_opt` VALUES (1641860701356096, 'b1366081-59e9-4f90-8aae-1b2d5880af71', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854453940256', 'no', '2024-10-22 16:16:32');
INSERT INTO `sys_user_opt` VALUES (1641860701356128, 'd2ce44db-a402-4af7-ab02-736232a62eb3', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854453940256', 'no', '2024-10-22 16:16:32');
INSERT INTO `sys_user_opt` VALUES (1641860707647520, '05cd9f02-2dc3-4538-b0ed-f603b886cfcb', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:16:34');
INSERT INTO `sys_user_opt` VALUES (1641860713938976, 'ed655350-01a4-4df6-8e1b-f3ca94985a8c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:16:38');
INSERT INTO `sys_user_opt` VALUES (1641860739104800, 'cc92ad1d-888c-43e7-abdd-3a213abd4a9c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:16:49');
INSERT INTO `sys_user_opt` VALUES (1641860751687744, '9cc30dfd-8752-46d3-abb6-978d7cfbfa1a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":1641854439260192,\"menuName\":\"测试-首页11\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'yes', '2024-10-22 16:16:56');
INSERT INTO `sys_user_opt` VALUES (1641860755882016, '9b3ec0b1-9c3b-4ed2-b3bc-b95e1f96af47', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/add', '{\"parentId\":1641854439260192,\"menuName\":\"测试-首页11\",\"menuUrl\":\"/sys/index\",\"permissions\":\"sys.index,sys.update,sys.insert\",\"menuType\":\"Menu\",\"icon\":\"\",\"sort\":0}', 'no', '2024-10-22 16:16:57');
INSERT INTO `sys_user_opt` VALUES (1641860762173472, '2b864da2-090b-43d6-a5fd-e43025259155', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:00');
INSERT INTO `sys_user_opt` VALUES (1641860831379488, '42e89f10-cb3d-4f7c-b596-1004be18b228', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/update', '{\"id\":1641854439260192,\"parentId\":null,\"menuName\":\"测试修改首页\",\"menuUrl\":null,\"permissions\":null,\"menuType\":null,\"icon\":null,\"sort\":0}', 'yes', '2024-10-22 16:17:34');
INSERT INTO `sys_user_opt` VALUES (1641860835573792, 'e5489bf2-f1ad-4877-b6a5-311c1b5f1b4b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/update', '{\"id\":1641854439260192,\"parentId\":null,\"menuName\":\"测试修改首页\",\"menuUrl\":null,\"permissions\":null,\"menuType\":null,\"icon\":null,\"sort\":0}', 'yes', '2024-10-22 16:17:36');
INSERT INTO `sys_user_opt` VALUES (1641860846059552, 'b305e7e4-ffb0-4f03-aba0-eb258376ac09', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:40');
INSERT INTO `sys_user_opt` VALUES (1641860862836768, 'b653f60a-e8c1-4699-957a-3f502b67b780', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:49');
INSERT INTO `sys_user_opt` VALUES (1641860864933920, 'd15d0235-5578-4937-a4c7-8d2cf26d6982', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:49');
INSERT INTO `sys_user_opt` VALUES (1641860864933952, '77ab37ba-cce8-4aff-b1f1-c53454a27f07', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:50');
INSERT INTO `sys_user_opt` VALUES (1641860864933984, '330200f4-199a-4e29-8bad-a6d291beeba6', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:50');
INSERT INTO `sys_user_opt` VALUES (1641860864934016, '63dadb58-6b75-45ee-9570-713eea2dd92a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:50');
INSERT INTO `sys_user_opt` VALUES (1641860867031072, '6d171996-85b4-469c-8d73-b9189158a8dd', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:17:50');
INSERT INTO `sys_user_opt` VALUES (1641861116592160, 'a405b98b-0908-4110-9b39-ebaddf85dba4', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:19:49');
INSERT INTO `sys_user_opt` VALUES (1641861116592192, 'd9a6bb22-5766-48ff-b15e-f946ebc3ba99', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:19:50');
INSERT INTO `sys_user_opt` VALUES (1641861187895328, 'b5d81949-380d-4024-ba4c-44e101a34c4d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:20:24');
INSERT INTO `sys_user_opt` VALUES (1641861202575392, '350270ec-6bde-47e2-acdf-22bf9d2d3f82', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854439260192', 'no', '2024-10-22 16:20:30');
INSERT INTO `sys_user_opt` VALUES (1641861204672544, '3f51a44b-35bf-4932-b1c2-d2a31e92a8f9', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854439260192', 'no', '2024-10-22 16:20:31');
INSERT INTO `sys_user_opt` VALUES (1641861204672576, 'ff735766-714f-489d-88bf-51fa17a7ae96', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-permission/del', '1641854439260192', 'no', '2024-10-22 16:20:32');
INSERT INTO `sys_user_opt` VALUES (1641861213061152, 'fc331dc7-04b3-4dda-8293-05f8a9812b0b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-22 16:20:35');
INSERT INTO `sys_user_opt` VALUES (1641861231935520, '381ac5c2-e60e-423f-b6d8-e8c6ebfd66bc', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-22 16:20:44');
INSERT INTO `sys_user_opt` VALUES (1641861242421280, 'd73c23a4-0a9d-4b0a-b6ab-02d42fab3b19', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/detail/1', '{roleId=1}', 'yes', '2024-10-22 16:20:49');
INSERT INTO `sys_user_opt` VALUES (1641861374541856, '8c8444ff-95d1-48cb-8e22-03615e2b1187', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-role/add', '{\"roleName\":\"admin\",\"roleCode\":\"admin\",\"authPermissionIdList\":[]}', 'no', '2024-10-22 16:21:53');
INSERT INTO `sys_user_opt` VALUES (1641861680726048, 'c3234f84-8bfb-4bcc-9e24-466802995993', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/base', '{id=1641852256124960, type=base}', 'yes', '2024-10-22 16:24:19');
INSERT INTO `sys_user_opt` VALUES (1641861695406112, '7ceee975-e888-42d1-a4db-e0f9b4bf7962', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/role', '{id=1641852256124960, type=role}', 'yes', '2024-10-22 16:24:25');
INSERT INTO `sys_user_opt` VALUES (1641861710086176, '3e4eb39a-9ac9-4c91-b85a-ca0c80947af1', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-type/1641852256124960/all', '{id=1641852256124960, type=all}', 'yes', '2024-10-22 16:24:33');
INSERT INTO `sys_user_opt` VALUES (1641870482472992, '3cdfb14c-ea88-4620-b196-97bf91daeca9', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:16');
INSERT INTO `sys_user_opt` VALUES (1641870484570144, 'a867598b-5edf-4321-87ce-76d84c3b8314', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:17');
INSERT INTO `sys_user_opt` VALUES (1641870486667296, '6a4dfa58-244c-4afa-9c15-13d844a0c025', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:18');
INSERT INTO `sys_user_opt` VALUES (1641870488764448, 'baa436f6-bd55-4dd3-90b1-4a28e4a5ba0e', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:18');
INSERT INTO `sys_user_opt` VALUES (1641870488764480, 'b17d4865-d7d4-42dc-a04c-be21b120e9b8', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:19');
INSERT INTO `sys_user_opt` VALUES (1641870488764512, '2e52ab6f-a225-4e54-95f4-5e2a69481704', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:19');
INSERT INTO `sys_user_opt` VALUES (1641870490861600, '4b870e95-6c84-4a20-b44d-4dec4c8a5bf0', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:19');
INSERT INTO `sys_user_opt` VALUES (1641870490861632, '96a427be-98ca-4584-a37a-ba64ced17b83', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:20');
INSERT INTO `sys_user_opt` VALUES (1641870492958752, '8b3bc5ff-a311-426c-bdc7-fd5bb0393a53', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:20');
INSERT INTO `sys_user_opt` VALUES (1641870492958784, '0adbee10-2b05-40fd-b34e-6e0ce0da3f94', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:34:20');
INSERT INTO `sys_user_opt` VALUES (1641870606204960, 'b407492d-0f1b-4273-994e-6e1d40bed757', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:35:14');
INSERT INTO `sys_user_opt` VALUES (1641870746714144, 'b39712d7-2ade-4c6f-a960-e907b1e61091', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:36:22');
INSERT INTO `sys_user_opt` VALUES (1641870862057504, '9c23b0a0-10f7-4748-9359-4cedae1cf08b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:37:17');
INSERT INTO `sys_user_opt` VALUES (1641870981595168, '321adbf0-14f0-4b9f-a7fc-4058354bf6eb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:38:14');
INSERT INTO `sys_user_opt` VALUES (1641871002566688, '3213c0bd-acf9-4734-b181-4878924d12c9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:38:23');
INSERT INTO `sys_user_opt` VALUES (1641871002566720, '840a4f3d-1434-48a6-b3a6-8fd1a2d0de4e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:38:24');
INSERT INTO `sys_user_opt` VALUES (1641871004663840, '5c5b0416-7537-4a6c-b525-501761fdeb31', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:38:24');
INSERT INTO `sys_user_opt` VALUES (1641871013052448, '96b6c6da-cfed-4e57-bfa1-14f1fd8f2dc2', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-22 17:38:28');
INSERT INTO `sys_user_opt` VALUES (1641871986130976, 'dc713a5b-bae9-451b-9fb4-2354be5697a8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-22 17:46:12');
INSERT INTO `sys_user_opt` VALUES (1641872193749024, '5bbf8fd3-4b5c-4d4c-94d4-b0c952243fa7', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-22 17:47:52');
INSERT INTO `sys_user_opt` VALUES (1641872212623392, '8d3e7538-ca2b-496f-86d8-965c19874368', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-22 17:48:00');
INSERT INTO `sys_user_opt` VALUES (1641998188544032, '251f2714-72cb-451d-84ef-595710292f65', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:29:11');
INSERT INTO `sys_user_opt` VALUES (1641998222098464, '4d79a8b1-62a9-450e-b50f-695a6db42f4a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-23 10:29:26');
INSERT INTO `sys_user_opt` VALUES (1641998305984544, 'b60c9943-e215-4fdf-9000-2cacb6e6d165', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:30:07');
INSERT INTO `sys_user_opt` VALUES (1641998320664608, 'bd9ef81d-9f1d-46ef-81d1-d250f1131124', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-23 10:30:14');
INSERT INTO `sys_user_opt` VALUES (1641998322761760, '55c2d557-217d-49e8-b3e6-ad764af96e73', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-23 10:30:15');
INSERT INTO `sys_user_opt` VALUES (1641998322761792, '71412685-6de5-4eca-a0c8-b94f0a097781', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-23 10:30:15');
INSERT INTO `sys_user_opt` VALUES (1641998324858912, 'bb6bdc21-86aa-4b60-97f9-4e7d35d1dff9', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-23 10:30:15');
INSERT INTO `sys_user_opt` VALUES (1641998337441824, '620c3f62-49ee-45d4-b425-efda4ad6761f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-23 10:30:21');
INSERT INTO `sys_user_opt` VALUES (1641998352121888, '7cfb22c0-96f5-44a0-916c-89ca8d0d11c5', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-23 10:30:28');
INSERT INTO `sys_user_opt` VALUES (1641998408744992, '33fbe3c2-c684-4f22-ba27-3e47bfe97b01', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-permission/page', '{}', 'yes', '2024-10-23 10:30:55');
INSERT INTO `sys_user_opt` VALUES (1641998433910816, 'f5d1f8b6-97c9-4364-8bb8-e7fbbc3ed40c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-role/add', '{\"roleName\":\"测试权限\",\"roleCode\":\"test\",\"authPermissionIdList\":[1641854439260192,1]}', 'no', '2024-10-23 10:31:07');
INSERT INTO `sys_user_opt` VALUES (1641998459076640, 'bcfef3a5-f5d8-4d0b-aaa7-6c6757951121', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-role/add', '{\"roleName\":\"测试权限\",\"roleCode\":\"test\",\"authPermissionIdList\":[1641854439260192,1641860751687712]}', 'yes', '2024-10-23 10:31:20');
INSERT INTO `sys_user_opt` VALUES (1641998488436768, 'bae34b3d-d1d6-46b7-beed-cb3ca7efad98', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-23 10:31:33');
INSERT INTO `sys_user_opt` VALUES (1641998507311136, '53760b29-529d-4dd5-957d-4e52ebd9d2e5', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/detail/1641998456979488', '{roleId=1641998456979488}', 'yes', '2024-10-23 10:31:43');
INSERT INTO `sys_user_opt` VALUES (1641998587002912, '18a7a553-8ab5-4d3a-992f-0aab66214889', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-role/update', '{\"id\":1641998456979488,\"roleName\":\"测试权限修改\",\"roleCode\":\"test-update\",\"authPermissionIdList\":null}', 'no', '2024-10-23 10:32:20');
INSERT INTO `sys_user_opt` VALUES (1641998691860544, 'ddebabb5-4351-4790-8d56-36690d291c5f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-role/update', '{\"id\":1641998456979488,\"roleName\":\"测试权限修改\",\"roleCode\":\"test-update\",\"authPermissionIdList\":[1641854439260192]}', 'yes', '2024-10-23 10:33:11');
INSERT INTO `sys_user_opt` VALUES (1641998702346272, 'c275a15d-ae35-41f4-94e2-8ec7c3618f15', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-23 10:33:16');
INSERT INTO `sys_user_opt` VALUES (1641998710734880, '64be5dcc-f846-4792-b454-e6f18ff1c3d5', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/detail/1641998456979488', '{roleId=1641998456979488}', 'yes', '2024-10-23 10:33:20');
INSERT INTO `sys_user_opt` VALUES (1641998729609248, '1423cd7c-073a-4b48-b8ed-ffec89af5651', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-role/update', '{\"id\":1641998456979488,\"roleName\":\"测试权限修改\",\"roleCode\":\"test-update\",\"authPermissionIdList\":[1641854439260192,1641860751687712]}', 'yes', '2024-10-23 10:33:29');
INSERT INTO `sys_user_opt` VALUES (1641998735900704, '02bba6e3-431c-4960-bb96-3a7b51094ef1', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/page', '{}', 'yes', '2024-10-23 10:33:31');
INSERT INTO `sys_user_opt` VALUES (1641998740095008, '86905cbb-ec95-44a3-9e1f-163e877ec4c8', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-role/detail/1641998456979488', '{roleId=1641998456979488}', 'yes', '2024-10-23 10:33:33');
INSERT INTO `sys_user_opt` VALUES (1641998964490304, '15db570c-a322-4d77-b39d-236c5f2d455e', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/user-role-relation', '{\"id\":1641852256124960,\"roleIdList\":[1641998456979488]}', 'yes', '2024-10-23 10:35:21');
INSERT INTO `sys_user_opt` VALUES (1641998985461824, '65a6a2d5-fdfb-4b22-8f96-323df6c72d12', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-user/user-role-relation', '{\"id\":1641852256124960,\"roleIdList\":[1641998456979488]}', 'yes', '2024-10-23 10:35:31');
INSERT INTO `sys_user_opt` VALUES (1641999146942496, '52a7cb2c-9274-42c8-9866-51bfcb56b09b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:36:48');
INSERT INTO `sys_user_opt` VALUES (1641999149039648, '48caa9b0-fde2-4015-a2a7-9c0952228b86', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:36:49');
INSERT INTO `sys_user_opt` VALUES (1641999151136800, 'e5244ccc-edc3-418b-8363-613305b17ce7', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:36:50');
INSERT INTO `sys_user_opt` VALUES (1641999153233952, '1370f0c9-d14f-4b14-be56-cc23e4e56d4a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:36:51');
INSERT INTO `sys_user_opt` VALUES (1641999155331104, 'ebb406b1-cf34-4f1c-a4a7-8af81ef725f9', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:36:51');
INSERT INTO `sys_user_opt` VALUES (1641999954346016, 'fd139fbd-d26b-4125-874b-d5c59ff81bd2', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 10:43:12');
INSERT INTO `sys_user_opt` VALUES (1641999971123232, '54563408-70aa-425b-891c-3981dbf5bec5', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-router', '{}', 'yes', '2024-10-23 10:43:21');
INSERT INTO `sys_user_opt` VALUES (1642020711956512, 'f33e6a78-9da0-462b-b152-a1644e34e07f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-config/page', '{}', 'yes', '2024-10-23 13:28:10');
INSERT INTO `sys_user_opt` VALUES (1642020758093856, '1164a69c-4d2b-49f1-8bb7-f47b69fe05fd', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:28:32');
INSERT INTO `sys_user_opt` VALUES (1642020760191008, '1d8a093f-718d-4482-891e-43fcb1d9e3dd', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:28:34');
INSERT INTO `sys_user_opt` VALUES (1642020760191040, 'bc29624c-0430-446b-90d1-e8b088f41e5b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:28:34');
INSERT INTO `sys_user_opt` VALUES (1642020762288160, 'd68313c3-193a-463f-acaa-5be010e4a35d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:28:34');
INSERT INTO `sys_user_opt` VALUES (1642020762288192, '763dd83a-dec5-4676-934a-29b3fbf2fa2a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:28:34');
INSERT INTO `sys_user_opt` VALUES (1642020768579616, '6624919c-ea7d-40e8-97e8-a72e2b1d6841', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:28:38');
INSERT INTO `sys_user_opt` VALUES (1642020927963168, '44b7e0ae-8660-4aca-9977-b044e556de29', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:29:53');
INSERT INTO `sys_user_opt` VALUES (1642020927963200, 'd4562471-1ea3-49a9-bcfc-13fc93a90062', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:29:54');
INSERT INTO `sys_user_opt` VALUES (1642020930060320, 'd7ef3879-f58e-4da2-821f-347048a0601b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:29:54');
INSERT INTO `sys_user_opt` VALUES (1642020930060352, '13dd3448-0654-4b22-bc96-daf2a5476686', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:29:54');
INSERT INTO `sys_user_opt` VALUES (1642020930060384, '05cb2dd7-06fe-4719-bf8e-c6fe16edc201', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:29:54');
INSERT INTO `sys_user_opt` VALUES (1642021242536032, '23b5d111-1358-46f3-930e-7335a8ef0c8d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试标题\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'yes', '2024-10-23 13:32:23');
INSERT INTO `sys_user_opt` VALUES (1642021487902752, '93532f5d-3941-4f31-80b2-dabbf463e19f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:34:20');
INSERT INTO `sys_user_opt` VALUES (1642021487902784, 'ebe56cee-133f-4a4e-87aa-3238674f6b49', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:34:21');
INSERT INTO `sys_user_opt` VALUES (1642021489999904, '545d961b-498e-47b6-8fe1-588acf7c068f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:34:21');
INSERT INTO `sys_user_opt` VALUES (1642021760532512, '477f4f05-fb8f-4278-ae89-20a53ef8bf0b', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试标题\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:36:30');
INSERT INTO `sys_user_opt` VALUES (1642021762629728, '2dedc443-8e69-4c26-ae58-db83d06ba963', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试标题\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:36:31');
INSERT INTO `sys_user_opt` VALUES (1642021764726880, '29103c7b-8bce-44d0-9a7f-90067a07c6be', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试标题\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:36:32');
INSERT INTO `sys_user_opt` VALUES (1642021764726976, '4e647b70-3389-4ac5-8779-701a42878519', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试标题\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:36:33');
INSERT INTO `sys_user_opt` VALUES (1642021785698368, 'a70f2859-ad83-4eb2-92fb-abbfb974ace2', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试标题\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"broadcast\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'yes', '2024-10-23 13:36:43');
INSERT INTO `sys_user_opt` VALUES (1642021791989792, 'd35db188-e5e7-4483-a79e-7188abed5887', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:46');
INSERT INTO `sys_user_opt` VALUES (1642021794086944, '755226a9-ea9f-46f4-b5bf-760b74c251a0', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:47');
INSERT INTO `sys_user_opt` VALUES (1642021796184096, 'd75fe1b4-eb65-4efc-b238-19a6376aa4db', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:47');
INSERT INTO `sys_user_opt` VALUES (1642021796184128, 'fe36233c-a3eb-4dbf-a91f-7b5b123b7c4d', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:47');
INSERT INTO `sys_user_opt` VALUES (1642021796184160, '1fc8b693-8c1c-4b01-976a-251d0f9cba82', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:48');
INSERT INTO `sys_user_opt` VALUES (1642021796184192, '94874b30-84e5-4fb8-864b-f43f022b6842', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:48');
INSERT INTO `sys_user_opt` VALUES (1642021798281248, '97427c76-8138-4266-b65e-102292315fb3', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:48');
INSERT INTO `sys_user_opt` VALUES (1642021798281280, '8c6d8565-e91b-4574-8b4a-611803628311', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:49');
INSERT INTO `sys_user_opt` VALUES (1642021800378400, 'a26702b2-a1d4-46ac-9d75-e3c72cec5056', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:36:49');
INSERT INTO `sys_user_opt` VALUES (1642021806669856, '4e6a60ea-065c-4329-841f-3da36c681367', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:36:53');
INSERT INTO `sys_user_opt` VALUES (1642021984927776, 'cf6f50f3-f7d0-444b-93db-d58e2213535c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:18');
INSERT INTO `sys_user_opt` VALUES (1642021987024928, '259f00ef-82ab-4428-8501-0bfe4416a2bb', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:18');
INSERT INTO `sys_user_opt` VALUES (1642021987024960, 'af28de4c-56cc-4d98-b51b-78da0251deb3', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:19');
INSERT INTO `sys_user_opt` VALUES (1642022003802144, '7950e8aa-bb87-4bdd-9967-8e4bda81ac0e', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:38:26');
INSERT INTO `sys_user_opt` VALUES (1642022033162304, '68fc54cf-1228-414e-b4f1-8e0d1781b341', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"broadcast\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'yes', '2024-10-23 13:38:41');
INSERT INTO `sys_user_opt` VALUES (1642022045745184, '0b1fbfa0-9f07-4cea-8292-b6486d768c59', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播2\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"broadcast\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'yes', '2024-10-23 13:38:46');
INSERT INTO `sys_user_opt` VALUES (1642022047842336, '6809ca40-df6f-4f68-976a-0bff12783ebe', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:38:48');
INSERT INTO `sys_user_opt` VALUES (1642022058328096, '13ec4dfc-e7ec-4a62-9674-e2a13eb28501', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:52');
INSERT INTO `sys_user_opt` VALUES (1642022060425248, 'f93b980c-1290-41db-8ac2-bcd1fbcb6310', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:53');
INSERT INTO `sys_user_opt` VALUES (1642022060425280, '52cd1ba2-6d99-46ff-929d-7c7c69d9194a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:53');
INSERT INTO `sys_user_opt` VALUES (1642022060425312, '242498a1-0a1a-4e0b-bde1-c5d224ab687c', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:54');
INSERT INTO `sys_user_opt` VALUES (1642022060425344, 'ba48bb95-0561-4345-b2ef-4410508fce4f', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:54');
INSERT INTO `sys_user_opt` VALUES (1642022060425376, '4293a833-0971-4418-8c48-9413eb77a156', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:38:54');
INSERT INTO `sys_user_opt` VALUES (1642022079299680, '1642d7ac-e8e8-4ae9-98da-b924923496ed', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播2\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:39:02');
INSERT INTO `sys_user_opt` VALUES (1642022079299776, '935cee18-5ebd-41f7-a43d-b6ee636c6df2', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播2\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:39:03');
INSERT INTO `sys_user_opt` VALUES (1642022081396832, 'ad7b5784-2d6f-4ad4-a7e5-1b72c60364eb', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播2\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:39:03');
INSERT INTO `sys_user_opt` VALUES (1642022081396928, '5a45f6f7-7ef5-4b92-a5b0-766a182c0322', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播2\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:39:03');
INSERT INTO `sys_user_opt` VALUES (1642022081397024, '7edba35f-0c8f-4a03-8642-9a5d738ba6ea', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播2\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":0,\"phone\":\"\",\"email\":\"\"}]}', 'no', '2024-10-23 13:39:04');
INSERT INTO `sys_user_opt` VALUES (1642022096076832, '9bd23261-457a-46fa-bff7-fc4d2e7fe217', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:39:11');
INSERT INTO `sys_user_opt` VALUES (1642022393872416, 'd5c13dec-1208-486a-a1f5-7a5776a16ff4', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:41:33');
INSERT INTO `sys_user_opt` VALUES (1642022425329760, 'db38bcb6-3764-4910-be4e-40d1d9c10acf', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-notice/send', '{\"title\":\"测试广播2\",\"content\":\"我是内容\",\"noticeType\":\"local\",\"messageStatus\":\"alone\",\"sysSendRequestList\":[{\"userId\":1,\"phone\":\"\",\"email\":\"\"}]}', 'yes', '2024-10-23 13:41:48');
INSERT INTO `sys_user_opt` VALUES (1642022431621152, 'ffd05964-ce1d-4fa8-8a72-e6b57070171a', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/page', '{}', 'yes', '2024-10-23 13:41:51');
INSERT INTO `sys_user_opt` VALUES (1642022446301216, '9a3bddd2-1298-4489-b43c-f1fc64fc5034', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:41:57');
INSERT INTO `sys_user_opt` VALUES (1642022446301248, 'bd8ecf22-f13e-45cd-9477-a28ed108cb60', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:41:58');
INSERT INTO `sys_user_opt` VALUES (1642022448398368, '19f13081-aa18-40a7-98cc-3e336127c1f9', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:41:58');
INSERT INTO `sys_user_opt` VALUES (1642022448398400, '1b602d7e-ef72-44ff-b5eb-464f5b1172c1', 'tes001', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:41:58');
INSERT INTO `sys_user_opt` VALUES (1642022500827168, '90c4bd84-b3e7-471d-82a9-e538b26a21df', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:42:23');
INSERT INTO `sys_user_opt` VALUES (1642022505021472, 'dd7edf45-6119-41a3-ac31-f77aeed793cf', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:42:25');
INSERT INTO `sys_user_opt` VALUES (1642022513410080, 'b13b2e94-8109-4392-b2c3-6492d87b271e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:42:30');
INSERT INTO `sys_user_opt` VALUES (1642022551158816, 'e908dd30-f21b-46c9-a4bf-da379445060b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-user/user-detail-base', '{}', 'yes', '2024-10-23 13:42:48');
INSERT INTO `sys_user_opt` VALUES (1642022572130336, '78c7dcd9-2b9f-4187-9b58-bd30ac54f624', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:42:58');
INSERT INTO `sys_user_opt` VALUES (1642022574227488, '7d7826fe-48d0-48ff-bf7b-6d322a0cc712', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:42:58');
INSERT INTO `sys_user_opt` VALUES (1642022576324640, '0c2d412e-6883-4567-bd7b-526a097e9d30', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:42:59');
INSERT INTO `sys_user_opt` VALUES (1642022578421792, '940d735e-5e34-4239-8990-4d373118676a', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:43:00');
INSERT INTO `sys_user_opt` VALUES (1642022580518944, 'c736b55f-0b98-427e-8def-8373b1e0a493', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:43:01');
INSERT INTO `sys_user_opt` VALUES (1642022601490464, '21711c17-7897-4aee-8d3b-f01a1fa7db0b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:43:11');
INSERT INTO `sys_user_opt` VALUES (1642022725222432, '0a459934-ca7e-472e-90fc-32ddea033acb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:44:10');
INSERT INTO `sys_user_opt` VALUES (1642022746193952, '95a2cd61-d514-4744-a280-dfcb98906c66', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:44:20');
INSERT INTO `sys_user_opt` VALUES (1642022800719904, 'ffc3b101-2fa6-4569-bc57-6a4c5fca291d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-notice/last-broad', '{}', 'yes', '2024-10-23 13:44:46');
INSERT INTO `sys_user_opt` VALUES (1642023325007904, '6da9ef70-6c12-4597-a2a1-8c737560a05f', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-mf', '', 'no', '2024-10-23 13:48:56');
INSERT INTO `sys_user_opt` VALUES (1642023408893984, '9ac34497-2dde-4e48-a506-cf8301907dd8', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-mf', '', 'no', '2024-10-23 13:49:36');
INSERT INTO `sys_user_opt` VALUES (1642030698594336, 'fbf69150-2fa7-44b8-9205-bd6d7cfbebe4', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-mf', '', 'yes', '2024-10-23 14:47:33');
INSERT INTO `sys_user_opt` VALUES (1642031084470304, '4a8a4327-cb44-4f08-b37f-3fcb86fb596e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-single-mf-business', '', 'yes', '2024-10-23 14:50:36');
INSERT INTO `sys_user_opt` VALUES (1642031088664640, 'eab755a9-bdb6-4ce1-9f0c-7801b348dbce', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-single-mf-business', '', 'yes', '2024-10-23 14:50:39');
INSERT INTO `sys_user_opt` VALUES (1642031092858944, '0b240836-dab6-4871-8d6e-c8fa875b90c0', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-single-mf-business', '', 'yes', '2024-10-23 14:50:41');
INSERT INTO `sys_user_opt` VALUES (1642031248048160, '776faa7b-4ea6-4cac-8827-7c3615f35788', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 14:51:54');
INSERT INTO `sys_user_opt` VALUES (1642032093200416, 'bfbb90d3-6cbe-462d-abcf-e52a506a2a4d', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 14:58:37');
INSERT INTO `sys_user_opt` VALUES (1642032122560544, 'f44bf885-80c6-4db7-a9f8-46da14d7338e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'no', '2024-10-23 14:58:52');
INSERT INTO `sys_user_opt` VALUES (1642032284041248, '8fd99e2e-81ee-4c97-bdb7-46c10ec349bb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'no', '2024-10-23 15:00:09');
INSERT INTO `sys_user_opt` VALUES (1642032321789984, '8715ec32-36f4-4fc4-9841-f2635b5a61a9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'no', '2024-10-23 15:00:26');
INSERT INTO `sys_user_opt` VALUES (1642032560865312, '7c80e920-5ccf-41d9-875a-e7f544a44295', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'no', '2024-10-23 15:02:20');
INSERT INTO `sys_user_opt` VALUES (1642032607002656, '034b6864-fd1d-4950-8725-c170709b186e', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'yes', '2024-10-23 15:02:43');
INSERT INTO `sys_user_opt` VALUES (1642032632168480, 'a5c271ff-a7b0-4745-89a6-bf4d653428b7', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'no', '2024-10-23 15:02:54');
INSERT INTO `sys_user_opt` VALUES (1642032634265632, '6de11970-6764-4403-b24b-e774c84d69eb', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'no', '2024-10-23 15:02:55');
INSERT INTO `sys_user_opt` VALUES (1642032634265664, 'bb9d4834-0abf-4442-ad69-05a649f22937', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031082373152', '{id=1642031082373152}', 'no', '2024-10-23 15:02:55');
INSERT INTO `sys_user_opt` VALUES (1642032638459936, '12330ecb-a92e-4667-9f6f-afbe0073f3c6', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:02:57');
INSERT INTO `sys_user_opt` VALUES (1642032646848544, 'cc479810-5a2f-44b5-9838-7168041ce8bc', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:03:01');
INSERT INTO `sys_user_opt` VALUES (1642032795746336, 'b424651e-67ce-4acb-8bc2-bb67d7955e41', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:04:13');
INSERT INTO `sys_user_opt` VALUES (1642032816717856, '1249cf0c-4691-4611-98df-7999747c753b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031088664608', '{id=1642031088664608}', 'yes', '2024-10-23 15:04:23');
INSERT INTO `sys_user_opt` VALUES (1642032825106464, 'cbd58ab2-faf9-425b-8dbc-d9df1ac925d7', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:04:27');
INSERT INTO `sys_user_opt` VALUES (1642032837689376, '6affb445-5cb3-4dc3-81f7-49e32f5667e4', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642031092858912', '{id=1642031092858912}', 'yes', '2024-10-23 15:04:33');
INSERT INTO `sys_user_opt` VALUES (1642032843980832, 'a10ddcd3-b7f4-410e-a7d0-32449825ef98', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:04:35');
INSERT INTO `sys_user_opt` VALUES (1642032860758080, 'd7f3d9f7-05ea-457d-9b83-b3889a3b4d20', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-single-mf-business', '', 'yes', '2024-10-23 15:04:44');
INSERT INTO `sys_user_opt` VALUES (1642032869146656, 'd7868594-0237-4842-905e-c421d7dded0b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:04:47');
INSERT INTO `sys_user_opt` VALUES (1642032881729568, '6cdc8724-d50c-46e2-8a24-0f8cef4a9af9', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-single-mf-business', '', 'yes', '2024-10-23 15:04:53');
INSERT INTO `sys_user_opt` VALUES (1642032885923872, '48a9408d-ae3f-4b74-8cbf-f8b2b050e94b', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:04:56');
INSERT INTO `sys_user_opt` VALUES (1642033028530208, 'e5f6edf6-f56c-4ccd-b93b-40e50ba3be58', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642032860758048', '{id=1642032860758048}', 'yes', '2024-10-23 15:06:03');
INSERT INTO `sys_user_opt` VALUES (1642033032724512, '22ed3391-5bfb-4893-9b4c-6c45593ec354', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642032860758048', '{id=1642032860758048}', 'no', '2024-10-23 15:06:05');
INSERT INTO `sys_user_opt` VALUES (1642033068376096, '313eaa62-218d-4372-a329-56bb478732c6', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:06:22');
INSERT INTO `sys_user_opt` VALUES (1642033124999200, '2bc42d20-d937-42c2-8af6-181626919006', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:06:49');
INSERT INTO `sys_user_opt` VALUES (1642033135484960, '87de5dd5-16af-4697-8330-01926eac7d39', 'admin', '192.168.56.1', '0|内网IP|内网IP', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642032860758048', '{id=1642032860758048}', 'yes', '2024-10-23 15:06:55');
INSERT INTO `sys_user_opt` VALUES (1642035767410752, 'c254cde7-5739-4c3a-94d6-b83efb6a38ff', 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'POST', '/sys-file/upload-single-mf-business', '', 'yes', '2024-10-23 15:27:49');
INSERT INTO `sys_user_opt` VALUES (1642035775799328, '1117e5f6-79e8-4e2c-9616-fcc3347250a5', 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:27:53');
INSERT INTO `sys_user_opt` VALUES (1642035788382240, '04496f2e-989f-4f07-adda-b45e9c39747a', 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642035767410720', '{id=1642035767410720}', 'yes', '2024-10-23 15:28:00');
INSERT INTO `sys_user_opt` VALUES (1642035790479392, 'cc11bbed-447e-4f71-a90a-dc7b8f255172', 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/del/1642035767410720', '{id=1642035767410720}', 'no', '2024-10-23 15:28:01');
INSERT INTO `sys_user_opt` VALUES (1642035796770848, '5136b8ec-75ee-4547-b055-49ca95a8636f', 'admin', '121.237.196.29', '中国|江苏省|南京市|电信', 'Chrome 12', 'Windows 10', 'GET', '/sys-file/page', '{}', 'yes', '2024-10-23 15:28:03');

SET FOREIGN_KEY_CHECKS = 1;
