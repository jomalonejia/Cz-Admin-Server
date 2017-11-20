/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cz

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-11-17 16:55:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `backend_role`
-- ----------------------------
DROP TABLE IF EXISTS `backend_role`;
CREATE TABLE `backend_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of backend_role
-- ----------------------------
INSERT INTO `backend_role` VALUES ('1', 'ROLE_USER');
INSERT INTO `backend_role` VALUES ('2', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for `backend_user`
-- ----------------------------
DROP TABLE IF EXISTS `backend_user`;
CREATE TABLE `backend_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `fullname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `profile` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of backend_user
-- ----------------------------
INSERT INTO `backend_user` VALUES ('1', '大蛀牙', 'admin@gmail.com', '1', '2017-08-01 14:38:20', '$2a$10$2NxKa9i9hyavf23J0ww2deX5kbxQvN0AGswdRgIQdj3me3U.GRrxq', 'http://otlht2gvo.bkt.clouddn.com/FmcEZZUaGhj9DKm9j_drFYh7BmNc');
INSERT INTO `backend_user` VALUES ('2', '吔屎啦', 'user1@qq.com', '1', '2017-08-09 14:26:32', '$2a$10$2NxKa9i9hyavf23J0ww2deX5kbxQvN0AGswdRgIQdj3me3U.GRrxq', 'http://otlht2gvo.bkt.clouddn.com/FhJjx2HL0l0OCLo6bb-MGKYc-UhG');
INSERT INTO `backend_user` VALUES ('3', '呵呵哒', 'user2@qq.com', '1', '2017-08-08 08:50:15', '$2a$10$cOb0oVdzScQTY4KldVzQQ.h2YhgqdtXbyFptSDprWbrOlj67WgAa6', 'http://otlht2gvo.bkt.clouddn.com/FgCT5ZtZiSXTC8tHZohHVBhrPxgc');
INSERT INTO `backend_user` VALUES ('913269116968128512', null, 'jomalone@outlook.com', '1', '2017-09-28 13:08:20', '$2a$10$WOMTeQOq9szz.JcNHCBPjey1181850e2MA/ZepPJRkYbelVQNk9/K', 'default.png');
INSERT INTO `backend_user` VALUES ('913573718590545920', null, '123@qq.com', '1', '2017-09-29 09:18:42', '$2a$10$NLF4rx44D6CED32eazqob.o/vFIvbenOZiKVYnIpb8UTyDP5l4blS', 'default.png');

-- ----------------------------
-- Table structure for `backend_user_relationship`
-- ----------------------------
DROP TABLE IF EXISTS `backend_user_relationship`;
CREATE TABLE `backend_user_relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user1_id` bigint(20) NOT NULL,
  `user2_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of backend_user_relationship
-- ----------------------------
INSERT INTO `backend_user_relationship` VALUES ('1', '1', '2');
INSERT INTO `backend_user_relationship` VALUES ('2', '1', '3');
INSERT INTO `backend_user_relationship` VALUES ('3', '2', '1');
INSERT INTO `backend_user_relationship` VALUES ('4', '3', '1');
INSERT INTO `backend_user_relationship` VALUES ('5', '3', '2');

-- ----------------------------
-- Table structure for `backend_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `backend_user_role`;
CREATE TABLE `backend_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=913573718913507329 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of backend_user_role
-- ----------------------------
INSERT INTO `backend_user_role` VALUES ('891889255350079488', '4', '1');
INSERT INTO `backend_user_role` VALUES ('891906392483500032', '1', '1');
INSERT INTO `backend_user_role` VALUES ('891906392609329152', '1', '2');
INSERT INTO `backend_user_role` VALUES ('892550087935307776', '892550087922724864', '1');
INSERT INTO `backend_user_role` VALUES ('894722136124538880', '894722136103567360', '1');
INSERT INTO `backend_user_role` VALUES ('913269117236563968', '913269116968128512', '1');
INSERT INTO `backend_user_role` VALUES ('913573718913507328', '913573718590545920', '1');

-- ----------------------------
-- Table structure for `front_authority`
-- ----------------------------
DROP TABLE IF EXISTS `front_authority`;
CREATE TABLE `front_authority` (
  `ID` varchar(255) NOT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `AUTHORITY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of front_authority
-- ----------------------------
INSERT INTO `front_authority` VALUES ('1', '1', 'USER');
INSERT INTO `front_authority` VALUES ('2', '2', 'ADMIN');
INSERT INTO `front_authority` VALUES ('3', '2', 'USER');

-- ----------------------------
-- Table structure for `front_user`
-- ----------------------------
DROP TABLE IF EXISTS `front_user`;
CREATE TABLE `front_user` (
  `ID` varchar(255) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `ENABLED` tinyint(4) DEFAULT NULL,
  `PROFILE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of front_user
-- ----------------------------
INSERT INTO `front_user` VALUES ('1', 'user', '$2a$10$a1hCMnbrK1E1rNFBNJZWL.9HfjH7FHBHg5oXTchWz8765BtAgkt6G', 'jomalone', 'user', '1', null, null, null);
INSERT INTO `front_user` VALUES ('2', 'admin', '$2a$10$2NxKa9i9hyavf23J0ww2deX5kbxQvN0AGswdRgIQdj3me3U.GRrxq', 'jomalone', 'admin', '1', null, null, null);

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('caAdmin-Scheduler', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('czAdmin-Scheduler', 'STATE_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('czAdmin-Scheduler', 'admin', '1498550340002', '20000');
INSERT INTO `qrtz_scheduler_state` VALUES ('iBase4J-Scheduler', 'Administrator1510888928092', '1510895011652', '20000');

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_cart`
-- ----------------------------
DROP TABLE IF EXISTS `sys_cart`;
CREATE TABLE `sys_cart` (
  `ID` varchar(255) NOT NULL,
  `ITEM_ID` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `COUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_cart
-- ----------------------------
INSERT INTO `sys_cart` VALUES ('1bca96b315db4471b77a513310eea451', '60e054fdd0c74824bbbac46bf7d08603', 'user', '2');
INSERT INTO `sys_cart` VALUES ('42e59460991941bc8e8f53551348d102', 'ffc1044c2f2a4f29a37a57eac72817d7', 'user', '1');

-- ----------------------------
-- Table structure for `sys_cart_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_cart_param`;
CREATE TABLE `sys_cart_param` (
  `CART_ID` varchar(255) DEFAULT NULL,
  `PARAM_ID` varchar(255) DEFAULT NULL,
  `PARAM_VALUE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_cart_param
-- ----------------------------
INSERT INTO `sys_cart_param` VALUES ('1bca96b315db4471b77a513310eea451', '1', '#997d52');
INSERT INTO `sys_cart_param` VALUES ('1bca96b315db4471b77a513310eea451', '2', 'S');
INSERT INTO `sys_cart_param` VALUES ('42e59460991941bc8e8f53551348d102', '2', 'L');

-- ----------------------------
-- Table structure for `sys_category`
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES ('1', '0', '电子产品');
INSERT INTO `sys_category` VALUES ('2', '0', '服装');
INSERT INTO `sys_category` VALUES ('3', '0', '文化音响');
INSERT INTO `sys_category` VALUES ('4', '0', null);
INSERT INTO `sys_category` VALUES ('5', '0', null);
INSERT INTO `sys_category` VALUES ('6', '0', null);
INSERT INTO `sys_category` VALUES ('7', '0', null);
INSERT INTO `sys_category` VALUES ('8', '0', null);
INSERT INTO `sys_category` VALUES ('9', '0', null);
INSERT INTO `sys_category` VALUES ('10', '1', '手机');
INSERT INTO `sys_category` VALUES ('11', '1', '充电器');
INSERT INTO `sys_category` VALUES ('12', '1', '耳机');
INSERT INTO `sys_category` VALUES ('13', '1', null);
INSERT INTO `sys_category` VALUES ('14', '1', null);
INSERT INTO `sys_category` VALUES ('15', '1', null);
INSERT INTO `sys_category` VALUES ('16', '1', null);
INSERT INTO `sys_category` VALUES ('17', '1', null);
INSERT INTO `sys_category` VALUES ('18', '1', null);
INSERT INTO `sys_category` VALUES ('19', '1', null);
INSERT INTO `sys_category` VALUES ('20', '2', 'T恤');
INSERT INTO `sys_category` VALUES ('21', '2', '卫衣');
INSERT INTO `sys_category` VALUES ('22', '2', null);
INSERT INTO `sys_category` VALUES ('23', '2', null);
INSERT INTO `sys_category` VALUES ('24', '2', null);
INSERT INTO `sys_category` VALUES ('25', '2', null);
INSERT INTO `sys_category` VALUES ('26', '2', null);
INSERT INTO `sys_category` VALUES ('27', '2', null);
INSERT INTO `sys_category` VALUES ('28', '2', null);
INSERT INTO `sys_category` VALUES ('29', '2', null);
INSERT INTO `sys_category` VALUES ('30', '3', '书籍');

-- ----------------------------
-- Table structure for `sys_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_item`;
CREATE TABLE `sys_item` (
  `ID` varchar(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  `PRICE` int(11) DEFAULT NULL,
  `IMAGE` text,
  `DESCRIBE` text,
  `DISCOUNT` float DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_item
-- ----------------------------
INSERT INTO `sys_item` VALUES ('60e054fdd0c74824bbbac46bf7d08603', 'FIIL Diva 智能蓝牙无线降噪耳机', '12', '2699', 'http://otlht2gvo.bkt.clouddn.com/FhpQQW8zATp_hjtj9GVll6_MKZGZ', '手势触控、智能启停', '0.6');
INSERT INTO `sys_item` VALUES ('82a72be021db4db19798cf7bd0ae74ac', '《深泽直人》', '30', '269', 'http://otlht2gvo.bkt.clouddn.com/Foi0SAy_hSVkS89WUVqDRQprPXZT', '首次面向中国读者介绍其作品', '1');
INSERT INTO `sys_item` VALUES ('832ddb73c77d4fa88f48f02a54442041', '小米6', '10', '2599', 'http://otlht2gvo.bkt.clouddn.com/Fp6jkEQZvDlmCxCS_fzfyo6fsbSl', '第六代小米手机', '1');
INSERT INTO `sys_item` VALUES ('ffc1044c2f2a4f29a37a57eac72817d7', 'supreme短袖', '20', '999', 'http://otlht2gvo.bkt.clouddn.com/Fg_DBildmaFwRl6gsHOz6iHdUewL', '冠希哥推荐', '1');

-- ----------------------------
-- Table structure for `sys_item_hot`
-- ----------------------------
DROP TABLE IF EXISTS `sys_item_hot`;
CREATE TABLE `sys_item_hot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_item_hot
-- ----------------------------
INSERT INTO `sys_item_hot` VALUES ('1', '60e054fdd0c74824bbbac46bf7d08603');
INSERT INTO `sys_item_hot` VALUES ('2', '82a72be021db4db19798cf7bd0ae74ac');
INSERT INTO `sys_item_hot` VALUES ('3', '832ddb73c77d4fa88f48f02a54442041');
INSERT INTO `sys_item_hot` VALUES ('4', 'ffc1044c2f2a4f29a37a57eac72817d7');

-- ----------------------------
-- Table structure for `sys_item_images`
-- ----------------------------
DROP TABLE IF EXISTS `sys_item_images`;
CREATE TABLE `sys_item_images` (
  `ID` varchar(255) NOT NULL DEFAULT '',
  `ITEM_ID` varchar(255) NOT NULL DEFAULT 'null',
  `URL` text NOT NULL,
  `POSITION` smallint(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_item_images
-- ----------------------------
INSERT INTO `sys_item_images` VALUES ('012bdaf5d319477cb2cc4832ad0aca66', 'dca86a8ede7246babb19aa50483ab714', '', '3');
INSERT INTO `sys_item_images` VALUES ('02082bc898764bad9d65bf6f974dfd30', '2a7d783535a645f69726e7a993a6a6cb', '', '3');
INSERT INTO `sys_item_images` VALUES ('023776aeab0348ac88f9949446625941', '832ddb73c77d4fa88f48f02a54442041', 'http://otlht2gvo.bkt.clouddn.com/Fp6jkEQZvDlmCxCS_fzfyo6fsbSl', '2');
INSERT INTO `sys_item_images` VALUES ('0472c4249fc24a68b21d3c67aebe2984', 'b99db128522349fabd9270bc9d9f9c64', '', '3');
INSERT INTO `sys_item_images` VALUES ('05357747b7fa4e208029dabb0829cb5f', 'ffc1044c2f2a4f29a37a57eac72817d7', '', '5');
INSERT INTO `sys_item_images` VALUES ('08d16cd48fd8478a897691c480a3a056', 'fa509b147ce6481ba649773c6854910c', '', '1');
INSERT INTO `sys_item_images` VALUES ('0b26f7580a7247dba7d39416891e6016', 'dca86a8ede7246babb19aa50483ab714', '', '1');
INSERT INTO `sys_item_images` VALUES ('0c3f9446d51c42459ab0617c9c94c518', '832ddb73c77d4fa88f48f02a54442041', 'http://otlht2gvo.bkt.clouddn.com/Fk0OmzxlsInUQsVeXw_I7GZmXLuY', '1');
INSERT INTO `sys_item_images` VALUES ('0d3e52d58de44f618d93100358f6cd94', '82a72be021db4db19798cf7bd0ae74ac', '', '4');
INSERT INTO `sys_item_images` VALUES ('0d48f0360a9d429c828c870c487f8f30', '228f876a2c794c53870d79d33dc2530a', '', '2');
INSERT INTO `sys_item_images` VALUES ('0d59f2dfeeab432686a6d49eb7f549bd', '69297c11adb240e4a1006304fa1dcf46', '', '4');
INSERT INTO `sys_item_images` VALUES ('1094898907244ee28aadac1bd6b80fb4', 'fa509b147ce6481ba649773c6854910c', '', '2');
INSERT INTO `sys_item_images` VALUES ('11282d10974143e28b3b4610ccd9617c', '5a07d84a6f734c2183d984b59afaa86e', '', '4');
INSERT INTO `sys_item_images` VALUES ('1208d934ff0a4d3a8a4c2feea7c6f451', '82a72be021db4db19798cf7bd0ae74ac', 'http://otlht2gvo.bkt.clouddn.com/Foi0SAy_hSVkS89WUVqDRQprPXZT', '0');
INSERT INTO `sys_item_images` VALUES ('131a29ee84754a6f91aee2b7c8766539', '60e054fdd0c74824bbbac46bf7d08603', 'http://otlht2gvo.bkt.clouddn.com/FkxMxxxMD9nCMx7cEUi2hsXwo-Jn', '2');
INSERT INTO `sys_item_images` VALUES ('1434131dd87d44a1b4b3d68508011fb0', '159cb72f620c48e78154c4f7c8b460e3', '', '4');
INSERT INTO `sys_item_images` VALUES ('14b28fad6aaa47828abbac4d0a477e5c', '69297c11adb240e4a1006304fa1dcf46', '', '1');
INSERT INTO `sys_item_images` VALUES ('1b227391ee2c44f39d4ba8eddcbab3c6', '2a7d783535a645f69726e7a993a6a6cb', '', '4');
INSERT INTO `sys_item_images` VALUES ('1b6fee2978f049f58bd06ed16dd7ace0', '086c65c0a17843e8a5375ded70f52b68', '', '0');
INSERT INTO `sys_item_images` VALUES ('1e21c89c0b6d4db4beeef98754cc18b1', '159cb72f620c48e78154c4f7c8b460e3', '', '0');
INSERT INTO `sys_item_images` VALUES ('1f09af8a7a284ed0850b4e229bc24b1e', 'b99db128522349fabd9270bc9d9f9c64', '', '1');
INSERT INTO `sys_item_images` VALUES ('1fe15c34a6544d58a9883c076ab1f8c6', '60e054fdd0c74824bbbac46bf7d08603', 'http://otlht2gvo.bkt.clouddn.com/FvgbMq-Fo5iDTmAfzA50r0RT1t-1', '3');
INSERT INTO `sys_item_images` VALUES ('208e8d1468ca4cfca97aea7da3c975ea', 'c8d79881a4e94f7ea1b67a8200e0a1dd', '', '4');
INSERT INTO `sys_item_images` VALUES ('20ff7a8f833a46bb87fa2df25f77dccf', 'ffc1044c2f2a4f29a37a57eac72817d7', '', '3');
INSERT INTO `sys_item_images` VALUES ('242de6aaccfa4d4a84da7a254753f517', '45641a40818f459ea896a42eb7488c7b', '', '4');
INSERT INTO `sys_item_images` VALUES ('26ad073e4b1d4c77afe570f7d43fa77d', 'dca86a8ede7246babb19aa50483ab714', '', '4');
INSERT INTO `sys_item_images` VALUES ('26dd440e3c3546ad9bc9143f8fdd82e4', '60e054fdd0c74824bbbac46bf7d08603', 'http://otlht2gvo.bkt.clouddn.com/FrkSsHtfiiITeRhjE9ZmOsJYfWAZ', '1');
INSERT INTO `sys_item_images` VALUES ('274a99dca3d24ee893de1ba9f5a0d926', '45641a40818f459ea896a42eb7488c7b', '', '0');
INSERT INTO `sys_item_images` VALUES ('28cf081ef1b64e26b2f07ed518102b17', 'b19bac8fcc6f40faa2fb8a6c1783fa1f', '', '2');
INSERT INTO `sys_item_images` VALUES ('2b8f0e365f764ab2b0ac71d963807533', '82a72be021db4db19798cf7bd0ae74ac', '', '3');
INSERT INTO `sys_item_images` VALUES ('2bfac9fdbb84439aa610675f5a887118', 'c750bf1be36d48819588e38da7513e6d', '', '4');
INSERT INTO `sys_item_images` VALUES ('2d06d85aeb154051b5fae1b08b70d750', '5a07d84a6f734c2183d984b59afaa86e', '', '2');
INSERT INTO `sys_item_images` VALUES ('335a83ffde564404b4ffad1660014a2c', 'dca86a8ede7246babb19aa50483ab714', '', '0');
INSERT INTO `sys_item_images` VALUES ('337cb14a8321469a8ead981c02f630f0', '60e054fdd0c74824bbbac46bf7d08603', '', '5');
INSERT INTO `sys_item_images` VALUES ('3685dd423a144b23b614a9954137096b', 'c8d79881a4e94f7ea1b67a8200e0a1dd', '', '5');
INSERT INTO `sys_item_images` VALUES ('373ec59d728d4568a70c462c9a8181f5', '159cb72f620c48e78154c4f7c8b460e3', '', '1');
INSERT INTO `sys_item_images` VALUES ('379c932b137b47209e5d867132923f80', '832ddb73c77d4fa88f48f02a54442041', '', '5');
INSERT INTO `sys_item_images` VALUES ('3886fb53d2d844f08359c5dfd6819444', 'c053465e0b93430a980627647ef042a7', '', '4');
INSERT INTO `sys_item_images` VALUES ('39e0fde6ea8b48f9a295da3fbe1a50be', '228f876a2c794c53870d79d33dc2530a', '', '0');
INSERT INTO `sys_item_images` VALUES ('3ca07774dfc14354b25505f3f609277c', 'fd55b6d38d094228bc769cefd5939911', '', '0');
INSERT INTO `sys_item_images` VALUES ('4206e258c4234f2b9a6eeb96de3fabb8', '086c65c0a17843e8a5375ded70f52b68', '', '3');
INSERT INTO `sys_item_images` VALUES ('43bf4666276b45119e41bdcb6e08127d', 'b19bac8fcc6f40faa2fb8a6c1783fa1f', '', '4');
INSERT INTO `sys_item_images` VALUES ('43f93e6f648b4c498ca44e947585ec6d', '82a72be021db4db19798cf7bd0ae74ac', '', '1');
INSERT INTO `sys_item_images` VALUES ('46ad563644ae4658a77f9e2bf2dea439', '5a07d84a6f734c2183d984b59afaa86e', '', '0');
INSERT INTO `sys_item_images` VALUES ('4985b39359554a1aa155a1bb8733a4c3', '3d96eb04ad114c189d6479f68bbff84e', '', '0');
INSERT INTO `sys_item_images` VALUES ('4b8cc3cc3773490fa58ae4ecf7327f62', '2a7d783535a645f69726e7a993a6a6cb', '', '5');
INSERT INTO `sys_item_images` VALUES ('4eaba40ff2524488b1a519429d62cd01', '086c65c0a17843e8a5375ded70f52b68', '', '5');
INSERT INTO `sys_item_images` VALUES ('4f03d08fc9b64ed997c854c7aa1add92', 'b19bac8fcc6f40faa2fb8a6c1783fa1f', '', '5');
INSERT INTO `sys_item_images` VALUES ('4f286f18f5f3401a9e3bc9f129cd4953', 'c053465e0b93430a980627647ef042a7', '', '0');
INSERT INTO `sys_item_images` VALUES ('504929f47be3498fadebc9ae91d7ba39', 'c750bf1be36d48819588e38da7513e6d', '', '2');
INSERT INTO `sys_item_images` VALUES ('51fb854a6bab482dbcf96d2141674d19', 'c053465e0b93430a980627647ef042a7', '', '2');
INSERT INTO `sys_item_images` VALUES ('5695e846cfa0449399c17f6993e06c93', '228f876a2c794c53870d79d33dc2530a', '', '4');
INSERT INTO `sys_item_images` VALUES ('57826b9e2d9647569f086870c766d04a', 'fa509b147ce6481ba649773c6854910c', '', '4');
INSERT INTO `sys_item_images` VALUES ('57d8a76179fe4d1a9b3a2d2acc64e267', 'ffc1044c2f2a4f29a37a57eac72817d7', 'http://otlht2gvo.bkt.clouddn.com/Fg_DBildmaFwRl6gsHOz6iHdUewL', '0');
INSERT INTO `sys_item_images` VALUES ('582333623d0442e6a16c46122f42f3d0', '086c65c0a17843e8a5375ded70f52b68', '', '2');
INSERT INTO `sys_item_images` VALUES ('58323ee5874f43caab9be91c408cbd5b', '3d96eb04ad114c189d6479f68bbff84e', '', '2');
INSERT INTO `sys_item_images` VALUES ('58479719c03444559b34efc92d5e8fb3', '69297c11adb240e4a1006304fa1dcf46', '', '3');
INSERT INTO `sys_item_images` VALUES ('59568e6331734305bd461baa67836bac', 'b19bac8fcc6f40faa2fb8a6c1783fa1f', '', '3');
INSERT INTO `sys_item_images` VALUES ('5ac18e90cd784e89a4d43b962939f659', '2a7d783535a645f69726e7a993a6a6cb', '', '0');
INSERT INTO `sys_item_images` VALUES ('5acf442a958a49c7863830be9b8660e5', 'c8d79881a4e94f7ea1b67a8200e0a1dd', '', '3');
INSERT INTO `sys_item_images` VALUES ('5f06f261305040cda8b5b0ae72adb061', '832ddb73c77d4fa88f48f02a54442041', '', '4');
INSERT INTO `sys_item_images` VALUES ('600efb7ef3914f35bad551277f1a089d', '7ea564c89a894959b6bdd2f3f6446bb3', '', '0');
INSERT INTO `sys_item_images` VALUES ('608a939fc29a4ff78812e427571100b8', '69297c11adb240e4a1006304fa1dcf46', '', '2');
INSERT INTO `sys_item_images` VALUES ('62ebba16a956444488f534cb0b095789', 'dca86a8ede7246babb19aa50483ab714', '', '5');
INSERT INTO `sys_item_images` VALUES ('64dff48e23b148fd8e2c4e45511ebefe', 'ffc1044c2f2a4f29a37a57eac72817d7', 'http://otlht2gvo.bkt.clouddn.com/Fqc88OXUVDcQEgVSPxE9zdzAM9YX', '2');
INSERT INTO `sys_item_images` VALUES ('65948f13be5943758aa0ee8dd7370af7', 'c8d79881a4e94f7ea1b67a8200e0a1dd', '', '1');
INSERT INTO `sys_item_images` VALUES ('69149a978b764e39a450f640df99c087', '3d96eb04ad114c189d6479f68bbff84e', '', '3');
INSERT INTO `sys_item_images` VALUES ('6b945f9c11594f6fa897145aeea412d0', '3d96eb04ad114c189d6479f68bbff84e', '', '4');
INSERT INTO `sys_item_images` VALUES ('6ba78a300ecd4ad9bab36e92f038a6fc', '832ddb73c77d4fa88f48f02a54442041', '', '3');
INSERT INTO `sys_item_images` VALUES ('6cc9a61a5cd3478e874df379790035c4', 'c053465e0b93430a980627647ef042a7', '', '5');
INSERT INTO `sys_item_images` VALUES ('6d360ea117f94136934a035143526cdc', 'c8d79881a4e94f7ea1b67a8200e0a1dd', '', '0');
INSERT INTO `sys_item_images` VALUES ('6ed9fd60de9e438c9f83ea99c36caa2b', '38cb398c0cad40458fc24cb46273ee72', '', '0');
INSERT INTO `sys_item_images` VALUES ('739280161d0f4274a3ec68016f04c28d', 'fa509b147ce6481ba649773c6854910c', '', '0');
INSERT INTO `sys_item_images` VALUES ('739d83e125534bb686cee4b65f00dd8f', '38cb398c0cad40458fc24cb46273ee72', '', '5');
INSERT INTO `sys_item_images` VALUES ('73f8ed4222b6476a8ccbafea93bd5c73', '159cb72f620c48e78154c4f7c8b460e3', '', '3');
INSERT INTO `sys_item_images` VALUES ('7a20a09ffce84dfabeddf601c8d2f783', 'fd55b6d38d094228bc769cefd5939911', '', '1');
INSERT INTO `sys_item_images` VALUES ('7cdf27185946448bafc35d7909edaa6e', '7ea564c89a894959b6bdd2f3f6446bb3', '', '4');
INSERT INTO `sys_item_images` VALUES ('7d497dc39c39490b9bfb6ccf962f82e3', 'c750bf1be36d48819588e38da7513e6d', '', '3');
INSERT INTO `sys_item_images` VALUES ('80fb779f97ad4c489f0b8ff84f8de53e', '086c65c0a17843e8a5375ded70f52b68', '', '1');
INSERT INTO `sys_item_images` VALUES ('8158664fb30d4b4ea3109609a3bb490a', 'c750bf1be36d48819588e38da7513e6d', '', '5');
INSERT INTO `sys_item_images` VALUES ('85e24ace12fd41d1bfbf1e61968f599b', '45641a40818f459ea896a42eb7488c7b', '', '5');
INSERT INTO `sys_item_images` VALUES ('86b04c56e75c4420ac5fe146822b982f', '2a7d783535a645f69726e7a993a6a6cb', '', '2');
INSERT INTO `sys_item_images` VALUES ('885c939090494e6696b3077006899765', '5a07d84a6f734c2183d984b59afaa86e', '', '5');
INSERT INTO `sys_item_images` VALUES ('8984399bf0d141239cd72c1a7b236ab3', 'c750bf1be36d48819588e38da7513e6d', '', '1');
INSERT INTO `sys_item_images` VALUES ('8bd48b4276ee4e97a2f0508e7d9235c7', 'b99db128522349fabd9270bc9d9f9c64', '', '2');
INSERT INTO `sys_item_images` VALUES ('8fe946f971064203a4e77190f60b1a95', '3d96eb04ad114c189d6479f68bbff84e', '', '1');
INSERT INTO `sys_item_images` VALUES ('961160c36a914a6399d5a23566b15d3e', 'ffc1044c2f2a4f29a37a57eac72817d7', 'http://otlht2gvo.bkt.clouddn.com/Fg93Kg0TYeFKQTFmwthSe4QI48Ca', '1');
INSERT INTO `sys_item_images` VALUES ('961e70b5d79a48f69cedba88ac5208ec', '7ea564c89a894959b6bdd2f3f6446bb3', '', '1');
INSERT INTO `sys_item_images` VALUES ('9b1ed13f7c8d47e99217eace681c0af8', '38cb398c0cad40458fc24cb46273ee72', '', '2');
INSERT INTO `sys_item_images` VALUES ('9e6af395ffb941248684fb408024f971', '159cb72f620c48e78154c4f7c8b460e3', '', '5');
INSERT INTO `sys_item_images` VALUES ('9f274546394444bbaee33b7e0acb98c8', '832ddb73c77d4fa88f48f02a54442041', 'http://otlht2gvo.bkt.clouddn.com/Fp6jkEQZvDlmCxCS_fzfyo6fsbSl', '0');
INSERT INTO `sys_item_images` VALUES ('9f54d46822974c9b8bd1064062c217fd', '60e054fdd0c74824bbbac46bf7d08603', 'http://otlht2gvo.bkt.clouddn.com/Fm6PCWoyG6EeMaYn5Vj1Q_pmj_6A', '4');
INSERT INTO `sys_item_images` VALUES ('a06f6e1f9948489995e01714fd792736', 'fa509b147ce6481ba649773c6854910c', '', '5');
INSERT INTO `sys_item_images` VALUES ('a5c41146cc51451da921f3deea9a6c7b', '69297c11adb240e4a1006304fa1dcf46', '', '5');
INSERT INTO `sys_item_images` VALUES ('a97023957b7445de87c9ce113619be26', '7ea564c89a894959b6bdd2f3f6446bb3', '', '3');
INSERT INTO `sys_item_images` VALUES ('ab7994ca05c641e8b856a6699c15970f', '38cb398c0cad40458fc24cb46273ee72', '', '3');
INSERT INTO `sys_item_images` VALUES ('ad16a835b31f43e8a9276a65e1bda0a1', '7ea564c89a894959b6bdd2f3f6446bb3', '', '5');
INSERT INTO `sys_item_images` VALUES ('b1d144e539fa4903a61f27b9810c210a', 'c750bf1be36d48819588e38da7513e6d', '', '0');
INSERT INTO `sys_item_images` VALUES ('b266093fff9442e89989158f35b74451', '228f876a2c794c53870d79d33dc2530a', '', '5');
INSERT INTO `sys_item_images` VALUES ('b56ba4e031974c1fbdc66f709bd01300', '38cb398c0cad40458fc24cb46273ee72', '', '4');
INSERT INTO `sys_item_images` VALUES ('b706859a960b4bb3838cf7698b5de4cb', 'b19bac8fcc6f40faa2fb8a6c1783fa1f', '', '1');
INSERT INTO `sys_item_images` VALUES ('b766bfe19db84b4b880251c0c9b97282', '3d96eb04ad114c189d6479f68bbff84e', '', '5');
INSERT INTO `sys_item_images` VALUES ('b982e730f69d45de99682e6fddb3ec12', 'ffc1044c2f2a4f29a37a57eac72817d7', '', '4');
INSERT INTO `sys_item_images` VALUES ('c178f1b4ab824e1a8c33a0a3145e8be0', 'dca86a8ede7246babb19aa50483ab714', '', '2');
INSERT INTO `sys_item_images` VALUES ('c1b315c84e7c4079baf79cab55e6f834', 'c8d79881a4e94f7ea1b67a8200e0a1dd', '', '2');
INSERT INTO `sys_item_images` VALUES ('c28e33fb706c49c3875df4a368935fb6', '45641a40818f459ea896a42eb7488c7b', '', '3');
INSERT INTO `sys_item_images` VALUES ('c359570a0b70454e8d1ecd26091cc332', 'fd55b6d38d094228bc769cefd5939911', '', '5');
INSERT INTO `sys_item_images` VALUES ('c5e6a4a626bc4b659a8cdf63feeca43d', '7ea564c89a894959b6bdd2f3f6446bb3', '', '2');
INSERT INTO `sys_item_images` VALUES ('c94e894058a54955a29d6849683ec527', '159cb72f620c48e78154c4f7c8b460e3', '', '2');
INSERT INTO `sys_item_images` VALUES ('ca59d2ea0a234db68f383e678ce21599', 'fd55b6d38d094228bc769cefd5939911', '', '3');
INSERT INTO `sys_item_images` VALUES ('cad4c8354ef2450e841f9f2b2bb5da98', 'b99db128522349fabd9270bc9d9f9c64', '', '4');
INSERT INTO `sys_item_images` VALUES ('cb9d39eb7bec4e6d80290ea6ec672cbd', 'b19bac8fcc6f40faa2fb8a6c1783fa1f', '', '0');
INSERT INTO `sys_item_images` VALUES ('d153a972938f4c249b257988651cbeae', '45641a40818f459ea896a42eb7488c7b', '', '2');
INSERT INTO `sys_item_images` VALUES ('d4785a0f562f4922967eea80235cfa6b', '38cb398c0cad40458fc24cb46273ee72', '', '1');
INSERT INTO `sys_item_images` VALUES ('d55467a3adf34f7f86ae9ce2d72315e9', 'c053465e0b93430a980627647ef042a7', '', '3');
INSERT INTO `sys_item_images` VALUES ('dba966e031c04866bb48dc35c62f143d', '69297c11adb240e4a1006304fa1dcf46', '', '0');
INSERT INTO `sys_item_images` VALUES ('e024c5a7ae2a470eb2140192e2b81a1f', '5a07d84a6f734c2183d984b59afaa86e', '', '3');
INSERT INTO `sys_item_images` VALUES ('e0b3d9ec2b754d4b845863d3e87ddc24', '086c65c0a17843e8a5375ded70f52b68', '', '4');
INSERT INTO `sys_item_images` VALUES ('e11c8654d1a9498380f51093c2a23f20', '82a72be021db4db19798cf7bd0ae74ac', '', '5');
INSERT INTO `sys_item_images` VALUES ('e24d7a7308da464cb75843dd31197bec', '2a7d783535a645f69726e7a993a6a6cb', '', '1');
INSERT INTO `sys_item_images` VALUES ('e872d5cb106f420a9f76cd60d9a6b80e', '82a72be021db4db19798cf7bd0ae74ac', '', '2');
INSERT INTO `sys_item_images` VALUES ('ee5f54563caa455c9383d6762683335a', '228f876a2c794c53870d79d33dc2530a', '', '1');
INSERT INTO `sys_item_images` VALUES ('eef58c8863dc4cc4a0827f7e11187bb0', 'fa509b147ce6481ba649773c6854910c', '', '3');
INSERT INTO `sys_item_images` VALUES ('ef2de069215e443dbe83c93cf5603c8d', 'c053465e0b93430a980627647ef042a7', '', '1');
INSERT INTO `sys_item_images` VALUES ('f136e6e088f040fbbfa111c749178921', 'fd55b6d38d094228bc769cefd5939911', '', '4');
INSERT INTO `sys_item_images` VALUES ('f56dbae998df4402a04e5f5f8423baab', 'b99db128522349fabd9270bc9d9f9c64', '', '5');
INSERT INTO `sys_item_images` VALUES ('f7610c9ac33148b5a705e1cb88276343', 'fd55b6d38d094228bc769cefd5939911', '', '2');
INSERT INTO `sys_item_images` VALUES ('f7a244234e6e44b9911c57ed0d91a59c', '5a07d84a6f734c2183d984b59afaa86e', '', '1');
INSERT INTO `sys_item_images` VALUES ('f9a48af174e74ba7bce507879ebcc1f2', 'b99db128522349fabd9270bc9d9f9c64', '', '0');
INSERT INTO `sys_item_images` VALUES ('ff053b0122574495a4657f7f9b4bf37e', '60e054fdd0c74824bbbac46bf7d08603', 'http://otlht2gvo.bkt.clouddn.com/FrRSn04wpAzyQY3RMVI3sz4a7e6A', '0');
INSERT INTO `sys_item_images` VALUES ('ff053d187b29480e9453215f637df239', '45641a40818f459ea896a42eb7488c7b', '', '1');
INSERT INTO `sys_item_images` VALUES ('ff23b8a439a04affb59d4c4343801b75', '228f876a2c794c53870d79d33dc2530a', '', '3');

-- ----------------------------
-- Table structure for `sys_item_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_item_param`;
CREATE TABLE `sys_item_param` (
  `ITEM_ID` varchar(255) NOT NULL,
  `PARAM_ID` int(11) NOT NULL,
  `PARAM_VALUE` varchar(255) NOT NULL,
  `INVENTORY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_item_param
-- ----------------------------
INSERT INTO `sys_item_param` VALUES ('60e054fdd0c74824bbbac46bf7d08603', '1', '#fa4d3a', '2');
INSERT INTO `sys_item_param` VALUES ('60e054fdd0c74824bbbac46bf7d08603', '1', '#997d52', '3');
INSERT INTO `sys_item_param` VALUES ('60e054fdd0c74824bbbac46bf7d08603', '2', 'XL', '3');
INSERT INTO `sys_item_param` VALUES ('60e054fdd0c74824bbbac46bf7d08603', '2', 'XXL', '1');
INSERT INTO `sys_item_param` VALUES ('60e054fdd0c74824bbbac46bf7d08603', '2', 'XXXL', '2');
INSERT INTO `sys_item_param` VALUES ('60e054fdd0c74824bbbac46bf7d08603', '2', 'S', '1');
INSERT INTO `sys_item_param` VALUES ('60e054fdd0c74824bbbac46bf7d08603', '2', 'XS', '1');
INSERT INTO `sys_item_param` VALUES ('82a72be021db4db19798cf7bd0ae74ac', '3', '平装版', '2');
INSERT INTO `sys_item_param` VALUES ('82a72be021db4db19798cf7bd0ae74ac', '3', '精装版', '6');
INSERT INTO `sys_item_param` VALUES ('ffc1044c2f2a4f29a37a57eac72817d7', '2', 'XS', '16');
INSERT INTO `sys_item_param` VALUES ('ffc1044c2f2a4f29a37a57eac72817d7', '2', 'S', '26');
INSERT INTO `sys_item_param` VALUES ('ffc1044c2f2a4f29a37a57eac72817d7', '2', 'M', '38');
INSERT INTO `sys_item_param` VALUES ('ffc1044c2f2a4f29a37a57eac72817d7', '2', 'L', '46');
INSERT INTO `sys_item_param` VALUES ('ffc1044c2f2a4f29a37a57eac72817d7', '2', 'XL', '53');
INSERT INTO `sys_item_param` VALUES ('832ddb73c77d4fa88f48f02a54442041', '1', '#0e0e0d', '0');
INSERT INTO `sys_item_param` VALUES ('832ddb73c77d4fa88f48f02a54442041', '1', '#f50e0e', '0');
INSERT INTO `sys_item_param` VALUES ('832ddb73c77d4fa88f48f02a54442041', '1', '#fbfaf8', '0');

-- ----------------------------
-- Table structure for `sys_order`
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
  `ID` varchar(255) NOT NULL,
  `ORDER_ID` varchar(255) DEFAULT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `POST_FEE` int(11) DEFAULT NULL,
  `PRICE` int(11) DEFAULT NULL,
  `TOTAL_PRICE` int(11) DEFAULT NULL,
  `SHIPPING_NAME` varchar(255) DEFAULT NULL,
  `SHIPPING_NUMBER` varchar(255) DEFAULT NULL,
  `ORDER_MESSAGE` text,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `PAYMENT_TIME` datetime DEFAULT NULL,
  `CONSIGN_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `CLOSE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES ('1', 'SDFJK', 'SJDKF', '1', '112', '124', '1234', 'JSDF', 'SDFJ', 'SDF', '2017-09-15 15:39:28', '2017-09-15 15:39:28', '2017-09-15 15:39:28', null, null, null);
INSERT INTO `sys_order` VALUES ('2', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_order` VALUES ('3', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_order` VALUES ('4', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_order` VALUES ('5', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_order` VALUES ('6', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARAM_NAME` varchar(255) DEFAULT NULL,
  `PARAM_DESCRIBE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES ('1', 'color', '颜色');
INSERT INTO `sys_param` VALUES ('2', 'size', '尺码');
INSERT INTO `sys_param` VALUES ('3', 'binding', '装帧');

-- ----------------------------
-- Table structure for `sys_param_value`
-- ----------------------------
DROP TABLE IF EXISTS `sys_param_value`;
CREATE TABLE `sys_param_value` (
  `PARAM_ID` int(11) DEFAULT NULL,
  `PARAM_VALUE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_param_value
-- ----------------------------
INSERT INTO `sys_param_value` VALUES ('2', 'XS');
INSERT INTO `sys_param_value` VALUES ('2', 'S');
INSERT INTO `sys_param_value` VALUES ('2', 'M');
INSERT INTO `sys_param_value` VALUES ('2', 'L');
INSERT INTO `sys_param_value` VALUES ('2', 'XL');
INSERT INTO `sys_param_value` VALUES ('2', 'XXL');
INSERT INTO `sys_param_value` VALUES ('2', 'XXXL');
INSERT INTO `sys_param_value` VALUES ('3', '平装版');
INSERT INTO `sys_param_value` VALUES ('3', '精装版');
INSERT INTO `sys_param_value` VALUES ('1', 'color');

-- ----------------------------
-- Table structure for `sys_show_images`
-- ----------------------------
DROP TABLE IF EXISTS `sys_show_images`;
CREATE TABLE `sys_show_images` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` text NOT NULL,
  `URL` text NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_show_images
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_order`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_order`;
CREATE TABLE `sys_user_order` (
  `ID` varchar(255) NOT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `ORDER_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_order
-- ----------------------------
