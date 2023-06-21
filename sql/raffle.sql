/*
 Navicat Premium Data Transfer

 Source Server         : mac本地
 Source Server Type    : MySQL
 Source Server Version : 50731 (5.7.31-log)
 Source Host           : localhost:3306
 Source Schema         : raffle

 Target Server Type    : MySQL
 Target Server Version : 50731 (5.7.31-log)
 File Encoding         : 65001

 Date: 22/06/2023 02:02:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bld_accept_prize
-- ----------------------------
DROP TABLE IF EXISTS `bld_accept_prize`;
CREATE TABLE `bld_accept_prize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_id` bigint(20) NOT NULL COMMENT '抽奖记录id',
  `phone` varchar(15) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '电话',
  `address` varchar(500) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_accept_prize
-- ----------------------------
BEGIN;
INSERT INTO `bld_accept_prize` (`id`, `record_id`, `phone`, `address`, `create_time`, `creator`, `update_time`, `updater`) VALUES (1, 11, '1111', '111', '2023-05-26 00:17:48', 'zhang', '2023-05-26 00:17:48', 'zhang');
COMMIT;

-- ----------------------------
-- Table structure for bld_activity
-- ----------------------------
DROP TABLE IF EXISTS `bld_activity`;
CREATE TABLE `bld_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(100) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '活动名称',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `describe` varchar(500) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_activity
-- ----------------------------
BEGIN;
INSERT INTO `bld_activity` (`id`, `activity_name`, `start_time`, `end_time`, `describe`, `create_time`, `creator`, `update_time`, `updater`) VALUES (11, '测试活动2', '2023-05-13 15:49:35', '2023-06-30 15:49:35', '', '2023-05-14 23:16:54', 'odin', '2023-05-14 23:16:54', 'odin');
INSERT INTO `bld_activity` (`id`, `activity_name`, `start_time`, `end_time`, `describe`, `create_time`, `creator`, `update_time`, `updater`) VALUES (13, '测试活动0522', '2023-05-13 15:49:35', '2023-06-30 15:49:35', '', '2023-05-22 10:29:57', 'odin', '2023-05-22 10:29:57', 'odin');
INSERT INTO `bld_activity` (`id`, `activity_name`, `start_time`, `end_time`, `describe`, `create_time`, `creator`, `update_time`, `updater`) VALUES (14, '测试活动3', '2023-05-13 15:49:35', '2023-06-29 15:49:35', '活动测试', '2023-05-26 20:51:57', 'odin', '2023-05-26 20:51:57', 'odin');
INSERT INTO `bld_activity` (`id`, `activity_name`, `start_time`, `end_time`, `describe`, `create_time`, `creator`, `update_time`, `updater`) VALUES (15, '测试活动0606', '2023-05-13 15:49:35', '2023-06-13 15:49:35', '', '2023-06-06 13:01:26', 'odin', '2023-06-13 13:01:26', 'odin');
COMMIT;

-- ----------------------------
-- Table structure for bld_activity_rule
-- ----------------------------
DROP TABLE IF EXISTS `bld_activity_rule`;
CREATE TABLE `bld_activity_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(20) NOT NULL COMMENT '活动id',
  `rule_id` bigint(20) NOT NULL COMMENT '规则id',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `un` (`activity_id`,`rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_activity_rule
-- ----------------------------
BEGIN;
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (20, 11, 1, '2023-05-14 23:16:54', 'odin', '2023-05-14 23:16:54', 'odin');
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (21, 11, 2, '2023-05-14 23:16:54', 'odin', '2023-05-14 23:16:54', 'odin');
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (24, 13, 1, '2023-05-22 10:29:57', 'odin', '2023-05-22 10:29:57', 'odin');
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (25, 13, 2, '2023-05-22 10:29:57', 'odin', '2023-05-22 10:29:57', 'odin');
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (26, 14, 1, '2023-05-26 20:51:57', 'odin', '2023-05-26 20:51:57', 'odin');
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (27, 14, 2, '2023-05-26 20:51:57', 'odin', '2023-05-26 20:51:57', 'odin');
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (28, 15, 1, '2023-06-06 13:01:26', 'odin', '2023-06-06 13:01:26', 'odin');
INSERT INTO `bld_activity_rule` (`id`, `activity_id`, `rule_id`, `create_time`, `creator`, `update_time`, `updater`) VALUES (29, 15, 2, '2023-06-06 13:01:26', 'odin', '2023-06-06 13:01:26', 'odin');
COMMIT;

-- ----------------------------
-- Table structure for bld_award
-- ----------------------------
DROP TABLE IF EXISTS `bld_award`;
CREATE TABLE `bld_award` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prize_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '奖品名称',
  `activity_id` bigint(20) NOT NULL COMMENT '活动id',
  `number` int(11) NOT NULL COMMENT '数量',
  `award_name` varchar(40) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '奖项名称',
  `probability` double NOT NULL COMMENT '概率',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_award
-- ----------------------------
BEGIN;
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (35, 1, 11, 5, '一等奖', 0.01, '2023-05-14 23:16:54', 'odin', '2023-05-14 23:16:54', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (36, 2, 11, 10, '二等奖', 0.03, '2023-05-14 23:16:54', 'odin', '2023-05-14 23:16:54', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (37, 3, 11, 10, '三等奖', 0.06, '2023-05-14 23:16:54', 'odin', '2023-05-14 23:16:54', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (38, 3, 11, 20, '谢谢参与', 0.9, '2023-05-14 23:16:54', 'odin', '2023-05-14 23:16:54', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (43, 1, 13, 5, '一等奖', 0.01, '2023-05-22 10:29:57', 'odin', '2023-05-22 10:29:57', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (44, 2, 13, 10, '二等奖', 0.03, '2023-05-22 10:29:57', 'odin', '2023-05-22 10:29:57', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (45, 4, 13, 12, '三等奖', 0.6, '2023-05-22 10:29:57', 'odin', '2023-05-22 10:29:57', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (47, 1, 14, 5, '一等奖', 0.01, '2023-05-26 20:51:57', 'odin', '2023-05-26 20:51:57', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (48, 2, 14, 10, '二等奖', 0.03, '2023-05-26 20:51:57', 'odin', '2023-05-26 20:51:57', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (49, 4, 14, 10, '三等奖', 0.6, '2023-05-26 20:51:57', 'odin', '2023-05-26 20:51:57', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (50, 0, 14, 10000, '谢谢参与', 0.9, '2023-05-26 20:51:57', 'odin', '2023-05-26 20:51:57', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (51, 1, 15, 5, '一等奖', 0.01, '2023-06-06 13:01:26', 'odin', '2023-06-06 13:01:26', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (52, 2, 15, 10, '二等奖', 0.03, '2023-06-06 13:01:26', 'odin', '2023-06-06 13:01:26', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (53, 4, 15, 10, '三等奖', 0.6, '2023-06-06 13:01:26', 'odin', '2023-06-06 13:01:26', 'odin');
INSERT INTO `bld_award` (`id`, `prize_id`, `activity_id`, `number`, `award_name`, `probability`, `create_time`, `creator`, `update_time`, `updater`) VALUES (54, 0, 15, 10000, '谢谢参与', 0.9, '2023-06-06 13:01:26', 'odin', '2023-06-06 13:01:26', 'odin');
COMMIT;

-- ----------------------------
-- Table structure for bld_prize
-- ----------------------------
DROP TABLE IF EXISTS `bld_prize`;
CREATE TABLE `bld_prize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prize_name` varchar(20) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '奖品名称',
  `inventory` int(11) NOT NULL COMMENT '库存',
  `money` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `type` int(1) DEFAULT NULL COMMENT '类型（1：商品，2：金钱）',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_prize
-- ----------------------------
BEGIN;
INSERT INTO `bld_prize` (`id`, `prize_name`, `inventory`, `money`, `type`, `create_time`, `creator`, `update_time`, `updater`) VALUES (1, 'test_prize', 185, 0, 1, '2023-05-06 15:22:06', 'odin', '2023-05-06 15:22:06', 'odin');
INSERT INTO `bld_prize` (`id`, `prize_name`, `inventory`, `money`, `type`, `create_time`, `creator`, `update_time`, `updater`) VALUES (2, '小米13', 170, 0, 1, '2023-05-06 00:45:37', 'odin', '2023-05-06 00:45:37', 'odin');
INSERT INTO `bld_prize` (`id`, `prize_name`, `inventory`, `money`, `type`, `create_time`, `creator`, `update_time`, `updater`) VALUES (3, '华为P50', 170, 0, 1, '2023-05-06 00:45:45', 'odin', '2023-05-06 00:45:45', 'odin');
INSERT INTO `bld_prize` (`id`, `prize_name`, `inventory`, `money`, `type`, `create_time`, `creator`, `update_time`, `updater`) VALUES (4, '100元现金', 180, 100, 2, '2023-05-25 20:45:46', 'odin', '2023-05-26 00:45:45', 'odin');
INSERT INTO `bld_prize` (`id`, `prize_name`, `inventory`, `money`, `type`, `create_time`, `creator`, `update_time`, `updater`) VALUES (5, 'MacBook-Air', 10, 0, 1, '2023-05-26 22:58:15', 'odin', '2023-05-26 22:58:15', 'odin');
COMMIT;

-- ----------------------------
-- Table structure for bld_record
-- ----------------------------
DROP TABLE IF EXISTS `bld_record`;
CREATE TABLE `bld_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `activity_id` bigint(20) NOT NULL COMMENT '活动id',
  `activity_name` varchar(20) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `award_id` bigint(20) NOT NULL COMMENT '奖项id',
  `is_winning` tinyint(1) DEFAULT '0' COMMENT '是否中奖：0未中奖，1中奖',
  `state` int(11) DEFAULT NULL COMMENT '状态（0，1，2，3）',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_record
-- ----------------------------
BEGIN;
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (65, 5, 14, '测试活动3', 50, 0, 1, '2023-05-26 20:52:39', 'odin', '2023-05-26 20:52:39', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (66, 5, 14, '测试活动3', 50, 0, 1, '2023-05-26 20:52:41', 'odin', '2023-05-26 20:52:41', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (67, 5, 14, '测试活动3', 49, 1, 4, '2023-05-26 20:52:42', 'odin', '2023-05-26 20:52:42', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (68, 5, 14, '测试活动3', 49, 1, 1, '2023-05-26 20:52:42', 'odin', '2023-05-26 20:52:42', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (69, 5, 14, '测试活动3', 50, 0, 1, '2023-05-26 20:52:43', 'odin', '2023-05-26 20:52:43', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (70, 5, 14, '测试活动3', 50, 0, 1, '2023-05-26 20:52:44', 'odin', '2023-05-26 20:52:44', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (71, 5, 14, '测试活动3', 49, 1, 1, '2023-05-26 20:52:45', 'odin', '2023-05-26 20:52:45', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (72, 5, 14, '测试活动3', 49, 1, 1, '2023-05-26 21:06:00', 'odin', '2023-05-26 21:06:00', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (73, 5, 14, '测试活动3', 50, 0, 1, '2023-05-26 21:06:06', 'odin', '2023-05-26 21:06:06', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (74, 6, 14, '测试活动3', 50, 0, 1, '2023-05-27 00:17:58', 'odin', '2023-05-27 00:17:58', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (75, 6, 13, '测试活动0522', 45, 1, 1, '2023-06-01 11:08:26', 'odin', '2023-06-01 11:08:26', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (76, 6, 13, '测试活动0522', 45, 1, 1, '2023-06-01 11:44:20', 'odin', '2023-06-01 11:44:20', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (77, 6, 15, '测试活动2', 54, 0, 1, '2023-06-06 13:01:39', 'odin', '2023-06-06 13:01:39', 'odin');
INSERT INTO `bld_record` (`id`, `user_id`, `activity_id`, `activity_name`, `award_id`, `is_winning`, `state`, `create_time`, `creator`, `update_time`, `updater`) VALUES (78, 6, 15, '测试活动0606', 54, 0, 1, '2023-06-06 14:24:13', 'odin', '2023-06-06 14:24:13', 'odin');
COMMIT;

-- ----------------------------
-- Table structure for bld_rule
-- ----------------------------
DROP TABLE IF EXISTS `bld_rule`;
CREATE TABLE `bld_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '规则名称',
  `max_join_number` int(11) NOT NULL COMMENT '最大可参与次数',
  `max_winning_number` int(11) NOT NULL COMMENT '最大可中奖次数',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_rule
-- ----------------------------
BEGIN;
INSERT INTO `bld_rule` (`id`, `rule_name`, `max_join_number`, `max_winning_number`, `create_time`, `creator`, `update_time`, `updater`) VALUES (1, '测试规则1', 23, 18, '2023-05-06 11:20:51', 'odin', '2023-05-06 15:33:09', 'odin');
INSERT INTO `bld_rule` (`id`, `rule_name`, `max_join_number`, `max_winning_number`, `create_time`, `creator`, `update_time`, `updater`) VALUES (2, '测试规则2', 20, 20, '2023-05-06 11:20:51', 'odin', '2023-05-06 15:33:09', 'odin');
COMMIT;

-- ----------------------------
-- Table structure for bld_user
-- ----------------------------
DROP TABLE IF EXISTS `bld_user`;
CREATE TABLE `bld_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '账号',
  `password` varchar(100) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '密码',
  `name` varchar(10) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '姓名',
  `phone` varchar(15) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '电话',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_user
-- ----------------------------
BEGIN;
INSERT INTO `bld_user` (`id`, `username`, `password`, `name`, `phone`, `create_time`, `creator`, `update_time`, `updater`) VALUES (6, 'zhang', 'e44985980c9d3c5d70b6a548b14d773a', 'odin', '1332326743', '2023-05-26 22:56:55', 'zhang', '2023-05-26 22:56:55', 'zhang');
COMMIT;

-- ----------------------------
-- Table structure for bld_user_wallet
-- ----------------------------
DROP TABLE IF EXISTS `bld_user_wallet`;
CREATE TABLE `bld_user_wallet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(50) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of bld_user_wallet
-- ----------------------------
BEGIN;
INSERT INTO `bld_user_wallet` (`id`, `user_id`, `balance`, `create_time`, `update_time`, `updater`) VALUES (6, 5, 0.00, '2023-05-26 22:41:00', '2023-05-26 22:41:00', '管理员');
INSERT INTO `bld_user_wallet` (`id`, `user_id`, `balance`, `create_time`, `update_time`, `updater`) VALUES (7, 0, 500.00, '2023-05-26 22:47:45', '2023-05-26 22:47:45', '管理员');
INSERT INTO `bld_user_wallet` (`id`, `user_id`, `balance`, `create_time`, `update_time`, `updater`) VALUES (8, 6, 0.00, '2023-05-26 22:56:55', '2023-05-26 22:56:55', '管理员');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
