/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : workstation

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 16/01/2024 00:07:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '部门名称',
    `parent_id`   bigint                                                        NOT NULL DEFAULT 0 COMMENT '父节点id',
    `tree_path`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '父节点id路径',
    `sort`        int                                                           NULL     DEFAULT 0 COMMENT '显示顺序',
    `status`      tinyint                                                       NOT NULL DEFAULT 1 COMMENT '状态(1:正常;0:禁用)',
    `deleted`     tinyint                                                       NULL     DEFAULT 0 COMMENT '逻辑删除标识(1:已删除;0:未删除)',
    `create_time` datetime                                                      NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL     DEFAULT NULL COMMENT '更新时间',
    `create_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '创建人ID',
    `update_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '修改人ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 174
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '部门表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept`
VALUES (1, '总公司', 0, '0', 1, 1, 0, NULL, '2023-12-08 00:20:29', 1, 1);
INSERT INTO `sys_dept`
VALUES (2, '研发部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);
INSERT INTO `sys_dept`
VALUES (3, '测试部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type_code`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '字典类型编码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '字典项名称',
    `value`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '字典项值',
    `sort`        int                                                           NULL DEFAULT 0 COMMENT '排序',
    `status`      tinyint                                                       NULL DEFAULT 0 COMMENT '状态(1:正常;0:禁用)',
    `defaulted`   tinyint                                                       NULL DEFAULT 0 COMMENT '是否默认(1:是;0:否)',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 71
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字典数据表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict`
VALUES (1, 'gender', '男', '1', 1, 1, 0, NULL, '2019-05-05 13:07:52', '2022-06-12 23:20:39');
INSERT INTO `sys_dict`
VALUES (2, 'gender', '女', '2', 2, 1, 0, NULL, '2019-04-19 11:33:00', '2019-07-02 14:23:05');
INSERT INTO `sys_dict`
VALUES (3, 'gender', '未知', '0', 1, 1, 0, NULL, '2020-10-17 08:09:31', '2020-10-17 08:09:31');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键 ',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '类型名称',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '类型编码',
    `status`      tinyint(1)                                                    NULL DEFAULT 0 COMMENT '状态(0:正常;1:禁用)',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `type_code` (`code` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 93
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字典类型表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type`
VALUES (1, '性别', 'gender', 1, NULL, '2019-12-06 19:03:32', '2022-06-12 16:21:28');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint                                                        NOT NULL COMMENT '父菜单ID',
    `tree_path`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '父节点ID路径',
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '菜单名称',
    `type`        tinyint                                                       NOT NULL COMMENT '菜单类型(1:菜单 2:目录 3:外链 4:按钮)',
    `path`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
    `component`   varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
    `perm`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '权限标识',
    `visible`     tinyint(1)                                                    NOT NULL DEFAULT 1 COMMENT '显示状态(1-显示;0-隐藏)',
    `sort`        int                                                           NULL     DEFAULT 0 COMMENT '排序',
    `icon`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '菜单图标',
    `redirect`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '跳转路径',
    `create_time` datetime                                                      NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL     DEFAULT NULL COMMENT '更新时间',
    `always_show` tinyint                                                       NULL     DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示(1:是 0:否)',
    `keep_alive`  tinyint                                                       NULL     DEFAULT NULL COMMENT '【菜单】是否开启页面缓存(1:是 0:否)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 107
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单管理'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, 0, '0', '系统管理', 2, '/system', 'Layout', NULL, 1, 1, 'system', '/system/user', '2021-08-28 09:12:21',
        '2021-08-28 09:12:21', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (2, 1, '0,1', '用户管理', 1, 'user', 'system/user/index', NULL, 1, 1, 'user', NULL, '2021-08-28 09:12:21',
        '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `sys_menu`
VALUES (3, 1, '0,1', '角色管理', 1, 'role', 'system/role/index', NULL, 1, 2, 'role', NULL, '2021-08-28 09:12:21',
        '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `sys_menu`
VALUES (4, 1, '0,1', '菜单管理', 1, 'menu', 'system/menu/index', NULL, 1, 3, 'menu', NULL, '2021-08-28 09:12:21',
        '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `sys_menu`
VALUES (5, 1, '0,1', '部门管理', 1, 'dept', 'system/dept/index', NULL, 1, 4, 'tree', NULL, '2021-08-28 09:12:21',
        '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `sys_menu`
VALUES (6, 1, '0,1', '字典管理', 1, 'dict', 'system/dict/index', NULL, 1, 5, 'dict', NULL, '2021-08-28 09:12:21',
        '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `sys_menu`
VALUES (31, 2, '0,1,2', '用户新增', 4, '', NULL, 'sys:user:add', 1, 1, '', '', '2022-10-23 11:04:08',
        '2022-10-23 11:04:11', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (32, 2, '0,1,2', '用户编辑', 4, '', NULL, 'sys:user:edit', 1, 2, '', '', '2022-10-23 11:04:08',
        '2022-10-23 11:04:11', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (33, 2, '0,1,2', '用户删除', 4, '', NULL, 'sys:user:delete', 1, 3, '', '', '2022-10-23 11:04:08',
        '2022-10-23 11:04:11', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (36, 0, '0', '组件封装', 2, '/component', 'Layout', NULL, 1, 10, 'menu', '', '2022-10-31 09:18:44',
        '2022-10-31 09:18:47', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (37, 36, '0,36', '富文本编辑器', 1, 'wang-editor', 'demo/wang-editor', NULL, 1, 1, '', '', NULL, NULL, NULL, 1);
INSERT INTO `sys_menu`
VALUES (38, 36, '0,36', '图片上传', 1, 'upload', 'demo/upload', NULL, 1, 2, '', '', '2022-11-20 23:16:30',
        '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `sys_menu`
VALUES (39, 36, '0,36', '图标选择器', 1, 'icon-selector', 'demo/icon-selector', NULL, 1, 3, '', '',
        '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `sys_menu`
VALUES (40, 0, '0', '系统工具', 2, '/api', 'Layout', NULL, 1, 7, 'api', '', '2022-02-17 22:51:20', NULL, 1, NULL);
INSERT INTO `sys_menu`
VALUES (41, 40, '0,40', '接口文档', 1, 'api-doc', 'demo/api/knife4j', NULL, 1, 1, 'api', '', '2022-02-17 22:51:20',
        NULL, NULL, 1);
INSERT INTO `sys_menu`
VALUES (70, 3, '0,1,3', '角色新增', 4, '', NULL, 'sys:role:add', 1, 1, '', NULL, '2023-05-20 23:39:09',
        '2023-05-20 23:39:09', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (71, 3, '0,1,3', '角色编辑', 4, '', NULL, 'sys:role:edit', 1, 2, '', NULL, '2023-05-20 23:40:31',
        '2023-05-20 23:40:31', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (72, 3, '0,1,3', '角色删除', 4, '', NULL, 'sys:role:delete', 1, 3, '', NULL, '2023-05-20 23:41:08',
        '2023-05-20 23:41:08', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (73, 4, '0,1,4', '菜单新增', 4, '', NULL, 'sys:menu:add', 1, 1, '', NULL, '2023-05-20 23:41:35',
        '2023-05-20 23:41:35', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (74, 4, '0,1,4', '菜单编辑', 4, '', NULL, 'sys:menu:edit', 1, 3, '', NULL, '2023-05-20 23:41:58',
        '2023-05-20 23:41:58', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (75, 4, '0,1,4', '菜单删除', 4, '', NULL, 'sys:menu:delete', 1, 3, '', NULL, '2023-05-20 23:44:18',
        '2023-05-20 23:44:18', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (76, 5, '0,1,5', '部门新增', 4, '', NULL, 'sys:dept:add', 1, 1, '', NULL, '2023-05-20 23:45:00',
        '2023-05-20 23:45:00', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (77, 5, '0,1,5', '部门编辑', 4, '', NULL, 'sys:dept:edit', 1, 2, '', NULL, '2023-05-20 23:46:16',
        '2023-05-20 23:46:16', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (78, 5, '0,1,5', '部门删除', 4, '', NULL, 'sys:dept:delete', 1, 3, '', NULL, '2023-05-20 23:46:36',
        '2023-05-20 23:46:36', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (79, 6, '0,1,6', '字典类型新增', 4, '', NULL, 'sys:dict_type:add', 1, 1, '', NULL, '2023-05-21 00:16:06',
        '2023-05-21 00:16:06', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (81, 6, '0,1,6', '字典类型编辑', 4, '', NULL, 'sys:dict_type:edit', 1, 2, '', NULL, '2023-05-21 00:27:37',
        '2023-05-21 00:27:37', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (84, 6, '0,1,6', '字典类型删除', 4, '', NULL, 'sys:dict_type:delete', 1, 3, '', NULL, '2023-05-21 00:29:39',
        '2023-05-21 00:29:39', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (85, 6, '0,1,6', '字典数据新增', 4, '', NULL, 'sys:dict:add', 1, 4, '', NULL, '2023-05-21 00:46:56',
        '2023-05-21 00:47:06', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (86, 6, '0,1,6', '字典数据编辑', 4, '', NULL, 'sys:dict:edit', 1, 5, '', NULL, '2023-05-21 00:47:36',
        '2023-05-21 00:47:36', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (87, 6, '0,1,6', '字典数据删除', 4, '', NULL, 'sys:dict:delete', 1, 6, '', NULL, '2023-05-21 00:48:10',
        '2023-05-21 00:48:20', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (88, 2, '0,1,2', '重置密码', 4, '', NULL, 'sys:user:reset_pwd', 1, 4, '', NULL, '2023-05-21 00:49:18',
        '2023-05-21 00:49:18', NULL, NULL);
INSERT INTO `sys_menu`
VALUES (90, 36, '0,36', 'Websocket', 1, 'websocket', 'demo/websocket', NULL, 1, 3, '', '', '2022-11-20 23:16:30', NULL,
        NULL, 1);
INSERT INTO `sys_menu`
VALUES (93, 36, '0,36', '签名', 1, 'signature', 'demo/signature', NULL, 1, 6, '', '', '2022-11-20 23:16:30',
        '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `sys_menu`
VALUES (95, 36, '0,36', '字典组件', 1, 'dict-demo', 'demo/dict', NULL, 1, 4, '', '', '2022-11-20 23:16:30',
        '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `sys_menu`
VALUES (97, 36, '0,36', 'Icons', 1, 'icon-demo', 'demo/icons', NULL, 1, 2, '', '', '2022-11-20 23:16:30', NULL, NULL,
        1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT,
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
    `code`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '角色编码',
    `sort`        int                                                          NULL     DEFAULT NULL COMMENT '显示顺序',
    `status`      tinyint(1)                                                   NULL     DEFAULT 1 COMMENT '角色状态(1-正常；0-停用)',
    `data_scope`  tinyint                                                      NULL     DEFAULT NULL COMMENT '数据权限(0-所有数据；1-部门及子部门数据；2-本部门数据；3-本人数据)',
    `deleted`     tinyint(1)                                                   NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除；1-已删除)',
    `create_time` datetime                                                     NULL     DEFAULT NULL COMMENT '更新时间',
    `update_time` datetime                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 132
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '系统管理员', 'ADMIN', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `sys_role`
VALUES (2, '普通角色', 'USER', 1, 1, 0, 0, NULL, NULL);
INSERT INTO `sys_role`
VALUES (3, '访问游客', 'GUEST', 3, 1, 2, 0, '2021-05-26 15:49:05', '2019-05-05 16:00:00');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (1, 1);
INSERT INTO `sys_role_menu`
VALUES (1, 2);
INSERT INTO `sys_role_menu`
VALUES (1, 31);
INSERT INTO `sys_role_menu`
VALUES (1, 32);
INSERT INTO `sys_role_menu`
VALUES (1, 33);
INSERT INTO `sys_role_menu`
VALUES (1, 88);
INSERT INTO `sys_role_menu`
VALUES (1, 3);
INSERT INTO `sys_role_menu`
VALUES (1, 70);
INSERT INTO `sys_role_menu`
VALUES (1, 71);
INSERT INTO `sys_role_menu`
VALUES (1, 72);
INSERT INTO `sys_role_menu`
VALUES (1, 4);
INSERT INTO `sys_role_menu`
VALUES (1, 73);
INSERT INTO `sys_role_menu`
VALUES (1, 74);
INSERT INTO `sys_role_menu`
VALUES (1, 75);
INSERT INTO `sys_role_menu`
VALUES (1, 5);
INSERT INTO `sys_role_menu`
VALUES (1, 76);
INSERT INTO `sys_role_menu`
VALUES (1, 77);
INSERT INTO `sys_role_menu`
VALUES (1, 78);
INSERT INTO `sys_role_menu`
VALUES (1, 6);
INSERT INTO `sys_role_menu`
VALUES (1, 79);
INSERT INTO `sys_role_menu`
VALUES (1, 81);
INSERT INTO `sys_role_menu`
VALUES (1, 84);
INSERT INTO `sys_role_menu`
VALUES (1, 85);
INSERT INTO `sys_role_menu`
VALUES (1, 86);
INSERT INTO `sys_role_menu`
VALUES (1, 87);
INSERT INTO `sys_role_menu`
VALUES (1, 40);
INSERT INTO `sys_role_menu`
VALUES (1, 41);
INSERT INTO `sys_role_menu`
VALUES (1, 36);
INSERT INTO `sys_role_menu`
VALUES (1, 37);
INSERT INTO `sys_role_menu`
VALUES (1, 38);
INSERT INTO `sys_role_menu`
VALUES (1, 39);
INSERT INTO `sys_role_menu`
VALUES (1, 95);
INSERT INTO `sys_role_menu`
VALUES (1, 93);
INSERT INTO `sys_role_menu`
VALUES (1, 94);
INSERT INTO `sys_role_menu`
VALUES (1, 98);
INSERT INTO `sys_role_menu`
VALUES (1, 99);
INSERT INTO `sys_role_menu`
VALUES (1, 100);
INSERT INTO `sys_role_menu`
VALUES (1, 101);
INSERT INTO `sys_role_menu`
VALUES (1, 89);
INSERT INTO `sys_role_menu`
VALUES (1, 96);
INSERT INTO `sys_role_menu`
VALUES (1, 97);
INSERT INTO `sys_role_menu`
VALUES (1, 90);
INSERT INTO `sys_role_menu`
VALUES (1, 91);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT,
    `username`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '用户名',
    `nickname`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '昵称',
    `gender`      tinyint(1)                                                    NULL DEFAULT 1 COMMENT '性别((1:男;2:女))',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `dept_id`     int                                                           NULL DEFAULT NULL COMMENT '部门ID',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
    `mobile`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '联系方式',
    `status`      tinyint(1)                                                    NULL DEFAULT 1 COMMENT '用户状态((1:正常;0:禁用))',
    `email`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
    `deleted`     tinyint(1)                                                    NULL DEFAULT 0 COMMENT '逻辑删除标识(0:未删除;1:已删除)',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `login_name` (`username` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 290
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', '系统管理员', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 1,
        'https://img1.baidu.com/it/u=3196140437,2370538747&fm=253&fmt=auto&app=138&f=JPEG?w=506&h=500', '17621210366',
        1, '', 0, '2019-10-10 13:41:22', '2022-07-31 12:39:30');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
