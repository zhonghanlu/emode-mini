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

 Date: 29/11/2024 16:26:47
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
  `menu_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单path',
  `menu_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `permissions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:list,sys:user:save)',
  `menu_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型：目录、按钮、菜单',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识1未删除-1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限详细记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES (1, 0, '机构管理', 'org', '', '', 'Directory', NULL, 1, 1);
INSERT INTO `auth_permission` VALUES (2, 0, '课程管理', 'course', '', '', 'Directory', NULL, 2, 1);
INSERT INTO `auth_permission` VALUES (3, 0, '售课核销', 'sale', '', '', 'Directory', NULL, 3, 1);
INSERT INTO `auth_permission` VALUES (4, 0, '家校一体', 'school', '', '', 'Directory', NULL, 4, 1);
INSERT INTO `auth_permission` VALUES (5, 0, '学生端管理', 'student', '', '', 'Directory', NULL, 5, 1);
INSERT INTO `auth_permission` VALUES (6, 0, '运营管理', 'operate', '', '', 'Directory', NULL, 6, 1);
INSERT INTO `auth_permission` VALUES (7, 0, '系统管理', 'system', '', '', 'Directory', NULL, 7, 1);
INSERT INTO `auth_permission` VALUES (100, 1, '学生管理', 'stu', '/org/stu/index', 'org:stu:list', 'Menu', NULL, 1, -1);
INSERT INTO `auth_permission` VALUES (101, 1, '家长管理', 'patriarch', '/org/patriarch/index', 'org:patriarch:list', 'Menu', NULL, 2, -1);
INSERT INTO `auth_permission` VALUES (102, 1, '教师管理', 'tea', '/org/tea/index', 'org:tea:list', 'Menu', NULL, 3, -1);
INSERT INTO `auth_permission` VALUES (103, 1, '教室管理', 'room', '/org/room/index', 'org:room:list', 'Menu', NULL, 4, -1);
INSERT INTO `auth_permission` VALUES (104, 1, '校区管理', 'school', '/org/school/index', 'org:school:list', 'Menu', NULL, 5, -1);
INSERT INTO `auth_permission` VALUES (105, 2, '待核销管理', 'class', '/course/class/index', 'course:class:list', 'Menu', NULL, 1, -1);
INSERT INTO `auth_permission` VALUES (106, 2, '班级管理', 'grade', '/course/grade/index', 'course:grade:list', 'Menu', NULL, 2, -1);
INSERT INTO `auth_permission` VALUES (107, 2, '课表管理', 'schedule', '/course/schedule/index', 'course:schedule:list', 'Menu', NULL, 3, -1);
INSERT INTO `auth_permission` VALUES (108, 2, '上课管理', 'up', '/course/up/index', 'course:up:list', 'Menu', NULL, 4, -1);
INSERT INTO `auth_permission` VALUES (109, 2, '缺课管理', 'lack', '/course/lack/index', 'course:lack:list', 'Menu', NULL, 5, -1);
INSERT INTO `auth_permission` VALUES (110, 2, '补课管理', 'repair', '/course/repair/index', 'course:repair:list', 'Menu', NULL, 6, -1);
INSERT INTO `auth_permission` VALUES (111, 3, '商品管理', 'product', '/sale/product/index', 'sale:product:list', 'Menu', NULL, 1, -1);
INSERT INTO `auth_permission` VALUES (112, 3, '订单管理', 'order', '/sale/order/index', 'sale:order:list', 'Menu', NULL, 2, -1);
INSERT INTO `auth_permission` VALUES (113, 3, '线下收款', 'patch', '/sale/patch/index', 'sale:patcht:list', 'Menu', NULL, 3, -1);
INSERT INTO `auth_permission` VALUES (114, 4, '家教老师', NULL, NULL, NULL, 'Menu', NULL, 1, -1);
INSERT INTO `auth_permission` VALUES (115, 4, '家教申请', NULL, NULL, NULL, 'Menu', NULL, 2, -1);
INSERT INTO `auth_permission` VALUES (116, 4, '安心到家', NULL, NULL, NULL, 'Menu', NULL, 3, -1);
INSERT INTO `auth_permission` VALUES (117, 5, '待更新', NULL, NULL, NULL, 'Menu', NULL, 1, -1);
INSERT INTO `auth_permission` VALUES (118, 6, '总账管理', NULL, NULL, NULL, 'Menu', NULL, 1, -1);
INSERT INTO `auth_permission` VALUES (119, 6, '数据报表', NULL, NULL, NULL, 'Menu', NULL, 2, -1);
INSERT INTO `auth_permission` VALUES (120, 6, '海报管理', NULL, NULL, NULL, 'Menu', NULL, 3, -1);
INSERT INTO `auth_permission` VALUES (121, 6, '拉新管理', NULL, NULL, NULL, 'Menu', NULL, 4, -1);
INSERT INTO `auth_permission` VALUES (122, 6, '意见箱管理', NULL, NULL, NULL, 'Menu', NULL, 5, -1);
INSERT INTO `auth_permission` VALUES (123, 7, '用户管理', 'user', '/system/user/index', 'system:user:list', 'Menu', NULL, 1, 1);
INSERT INTO `auth_permission` VALUES (124, 7, '角色管理', 'role', '/system/role/index', 'system:role:list', 'Menu', NULL, 2, 1);
INSERT INTO `auth_permission` VALUES (125, 7, '权限管理', 'auth', '/system/auth/index', 'system:auth:list', 'Menu', NULL, 3, 1);
INSERT INTO `auth_permission` VALUES (126, 7, '参数配置', 'config', '/system/config/index', 'system:config:list', 'Menu', NULL, 4, 1);
INSERT INTO `auth_permission` VALUES (127, 7, '文件管理', 'file', '/system/file/index', 'system:file:list', 'Menu', NULL, 5, 1);
INSERT INTO `auth_permission` VALUES (128, 7, '日志管理', 'log', '', '', 'Menu', NULL, 6, 1);
INSERT INTO `auth_permission` VALUES (129, 7, '站内信', 'notice', '/system/notice/index', 'system:notice:list', 'Menu', NULL, 7, 1);
INSERT INTO `auth_permission` VALUES (200, 128, '登录日志', 'login_log', '/system/login_log/index', 'system:login_log:list', 'Menu', NULL, 1, 1);
INSERT INTO `auth_permission` VALUES (201, 128, '操作日志', 'opt_log', '/system/opt_log/index', 'system:opt_log:list', 'Menu', NULL, 2, 1);
INSERT INTO `auth_permission` VALUES (1000, 123, '用户查询', NULL, NULL, 'system:user:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1001, 123, '用户新增', NULL, NULL, 'system:user:add', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1002, 123, '用户修改', NULL, NULL, 'system:user:edit', 'Button', '#', 3, 1);
INSERT INTO `auth_permission` VALUES (1003, 124, '角色查询', NULL, NULL, 'system:role:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1004, 124, '角色新增', NULL, NULL, 'system:role:add', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1005, 124, '角色修改', NULL, NULL, 'system:role:edit', 'Button', '#', 3, 1);
INSERT INTO `auth_permission` VALUES (1006, 124, '角色删除', NULL, NULL, 'system:role:remove', 'Button', '#', 4, 1);
INSERT INTO `auth_permission` VALUES (1007, 125, '权限查询', NULL, NULL, 'system:auth:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1008, 125, '权限新增', NULL, NULL, 'system:auth:add', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1009, 125, '权限修改', NULL, NULL, 'system:auth:edit', 'Button', '#', 3, 1);
INSERT INTO `auth_permission` VALUES (1010, 125, '权限删除', NULL, NULL, 'system:auth:remove', 'Button', '#', 4, 1);
INSERT INTO `auth_permission` VALUES (1011, 126, '参数查询', NULL, NULL, 'system:config:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1012, 126, '参数新增', NULL, NULL, 'system:config:add', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1013, 126, '参数修改', NULL, NULL, 'system:config:edit', 'Button', '#', 3, 1);
INSERT INTO `auth_permission` VALUES (1014, 126, '参数删除', NULL, NULL, 'system:config:remove', 'Button', '#', 4, 1);
INSERT INTO `auth_permission` VALUES (1015, 127, '文件查询', NULL, NULL, 'system:file:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1016, 127, '文件新增', NULL, NULL, 'system:file:add', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1017, 127, '文件预览', NULL, NULL, 'system:file:detail', 'Button', '#', 3, 1);
INSERT INTO `auth_permission` VALUES (1018, 127, '文件删除', NULL, NULL, 'system:file:remove', 'Button', '#', 4, 1);
INSERT INTO `auth_permission` VALUES (1021, 200, '登录日志查询', NULL, NULL, 'system:login_log:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1022, 200, '登录日志查看', NULL, NULL, 'system:login_log:detail', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1023, 201, '操作日志查询', NULL, NULL, 'system:opt_log:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1024, 201, '操作日志查看', NULL, NULL, 'system:opt_log:detail', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1025, 129, '站内信查询', NULL, NULL, 'system:notice:query', 'Button', '#', 1, 1);
INSERT INTO `auth_permission` VALUES (1026, 129, '站内信发送', NULL, NULL, 'system:notice:add', 'Button', '#', 2, 1);
INSERT INTO `auth_permission` VALUES (1027, 129, '站内信查看', NULL, NULL, 'system:notice:detail', 'Button', '#', 3, 1);

SET FOREIGN_KEY_CHECKS = 1;
