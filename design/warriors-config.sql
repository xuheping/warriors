/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : warriors-config

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 11/09/2020 22:03:55 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `properties`
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) NOT NULL,
  `value` varchar(500) NOT NULL,
  `application` varchar(50) NOT NULL,
  `profile` varchar(50) NOT NULL,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `properties`
-- ----------------------------
BEGIN;
INSERT INTO `properties` VALUES ('1', 'server.port', '8083', 'WARRIORS-LAKER', 'config', 'master'), ('2', 'warriors.url', 'www.warriors.com', 'WARRIORS-LAKER', 'config', 'master');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
