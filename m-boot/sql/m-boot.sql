/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : m-boot

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 10/06/2019 20:04:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键:用户ID',
  `user_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录账号',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录密码(由英文字母, 数字, 下划线组成, 必须以英文字母开头)',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户真实名称',
  `user_birthday` datetime(0) NULL DEFAULT NULL COMMENT '用户出生日期',
  `user_gender` int(1) NULL DEFAULT NULL COMMENT '1男  2女',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统模块-用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, '', '', '嘤嘤嘤', NULL, 0, '2019-06-08 11:28:37', 0, '2019-06-08 12:12:28', 0);
INSERT INTO `sys_user` VALUES (3, 'admin2', 'admin', 'admin', NULL, 0, '2019-06-08 11:29:05', 0, NULL, 0);
INSERT INTO `sys_user` VALUES (4, 'zhangsan', 'admin', 'admin', NULL, 0, '2019-06-08 11:33:18', 0, NULL, 0);
INSERT INTO `sys_user` VALUES (5, 'test', '', '', NULL, 0, '2019-06-08 11:33:29', 0, NULL, 0);
INSERT INTO `sys_user` VALUES (6, 'zhangsa1n', 'admin', 'admin', NULL, 0, '2019-06-08 16:39:12', 0, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
