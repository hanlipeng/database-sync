/*
Navicat MySQL Data Transfer

Source Server         : mytest
Source Server Version : 50718
Source Host           : 10.88.1.86:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-03-30 19:06:02
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
INSERT INTO `dest_join_table` VALUES ('1', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('2', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('3', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('4', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('5', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('6', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('7', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('8', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('9', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('10', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('11', '2', '3', null);
INSERT INTO `dest_join_table` VALUES ('12', '2', '3', null);

-- ----------------------------
-- Table structure for dest_m2m_table
-- ----------------------------
DROP TABLE IF EXISTS `dest_m2m_table`;
CREATE TABLE `dest_m2m_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dest_m2m_table
-- ----------------------------
INSERT INTO `dest_m2m_table` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_m2m_table` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

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
INSERT INTO `dest_no_time_table` VALUES ('1', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('2', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('3', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('4', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('5', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('6', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('7', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('8', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('9', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('10', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('11', '2', '3', null);
INSERT INTO `dest_no_time_table` VALUES ('12', '2', '3', null);

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
INSERT INTO `dest_table` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

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
INSERT INTO `dest_table_special_key` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `dest_table_special_key` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for join_table
-- ----------------------------
DROP TABLE IF EXISTS `join_table`;
CREATE TABLE `join_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of join_table
-- ----------------------------
INSERT INTO `join_table` VALUES ('1', '2', '3');
INSERT INTO `join_table` VALUES ('2', '2', '3');
INSERT INTO `join_table` VALUES ('3', '2', '3');
INSERT INTO `join_table` VALUES ('4', '2', '3');
INSERT INTO `join_table` VALUES ('5', '2', '3');
INSERT INTO `join_table` VALUES ('6', '2', '3');
INSERT INTO `join_table` VALUES ('7', '2', '3');
INSERT INTO `join_table` VALUES ('8', '2', '3');
INSERT INTO `join_table` VALUES ('9', '2', '3');
INSERT INTO `join_table` VALUES ('10', '2', '3');
INSERT INTO `join_table` VALUES ('11', '2', '3');
INSERT INTO `join_table` VALUES ('12', '2', '3');

-- ----------------------------
-- Table structure for module_dest_table1
-- ----------------------------
DROP TABLE IF EXISTS `module_dest_table1`;
CREATE TABLE `module_dest_table1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_dest_table1
-- ----------------------------

-- ----------------------------
-- Table structure for module_dest_table2
-- ----------------------------
DROP TABLE IF EXISTS `module_dest_table2`;
CREATE TABLE `module_dest_table2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_dest_table2
-- ----------------------------

-- ----------------------------
-- Table structure for module_src_table1
-- ----------------------------
DROP TABLE IF EXISTS `module_src_table1`;
CREATE TABLE `module_src_table1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_src_table1
-- ----------------------------
INSERT INTO `module_src_table1` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table1` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for module_src_table2
-- ----------------------------
DROP TABLE IF EXISTS `module_src_table2`;
CREATE TABLE `module_src_table2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_src_table2
-- ----------------------------
INSERT INTO `module_src_table2` VALUES ('1', '2', '3', '2018-03');
INSERT INTO `module_src_table2` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `module_src_table2` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for primary_dest_table
-- ----------------------------
DROP TABLE IF EXISTS `primary_dest_table`;
CREATE TABLE `primary_dest_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of primary_dest_table
-- ----------------------------
INSERT INTO `primary_dest_table` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_dest_table` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

-- ----------------------------
-- Table structure for primary_src_table
-- ----------------------------
DROP TABLE IF EXISTS `primary_src_table`;
CREATE TABLE `primary_src_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value1` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of primary_src_table
-- ----------------------------
INSERT INTO `primary_src_table` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `primary_src_table` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

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
INSERT INTO `src_m2m_table` VALUES ('1', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('2', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('3', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('4', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('5', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('6', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('7', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('8', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('9', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('10', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('11', '2', '3', '2018-03-30 11:27:20');
INSERT INTO `src_m2m_table` VALUES ('12', '2', '3', '2018-03-30 11:27:20');

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

-- ----------------------------
-- Table structure for syc_db
-- ----------------------------
DROP TABLE IF EXISTS `syc_db`;
CREATE TABLE `syc_db` (
  `db_id` int(11) NOT NULL AUTO_INCREMENT,
  `src_dburl` varchar(45) NOT NULL COMMENT '数据源库地址(带端口号)',
  `src_dbuser` varchar(45) NOT NULL COMMENT '数据源库账号',
  `src_dbpwd` varchar(45) NOT NULL COMMENT '数据源库密码',
  `src_dbtype` varchar(45) NOT NULL COMMENT '数据库种类:\nmysql:"mysql"\nsqlserver:"mssql"',
  `src_dbname` varchar(45) NOT NULL COMMENT '数据库名',
  `dest_dburl` varchar(45) NOT NULL COMMENT '目标库地址(带端口,库名)',
  `dest_dbuser` varchar(45) NOT NULL COMMENT '目标库账户',
  `dest_dbpwd` varchar(45) NOT NULL COMMENT '目标库密码',
  `dest_dbtype` varchar(45) NOT NULL COMMENT '数据库种类:\nmysql:"mysql"\nsqlserver:"mssql"',
  `dest_dbname` varchar(45) NOT NULL COMMENT '数据库名',
  `info_get` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syc_db
-- ----------------------------
INSERT INTO `syc_db` VALUES ('1', '127.0.0.1:3306', 'root', 'root', 'mysql', 'test', '127.0.0.1:3306', 'root', 'root', 'mysql', 'test', null);

-- ----------------------------
-- Table structure for syc_field
-- ----------------------------
DROP TABLE IF EXISTS `syc_field`;
CREATE TABLE `syc_field` (
  `field_id` int(11) NOT NULL AUTO_INCREMENT,
  `src_field_name` varchar(45) NOT NULL COMMENT '数据源列名',
  `dest_field_name` varchar(45) DEFAULT NULL COMMENT '目标列名',
  `is_time` varchar(3) DEFAULT NULL COMMENT '是否是时间更新列(true:1,false:0)',
  `is_primary` int(11) NOT NULL DEFAULT '0' COMMENT '是否为主键(true:1,false:0)',
  `join_field_id` int(11) DEFAULT NULL,
  `table_id` int(11) NOT NULL,
  `special_key` text,
  PRIMARY KEY (`field_id`) USING BTREE,
  UNIQUE KEY `unique_index` (`dest_field_name`,`table_id`,`src_field_name`) USING BTREE,
  KEY `syc_fieldI1` (`table_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syc_field
-- ----------------------------
INSERT INTO `syc_field` VALUES ('147', 'id', 'id', null, '1', null, '1', null);
INSERT INTO `syc_field` VALUES ('148', 'value1', 'value1', null, '0', null, '1', null);
INSERT INTO `syc_field` VALUES ('149', 'value2', 'value2', null, '0', null, '1', null);
INSERT INTO `syc_field` VALUES ('150', 'date', 'date', '120', '0', null, '1', null);
INSERT INTO `syc_field` VALUES ('151', 'id', 'id', null, '1', null, '2', null);
INSERT INTO `syc_field` VALUES ('152', 'value1', 'value1', null, '0', null, '2', null);
INSERT INTO `syc_field` VALUES ('153', 'value2', 'value2', null, '0', null, '2', null);
INSERT INTO `syc_field` VALUES ('154', 'id', 'id', null, '1', null, '3', null);
INSERT INTO `syc_field` VALUES ('155', 'value1', 'value1', null, '0', null, '3', null);
INSERT INTO `syc_field` VALUES ('156', 'value2', 'value2', null, '0', null, '3', null);
INSERT INTO `syc_field` VALUES ('157', 'date', 'date', '120', '0', null, '3', null);
INSERT INTO `syc_field` VALUES ('158', 'id', 'id', null, '1', '147', '4', null);
INSERT INTO `syc_field` VALUES ('159', 'value1', 'value1', null, '0', null, '4', null);
INSERT INTO `syc_field` VALUES ('160', 'value2', 'value2', null, '0', null, '4', null);
INSERT INTO `syc_field` VALUES ('161', 'id', 'id', null, '1', null, '5', null);
INSERT INTO `syc_field` VALUES ('162', 'value1', 'value1', null, '0', null, '5', null);
INSERT INTO `syc_field` VALUES ('163', 'value2', 'value2', null, '0', null, '5', null);
INSERT INTO `syc_field` VALUES ('164', 'date', 'date', '120', '0', null, '5', null);
INSERT INTO `syc_field` VALUES ('165', 'id', 'id', null, '1', null, '6', null);
INSERT INTO `syc_field` VALUES ('166', 'value1', 'value1', null, '0', null, '6', null);
INSERT INTO `syc_field` VALUES ('167', 'value2', 'value2', null, '0', null, '6', null);
INSERT INTO `syc_field` VALUES ('168', 'date', 'date', '120', '0', null, '6', null);
INSERT INTO `syc_field` VALUES ('169', 'id', 'id', null, '1', null, '7', null);
INSERT INTO `syc_field` VALUES ('170', 'value1', 'value1', null, '0', null, '7', null);
INSERT INTO `syc_field` VALUES ('171', 'value2', 'value2', null, '0', null, '7', null);
INSERT INTO `syc_field` VALUES ('172', 'date', 'date', null, '0', null, '7', null);
INSERT INTO `syc_field` VALUES ('173', 'id', 'id', null, '1', null, '8', null);
INSERT INTO `syc_field` VALUES ('174', 'value1', 'value1', null, '0', null, '8', null);
INSERT INTO `syc_field` VALUES ('175', 'value2', 'value2', null, '0', null, '8', null);
INSERT INTO `syc_field` VALUES ('176', 'date', 'date', '120', '0', null, '8', null);

-- ----------------------------
-- Table structure for syc_module
-- ----------------------------
DROP TABLE IF EXISTS `syc_module`;
CREATE TABLE `syc_module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `runable` int(1) DEFAULT '0',
  `special_key` text,
  `module_transaction` char(1) DEFAULT '0',
  `order` int(11) DEFAULT '1',
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syc_module
-- ----------------------------
INSERT INTO `syc_module` VALUES ('1', 'default', '基础测试,测试最基础的同步方法,包含时间增量同步,数据不做任何处理.', '1', null, '0', '1');
INSERT INTO `syc_module` VALUES ('2', 'no time sync Method', '不需要时间增量同步的方法,数据不做任何处理', '1', null, '0', '1');
INSERT INTO `syc_module` VALUES ('3', 'm2m sync Method', '多对多字段拆分方法', '1', null, '0', '1');
INSERT INTO `syc_module` VALUES ('4', 'join time sync Method', '通过表与表的关联添加时间条件更新', '1', null, '0', '1');
INSERT INTO `syc_module` VALUES ('5', 'table specialKey', '常用的tableSpecialKey配置', '1', null, '0', '1');
INSERT INTO `syc_module` VALUES ('6', 'module transaction Method', '模块级事务同步方法', '1', null, '1', '1');
INSERT INTO `syc_module` VALUES ('7', 'primary Key upper Method', '以主键为增量同步条件的同步方法', '1', null, '0', '1');

-- ----------------------------
-- Table structure for syc_table
-- ----------------------------
DROP TABLE IF EXISTS `syc_table`;
CREATE TABLE `syc_table` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `src_table` varchar(45) NOT NULL COMMENT '数据源表名',
  `dest_table` varchar(45) NOT NULL COMMENT '目标表名',
  `db_id` int(11) NOT NULL,
  `table_type` varchar(2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `runable` int(1) DEFAULT '1',
  `module_id` int(11) DEFAULT NULL,
  `special_key` text,
  `order` int(11) DEFAULT '1',
  `sync_time` datetime DEFAULT NULL,
  `get_info` char(1) DEFAULT NULL,
  PRIMARY KEY (`table_id`) USING BTREE,
  KEY `fk_syc_table_syc_db1_idx` (`db_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syc_table
-- ----------------------------
INSERT INTO `syc_table` VALUES ('1', 'src_table', 'dest_table', '1', '1', '1', '1', '1', null, '1', '1990-01-01 00:00:00', '0');
INSERT INTO `syc_table` VALUES ('2', 'join_table', 'dest_no_time_table', '1', '3', '1', '1', '2', null, '1', '1990-01-01 00:00:00', '0');
INSERT INTO `syc_table` VALUES ('3', 'src_m2m_table', 'dest_m2m_table', '1', '3', '1', '1', '3', null, '1', '1990-01-01 00:00:00', '0');
INSERT INTO `syc_table` VALUES ('4', 'join_table', 'dest_join_table', '1', '4', '1', '1', '4', null, '1', '1990-01-01 00:00:00', '0');
INSERT INTO `syc_table` VALUES ('5', 'src_table_special_key', 'dest_table_special_key', '1', '1', '1', '0', '5', '{\"S\":[\"replace into src_table_special_key(id,value1,value2) value(1,\'在同步开始前运行的语句\',null)\",\"replace into src_table_special_key(id,value1,value2) value(2,\'在同步结束后会由E运行的语句删除\',null)\"],\"E\":\"delete from dest_table_special_key where id=2\",\"ONCE\":\"1\"}', '1', '1990-01-01 00:00:00', '0');
INSERT INTO `syc_table` VALUES ('6', 'module_src_table1', 'module_dest_table1', '1', '3', '1', '1', '6', null, '1', '1990-01-01 00:00:00', '0');
INSERT INTO `syc_table` VALUES ('7', 'module_src_table2', 'module_dest_table2', '1', '3', '1', '1', '6', null, '2', '1990-01-01 00:00:00', '0');
INSERT INTO `syc_table` VALUES ('8', 'primary_src_table', 'primary_dest_table', '1', '5', null, '1', '7', '{\"primaryValue\":\"0\"}', '1', '1990-01-01 00:00:00', '0');
