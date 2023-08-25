/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80029
Source Host           : localhost:3306
Source Database       : tzw

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2023-05-12 18:03:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `emain` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES ('5', 'wuwuwu', '2023-05-12', '1231@qq.com', 'inwanchiji');
INSERT INTO `files` VALUES ('6', '你好啊', '2023-05-12', '123456@qq.com', '打击hi三代技术');
INSERT INTO `files` VALUES ('7', '你好啊111', '2023-05-12', '123456@qq.com', '打击hi三代技术');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12', '1999', '123', '1');
INSERT INTO `user` VALUES ('33', '2000', '123', null);
INSERT INTO `user` VALUES ('34', '2024', '000000', null);
