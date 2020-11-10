/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : warriors-houston

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 11/09/2020 22:03:32 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_student`
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名字',
  `birth` date DEFAULT NULL COMMENT '生日',
  `weight` double(10,2) DEFAULT NULL COMMENT '体重',
  `height` double(10,2) DEFAULT NULL COMMENT '身高',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生表';

-- ----------------------------
--  Records of `tb_student`
-- ----------------------------
BEGIN;
INSERT INTO `tb_student` VALUES ('1', '许和平', '1993-04-25', '80.00', '178.00', '2020-11-09 10:26:55', '2020-11-09 10:26:57'), ('2', 'XXX', '2020-11-09', '100.00', '150.00', '2020-11-09 15:49:33', '2020-11-09 15:49:35');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
