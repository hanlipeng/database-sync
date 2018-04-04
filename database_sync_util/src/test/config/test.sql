/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : syc_test

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-05 02:54:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dest_join_table
-- ----------------------------
DROP TABLE IF EXISTS `dest_join_table`;
CREATE TABLE `dest_join_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_join_table
-- ----------------------------

-- ----------------------------
-- Table structure for dest_m2m_table
-- ----------------------------
DROP TABLE IF EXISTS `dest_m2m_table`;
CREATE TABLE `dest_m2m_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `old_mark` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_m2m_table
-- ----------------------------

-- ----------------------------
-- Table structure for dest_module_table1
-- ----------------------------
DROP TABLE IF EXISTS `dest_module_table1`;
CREATE TABLE `dest_module_table1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_module_table1
-- ----------------------------

-- ----------------------------
-- Table structure for dest_module_table2
-- ----------------------------
DROP TABLE IF EXISTS `dest_module_table2`;
CREATE TABLE `dest_module_table2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_module_table2
-- ----------------------------

-- ----------------------------
-- Table structure for dest_no_time_table
-- ----------------------------
DROP TABLE IF EXISTS `dest_no_time_table`;
CREATE TABLE `dest_no_time_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_no_time_table
-- ----------------------------

-- ----------------------------
-- Table structure for dest_primary_table
-- ----------------------------
DROP TABLE IF EXISTS `dest_primary_table`;
CREATE TABLE `dest_primary_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_primary_table
-- ----------------------------

-- ----------------------------
-- Table structure for dest_table
-- ----------------------------
DROP TABLE IF EXISTS `dest_table`;
CREATE TABLE `dest_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_table
-- ----------------------------

-- ----------------------------
-- Table structure for dest_table_special_key
-- ----------------------------
DROP TABLE IF EXISTS `dest_table_special_key`;
CREATE TABLE `dest_table_special_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_table_special_key
-- ----------------------------

-- ----------------------------
-- Table structure for src_join_table
-- ----------------------------
DROP TABLE IF EXISTS `src_join_table`;
CREATE TABLE `src_join_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_join_table
-- ----------------------------
INSERT INTO `src_join_table` VALUES ('1', '2', '3');
INSERT INTO `src_join_table` VALUES ('2', '2', '3');
INSERT INTO `src_join_table` VALUES ('3', '2', '3');
INSERT INTO `src_join_table` VALUES ('4', '2', '3');
INSERT INTO `src_join_table` VALUES ('5', '2', '3');
INSERT INTO `src_join_table` VALUES ('6', '2', '3');
INSERT INTO `src_join_table` VALUES ('7', '2', '3');
INSERT INTO `src_join_table` VALUES ('8', '2', '3');
INSERT INTO `src_join_table` VALUES ('9', '2', '3');
INSERT INTO `src_join_table` VALUES ('10', '2', '3');
INSERT INTO `src_join_table` VALUES ('11', '2', '3');
INSERT INTO `src_join_table` VALUES ('12', '2', '3');

-- ----------------------------
-- Table structure for src_m2m_table
-- ----------------------------
DROP TABLE IF EXISTS `src_m2m_table`;
CREATE TABLE `src_m2m_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_m2m_table
-- ----------------------------
INSERT INTO `src_m2m_table` VALUES ('1', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('2', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('3', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('4', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('5', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('6', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('7', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('8', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('9', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('10', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('11', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');
INSERT INTO `src_m2m_table` VALUES ('12', '2,2,2,3', '2,2,2', '2018-04-05 02:50:26');

-- ----------------------------
-- Table structure for src_module_table1
-- ----------------------------
DROP TABLE IF EXISTS `src_module_table1`;
CREATE TABLE `src_module_table1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_module_table1
-- ----------------------------
INSERT INTO `src_module_table1` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table1` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for src_module_table2
-- ----------------------------
DROP TABLE IF EXISTS `src_module_table2`;
CREATE TABLE `src_module_table2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_module_table2
-- ----------------------------
INSERT INTO `src_module_table2` VALUES ('1', '2', '3', '2018-03');
INSERT INTO `src_module_table2` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_module_table2` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for src_primary_table
-- ----------------------------
DROP TABLE IF EXISTS `src_primary_table`;
CREATE TABLE `src_primary_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_primary_table
-- ----------------------------
INSERT INTO `src_primary_table` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_primary_table` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for src_table
-- ----------------------------
DROP TABLE IF EXISTS `src_table`;
CREATE TABLE `src_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_table
-- ----------------------------
INSERT INTO `src_table` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for src_table_special_key
-- ----------------------------
DROP TABLE IF EXISTS `src_table_special_key`;
CREATE TABLE `src_table_special_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_table_special_key
-- ----------------------------
INSERT INTO `src_table_special_key` VALUES ('1', '在同步开始前运行的语句', null, null);
INSERT INTO `src_table_special_key` VALUES ('2', '在同步结束后会由E运行的语句删除', null, null);
INSERT INTO `src_table_special_key` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_table_special_key` VALUES ('12', '2', '3', '2018-03-30 11:27:20');
