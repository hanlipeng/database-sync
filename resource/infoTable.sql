/*
Navicat MySQL Data Transfer

Source Server         : mytest
Source Server Version : 50718
Source Host           : 10.88.1.86:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-03-30 19:06:55
*/

SET FOREIGN_KEY_CHECKS=0;

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
