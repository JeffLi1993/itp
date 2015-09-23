/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50617
Source Host           : localhost:3307
Source Database       : itp

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-09-23 16:06:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `CI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CI_Name` varchar(20) DEFAULT NULL,
  `CI_StudentSum` varchar(20) DEFAULT NULL,
  `CI_Profession` varchar(20) DEFAULT NULL,
  `CI_College` varchar(20) DEFAULT NULL,
  `CI_Sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('1', '信息管理与信息系统一班', '29', '信息管理与信息系统', '信息与工程学院', '');
INSERT INTO `class_info` VALUES ('2', '信息管理与信息系统二班', '29', '信息管理与信息系统', '信息与工程学院', '');
INSERT INTO `class_info` VALUES ('3', '生物医学工程专业生工一班', '31', '生物医学工程系', '仁济学院', '');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `C_ID` int(11) NOT NULL AUTO_INCREMENT,
  `C_Name` varchar(20) DEFAULT NULL,
  `C_Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '面向对象程序设计', '无');
INSERT INTO `course` VALUES ('2', '英语', '无');
INSERT INTO `course` VALUES ('3', '离散数学', '无');

-- ----------------------------
-- Table structure for course_chapter
-- ----------------------------
DROP TABLE IF EXISTS `course_chapter`;
CREATE TABLE `course_chapter` (
  `CC_Id` int(11) NOT NULL AUTO_INCREMENT,
  `C_Id` int(11) DEFAULT NULL,
  `CC_Name` varchar(20) DEFAULT NULL,
  `CC_Sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CC_Id`),
  KEY `FK_Reference_38` (`C_Id`),
  CONSTRAINT `FK_Reference_38` FOREIGN KEY (`C_Id`) REFERENCES `course` (`C_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_chapter
-- ----------------------------
INSERT INTO `course_chapter` VALUES ('1', '2', 'Util1', '');
INSERT INTO `course_chapter` VALUES ('2', '2', 'Util2', '');
INSERT INTO `course_chapter` VALUES ('3', '2', 'Util3', '');
INSERT INTO `course_chapter` VALUES ('4', '1', 'C#语言概述', '');
INSERT INTO `course_chapter` VALUES ('5', '1', '数据类型与流程控制', '');
INSERT INTO `course_chapter` VALUES ('6', '1', '数组与集合', '');
INSERT INTO `course_chapter` VALUES ('7', '3', '逻辑和证明', '');
INSERT INTO `course_chapter` VALUES ('8', '3', '集合', '');
INSERT INTO `course_chapter` VALUES ('9', '3', '高级计数技术', '');
INSERT INTO `course_chapter` VALUES ('10', '3', '关系', null);
INSERT INTO `course_chapter` VALUES ('11', '3', '图', null);
INSERT INTO `course_chapter` VALUES ('12', '3', '树', null);
INSERT INTO `course_chapter` VALUES ('13', '1', '窗体、控件和组件', null);
INSERT INTO `course_chapter` VALUES ('14', '1', '用户自定义控件', null);
INSERT INTO `course_chapter` VALUES ('15', '1', '面向对象基础编程', null);
INSERT INTO `course_chapter` VALUES ('16', '1', '面向对象的高级编程', null);
INSERT INTO `course_chapter` VALUES ('17', '1', '图形图像处理', null);

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `CI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CTR_ID` int(11) DEFAULT NULL,
  `CI_Place` varchar(20) DEFAULT NULL,
  `CI_DateTime` varchar(40) DEFAULT NULL,
  `CI_Period` varchar(20) DEFAULT NULL,
  `CI_Desription` varchar(50) DEFAULT NULL,
  `CI_QRCode` varchar(50) DEFAULT NULL,
  `CI_State` varchar(20) DEFAULT NULL,
  `CI_Over` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`CI_ID`),
  KEY `FK_Reference_15` (`CTR_ID`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`CTR_ID`) REFERENCES `course_teacher_relation` (`CTR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES ('1', '1', '求新楼1', '2015-09-23 14:00:00', '2', '无', '/wmuitphtml/qrcode/20140815141311.png', 'F', '2014-08-15 15:30:00');
INSERT INTO `course_info` VALUES ('2', '1', '求新楼1', '2015-09-23 14:00:00', '2', '1', '/wmuitphtml/qrcode/20140815141414.png', 'F', '2014-08-15 17:10:00');
INSERT INTO `course_info` VALUES ('3', '2', '求新楼1', '2014-08-15 14:30:47', '2', '321', '/wmuitphtml/qrcode/20140815141519.png', 'F', '2014-08-15 16:00:47');
INSERT INTO `course_info` VALUES ('4', '3', '求新楼1', '2014-08-15 18:30:19', '3', '12312', '/wmuitphtml/qrcode/20140815142252.png', 'F', '2014-08-15 20:45:19');
INSERT INTO `course_info` VALUES ('5', '1', '求新楼1', '2015-09-23 14:00:00', '2', '课详情描述', null, 'F', '2015-09-23 14:00:00');
INSERT INTO `course_info` VALUES ('6', '1', '求新楼1', '2015-09-23 14:00:00', '3', '这是求新楼1的课羊美11', null, 'F', '2015-09-23 14:00:00');
INSERT INTO `course_info` VALUES ('7', '1', '求新楼1', '2014-09-05 19:50:34', '2', '这是羊美 求新楼1', null, 'F', '2014-09-05 21:20:34');
INSERT INTO `course_info` VALUES ('8', '1', '求新楼-102', '2015-09-23 15:57:47', '3', '求新楼-102 信管英语课', null, 'T', '3915-09-23 18:12:47');

-- ----------------------------
-- Table structure for course_teacher_relation
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher_relation`;
CREATE TABLE `course_teacher_relation` (
  `CTR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TI_ID` int(11) DEFAULT NULL,
  `C_ID` int(11) DEFAULT NULL,
  `CTR_Sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CTR_ID`),
  KEY `FK_Reference_16` (`C_ID`),
  KEY `FK_Reference_20` (`TI_ID`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`C_ID`) REFERENCES `course` (`C_ID`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`TI_ID`) REFERENCES `teacher_info` (`TI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_teacher_relation
-- ----------------------------
INSERT INTO `course_teacher_relation` VALUES ('1', '1', '2', '');
INSERT INTO `course_teacher_relation` VALUES ('2', '2', '3', '无');
INSERT INTO `course_teacher_relation` VALUES ('3', '2', '1', '');

-- ----------------------------
-- Table structure for discussion_student_relation
-- ----------------------------
DROP TABLE IF EXISTS `discussion_student_relation`;
CREATE TABLE `discussion_student_relation` (
  `DT_ID` int(11) DEFAULT NULL,
  `SI_ID` int(11) DEFAULT NULL,
  KEY `DT_ID` (`DT_ID`),
  KEY `SI_ID` (`SI_ID`),
  CONSTRAINT `discussion_student_relation_ibfk_1` FOREIGN KEY (`DT_ID`) REFERENCES `discussion_topic` (`DT_ID`),
  CONSTRAINT `discussion_student_relation_ibfk_2` FOREIGN KEY (`SI_ID`) REFERENCES `student_info` (`SI_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discussion_student_relation
-- ----------------------------
INSERT INTO `discussion_student_relation` VALUES ('6', '327');

-- ----------------------------
-- Table structure for discussion_student_reply
-- ----------------------------
DROP TABLE IF EXISTS `discussion_student_reply`;
CREATE TABLE `discussion_student_reply` (
  `DSR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DT_ID` int(11) DEFAULT NULL,
  `SI_ID` int(11) DEFAULT NULL,
  `DSR_Content` varchar(50) DEFAULT NULL,
  `DSR_DataTime` varchar(50) DEFAULT NULL,
  `DSR_Top` varchar(20) DEFAULT NULL,
  `DSR_Fine` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DSR_ID`),
  KEY `FK_Reference_18` (`DT_ID`),
  KEY `FK_Reference_26` (`SI_ID`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`DT_ID`) REFERENCES `discussion_topic` (`DT_ID`),
  CONSTRAINT `FK_Reference_26` FOREIGN KEY (`SI_ID`) REFERENCES `student_info` (`SI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discussion_student_reply
-- ----------------------------
INSERT INTO `discussion_student_reply` VALUES ('1', '6', '327', '破婆婆送你哄哄你明', '2014-09-05 19:46:45', 'F', 'F');
INSERT INTO `discussion_student_reply` VALUES ('2', '6', '327', '人猿进化而成的能制造和使用工具进行劳动、并能运用语言进行交际的高级', '2014-09-05 19:48:00', 'T', 'F');
INSERT INTO `discussion_student_reply` VALUES ('3', '6', '327', '哈哈', '2014-09-05 19:48:30', 'F', 'F');

-- ----------------------------
-- Table structure for discussion_teacher_reply
-- ----------------------------
DROP TABLE IF EXISTS `discussion_teacher_reply`;
CREATE TABLE `discussion_teacher_reply` (
  `DTR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DT_ID` int(11) DEFAULT NULL,
  `TI_ID` int(11) DEFAULT NULL,
  `DTR_Content` varchar(50) DEFAULT NULL,
  `DTR_DataTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DTR_ID`),
  KEY `FK_Reference_19` (`DT_ID`),
  KEY `FK_Reference_25` (`TI_ID`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`DT_ID`) REFERENCES `discussion_topic` (`DT_ID`),
  CONSTRAINT `FK_Reference_25` FOREIGN KEY (`TI_ID`) REFERENCES `teacher_info` (`TI_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discussion_teacher_reply
-- ----------------------------

-- ----------------------------
-- Table structure for discussion_topic
-- ----------------------------
DROP TABLE IF EXISTS `discussion_topic`;
CREATE TABLE `discussion_topic` (
  `DT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CI_ID` int(11) DEFAULT NULL,
  `DT_Name` varchar(200) DEFAULT NULL,
  `DT_Content` varchar(400) DEFAULT NULL,
  `DT_State` varchar(20) DEFAULT NULL,
  `DT_DateTime` varchar(20) DEFAULT NULL,
  `DT_Top` varchar(20) DEFAULT NULL,
  `DT_Fine` varchar(20) DEFAULT NULL,
  `DT_Scan` varchar(100) DEFAULT NULL,
  `DT_Reply` varchar(100) DEFAULT NULL,
  `DT_Sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DT_ID`),
  KEY `FK_Reference_24` (`CI_ID`),
  CONSTRAINT `FK_Reference_24` FOREIGN KEY (`CI_ID`) REFERENCES `course_info` (`CI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discussion_topic
-- ----------------------------
INSERT INTO `discussion_topic` VALUES ('1', '4', '数据库设计问题', '数据库设计中，外模型是？', null, '2014-08-15 14:31:15', null, null, '4', '0', null);
INSERT INTO `discussion_topic` VALUES ('2', '2', '对于出国游学的问题', '你们喜欢去什么国家', null, '2014-08-15 14:50:56', null, null, '3', '0', null);
INSERT INTO `discussion_topic` VALUES ('3', '3', '事务的问题', '事务的执行次序？', null, '2014-08-15 14:32:10', null, null, '0', '0', null);
INSERT INTO `discussion_topic` VALUES ('4', '3', '概念数据模型问题', '概念数据模型依赖于哪个数据库管理系统？', null, '2014-08-15 14:32:42', null, null, '0', '0', null);
INSERT INTO `discussion_topic` VALUES ('5', '1', '对于口音的问题？', '你们喜欢什么口音的英语', null, '2014-08-15 14:32:53', null, null, '0', '0', null);
INSERT INTO `discussion_topic` VALUES ('6', '5', '数据库设计问题', '数据库设计问题 大家说', null, '2014-09-05 19:33:01', null, null, '5', '3', 'T');
INSERT INTO `discussion_topic` VALUES ('7', '6', '事务的问题', '事务的问题 大家爱说', null, '2014-09-05 19:33:15', null, null, '0', '0', 'T');

-- ----------------------------
-- Table structure for exam_student
-- ----------------------------
DROP TABLE IF EXISTS `exam_student`;
CREATE TABLE `exam_student` (
  `ES_id` int(11) NOT NULL AUTO_INCREMENT,
  `PI_ID` int(11) DEFAULT NULL,
  `SI_ID` int(11) DEFAULT NULL,
  `ES_Objective_Score` varchar(20) DEFAULT NULL,
  `ES_Sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ES_id`),
  KEY `FK_Reference_33` (`SI_ID`),
  KEY `FK_Reference_9` (`PI_ID`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`SI_ID`) REFERENCES `student_info` (`SI_ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`PI_ID`) REFERENCES `paper_info` (`PI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=531 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_student
-- ----------------------------
INSERT INTO `exam_student` VALUES ('1', '1', '350', null, 'T');
INSERT INTO `exam_student` VALUES ('2', '1', '351', null, 'T');
INSERT INTO `exam_student` VALUES ('3', '1', '352', null, 'T');
INSERT INTO `exam_student` VALUES ('4', '1', '353', null, 'T');
INSERT INTO `exam_student` VALUES ('5', '1', '354', null, 'T');
INSERT INTO `exam_student` VALUES ('6', '1', '355', null, 'T');
INSERT INTO `exam_student` VALUES ('7', '1', '356', null, 'T');
INSERT INTO `exam_student` VALUES ('8', '1', '357', null, 'T');
INSERT INTO `exam_student` VALUES ('9', '1', '358', null, 'T');
INSERT INTO `exam_student` VALUES ('10', '1', '359', null, 'T');
INSERT INTO `exam_student` VALUES ('11', '1', '360', null, 'T');
INSERT INTO `exam_student` VALUES ('12', '1', '361', null, 'T');
INSERT INTO `exam_student` VALUES ('13', '1', '362', null, 'T');
INSERT INTO `exam_student` VALUES ('14', '1', '363', null, 'T');
INSERT INTO `exam_student` VALUES ('15', '1', '364', null, 'T');
INSERT INTO `exam_student` VALUES ('16', '1', '365', null, 'T');
INSERT INTO `exam_student` VALUES ('17', '1', '366', null, 'T');
INSERT INTO `exam_student` VALUES ('18', '1', '367', null, 'T');
INSERT INTO `exam_student` VALUES ('19', '1', '368', null, 'T');
INSERT INTO `exam_student` VALUES ('20', '1', '369', null, 'T');
INSERT INTO `exam_student` VALUES ('21', '1', '370', null, 'T');
INSERT INTO `exam_student` VALUES ('22', '1', '371', null, 'T');
INSERT INTO `exam_student` VALUES ('23', '1', '372', null, 'T');
INSERT INTO `exam_student` VALUES ('24', '1', '373', null, 'T');
INSERT INTO `exam_student` VALUES ('25', '1', '374', null, 'T');
INSERT INTO `exam_student` VALUES ('26', '1', '375', null, 'T');
INSERT INTO `exam_student` VALUES ('27', '1', '376', null, 'T');
INSERT INTO `exam_student` VALUES ('28', '1', '377', null, 'T');
INSERT INTO `exam_student` VALUES ('29', '1', '378', null, 'T');
INSERT INTO `exam_student` VALUES ('30', '1', '379', null, 'T');
INSERT INTO `exam_student` VALUES ('31', '1', '380', null, 'T');
INSERT INTO `exam_student` VALUES ('32', '2', '321', null, 'T');
INSERT INTO `exam_student` VALUES ('33', '2', '322', null, 'T');
INSERT INTO `exam_student` VALUES ('34', '2', '323', null, 'T');
INSERT INTO `exam_student` VALUES ('35', '2', '324', null, 'T');
INSERT INTO `exam_student` VALUES ('36', '2', '325', null, 'T');
INSERT INTO `exam_student` VALUES ('37', '2', '326', null, 'T');
INSERT INTO `exam_student` VALUES ('38', '2', '327', null, 'T');
INSERT INTO `exam_student` VALUES ('39', '2', '328', null, 'T');
INSERT INTO `exam_student` VALUES ('40', '2', '329', null, 'T');
INSERT INTO `exam_student` VALUES ('41', '2', '330', null, 'T');
INSERT INTO `exam_student` VALUES ('42', '2', '331', null, 'T');
INSERT INTO `exam_student` VALUES ('43', '2', '332', null, 'T');
INSERT INTO `exam_student` VALUES ('44', '2', '333', null, 'T');
INSERT INTO `exam_student` VALUES ('45', '2', '334', null, 'T');
INSERT INTO `exam_student` VALUES ('46', '2', '335', null, 'T');
INSERT INTO `exam_student` VALUES ('47', '2', '336', null, 'T');
INSERT INTO `exam_student` VALUES ('48', '2', '337', null, 'T');
INSERT INTO `exam_student` VALUES ('49', '2', '338', null, 'T');
INSERT INTO `exam_student` VALUES ('50', '2', '339', null, 'T');
INSERT INTO `exam_student` VALUES ('51', '2', '340', null, 'T');
INSERT INTO `exam_student` VALUES ('52', '2', '341', null, 'T');
INSERT INTO `exam_student` VALUES ('53', '2', '342', null, 'T');
INSERT INTO `exam_student` VALUES ('54', '2', '343', null, 'T');
INSERT INTO `exam_student` VALUES ('55', '2', '344', null, 'T');
INSERT INTO `exam_student` VALUES ('56', '2', '345', null, 'T');
INSERT INTO `exam_student` VALUES ('57', '2', '346', null, 'T');
INSERT INTO `exam_student` VALUES ('58', '2', '347', null, 'T');
INSERT INTO `exam_student` VALUES ('59', '2', '348', null, 'T');
INSERT INTO `exam_student` VALUES ('60', '2', '349', null, 'T');
INSERT INTO `exam_student` VALUES ('61', '2', '1', null, 'T');
INSERT INTO `exam_student` VALUES ('62', '2', '2', null, 'T');
INSERT INTO `exam_student` VALUES ('63', '2', '3', null, 'T');
INSERT INTO `exam_student` VALUES ('64', '2', '4', null, 'T');
INSERT INTO `exam_student` VALUES ('65', '2', '5', null, 'T');
INSERT INTO `exam_student` VALUES ('66', '2', '6', null, 'T');
INSERT INTO `exam_student` VALUES ('67', '2', '7', null, 'T');
INSERT INTO `exam_student` VALUES ('68', '2', '8', null, 'T');
INSERT INTO `exam_student` VALUES ('69', '2', '9', null, 'T');
INSERT INTO `exam_student` VALUES ('70', '2', '10', null, 'T');
INSERT INTO `exam_student` VALUES ('71', '2', '11', null, 'T');
INSERT INTO `exam_student` VALUES ('72', '2', '12', null, 'T');
INSERT INTO `exam_student` VALUES ('73', '2', '13', null, 'T');
INSERT INTO `exam_student` VALUES ('74', '2', '14', null, 'T');
INSERT INTO `exam_student` VALUES ('75', '2', '15', null, 'T');
INSERT INTO `exam_student` VALUES ('76', '2', '16', null, 'T');
INSERT INTO `exam_student` VALUES ('77', '2', '17', null, 'T');
INSERT INTO `exam_student` VALUES ('78', '2', '18', null, 'T');
INSERT INTO `exam_student` VALUES ('79', '2', '19', null, 'T');
INSERT INTO `exam_student` VALUES ('80', '2', '20', null, 'T');
INSERT INTO `exam_student` VALUES ('81', '2', '21', null, 'T');
INSERT INTO `exam_student` VALUES ('82', '2', '22', null, 'T');
INSERT INTO `exam_student` VALUES ('83', '2', '23', null, 'T');
INSERT INTO `exam_student` VALUES ('84', '2', '24', null, 'T');
INSERT INTO `exam_student` VALUES ('85', '2', '25', null, 'T');
INSERT INTO `exam_student` VALUES ('86', '2', '26', null, 'T');
INSERT INTO `exam_student` VALUES ('87', '2', '27', null, 'T');
INSERT INTO `exam_student` VALUES ('88', '2', '28', null, 'T');
INSERT INTO `exam_student` VALUES ('89', '2', '29', null, 'T');
INSERT INTO `exam_student` VALUES ('90', '3', '350', null, 'T');
INSERT INTO `exam_student` VALUES ('91', '3', '351', null, 'T');
INSERT INTO `exam_student` VALUES ('92', '3', '352', null, 'T');
INSERT INTO `exam_student` VALUES ('93', '3', '353', null, 'T');
INSERT INTO `exam_student` VALUES ('94', '3', '354', null, 'T');
INSERT INTO `exam_student` VALUES ('95', '3', '355', null, 'T');
INSERT INTO `exam_student` VALUES ('96', '3', '356', null, 'T');
INSERT INTO `exam_student` VALUES ('97', '3', '357', null, 'T');
INSERT INTO `exam_student` VALUES ('98', '3', '358', null, 'T');
INSERT INTO `exam_student` VALUES ('99', '3', '359', null, 'T');
INSERT INTO `exam_student` VALUES ('100', '3', '360', null, 'T');
INSERT INTO `exam_student` VALUES ('101', '3', '361', null, 'T');
INSERT INTO `exam_student` VALUES ('102', '3', '362', null, 'T');
INSERT INTO `exam_student` VALUES ('103', '3', '363', null, 'T');
INSERT INTO `exam_student` VALUES ('104', '3', '364', null, 'T');
INSERT INTO `exam_student` VALUES ('105', '3', '365', null, 'T');
INSERT INTO `exam_student` VALUES ('106', '3', '366', null, 'T');
INSERT INTO `exam_student` VALUES ('107', '3', '367', null, 'T');
INSERT INTO `exam_student` VALUES ('108', '3', '368', null, 'T');
INSERT INTO `exam_student` VALUES ('109', '3', '369', null, 'T');
INSERT INTO `exam_student` VALUES ('110', '3', '370', null, 'T');
INSERT INTO `exam_student` VALUES ('111', '3', '371', null, 'T');
INSERT INTO `exam_student` VALUES ('112', '3', '372', null, 'T');
INSERT INTO `exam_student` VALUES ('113', '3', '373', null, 'T');
INSERT INTO `exam_student` VALUES ('114', '3', '374', null, 'T');
INSERT INTO `exam_student` VALUES ('115', '3', '375', null, 'T');
INSERT INTO `exam_student` VALUES ('116', '3', '376', null, 'T');
INSERT INTO `exam_student` VALUES ('117', '3', '377', null, 'T');
INSERT INTO `exam_student` VALUES ('118', '3', '378', null, 'T');
INSERT INTO `exam_student` VALUES ('119', '3', '379', null, 'T');
INSERT INTO `exam_student` VALUES ('120', '3', '380', null, 'T');
INSERT INTO `exam_student` VALUES ('121', '3', '321', null, 'T');
INSERT INTO `exam_student` VALUES ('122', '3', '322', null, 'T');
INSERT INTO `exam_student` VALUES ('123', '3', '323', null, 'T');
INSERT INTO `exam_student` VALUES ('124', '3', '324', null, 'T');
INSERT INTO `exam_student` VALUES ('125', '3', '325', null, 'T');
INSERT INTO `exam_student` VALUES ('126', '3', '326', null, 'T');
INSERT INTO `exam_student` VALUES ('127', '3', '327', null, 'T');
INSERT INTO `exam_student` VALUES ('128', '3', '328', null, 'T');
INSERT INTO `exam_student` VALUES ('129', '3', '329', null, 'T');
INSERT INTO `exam_student` VALUES ('130', '3', '330', null, 'T');
INSERT INTO `exam_student` VALUES ('131', '3', '331', null, 'T');
INSERT INTO `exam_student` VALUES ('132', '3', '332', null, 'T');
INSERT INTO `exam_student` VALUES ('133', '3', '333', null, 'T');
INSERT INTO `exam_student` VALUES ('134', '3', '334', null, 'T');
INSERT INTO `exam_student` VALUES ('135', '3', '335', null, 'T');
INSERT INTO `exam_student` VALUES ('136', '3', '336', null, 'T');
INSERT INTO `exam_student` VALUES ('137', '3', '337', null, 'T');
INSERT INTO `exam_student` VALUES ('138', '3', '338', null, 'T');
INSERT INTO `exam_student` VALUES ('139', '3', '339', null, 'T');
INSERT INTO `exam_student` VALUES ('140', '3', '340', null, 'T');
INSERT INTO `exam_student` VALUES ('141', '3', '341', null, 'T');
INSERT INTO `exam_student` VALUES ('142', '3', '342', null, 'T');
INSERT INTO `exam_student` VALUES ('143', '3', '343', null, 'T');
INSERT INTO `exam_student` VALUES ('144', '3', '344', null, 'T');
INSERT INTO `exam_student` VALUES ('145', '3', '345', null, 'T');
INSERT INTO `exam_student` VALUES ('146', '3', '346', null, 'T');
INSERT INTO `exam_student` VALUES ('147', '3', '347', null, 'T');
INSERT INTO `exam_student` VALUES ('148', '3', '348', null, 'T');
INSERT INTO `exam_student` VALUES ('149', '3', '349', null, 'T');
INSERT INTO `exam_student` VALUES ('150', '3', '1', null, 'T');
INSERT INTO `exam_student` VALUES ('151', '3', '2', null, 'T');
INSERT INTO `exam_student` VALUES ('152', '3', '3', null, 'T');
INSERT INTO `exam_student` VALUES ('153', '3', '4', null, 'T');
INSERT INTO `exam_student` VALUES ('154', '3', '5', null, 'T');
INSERT INTO `exam_student` VALUES ('155', '3', '6', null, 'T');
INSERT INTO `exam_student` VALUES ('156', '3', '7', null, 'T');
INSERT INTO `exam_student` VALUES ('157', '3', '8', null, 'T');
INSERT INTO `exam_student` VALUES ('158', '3', '9', null, 'T');
INSERT INTO `exam_student` VALUES ('159', '3', '10', null, 'T');
INSERT INTO `exam_student` VALUES ('160', '3', '11', null, 'T');
INSERT INTO `exam_student` VALUES ('161', '3', '12', null, 'T');
INSERT INTO `exam_student` VALUES ('162', '3', '13', null, 'T');
INSERT INTO `exam_student` VALUES ('163', '3', '14', null, 'T');
INSERT INTO `exam_student` VALUES ('164', '3', '15', null, 'T');
INSERT INTO `exam_student` VALUES ('165', '3', '16', null, 'T');
INSERT INTO `exam_student` VALUES ('166', '3', '17', null, 'T');
INSERT INTO `exam_student` VALUES ('167', '3', '18', null, 'T');
INSERT INTO `exam_student` VALUES ('168', '3', '19', null, 'T');
INSERT INTO `exam_student` VALUES ('169', '3', '20', null, 'T');
INSERT INTO `exam_student` VALUES ('170', '3', '21', null, 'T');
INSERT INTO `exam_student` VALUES ('171', '3', '22', null, 'T');
INSERT INTO `exam_student` VALUES ('172', '3', '23', null, 'T');
INSERT INTO `exam_student` VALUES ('173', '3', '24', null, 'T');
INSERT INTO `exam_student` VALUES ('174', '3', '25', null, 'T');
INSERT INTO `exam_student` VALUES ('175', '3', '26', null, 'T');
INSERT INTO `exam_student` VALUES ('176', '3', '27', null, 'T');
INSERT INTO `exam_student` VALUES ('177', '3', '28', null, 'T');
INSERT INTO `exam_student` VALUES ('178', '3', '29', null, 'T');
INSERT INTO `exam_student` VALUES ('179', '4', '350', null, 'T');
INSERT INTO `exam_student` VALUES ('180', '4', '351', null, 'T');
INSERT INTO `exam_student` VALUES ('181', '4', '352', null, 'T');
INSERT INTO `exam_student` VALUES ('182', '4', '353', null, 'T');
INSERT INTO `exam_student` VALUES ('183', '4', '354', null, 'T');
INSERT INTO `exam_student` VALUES ('184', '4', '355', null, 'T');
INSERT INTO `exam_student` VALUES ('185', '4', '356', null, 'T');
INSERT INTO `exam_student` VALUES ('186', '4', '357', null, 'T');
INSERT INTO `exam_student` VALUES ('187', '4', '358', null, 'T');
INSERT INTO `exam_student` VALUES ('188', '4', '359', null, 'T');
INSERT INTO `exam_student` VALUES ('189', '4', '360', null, 'T');
INSERT INTO `exam_student` VALUES ('190', '4', '361', null, 'T');
INSERT INTO `exam_student` VALUES ('191', '4', '362', null, 'T');
INSERT INTO `exam_student` VALUES ('192', '4', '363', null, 'T');
INSERT INTO `exam_student` VALUES ('193', '4', '364', null, 'T');
INSERT INTO `exam_student` VALUES ('194', '4', '365', null, 'T');
INSERT INTO `exam_student` VALUES ('195', '4', '366', null, 'T');
INSERT INTO `exam_student` VALUES ('196', '4', '367', null, 'T');
INSERT INTO `exam_student` VALUES ('197', '4', '368', null, 'T');
INSERT INTO `exam_student` VALUES ('198', '4', '369', null, 'T');
INSERT INTO `exam_student` VALUES ('199', '4', '370', null, 'T');
INSERT INTO `exam_student` VALUES ('200', '4', '371', null, 'T');
INSERT INTO `exam_student` VALUES ('201', '4', '372', null, 'T');
INSERT INTO `exam_student` VALUES ('202', '4', '373', null, 'T');
INSERT INTO `exam_student` VALUES ('203', '4', '374', null, 'T');
INSERT INTO `exam_student` VALUES ('204', '4', '375', null, 'T');
INSERT INTO `exam_student` VALUES ('205', '4', '376', null, 'T');
INSERT INTO `exam_student` VALUES ('206', '4', '377', null, 'T');
INSERT INTO `exam_student` VALUES ('207', '4', '378', null, 'T');
INSERT INTO `exam_student` VALUES ('208', '4', '379', null, 'T');
INSERT INTO `exam_student` VALUES ('209', '4', '380', null, 'T');
INSERT INTO `exam_student` VALUES ('210', '4', '321', null, 'T');
INSERT INTO `exam_student` VALUES ('211', '4', '322', null, 'T');
INSERT INTO `exam_student` VALUES ('212', '4', '323', null, 'T');
INSERT INTO `exam_student` VALUES ('213', '4', '324', null, 'T');
INSERT INTO `exam_student` VALUES ('214', '4', '325', null, 'T');
INSERT INTO `exam_student` VALUES ('215', '4', '326', null, 'T');
INSERT INTO `exam_student` VALUES ('216', '4', '327', null, 'T');
INSERT INTO `exam_student` VALUES ('217', '4', '328', null, 'T');
INSERT INTO `exam_student` VALUES ('218', '4', '329', null, 'T');
INSERT INTO `exam_student` VALUES ('219', '4', '330', null, 'T');
INSERT INTO `exam_student` VALUES ('220', '4', '331', null, 'T');
INSERT INTO `exam_student` VALUES ('221', '4', '332', null, 'T');
INSERT INTO `exam_student` VALUES ('222', '4', '333', null, 'T');
INSERT INTO `exam_student` VALUES ('223', '4', '334', null, 'T');
INSERT INTO `exam_student` VALUES ('224', '4', '335', null, 'T');
INSERT INTO `exam_student` VALUES ('225', '4', '336', null, 'T');
INSERT INTO `exam_student` VALUES ('226', '4', '337', null, 'T');
INSERT INTO `exam_student` VALUES ('227', '4', '338', null, 'T');
INSERT INTO `exam_student` VALUES ('228', '4', '339', null, 'T');
INSERT INTO `exam_student` VALUES ('229', '4', '340', null, 'T');
INSERT INTO `exam_student` VALUES ('230', '4', '341', null, 'T');
INSERT INTO `exam_student` VALUES ('231', '4', '342', null, 'T');
INSERT INTO `exam_student` VALUES ('232', '4', '343', null, 'T');
INSERT INTO `exam_student` VALUES ('233', '4', '344', null, 'T');
INSERT INTO `exam_student` VALUES ('234', '4', '345', null, 'T');
INSERT INTO `exam_student` VALUES ('235', '4', '346', null, 'T');
INSERT INTO `exam_student` VALUES ('236', '4', '347', null, 'T');
INSERT INTO `exam_student` VALUES ('237', '4', '348', null, 'T');
INSERT INTO `exam_student` VALUES ('238', '4', '349', null, 'T');
INSERT INTO `exam_student` VALUES ('239', '4', '1', null, 'T');
INSERT INTO `exam_student` VALUES ('240', '4', '2', null, 'T');
INSERT INTO `exam_student` VALUES ('241', '4', '3', null, 'T');
INSERT INTO `exam_student` VALUES ('242', '4', '4', null, 'T');
INSERT INTO `exam_student` VALUES ('243', '4', '5', null, 'T');
INSERT INTO `exam_student` VALUES ('244', '4', '6', null, 'T');
INSERT INTO `exam_student` VALUES ('245', '4', '7', null, 'T');
INSERT INTO `exam_student` VALUES ('246', '4', '8', null, 'T');
INSERT INTO `exam_student` VALUES ('247', '4', '9', null, 'T');
INSERT INTO `exam_student` VALUES ('248', '4', '10', null, 'T');
INSERT INTO `exam_student` VALUES ('249', '4', '11', null, 'T');
INSERT INTO `exam_student` VALUES ('250', '4', '12', null, 'T');
INSERT INTO `exam_student` VALUES ('251', '4', '13', null, 'T');
INSERT INTO `exam_student` VALUES ('252', '4', '14', null, 'T');
INSERT INTO `exam_student` VALUES ('253', '4', '15', null, 'T');
INSERT INTO `exam_student` VALUES ('254', '4', '16', null, 'T');
INSERT INTO `exam_student` VALUES ('255', '4', '17', null, 'T');
INSERT INTO `exam_student` VALUES ('256', '4', '18', null, 'T');
INSERT INTO `exam_student` VALUES ('257', '4', '19', null, 'T');
INSERT INTO `exam_student` VALUES ('258', '4', '20', null, 'T');
INSERT INTO `exam_student` VALUES ('259', '4', '21', null, 'T');
INSERT INTO `exam_student` VALUES ('260', '4', '22', null, 'T');
INSERT INTO `exam_student` VALUES ('261', '4', '23', null, 'T');
INSERT INTO `exam_student` VALUES ('262', '4', '24', null, 'T');
INSERT INTO `exam_student` VALUES ('263', '4', '25', null, 'T');
INSERT INTO `exam_student` VALUES ('264', '4', '26', null, 'T');
INSERT INTO `exam_student` VALUES ('265', '4', '27', null, 'T');
INSERT INTO `exam_student` VALUES ('266', '4', '28', null, 'T');
INSERT INTO `exam_student` VALUES ('267', '4', '29', null, 'T');
INSERT INTO `exam_student` VALUES ('268', '5', '321', null, 'T');
INSERT INTO `exam_student` VALUES ('269', '5', '322', null, 'T');
INSERT INTO `exam_student` VALUES ('270', '5', '323', null, 'T');
INSERT INTO `exam_student` VALUES ('271', '5', '324', null, 'T');
INSERT INTO `exam_student` VALUES ('272', '5', '325', null, 'T');
INSERT INTO `exam_student` VALUES ('273', '5', '326', null, 'T');
INSERT INTO `exam_student` VALUES ('274', '5', '327', null, 'T');
INSERT INTO `exam_student` VALUES ('275', '5', '328', null, 'T');
INSERT INTO `exam_student` VALUES ('276', '5', '329', null, 'T');
INSERT INTO `exam_student` VALUES ('277', '5', '330', null, 'T');
INSERT INTO `exam_student` VALUES ('278', '5', '331', null, 'T');
INSERT INTO `exam_student` VALUES ('279', '5', '332', null, 'T');
INSERT INTO `exam_student` VALUES ('280', '5', '333', null, 'T');
INSERT INTO `exam_student` VALUES ('281', '5', '334', null, 'T');
INSERT INTO `exam_student` VALUES ('282', '5', '335', null, 'T');
INSERT INTO `exam_student` VALUES ('283', '5', '336', null, 'T');
INSERT INTO `exam_student` VALUES ('284', '5', '337', null, 'T');
INSERT INTO `exam_student` VALUES ('285', '5', '338', null, 'T');
INSERT INTO `exam_student` VALUES ('286', '5', '339', null, 'T');
INSERT INTO `exam_student` VALUES ('287', '5', '340', null, 'T');
INSERT INTO `exam_student` VALUES ('288', '5', '341', null, 'T');
INSERT INTO `exam_student` VALUES ('289', '5', '342', null, 'T');
INSERT INTO `exam_student` VALUES ('290', '5', '343', null, 'T');
INSERT INTO `exam_student` VALUES ('291', '5', '344', null, 'T');
INSERT INTO `exam_student` VALUES ('292', '5', '345', null, 'T');
INSERT INTO `exam_student` VALUES ('293', '5', '346', null, 'T');
INSERT INTO `exam_student` VALUES ('294', '5', '347', null, 'T');
INSERT INTO `exam_student` VALUES ('295', '5', '348', null, 'T');
INSERT INTO `exam_student` VALUES ('296', '5', '349', null, 'T');
INSERT INTO `exam_student` VALUES ('297', '5', '1', null, 'T');
INSERT INTO `exam_student` VALUES ('298', '5', '2', null, 'T');
INSERT INTO `exam_student` VALUES ('299', '5', '3', null, 'T');
INSERT INTO `exam_student` VALUES ('300', '5', '4', null, 'T');
INSERT INTO `exam_student` VALUES ('301', '5', '5', null, 'T');
INSERT INTO `exam_student` VALUES ('302', '5', '6', null, 'T');
INSERT INTO `exam_student` VALUES ('303', '5', '7', null, 'T');
INSERT INTO `exam_student` VALUES ('304', '5', '8', null, 'T');
INSERT INTO `exam_student` VALUES ('305', '5', '9', null, 'T');
INSERT INTO `exam_student` VALUES ('306', '5', '10', null, 'T');
INSERT INTO `exam_student` VALUES ('307', '5', '11', null, 'T');
INSERT INTO `exam_student` VALUES ('308', '5', '12', null, 'T');
INSERT INTO `exam_student` VALUES ('309', '5', '13', null, 'T');
INSERT INTO `exam_student` VALUES ('310', '5', '14', null, 'T');
INSERT INTO `exam_student` VALUES ('311', '5', '15', null, 'T');
INSERT INTO `exam_student` VALUES ('312', '5', '16', null, 'T');
INSERT INTO `exam_student` VALUES ('313', '5', '17', null, 'T');
INSERT INTO `exam_student` VALUES ('314', '5', '18', null, 'T');
INSERT INTO `exam_student` VALUES ('315', '5', '19', null, 'T');
INSERT INTO `exam_student` VALUES ('316', '5', '20', null, 'T');
INSERT INTO `exam_student` VALUES ('317', '5', '21', null, 'T');
INSERT INTO `exam_student` VALUES ('318', '5', '22', null, 'T');
INSERT INTO `exam_student` VALUES ('319', '5', '23', null, 'T');
INSERT INTO `exam_student` VALUES ('320', '5', '24', null, 'T');
INSERT INTO `exam_student` VALUES ('321', '5', '25', null, 'T');
INSERT INTO `exam_student` VALUES ('322', '5', '26', null, 'T');
INSERT INTO `exam_student` VALUES ('323', '5', '27', null, 'T');
INSERT INTO `exam_student` VALUES ('324', '5', '28', null, 'T');
INSERT INTO `exam_student` VALUES ('325', '5', '29', null, 'T');
INSERT INTO `exam_student` VALUES ('326', '6', '321', null, 'T');
INSERT INTO `exam_student` VALUES ('327', '6', '322', null, 'T');
INSERT INTO `exam_student` VALUES ('328', '6', '323', null, 'T');
INSERT INTO `exam_student` VALUES ('329', '6', '324', null, 'T');
INSERT INTO `exam_student` VALUES ('330', '6', '325', null, 'T');
INSERT INTO `exam_student` VALUES ('331', '6', '326', null, 'T');
INSERT INTO `exam_student` VALUES ('332', '6', '327', null, 'T');
INSERT INTO `exam_student` VALUES ('333', '6', '328', null, 'T');
INSERT INTO `exam_student` VALUES ('334', '6', '329', null, 'T');
INSERT INTO `exam_student` VALUES ('335', '6', '330', null, 'T');
INSERT INTO `exam_student` VALUES ('336', '6', '331', null, 'T');
INSERT INTO `exam_student` VALUES ('337', '6', '332', null, 'T');
INSERT INTO `exam_student` VALUES ('338', '6', '333', null, 'T');
INSERT INTO `exam_student` VALUES ('339', '6', '334', null, 'T');
INSERT INTO `exam_student` VALUES ('340', '6', '335', null, 'T');
INSERT INTO `exam_student` VALUES ('341', '6', '336', null, 'T');
INSERT INTO `exam_student` VALUES ('342', '6', '337', null, 'T');
INSERT INTO `exam_student` VALUES ('343', '6', '338', null, 'T');
INSERT INTO `exam_student` VALUES ('344', '6', '339', null, 'T');
INSERT INTO `exam_student` VALUES ('345', '6', '340', null, 'T');
INSERT INTO `exam_student` VALUES ('346', '6', '341', null, 'T');
INSERT INTO `exam_student` VALUES ('347', '6', '342', null, 'T');
INSERT INTO `exam_student` VALUES ('348', '6', '343', null, 'T');
INSERT INTO `exam_student` VALUES ('349', '6', '344', null, 'T');
INSERT INTO `exam_student` VALUES ('350', '6', '345', null, 'T');
INSERT INTO `exam_student` VALUES ('351', '6', '346', null, 'T');
INSERT INTO `exam_student` VALUES ('352', '6', '347', null, 'T');
INSERT INTO `exam_student` VALUES ('353', '6', '348', null, 'T');
INSERT INTO `exam_student` VALUES ('354', '6', '349', null, 'T');
INSERT INTO `exam_student` VALUES ('355', '6', '1', null, 'T');
INSERT INTO `exam_student` VALUES ('356', '6', '2', null, 'T');
INSERT INTO `exam_student` VALUES ('357', '6', '3', null, 'T');
INSERT INTO `exam_student` VALUES ('358', '6', '4', null, 'T');
INSERT INTO `exam_student` VALUES ('359', '6', '5', null, 'T');
INSERT INTO `exam_student` VALUES ('360', '6', '6', null, 'T');
INSERT INTO `exam_student` VALUES ('361', '6', '7', null, 'T');
INSERT INTO `exam_student` VALUES ('362', '6', '8', null, 'T');
INSERT INTO `exam_student` VALUES ('363', '6', '9', null, 'T');
INSERT INTO `exam_student` VALUES ('364', '6', '10', null, 'T');
INSERT INTO `exam_student` VALUES ('365', '6', '11', null, 'T');
INSERT INTO `exam_student` VALUES ('366', '6', '12', null, 'T');
INSERT INTO `exam_student` VALUES ('367', '6', '13', null, 'T');
INSERT INTO `exam_student` VALUES ('368', '6', '14', null, 'T');
INSERT INTO `exam_student` VALUES ('369', '6', '15', null, 'T');
INSERT INTO `exam_student` VALUES ('370', '6', '16', null, 'T');
INSERT INTO `exam_student` VALUES ('371', '6', '17', null, 'T');
INSERT INTO `exam_student` VALUES ('372', '6', '18', null, 'T');
INSERT INTO `exam_student` VALUES ('373', '6', '19', null, 'T');
INSERT INTO `exam_student` VALUES ('374', '6', '20', null, 'T');
INSERT INTO `exam_student` VALUES ('375', '6', '21', null, 'T');
INSERT INTO `exam_student` VALUES ('376', '6', '22', null, 'T');
INSERT INTO `exam_student` VALUES ('377', '6', '23', null, 'T');
INSERT INTO `exam_student` VALUES ('378', '6', '24', null, 'T');
INSERT INTO `exam_student` VALUES ('379', '6', '25', null, 'T');
INSERT INTO `exam_student` VALUES ('380', '6', '26', null, 'T');
INSERT INTO `exam_student` VALUES ('381', '6', '27', null, 'T');
INSERT INTO `exam_student` VALUES ('382', '6', '28', null, 'T');
INSERT INTO `exam_student` VALUES ('383', '6', '29', null, 'T');
INSERT INTO `exam_student` VALUES ('384', '7', '321', null, 'T');
INSERT INTO `exam_student` VALUES ('385', '7', '322', null, 'T');
INSERT INTO `exam_student` VALUES ('386', '7', '323', null, 'T');
INSERT INTO `exam_student` VALUES ('387', '7', '324', null, 'T');
INSERT INTO `exam_student` VALUES ('388', '7', '325', null, 'T');
INSERT INTO `exam_student` VALUES ('389', '7', '326', null, 'F');
INSERT INTO `exam_student` VALUES ('390', '7', '327', null, 'F');
INSERT INTO `exam_student` VALUES ('391', '7', '328', null, 'T');
INSERT INTO `exam_student` VALUES ('392', '7', '329', null, 'T');
INSERT INTO `exam_student` VALUES ('393', '7', '330', null, 'T');
INSERT INTO `exam_student` VALUES ('394', '7', '331', null, 'T');
INSERT INTO `exam_student` VALUES ('395', '7', '332', null, 'T');
INSERT INTO `exam_student` VALUES ('396', '7', '333', null, 'T');
INSERT INTO `exam_student` VALUES ('397', '7', '334', null, 'T');
INSERT INTO `exam_student` VALUES ('398', '7', '335', null, 'T');
INSERT INTO `exam_student` VALUES ('399', '7', '336', null, 'T');
INSERT INTO `exam_student` VALUES ('400', '7', '337', null, 'T');
INSERT INTO `exam_student` VALUES ('401', '7', '338', null, 'T');
INSERT INTO `exam_student` VALUES ('402', '7', '339', null, 'T');
INSERT INTO `exam_student` VALUES ('403', '7', '340', null, 'T');
INSERT INTO `exam_student` VALUES ('404', '7', '341', null, 'T');
INSERT INTO `exam_student` VALUES ('405', '7', '342', null, 'T');
INSERT INTO `exam_student` VALUES ('406', '7', '343', null, 'T');
INSERT INTO `exam_student` VALUES ('407', '7', '344', null, 'T');
INSERT INTO `exam_student` VALUES ('408', '7', '345', null, 'T');
INSERT INTO `exam_student` VALUES ('409', '7', '346', null, 'T');
INSERT INTO `exam_student` VALUES ('410', '7', '347', null, 'T');
INSERT INTO `exam_student` VALUES ('411', '7', '348', null, 'T');
INSERT INTO `exam_student` VALUES ('412', '7', '349', null, 'T');
INSERT INTO `exam_student` VALUES ('413', '7', '1', null, 'F');
INSERT INTO `exam_student` VALUES ('414', '7', '2', null, 'T');
INSERT INTO `exam_student` VALUES ('415', '7', '3', null, 'T');
INSERT INTO `exam_student` VALUES ('416', '7', '4', null, 'T');
INSERT INTO `exam_student` VALUES ('417', '7', '5', null, 'T');
INSERT INTO `exam_student` VALUES ('418', '7', '6', null, 'T');
INSERT INTO `exam_student` VALUES ('419', '7', '7', null, 'T');
INSERT INTO `exam_student` VALUES ('420', '7', '8', null, 'T');
INSERT INTO `exam_student` VALUES ('421', '7', '9', null, 'T');
INSERT INTO `exam_student` VALUES ('422', '7', '10', null, 'T');
INSERT INTO `exam_student` VALUES ('423', '7', '11', null, 'T');
INSERT INTO `exam_student` VALUES ('424', '7', '12', null, 'T');
INSERT INTO `exam_student` VALUES ('425', '7', '13', null, 'T');
INSERT INTO `exam_student` VALUES ('426', '7', '14', null, 'T');
INSERT INTO `exam_student` VALUES ('427', '7', '15', null, 'T');
INSERT INTO `exam_student` VALUES ('428', '7', '16', null, 'T');
INSERT INTO `exam_student` VALUES ('429', '7', '17', null, 'T');
INSERT INTO `exam_student` VALUES ('430', '7', '18', null, 'T');
INSERT INTO `exam_student` VALUES ('431', '7', '19', null, 'T');
INSERT INTO `exam_student` VALUES ('432', '7', '20', null, 'T');
INSERT INTO `exam_student` VALUES ('433', '7', '21', null, 'T');
INSERT INTO `exam_student` VALUES ('434', '7', '22', null, 'T');
INSERT INTO `exam_student` VALUES ('435', '7', '23', null, 'T');
INSERT INTO `exam_student` VALUES ('436', '7', '24', null, 'T');
INSERT INTO `exam_student` VALUES ('437', '7', '25', null, 'T');
INSERT INTO `exam_student` VALUES ('438', '7', '26', null, 'T');
INSERT INTO `exam_student` VALUES ('439', '7', '27', null, 'T');
INSERT INTO `exam_student` VALUES ('440', '7', '28', null, 'T');
INSERT INTO `exam_student` VALUES ('441', '7', '29', null, 'T');
INSERT INTO `exam_student` VALUES ('442', '8', '350', null, 'T');
INSERT INTO `exam_student` VALUES ('443', '8', '351', null, 'T');
INSERT INTO `exam_student` VALUES ('444', '8', '352', null, 'T');
INSERT INTO `exam_student` VALUES ('445', '8', '353', null, 'T');
INSERT INTO `exam_student` VALUES ('446', '8', '354', null, 'T');
INSERT INTO `exam_student` VALUES ('447', '8', '355', null, 'T');
INSERT INTO `exam_student` VALUES ('448', '8', '356', null, 'T');
INSERT INTO `exam_student` VALUES ('449', '8', '357', null, 'T');
INSERT INTO `exam_student` VALUES ('450', '8', '358', null, 'T');
INSERT INTO `exam_student` VALUES ('451', '8', '359', null, 'T');
INSERT INTO `exam_student` VALUES ('452', '8', '360', null, 'T');
INSERT INTO `exam_student` VALUES ('453', '8', '361', null, 'T');
INSERT INTO `exam_student` VALUES ('454', '8', '362', null, 'T');
INSERT INTO `exam_student` VALUES ('455', '8', '363', null, 'T');
INSERT INTO `exam_student` VALUES ('456', '8', '364', null, 'T');
INSERT INTO `exam_student` VALUES ('457', '8', '365', null, 'T');
INSERT INTO `exam_student` VALUES ('458', '8', '366', null, 'T');
INSERT INTO `exam_student` VALUES ('459', '8', '367', null, 'T');
INSERT INTO `exam_student` VALUES ('460', '8', '368', null, 'T');
INSERT INTO `exam_student` VALUES ('461', '8', '369', null, 'T');
INSERT INTO `exam_student` VALUES ('462', '8', '370', null, 'T');
INSERT INTO `exam_student` VALUES ('463', '8', '371', null, 'T');
INSERT INTO `exam_student` VALUES ('464', '8', '372', null, 'T');
INSERT INTO `exam_student` VALUES ('465', '8', '373', null, 'T');
INSERT INTO `exam_student` VALUES ('466', '8', '374', null, 'T');
INSERT INTO `exam_student` VALUES ('467', '8', '375', null, 'T');
INSERT INTO `exam_student` VALUES ('468', '8', '376', null, 'T');
INSERT INTO `exam_student` VALUES ('469', '8', '377', null, 'T');
INSERT INTO `exam_student` VALUES ('470', '8', '378', null, 'T');
INSERT INTO `exam_student` VALUES ('471', '8', '379', null, 'T');
INSERT INTO `exam_student` VALUES ('472', '8', '380', null, 'T');
INSERT INTO `exam_student` VALUES ('473', '8', '321', null, 'T');
INSERT INTO `exam_student` VALUES ('474', '8', '322', null, 'T');
INSERT INTO `exam_student` VALUES ('475', '8', '323', null, 'T');
INSERT INTO `exam_student` VALUES ('476', '8', '324', null, 'T');
INSERT INTO `exam_student` VALUES ('477', '8', '325', null, 'T');
INSERT INTO `exam_student` VALUES ('478', '8', '326', null, 'T');
INSERT INTO `exam_student` VALUES ('479', '8', '327', null, 'T');
INSERT INTO `exam_student` VALUES ('480', '8', '328', null, 'T');
INSERT INTO `exam_student` VALUES ('481', '8', '329', null, 'T');
INSERT INTO `exam_student` VALUES ('482', '8', '330', null, 'T');
INSERT INTO `exam_student` VALUES ('483', '8', '331', null, 'T');
INSERT INTO `exam_student` VALUES ('484', '8', '332', null, 'T');
INSERT INTO `exam_student` VALUES ('485', '8', '333', null, 'T');
INSERT INTO `exam_student` VALUES ('486', '8', '334', null, 'T');
INSERT INTO `exam_student` VALUES ('487', '8', '335', null, 'T');
INSERT INTO `exam_student` VALUES ('488', '8', '336', null, 'T');
INSERT INTO `exam_student` VALUES ('489', '8', '337', null, 'T');
INSERT INTO `exam_student` VALUES ('490', '8', '338', null, 'T');
INSERT INTO `exam_student` VALUES ('491', '8', '339', null, 'T');
INSERT INTO `exam_student` VALUES ('492', '8', '340', null, 'T');
INSERT INTO `exam_student` VALUES ('493', '8', '341', null, 'T');
INSERT INTO `exam_student` VALUES ('494', '8', '342', null, 'T');
INSERT INTO `exam_student` VALUES ('495', '8', '343', null, 'T');
INSERT INTO `exam_student` VALUES ('496', '8', '344', null, 'T');
INSERT INTO `exam_student` VALUES ('497', '8', '345', null, 'T');
INSERT INTO `exam_student` VALUES ('498', '8', '346', null, 'T');
INSERT INTO `exam_student` VALUES ('499', '8', '347', null, 'T');
INSERT INTO `exam_student` VALUES ('500', '8', '348', null, 'T');
INSERT INTO `exam_student` VALUES ('501', '8', '349', null, 'T');
INSERT INTO `exam_student` VALUES ('502', '9', '1', null, 'T');
INSERT INTO `exam_student` VALUES ('503', '9', '2', null, 'T');
INSERT INTO `exam_student` VALUES ('504', '9', '3', null, 'T');
INSERT INTO `exam_student` VALUES ('505', '9', '4', null, 'T');
INSERT INTO `exam_student` VALUES ('506', '9', '5', null, 'T');
INSERT INTO `exam_student` VALUES ('507', '9', '6', null, 'T');
INSERT INTO `exam_student` VALUES ('508', '9', '7', null, 'T');
INSERT INTO `exam_student` VALUES ('509', '9', '8', null, 'T');
INSERT INTO `exam_student` VALUES ('510', '9', '9', null, 'T');
INSERT INTO `exam_student` VALUES ('511', '9', '10', null, 'T');
INSERT INTO `exam_student` VALUES ('512', '9', '11', null, 'T');
INSERT INTO `exam_student` VALUES ('513', '9', '12', null, 'T');
INSERT INTO `exam_student` VALUES ('514', '9', '13', null, 'T');
INSERT INTO `exam_student` VALUES ('515', '9', '14', null, 'T');
INSERT INTO `exam_student` VALUES ('516', '9', '15', null, 'T');
INSERT INTO `exam_student` VALUES ('517', '9', '16', null, 'T');
INSERT INTO `exam_student` VALUES ('518', '9', '17', null, 'T');
INSERT INTO `exam_student` VALUES ('519', '9', '18', null, 'T');
INSERT INTO `exam_student` VALUES ('520', '9', '19', null, 'T');
INSERT INTO `exam_student` VALUES ('521', '9', '20', null, 'T');
INSERT INTO `exam_student` VALUES ('522', '9', '21', null, 'T');
INSERT INTO `exam_student` VALUES ('523', '9', '22', null, 'T');
INSERT INTO `exam_student` VALUES ('524', '9', '23', null, 'T');
INSERT INTO `exam_student` VALUES ('525', '9', '24', null, 'T');
INSERT INTO `exam_student` VALUES ('526', '9', '25', null, 'T');
INSERT INTO `exam_student` VALUES ('527', '9', '26', null, 'T');
INSERT INTO `exam_student` VALUES ('528', '9', '27', null, 'T');
INSERT INTO `exam_student` VALUES ('529', '9', '28', null, 'T');
INSERT INTO `exam_student` VALUES ('530', '9', '29', null, 'T');

-- ----------------------------
-- Table structure for exam_student_result
-- ----------------------------
DROP TABLE IF EXISTS `exam_student_result`;
CREATE TABLE `exam_student_result` (
  `ESR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ES_id` int(11) DEFAULT NULL,
  `SI_ID` int(11) DEFAULT NULL,
  `ESR_Content` varchar(50) DEFAULT NULL,
  `ESR_Score` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ESR_ID`),
  KEY `FK_Reference_10` (`ES_id`),
  KEY `FK_Reference_14` (`SI_ID`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`ES_id`) REFERENCES `exam_student` (`ES_id`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`SI_ID`) REFERENCES `subject_infor` (`SI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_student_result
-- ----------------------------
INSERT INTO `exam_student_result` VALUES ('1', '413', '4', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('2', '413', '6', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('3', '413', '8', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('4', '413', '11', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('5', '413', '14', 'A', 'T');
INSERT INTO `exam_student_result` VALUES ('6', '413', '16', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('7', '413', '21', 'D', 'F');
INSERT INTO `exam_student_result` VALUES ('8', '413', '22', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('9', '413', '24', 'A', 'T');
INSERT INTO `exam_student_result` VALUES ('10', '413', '28', 'D', 'F');
INSERT INTO `exam_student_result` VALUES ('11', '413', '32', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('12', '413', '33', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('13', '413', '35', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('14', '413', '36', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('15', '413', '41', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('16', '413', '46', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('17', '413', '48', 'B', 'T');
INSERT INTO `exam_student_result` VALUES ('18', '413', '53', 'D', 'T');
INSERT INTO `exam_student_result` VALUES ('19', '413', '54', 'B', 'T');
INSERT INTO `exam_student_result` VALUES ('20', '413', '55', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('21', '413', '58', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('22', '413', '59', 'D', 'F');
INSERT INTO `exam_student_result` VALUES ('23', '413', '64', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('24', '413', '68', 'A', 'T');
INSERT INTO `exam_student_result` VALUES ('25', '413', '70', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('26', '413', '73', 'D', 'F');
INSERT INTO `exam_student_result` VALUES ('27', '413', '76', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('28', '413', '77', 'C', 'T');
INSERT INTO `exam_student_result` VALUES ('29', '413', '78', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('30', '389', '4', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('31', '389', '6', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('32', '389', '8', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('33', '389', '11', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('34', '389', '14', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('35', '389', '16', 'C', 'T');
INSERT INTO `exam_student_result` VALUES ('36', '389', '21', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('37', '389', '22', 'B', 'T');
INSERT INTO `exam_student_result` VALUES ('38', '389', '24', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('39', '389', '28', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('40', '389', '32', 'A', 'T');
INSERT INTO `exam_student_result` VALUES ('41', '389', '33', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('42', '389', '35', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('43', '389', '36', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('44', '389', '41', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('45', '389', '46', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('46', '389', '48', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('47', '389', '53', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('48', '389', '54', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('49', '389', '55', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('50', '389', '58', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('51', '389', '59', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('52', '389', '64', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('53', '389', '68', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('54', '389', '70', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('55', '389', '73', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('56', '389', '76', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('57', '389', '77', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('58', '389', '78', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('59', '390', '4', 'D', 'F');
INSERT INTO `exam_student_result` VALUES ('60', '390', '6', 'D', 'F');
INSERT INTO `exam_student_result` VALUES ('61', '390', '8', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('62', '390', '11', 'C', 'T');
INSERT INTO `exam_student_result` VALUES ('63', '390', '14', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('64', '390', '16', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('65', '390', '21', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('66', '390', '22', 'B', 'T');
INSERT INTO `exam_student_result` VALUES ('67', '390', '24', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('68', '390', '28', 'A', 'T');
INSERT INTO `exam_student_result` VALUES ('69', '390', '32', 'A', 'T');
INSERT INTO `exam_student_result` VALUES ('70', '390', '33', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('71', '390', '35', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('72', '390', '36', 'D', 'T');
INSERT INTO `exam_student_result` VALUES ('73', '390', '41', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('74', '390', '46', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('75', '390', '48', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('76', '390', '53', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('77', '390', '54', 'A', 'F');
INSERT INTO `exam_student_result` VALUES ('78', '390', '55', 'A', 'T');
INSERT INTO `exam_student_result` VALUES ('79', '390', '58', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('80', '390', '59', 'D', 'F');
INSERT INTO `exam_student_result` VALUES ('81', '390', '64', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('82', '390', '68', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('83', '390', '70', 'B', 'T');
INSERT INTO `exam_student_result` VALUES ('84', '390', '73', 'C', 'F');
INSERT INTO `exam_student_result` VALUES ('85', '390', '76', 'B', 'F');
INSERT INTO `exam_student_result` VALUES ('86', '390', '77', 'C', 'T');
INSERT INTO `exam_student_result` VALUES ('87', '390', '78', 'B', 'F');

-- ----------------------------
-- Table structure for message_receive
-- ----------------------------
DROP TABLE IF EXISTS `message_receive`;
CREATE TABLE `message_receive` (
  `MR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UL_ID` int(11) DEFAULT NULL,
  `MS_ID` int(11) DEFAULT NULL,
  `MR_Read` varchar(20) DEFAULT NULL,
  `MR_Delete` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MR_ID`),
  KEY `FK_Reference_28` (`UL_ID`),
  KEY `FK_Reference_29` (`MS_ID`),
  CONSTRAINT `FK_Reference_28` FOREIGN KEY (`UL_ID`) REFERENCES `user_login` (`UL_ID`),
  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`MS_ID`) REFERENCES `message_sender` (`MS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_receive
-- ----------------------------

-- ----------------------------
-- Table structure for message_sender
-- ----------------------------
DROP TABLE IF EXISTS `message_sender`;
CREATE TABLE `message_sender` (
  `MS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UL_ID` int(11) DEFAULT NULL,
  `MS_Topic` varchar(20) DEFAULT NULL,
  `MS_Content` varchar(50) DEFAULT NULL,
  `MS_SendTime` varchar(20) DEFAULT NULL,
  `MS_Delete` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MS_ID`),
  KEY `FK_Reference_27` (`UL_ID`),
  CONSTRAINT `FK_Reference_27` FOREIGN KEY (`UL_ID`) REFERENCES `user_login` (`UL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_sender
-- ----------------------------

-- ----------------------------
-- Table structure for paper_info
-- ----------------------------
DROP TABLE IF EXISTS `paper_info`;
CREATE TABLE `paper_info` (
  `PI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TI_ID` int(11) DEFAULT NULL,
  `CI_ID` int(11) DEFAULT NULL,
  `PI_Name` varchar(50) DEFAULT NULL,
  `PI_Sign` varchar(20) DEFAULT NULL,
  `PI_Zip_Name` varchar(50) DEFAULT NULL,
  `PI_DateTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PI_ID`),
  KEY `FK_Reference_34` (`CI_ID`),
  KEY `FK_Reference_36` (`TI_ID`),
  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`CI_ID`) REFERENCES `course_info` (`CI_ID`),
  CONSTRAINT `FK_Reference_36` FOREIGN KEY (`TI_ID`) REFERENCES `teacher_info` (`TI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_info
-- ----------------------------
INSERT INTO `paper_info` VALUES ('1', '1', '2', 'Test1', 'T', null, null);
INSERT INTO `paper_info` VALUES ('2', '1', '1', 'Test1', 'F', null, null);
INSERT INTO `paper_info` VALUES ('3', '2', '4', '练习卷1', 'F', null, null);
INSERT INTO `paper_info` VALUES ('4', '2', '4', '练习卷2', 'F', null, null);
INSERT INTO `paper_info` VALUES ('5', '1', '1', 'Test2', 'F', null, null);
INSERT INTO `paper_info` VALUES ('6', '2', '3', '练习卷3', 'F', null, null);
INSERT INTO `paper_info` VALUES ('7', '1', '5', '练习卷1', 'T', null, null);
INSERT INTO `paper_info` VALUES ('8', '1', '6', '练习卷2', 'F', null, null);
INSERT INTO `paper_info` VALUES ('9', '1', '8', '英语Test01', 'F', null, null);

-- ----------------------------
-- Table structure for paper_subject_relation
-- ----------------------------
DROP TABLE IF EXISTS `paper_subject_relation`;
CREATE TABLE `paper_subject_relation` (
  `PSR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PI_ID` int(11) DEFAULT NULL,
  `SI_ID` int(11) DEFAULT NULL,
  `PSR_Time` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`PSR_ID`),
  KEY `FK_Reference_12` (`PI_ID`),
  KEY `FK_Reference_13` (`SI_ID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`PI_ID`) REFERENCES `paper_info` (`PI_ID`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`SI_ID`) REFERENCES `subject_infor` (`SI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_subject_relation
-- ----------------------------
INSERT INTO `paper_subject_relation` VALUES ('1', '1', '38', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('2', '1', '39', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('3', '1', '40', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('4', '1', '41', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('5', '1', '42', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('6', '1', '43', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('7', '1', '44', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('8', '1', '45', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('9', '1', '46', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('10', '1', '47', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('11', '1', '48', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('12', '1', '49', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('13', '1', '50', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('14', '1', '51', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('15', '1', '59', '2014-08-15 14:28:11.0');
INSERT INTO `paper_subject_relation` VALUES ('16', '2', '29', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('17', '2', '30', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('18', '2', '32', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('19', '2', '34', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('20', '2', '41', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('21', '2', '43', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('22', '2', '44', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('23', '2', '50', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('24', '2', '53', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('25', '2', '61', '2014-08-15 14:28:39.0');
INSERT INTO `paper_subject_relation` VALUES ('26', '3', '3', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('27', '3', '5', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('28', '3', '10', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('29', '3', '12', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('30', '3', '13', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('31', '3', '15', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('32', '3', '16', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('33', '3', '18', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('34', '3', '69', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('35', '3', '70', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('36', '3', '71', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('37', '3', '73', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('38', '3', '74', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('39', '3', '75', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('40', '3', '76', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('41', '3', '77', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('42', '3', '79', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('43', '3', '81', '2014-08-15 14:29:18.0');
INSERT INTO `paper_subject_relation` VALUES ('44', '4', '9', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('45', '4', '11', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('46', '4', '14', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('47', '4', '20', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('48', '4', '25', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('49', '4', '26', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('50', '4', '27', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('51', '4', '30', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('52', '4', '31', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('53', '4', '33', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('54', '4', '34', '2014-08-15 14:29:51.0');
INSERT INTO `paper_subject_relation` VALUES ('55', '6', '37', '2014-08-15 14:30:18.0');
INSERT INTO `paper_subject_relation` VALUES ('56', '6', '38', '2014-08-15 14:30:18.0');
INSERT INTO `paper_subject_relation` VALUES ('57', '6', '39', '2014-08-15 14:30:18.0');
INSERT INTO `paper_subject_relation` VALUES ('58', '6', '40', '2014-08-15 14:30:18.0');
INSERT INTO `paper_subject_relation` VALUES ('59', '6', '41', '2014-08-15 14:30:19.0');
INSERT INTO `paper_subject_relation` VALUES ('60', '6', '42', '2014-08-15 14:30:19.0');
INSERT INTO `paper_subject_relation` VALUES ('61', '6', '48', '2014-08-15 14:30:19.0');
INSERT INTO `paper_subject_relation` VALUES ('62', '5', '51', '2014-08-15 14:30:33.0');
INSERT INTO `paper_subject_relation` VALUES ('63', '5', '53', '2014-08-15 14:30:33.0');
INSERT INTO `paper_subject_relation` VALUES ('64', '5', '58', '2014-08-15 14:30:33.0');
INSERT INTO `paper_subject_relation` VALUES ('65', '7', '4', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('66', '7', '6', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('67', '7', '8', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('68', '7', '11', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('69', '7', '14', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('70', '7', '16', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('71', '7', '21', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('72', '7', '22', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('73', '7', '24', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('74', '7', '28', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('75', '7', '32', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('76', '7', '33', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('77', '7', '35', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('78', '7', '36', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('79', '7', '41', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('80', '7', '46', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('81', '7', '48', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('82', '7', '53', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('83', '7', '54', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('84', '7', '55', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('85', '7', '58', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('86', '7', '59', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('87', '7', '64', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('88', '7', '68', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('89', '7', '70', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('90', '7', '73', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('91', '7', '76', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('92', '7', '77', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('93', '7', '78', '2014-09-05 19:26:23.0');
INSERT INTO `paper_subject_relation` VALUES ('94', '8', '3', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('95', '8', '50', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('96', '8', '53', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('97', '8', '54', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('98', '8', '59', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('99', '8', '61', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('100', '8', '72', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('101', '8', '73', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('102', '8', '74', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('103', '8', '76', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('104', '8', '77', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('105', '8', '80', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('106', '8', '81', '2014-09-05 19:28:14.0');
INSERT INTO `paper_subject_relation` VALUES ('107', '9', '1', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('108', '9', '2', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('109', '9', '3', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('110', '9', '4', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('111', '9', '5', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('112', '9', '6', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('113', '9', '7', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('114', '9', '8', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('115', '9', '9', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('116', '9', '10', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('117', '9', '11', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('118', '9', '12', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('119', '9', '13', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('120', '9', '14', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('121', '9', '15', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('122', '9', '16', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('123', '9', '17', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('124', '9', '18', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('125', '9', '19', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('126', '9', '20', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('127', '9', '21', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('128', '9', '22', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('129', '9', '23', '2015-09-23 15:59:08.0');
INSERT INTO `paper_subject_relation` VALUES ('130', '9', '24', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('131', '9', '25', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('132', '9', '26', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('133', '9', '27', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('134', '9', '28', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('135', '9', '29', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('136', '9', '30', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('137', '9', '31', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('138', '9', '32', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('139', '9', '33', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('140', '9', '34', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('141', '9', '35', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('142', '9', '36', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('143', '9', '37', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('144', '9', '38', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('145', '9', '39', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('146', '9', '40', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('147', '9', '41', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('148', '9', '42', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('149', '9', '43', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('150', '9', '44', '2015-09-23 15:59:09.0');
INSERT INTO `paper_subject_relation` VALUES ('151', '9', '45', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('152', '9', '46', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('153', '9', '47', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('154', '9', '48', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('155', '9', '49', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('156', '9', '50', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('157', '9', '51', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('158', '9', '52', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('159', '9', '53', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('160', '9', '54', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('161', '9', '55', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('162', '9', '58', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('163', '9', '59', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('164', '9', '60', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('165', '9', '61', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('166', '9', '63', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('167', '9', '64', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('168', '9', '65', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('169', '9', '66', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('170', '9', '67', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('171', '9', '68', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('172', '9', '69', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('173', '9', '70', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('174', '9', '71', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('175', '9', '72', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('176', '9', '73', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('177', '9', '74', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('178', '9', '75', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('179', '9', '76', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('180', '9', '77', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('181', '9', '78', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('182', '9', '79', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('183', '9', '80', '2015-09-23 15:59:10.0');
INSERT INTO `paper_subject_relation` VALUES ('184', '9', '81', '2015-09-23 15:59:10.0');

-- ----------------------------
-- Table structure for profession_info
-- ----------------------------
DROP TABLE IF EXISTS `profession_info`;
CREATE TABLE `profession_info` (
  `PI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PI_Profession` varchar(20) DEFAULT NULL,
  `PI_College` varchar(20) DEFAULT NULL,
  `PI_Sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profession_info
-- ----------------------------
INSERT INTO `profession_info` VALUES ('2', '信息管理与信息系统', '信工学院', '信工学院信息管理与信息系统');
INSERT INTO `profession_info` VALUES ('3', '电子信息工程', '信息与工程学院', '信息与工程学院电子信息工程');
INSERT INTO `profession_info` VALUES ('4', '生物医学工程', '信息与工程学院', '信息与工程学院 生物医学工程');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
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
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
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
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
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
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('wmuitpScheduler', 'job__8', 'courseInfoid8', null, 'sedion.jeffli.wmuitp.service.impl.quartz.JobCourseInfoStop', '0', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000A636F75727365496E666F737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000087800);

-- ----------------------------
-- Table structure for qrtz_locks
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
INSERT INTO `qrtz_locks` VALUES ('wmuitpScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('wmuitpScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
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
-- Table structure for qrtz_scheduler_state
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
INSERT INTO `qrtz_scheduler_state` VALUES ('wmuitpScheduler', 'JeffLee-PC1442994377340', '1442995257202', '7500');
INSERT INTO `qrtz_scheduler_state` VALUES ('wmuitpScheduler', 'JeffLee-PC1442994701813', '1442995257228', '7500');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
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
INSERT INTO `qrtz_simple_triggers` VALUES ('wmuitpScheduler', 'courseInfoid8', 'courseInfoid8', '0', '0', '0');

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
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
-- Table structure for qrtz_triggers
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
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('wmuitpScheduler', 'courseInfoid8', 'courseInfoid8', 'job__8', 'courseInfoid8', null, '61401146567000', '-1', '5', 'WAITING', 'SIMPLE', '61401146567000', '0', null, '0', '');

-- ----------------------------
-- Table structure for student_course_relation
-- ----------------------------
DROP TABLE IF EXISTS `student_course_relation`;
CREATE TABLE `student_course_relation` (
  `SCR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CI_ID` int(11) DEFAULT NULL,
  `SI_ID` int(11) DEFAULT NULL,
  `SCR_CourseEvaluation` varchar(50) DEFAULT NULL,
  `SCR_Present` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SCR_ID`),
  KEY `FK_Reference_30` (`CI_ID`),
  KEY `FK_Reference_31` (`SI_ID`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`CI_ID`) REFERENCES `course_info` (`CI_ID`),
  CONSTRAINT `FK_Reference_31` FOREIGN KEY (`SI_ID`) REFERENCES `student_info` (`SI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=473 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_course_relation
-- ----------------------------
INSERT INTO `student_course_relation` VALUES ('1', '1', '321', null, 'F');
INSERT INTO `student_course_relation` VALUES ('2', '1', '322', null, 'F');
INSERT INTO `student_course_relation` VALUES ('3', '1', '323', null, 'F');
INSERT INTO `student_course_relation` VALUES ('4', '1', '324', null, 'F');
INSERT INTO `student_course_relation` VALUES ('5', '1', '325', null, 'F');
INSERT INTO `student_course_relation` VALUES ('6', '1', '326', null, 'F');
INSERT INTO `student_course_relation` VALUES ('7', '1', '327', null, 'F');
INSERT INTO `student_course_relation` VALUES ('8', '1', '328', null, 'F');
INSERT INTO `student_course_relation` VALUES ('9', '1', '329', null, 'F');
INSERT INTO `student_course_relation` VALUES ('10', '1', '330', null, 'F');
INSERT INTO `student_course_relation` VALUES ('11', '1', '331', null, 'F');
INSERT INTO `student_course_relation` VALUES ('12', '1', '332', null, 'F');
INSERT INTO `student_course_relation` VALUES ('13', '1', '333', null, 'F');
INSERT INTO `student_course_relation` VALUES ('14', '1', '334', null, 'F');
INSERT INTO `student_course_relation` VALUES ('15', '1', '335', null, 'F');
INSERT INTO `student_course_relation` VALUES ('16', '1', '336', null, 'F');
INSERT INTO `student_course_relation` VALUES ('17', '1', '337', null, 'F');
INSERT INTO `student_course_relation` VALUES ('18', '1', '338', null, 'F');
INSERT INTO `student_course_relation` VALUES ('19', '1', '339', null, 'F');
INSERT INTO `student_course_relation` VALUES ('20', '1', '340', null, 'F');
INSERT INTO `student_course_relation` VALUES ('21', '1', '341', null, 'F');
INSERT INTO `student_course_relation` VALUES ('22', '1', '342', null, 'F');
INSERT INTO `student_course_relation` VALUES ('23', '1', '343', null, 'F');
INSERT INTO `student_course_relation` VALUES ('24', '1', '344', null, 'F');
INSERT INTO `student_course_relation` VALUES ('25', '1', '345', null, 'F');
INSERT INTO `student_course_relation` VALUES ('26', '1', '346', null, 'F');
INSERT INTO `student_course_relation` VALUES ('27', '1', '347', null, 'F');
INSERT INTO `student_course_relation` VALUES ('28', '1', '348', null, 'F');
INSERT INTO `student_course_relation` VALUES ('29', '1', '349', null, 'F');
INSERT INTO `student_course_relation` VALUES ('30', '1', '1', null, 'F');
INSERT INTO `student_course_relation` VALUES ('31', '1', '2', null, 'F');
INSERT INTO `student_course_relation` VALUES ('32', '1', '3', null, 'F');
INSERT INTO `student_course_relation` VALUES ('33', '1', '4', null, 'F');
INSERT INTO `student_course_relation` VALUES ('34', '1', '5', null, 'F');
INSERT INTO `student_course_relation` VALUES ('35', '1', '6', null, 'F');
INSERT INTO `student_course_relation` VALUES ('36', '1', '7', null, 'F');
INSERT INTO `student_course_relation` VALUES ('37', '1', '8', null, 'F');
INSERT INTO `student_course_relation` VALUES ('38', '1', '9', null, 'F');
INSERT INTO `student_course_relation` VALUES ('39', '1', '10', null, 'F');
INSERT INTO `student_course_relation` VALUES ('40', '1', '11', null, 'F');
INSERT INTO `student_course_relation` VALUES ('41', '1', '12', null, 'F');
INSERT INTO `student_course_relation` VALUES ('42', '1', '13', null, 'F');
INSERT INTO `student_course_relation` VALUES ('43', '1', '14', null, 'F');
INSERT INTO `student_course_relation` VALUES ('44', '1', '15', null, 'F');
INSERT INTO `student_course_relation` VALUES ('45', '1', '16', null, 'F');
INSERT INTO `student_course_relation` VALUES ('46', '1', '17', null, 'F');
INSERT INTO `student_course_relation` VALUES ('47', '1', '18', null, 'F');
INSERT INTO `student_course_relation` VALUES ('48', '1', '19', null, 'F');
INSERT INTO `student_course_relation` VALUES ('49', '1', '20', null, 'F');
INSERT INTO `student_course_relation` VALUES ('50', '1', '21', null, 'F');
INSERT INTO `student_course_relation` VALUES ('51', '1', '22', null, 'F');
INSERT INTO `student_course_relation` VALUES ('52', '1', '23', null, 'F');
INSERT INTO `student_course_relation` VALUES ('53', '1', '24', null, 'F');
INSERT INTO `student_course_relation` VALUES ('54', '1', '25', null, 'F');
INSERT INTO `student_course_relation` VALUES ('55', '1', '26', null, 'F');
INSERT INTO `student_course_relation` VALUES ('56', '1', '27', null, 'F');
INSERT INTO `student_course_relation` VALUES ('57', '1', '28', null, 'F');
INSERT INTO `student_course_relation` VALUES ('58', '1', '29', null, 'F');
INSERT INTO `student_course_relation` VALUES ('59', '2', '350', null, 'F');
INSERT INTO `student_course_relation` VALUES ('60', '2', '351', null, 'F');
INSERT INTO `student_course_relation` VALUES ('61', '2', '352', null, 'F');
INSERT INTO `student_course_relation` VALUES ('62', '2', '353', null, 'F');
INSERT INTO `student_course_relation` VALUES ('63', '2', '354', null, 'F');
INSERT INTO `student_course_relation` VALUES ('64', '2', '355', null, 'F');
INSERT INTO `student_course_relation` VALUES ('65', '2', '356', null, 'F');
INSERT INTO `student_course_relation` VALUES ('66', '2', '357', null, 'F');
INSERT INTO `student_course_relation` VALUES ('67', '2', '358', null, 'F');
INSERT INTO `student_course_relation` VALUES ('68', '2', '359', null, 'F');
INSERT INTO `student_course_relation` VALUES ('69', '2', '360', null, 'F');
INSERT INTO `student_course_relation` VALUES ('70', '2', '361', null, 'F');
INSERT INTO `student_course_relation` VALUES ('71', '2', '362', null, 'F');
INSERT INTO `student_course_relation` VALUES ('72', '2', '363', null, 'F');
INSERT INTO `student_course_relation` VALUES ('73', '2', '364', null, 'F');
INSERT INTO `student_course_relation` VALUES ('74', '2', '365', null, 'F');
INSERT INTO `student_course_relation` VALUES ('75', '2', '366', null, 'F');
INSERT INTO `student_course_relation` VALUES ('76', '2', '367', null, 'F');
INSERT INTO `student_course_relation` VALUES ('77', '2', '368', null, 'F');
INSERT INTO `student_course_relation` VALUES ('78', '2', '369', null, 'F');
INSERT INTO `student_course_relation` VALUES ('79', '2', '370', null, 'F');
INSERT INTO `student_course_relation` VALUES ('80', '2', '371', null, 'F');
INSERT INTO `student_course_relation` VALUES ('81', '2', '372', null, 'F');
INSERT INTO `student_course_relation` VALUES ('82', '2', '373', null, 'F');
INSERT INTO `student_course_relation` VALUES ('83', '2', '374', null, 'F');
INSERT INTO `student_course_relation` VALUES ('84', '2', '375', null, 'F');
INSERT INTO `student_course_relation` VALUES ('85', '2', '376', null, 'F');
INSERT INTO `student_course_relation` VALUES ('86', '2', '377', null, 'F');
INSERT INTO `student_course_relation` VALUES ('87', '2', '378', null, 'F');
INSERT INTO `student_course_relation` VALUES ('88', '2', '379', null, 'F');
INSERT INTO `student_course_relation` VALUES ('89', '2', '380', null, 'F');
INSERT INTO `student_course_relation` VALUES ('90', '3', '321', null, 'F');
INSERT INTO `student_course_relation` VALUES ('91', '3', '322', null, 'F');
INSERT INTO `student_course_relation` VALUES ('92', '3', '323', null, 'F');
INSERT INTO `student_course_relation` VALUES ('93', '3', '324', null, 'F');
INSERT INTO `student_course_relation` VALUES ('94', '3', '325', null, 'F');
INSERT INTO `student_course_relation` VALUES ('95', '3', '326', null, 'F');
INSERT INTO `student_course_relation` VALUES ('96', '3', '327', null, 'F');
INSERT INTO `student_course_relation` VALUES ('97', '3', '328', null, 'F');
INSERT INTO `student_course_relation` VALUES ('98', '3', '329', null, 'F');
INSERT INTO `student_course_relation` VALUES ('99', '3', '330', null, 'F');
INSERT INTO `student_course_relation` VALUES ('100', '3', '331', null, 'F');
INSERT INTO `student_course_relation` VALUES ('101', '3', '332', null, 'F');
INSERT INTO `student_course_relation` VALUES ('102', '3', '333', null, 'F');
INSERT INTO `student_course_relation` VALUES ('103', '3', '334', null, 'F');
INSERT INTO `student_course_relation` VALUES ('104', '3', '335', null, 'F');
INSERT INTO `student_course_relation` VALUES ('105', '3', '336', null, 'F');
INSERT INTO `student_course_relation` VALUES ('106', '3', '337', null, 'F');
INSERT INTO `student_course_relation` VALUES ('107', '3', '338', null, 'F');
INSERT INTO `student_course_relation` VALUES ('108', '3', '339', null, 'F');
INSERT INTO `student_course_relation` VALUES ('109', '3', '340', null, 'F');
INSERT INTO `student_course_relation` VALUES ('110', '3', '341', null, 'F');
INSERT INTO `student_course_relation` VALUES ('111', '3', '342', null, 'F');
INSERT INTO `student_course_relation` VALUES ('112', '3', '343', null, 'F');
INSERT INTO `student_course_relation` VALUES ('113', '3', '344', null, 'F');
INSERT INTO `student_course_relation` VALUES ('114', '3', '345', null, 'F');
INSERT INTO `student_course_relation` VALUES ('115', '3', '346', null, 'F');
INSERT INTO `student_course_relation` VALUES ('116', '3', '347', null, 'F');
INSERT INTO `student_course_relation` VALUES ('117', '3', '348', null, 'F');
INSERT INTO `student_course_relation` VALUES ('118', '3', '349', null, 'F');
INSERT INTO `student_course_relation` VALUES ('119', '3', '1', null, 'F');
INSERT INTO `student_course_relation` VALUES ('120', '3', '2', null, 'F');
INSERT INTO `student_course_relation` VALUES ('121', '3', '3', null, 'F');
INSERT INTO `student_course_relation` VALUES ('122', '3', '4', null, 'F');
INSERT INTO `student_course_relation` VALUES ('123', '3', '5', null, 'F');
INSERT INTO `student_course_relation` VALUES ('124', '3', '6', null, 'F');
INSERT INTO `student_course_relation` VALUES ('125', '3', '7', null, 'F');
INSERT INTO `student_course_relation` VALUES ('126', '3', '8', null, 'F');
INSERT INTO `student_course_relation` VALUES ('127', '3', '9', null, 'F');
INSERT INTO `student_course_relation` VALUES ('128', '3', '10', null, 'F');
INSERT INTO `student_course_relation` VALUES ('129', '3', '11', null, 'F');
INSERT INTO `student_course_relation` VALUES ('130', '3', '12', null, 'F');
INSERT INTO `student_course_relation` VALUES ('131', '3', '13', null, 'F');
INSERT INTO `student_course_relation` VALUES ('132', '3', '14', null, 'F');
INSERT INTO `student_course_relation` VALUES ('133', '3', '15', null, 'F');
INSERT INTO `student_course_relation` VALUES ('134', '3', '16', null, 'F');
INSERT INTO `student_course_relation` VALUES ('135', '3', '17', null, 'F');
INSERT INTO `student_course_relation` VALUES ('136', '3', '18', null, 'F');
INSERT INTO `student_course_relation` VALUES ('137', '3', '19', null, 'F');
INSERT INTO `student_course_relation` VALUES ('138', '3', '20', null, 'F');
INSERT INTO `student_course_relation` VALUES ('139', '3', '21', null, 'F');
INSERT INTO `student_course_relation` VALUES ('140', '3', '22', null, 'F');
INSERT INTO `student_course_relation` VALUES ('141', '3', '23', null, 'F');
INSERT INTO `student_course_relation` VALUES ('142', '3', '24', null, 'F');
INSERT INTO `student_course_relation` VALUES ('143', '3', '25', null, 'F');
INSERT INTO `student_course_relation` VALUES ('144', '3', '26', null, 'F');
INSERT INTO `student_course_relation` VALUES ('145', '3', '27', null, 'F');
INSERT INTO `student_course_relation` VALUES ('146', '3', '28', null, 'F');
INSERT INTO `student_course_relation` VALUES ('147', '3', '29', null, 'F');
INSERT INTO `student_course_relation` VALUES ('148', '4', '350', null, 'F');
INSERT INTO `student_course_relation` VALUES ('149', '4', '351', null, 'F');
INSERT INTO `student_course_relation` VALUES ('150', '4', '352', null, 'F');
INSERT INTO `student_course_relation` VALUES ('151', '4', '353', null, 'F');
INSERT INTO `student_course_relation` VALUES ('152', '4', '354', null, 'F');
INSERT INTO `student_course_relation` VALUES ('153', '4', '355', null, 'F');
INSERT INTO `student_course_relation` VALUES ('154', '4', '356', null, 'F');
INSERT INTO `student_course_relation` VALUES ('155', '4', '357', null, 'F');
INSERT INTO `student_course_relation` VALUES ('156', '4', '358', null, 'F');
INSERT INTO `student_course_relation` VALUES ('157', '4', '359', null, 'F');
INSERT INTO `student_course_relation` VALUES ('158', '4', '360', null, 'F');
INSERT INTO `student_course_relation` VALUES ('159', '4', '361', null, 'F');
INSERT INTO `student_course_relation` VALUES ('160', '4', '362', null, 'F');
INSERT INTO `student_course_relation` VALUES ('161', '4', '363', null, 'F');
INSERT INTO `student_course_relation` VALUES ('162', '4', '364', null, 'F');
INSERT INTO `student_course_relation` VALUES ('163', '4', '365', null, 'F');
INSERT INTO `student_course_relation` VALUES ('164', '4', '366', null, 'F');
INSERT INTO `student_course_relation` VALUES ('165', '4', '367', null, 'F');
INSERT INTO `student_course_relation` VALUES ('166', '4', '368', null, 'F');
INSERT INTO `student_course_relation` VALUES ('167', '4', '369', null, 'F');
INSERT INTO `student_course_relation` VALUES ('168', '4', '370', null, 'F');
INSERT INTO `student_course_relation` VALUES ('169', '4', '371', null, 'F');
INSERT INTO `student_course_relation` VALUES ('170', '4', '372', null, 'F');
INSERT INTO `student_course_relation` VALUES ('171', '4', '373', null, 'F');
INSERT INTO `student_course_relation` VALUES ('172', '4', '374', null, 'F');
INSERT INTO `student_course_relation` VALUES ('173', '4', '375', null, 'F');
INSERT INTO `student_course_relation` VALUES ('174', '4', '376', null, 'F');
INSERT INTO `student_course_relation` VALUES ('175', '4', '377', null, 'F');
INSERT INTO `student_course_relation` VALUES ('176', '4', '378', null, 'F');
INSERT INTO `student_course_relation` VALUES ('177', '4', '379', null, 'F');
INSERT INTO `student_course_relation` VALUES ('178', '4', '380', null, 'F');
INSERT INTO `student_course_relation` VALUES ('179', '4', '321', null, 'F');
INSERT INTO `student_course_relation` VALUES ('180', '4', '322', null, 'F');
INSERT INTO `student_course_relation` VALUES ('181', '4', '323', null, 'F');
INSERT INTO `student_course_relation` VALUES ('182', '4', '324', null, 'F');
INSERT INTO `student_course_relation` VALUES ('183', '4', '325', null, 'F');
INSERT INTO `student_course_relation` VALUES ('184', '4', '326', null, 'F');
INSERT INTO `student_course_relation` VALUES ('185', '4', '327', null, 'F');
INSERT INTO `student_course_relation` VALUES ('186', '4', '328', null, 'F');
INSERT INTO `student_course_relation` VALUES ('187', '4', '329', null, 'F');
INSERT INTO `student_course_relation` VALUES ('188', '4', '330', null, 'F');
INSERT INTO `student_course_relation` VALUES ('189', '4', '331', null, 'F');
INSERT INTO `student_course_relation` VALUES ('190', '4', '332', null, 'F');
INSERT INTO `student_course_relation` VALUES ('191', '4', '333', null, 'F');
INSERT INTO `student_course_relation` VALUES ('192', '4', '334', null, 'F');
INSERT INTO `student_course_relation` VALUES ('193', '4', '335', null, 'F');
INSERT INTO `student_course_relation` VALUES ('194', '4', '336', null, 'F');
INSERT INTO `student_course_relation` VALUES ('195', '4', '337', null, 'F');
INSERT INTO `student_course_relation` VALUES ('196', '4', '338', null, 'F');
INSERT INTO `student_course_relation` VALUES ('197', '4', '339', null, 'F');
INSERT INTO `student_course_relation` VALUES ('198', '4', '340', null, 'F');
INSERT INTO `student_course_relation` VALUES ('199', '4', '341', null, 'F');
INSERT INTO `student_course_relation` VALUES ('200', '4', '342', null, 'F');
INSERT INTO `student_course_relation` VALUES ('201', '4', '343', null, 'F');
INSERT INTO `student_course_relation` VALUES ('202', '4', '344', null, 'F');
INSERT INTO `student_course_relation` VALUES ('203', '4', '345', null, 'F');
INSERT INTO `student_course_relation` VALUES ('204', '4', '346', null, 'F');
INSERT INTO `student_course_relation` VALUES ('205', '4', '347', null, 'F');
INSERT INTO `student_course_relation` VALUES ('206', '4', '348', null, 'F');
INSERT INTO `student_course_relation` VALUES ('207', '4', '349', null, 'F');
INSERT INTO `student_course_relation` VALUES ('208', '4', '1', null, 'F');
INSERT INTO `student_course_relation` VALUES ('209', '4', '2', null, 'F');
INSERT INTO `student_course_relation` VALUES ('210', '4', '3', null, 'F');
INSERT INTO `student_course_relation` VALUES ('211', '4', '4', null, 'F');
INSERT INTO `student_course_relation` VALUES ('212', '4', '5', null, 'F');
INSERT INTO `student_course_relation` VALUES ('213', '4', '6', null, 'F');
INSERT INTO `student_course_relation` VALUES ('214', '4', '7', null, 'F');
INSERT INTO `student_course_relation` VALUES ('215', '4', '8', null, 'F');
INSERT INTO `student_course_relation` VALUES ('216', '4', '9', null, 'F');
INSERT INTO `student_course_relation` VALUES ('217', '4', '10', null, 'F');
INSERT INTO `student_course_relation` VALUES ('218', '4', '11', null, 'F');
INSERT INTO `student_course_relation` VALUES ('219', '4', '12', null, 'F');
INSERT INTO `student_course_relation` VALUES ('220', '4', '13', null, 'F');
INSERT INTO `student_course_relation` VALUES ('221', '4', '14', null, 'F');
INSERT INTO `student_course_relation` VALUES ('222', '4', '15', null, 'F');
INSERT INTO `student_course_relation` VALUES ('223', '4', '16', null, 'F');
INSERT INTO `student_course_relation` VALUES ('224', '4', '17', null, 'F');
INSERT INTO `student_course_relation` VALUES ('225', '4', '18', null, 'F');
INSERT INTO `student_course_relation` VALUES ('226', '4', '19', null, 'F');
INSERT INTO `student_course_relation` VALUES ('227', '4', '20', null, 'F');
INSERT INTO `student_course_relation` VALUES ('228', '4', '21', null, 'F');
INSERT INTO `student_course_relation` VALUES ('229', '4', '22', null, 'F');
INSERT INTO `student_course_relation` VALUES ('230', '4', '23', null, 'F');
INSERT INTO `student_course_relation` VALUES ('231', '4', '24', null, 'F');
INSERT INTO `student_course_relation` VALUES ('232', '4', '25', null, 'F');
INSERT INTO `student_course_relation` VALUES ('233', '4', '26', null, 'F');
INSERT INTO `student_course_relation` VALUES ('234', '4', '27', null, 'F');
INSERT INTO `student_course_relation` VALUES ('235', '4', '28', null, 'F');
INSERT INTO `student_course_relation` VALUES ('236', '4', '29', null, 'F');
INSERT INTO `student_course_relation` VALUES ('237', '5', '321', null, 'F');
INSERT INTO `student_course_relation` VALUES ('238', '5', '322', null, 'F');
INSERT INTO `student_course_relation` VALUES ('239', '5', '323', null, 'F');
INSERT INTO `student_course_relation` VALUES ('240', '5', '324', null, 'F');
INSERT INTO `student_course_relation` VALUES ('241', '5', '325', null, 'F');
INSERT INTO `student_course_relation` VALUES ('242', '5', '326', null, 'F');
INSERT INTO `student_course_relation` VALUES ('243', '5', '327', null, 'F');
INSERT INTO `student_course_relation` VALUES ('244', '5', '328', null, 'F');
INSERT INTO `student_course_relation` VALUES ('245', '5', '329', null, 'F');
INSERT INTO `student_course_relation` VALUES ('246', '5', '330', null, 'F');
INSERT INTO `student_course_relation` VALUES ('247', '5', '331', null, 'F');
INSERT INTO `student_course_relation` VALUES ('248', '5', '332', null, 'F');
INSERT INTO `student_course_relation` VALUES ('249', '5', '333', null, 'F');
INSERT INTO `student_course_relation` VALUES ('250', '5', '334', null, 'F');
INSERT INTO `student_course_relation` VALUES ('251', '5', '335', null, 'F');
INSERT INTO `student_course_relation` VALUES ('252', '5', '336', null, 'F');
INSERT INTO `student_course_relation` VALUES ('253', '5', '337', null, 'F');
INSERT INTO `student_course_relation` VALUES ('254', '5', '338', null, 'F');
INSERT INTO `student_course_relation` VALUES ('255', '5', '339', null, 'F');
INSERT INTO `student_course_relation` VALUES ('256', '5', '340', null, 'F');
INSERT INTO `student_course_relation` VALUES ('257', '5', '341', null, 'F');
INSERT INTO `student_course_relation` VALUES ('258', '5', '342', null, 'F');
INSERT INTO `student_course_relation` VALUES ('259', '5', '343', null, 'F');
INSERT INTO `student_course_relation` VALUES ('260', '5', '344', null, 'F');
INSERT INTO `student_course_relation` VALUES ('261', '5', '345', null, 'F');
INSERT INTO `student_course_relation` VALUES ('262', '5', '346', null, 'F');
INSERT INTO `student_course_relation` VALUES ('263', '5', '347', null, 'F');
INSERT INTO `student_course_relation` VALUES ('264', '5', '348', null, 'F');
INSERT INTO `student_course_relation` VALUES ('265', '5', '349', null, 'F');
INSERT INTO `student_course_relation` VALUES ('266', '5', '1', null, 'F');
INSERT INTO `student_course_relation` VALUES ('267', '5', '2', null, 'F');
INSERT INTO `student_course_relation` VALUES ('268', '5', '3', null, 'F');
INSERT INTO `student_course_relation` VALUES ('269', '5', '4', null, 'F');
INSERT INTO `student_course_relation` VALUES ('270', '5', '5', null, 'F');
INSERT INTO `student_course_relation` VALUES ('271', '5', '6', null, 'F');
INSERT INTO `student_course_relation` VALUES ('272', '5', '7', null, 'F');
INSERT INTO `student_course_relation` VALUES ('273', '5', '8', null, 'F');
INSERT INTO `student_course_relation` VALUES ('274', '5', '9', null, 'F');
INSERT INTO `student_course_relation` VALUES ('275', '5', '10', null, 'F');
INSERT INTO `student_course_relation` VALUES ('276', '5', '11', null, 'F');
INSERT INTO `student_course_relation` VALUES ('277', '5', '12', null, 'F');
INSERT INTO `student_course_relation` VALUES ('278', '5', '13', null, 'F');
INSERT INTO `student_course_relation` VALUES ('279', '5', '14', null, 'F');
INSERT INTO `student_course_relation` VALUES ('280', '5', '15', null, 'F');
INSERT INTO `student_course_relation` VALUES ('281', '5', '16', null, 'F');
INSERT INTO `student_course_relation` VALUES ('282', '5', '17', null, 'F');
INSERT INTO `student_course_relation` VALUES ('283', '5', '18', null, 'F');
INSERT INTO `student_course_relation` VALUES ('284', '5', '19', null, 'F');
INSERT INTO `student_course_relation` VALUES ('285', '5', '20', null, 'F');
INSERT INTO `student_course_relation` VALUES ('286', '5', '21', null, 'F');
INSERT INTO `student_course_relation` VALUES ('287', '5', '22', null, 'F');
INSERT INTO `student_course_relation` VALUES ('288', '5', '23', null, 'F');
INSERT INTO `student_course_relation` VALUES ('289', '5', '24', null, 'F');
INSERT INTO `student_course_relation` VALUES ('290', '5', '25', null, 'F');
INSERT INTO `student_course_relation` VALUES ('291', '5', '26', null, 'F');
INSERT INTO `student_course_relation` VALUES ('292', '5', '27', null, 'F');
INSERT INTO `student_course_relation` VALUES ('293', '5', '28', null, 'F');
INSERT INTO `student_course_relation` VALUES ('294', '5', '29', null, 'F');
INSERT INTO `student_course_relation` VALUES ('295', '6', '350', null, 'F');
INSERT INTO `student_course_relation` VALUES ('296', '6', '351', null, 'F');
INSERT INTO `student_course_relation` VALUES ('297', '6', '352', null, 'F');
INSERT INTO `student_course_relation` VALUES ('298', '6', '353', null, 'F');
INSERT INTO `student_course_relation` VALUES ('299', '6', '354', null, 'F');
INSERT INTO `student_course_relation` VALUES ('300', '6', '355', null, 'F');
INSERT INTO `student_course_relation` VALUES ('301', '6', '356', null, 'F');
INSERT INTO `student_course_relation` VALUES ('302', '6', '357', null, 'F');
INSERT INTO `student_course_relation` VALUES ('303', '6', '358', null, 'F');
INSERT INTO `student_course_relation` VALUES ('304', '6', '359', null, 'F');
INSERT INTO `student_course_relation` VALUES ('305', '6', '360', null, 'F');
INSERT INTO `student_course_relation` VALUES ('306', '6', '361', null, 'F');
INSERT INTO `student_course_relation` VALUES ('307', '6', '362', null, 'F');
INSERT INTO `student_course_relation` VALUES ('308', '6', '363', null, 'F');
INSERT INTO `student_course_relation` VALUES ('309', '6', '364', null, 'F');
INSERT INTO `student_course_relation` VALUES ('310', '6', '365', null, 'F');
INSERT INTO `student_course_relation` VALUES ('311', '6', '366', null, 'F');
INSERT INTO `student_course_relation` VALUES ('312', '6', '367', null, 'F');
INSERT INTO `student_course_relation` VALUES ('313', '6', '368', null, 'F');
INSERT INTO `student_course_relation` VALUES ('314', '6', '369', null, 'F');
INSERT INTO `student_course_relation` VALUES ('315', '6', '370', null, 'F');
INSERT INTO `student_course_relation` VALUES ('316', '6', '371', null, 'F');
INSERT INTO `student_course_relation` VALUES ('317', '6', '372', null, 'F');
INSERT INTO `student_course_relation` VALUES ('318', '6', '373', null, 'F');
INSERT INTO `student_course_relation` VALUES ('319', '6', '374', null, 'F');
INSERT INTO `student_course_relation` VALUES ('320', '6', '375', null, 'F');
INSERT INTO `student_course_relation` VALUES ('321', '6', '376', null, 'F');
INSERT INTO `student_course_relation` VALUES ('322', '6', '377', null, 'F');
INSERT INTO `student_course_relation` VALUES ('323', '6', '378', null, 'F');
INSERT INTO `student_course_relation` VALUES ('324', '6', '379', null, 'F');
INSERT INTO `student_course_relation` VALUES ('325', '6', '380', null, 'F');
INSERT INTO `student_course_relation` VALUES ('326', '6', '321', null, 'F');
INSERT INTO `student_course_relation` VALUES ('327', '6', '322', null, 'F');
INSERT INTO `student_course_relation` VALUES ('328', '6', '323', null, 'F');
INSERT INTO `student_course_relation` VALUES ('329', '6', '324', null, 'F');
INSERT INTO `student_course_relation` VALUES ('330', '6', '325', null, 'F');
INSERT INTO `student_course_relation` VALUES ('331', '6', '326', null, 'F');
INSERT INTO `student_course_relation` VALUES ('332', '6', '327', null, 'F');
INSERT INTO `student_course_relation` VALUES ('333', '6', '328', null, 'F');
INSERT INTO `student_course_relation` VALUES ('334', '6', '329', null, 'F');
INSERT INTO `student_course_relation` VALUES ('335', '6', '330', null, 'F');
INSERT INTO `student_course_relation` VALUES ('336', '6', '331', null, 'F');
INSERT INTO `student_course_relation` VALUES ('337', '6', '332', null, 'F');
INSERT INTO `student_course_relation` VALUES ('338', '6', '333', null, 'F');
INSERT INTO `student_course_relation` VALUES ('339', '6', '334', null, 'F');
INSERT INTO `student_course_relation` VALUES ('340', '6', '335', null, 'F');
INSERT INTO `student_course_relation` VALUES ('341', '6', '336', null, 'F');
INSERT INTO `student_course_relation` VALUES ('342', '6', '337', null, 'F');
INSERT INTO `student_course_relation` VALUES ('343', '6', '338', null, 'F');
INSERT INTO `student_course_relation` VALUES ('344', '6', '339', null, 'F');
INSERT INTO `student_course_relation` VALUES ('345', '6', '340', null, 'F');
INSERT INTO `student_course_relation` VALUES ('346', '6', '341', null, 'F');
INSERT INTO `student_course_relation` VALUES ('347', '6', '342', null, 'F');
INSERT INTO `student_course_relation` VALUES ('348', '6', '343', null, 'F');
INSERT INTO `student_course_relation` VALUES ('349', '6', '344', null, 'F');
INSERT INTO `student_course_relation` VALUES ('350', '6', '345', null, 'F');
INSERT INTO `student_course_relation` VALUES ('351', '6', '346', null, 'F');
INSERT INTO `student_course_relation` VALUES ('352', '6', '347', null, 'F');
INSERT INTO `student_course_relation` VALUES ('353', '6', '348', null, 'F');
INSERT INTO `student_course_relation` VALUES ('354', '6', '349', null, 'F');
INSERT INTO `student_course_relation` VALUES ('355', '7', '350', null, 'F');
INSERT INTO `student_course_relation` VALUES ('356', '7', '351', null, 'F');
INSERT INTO `student_course_relation` VALUES ('357', '7', '352', null, 'F');
INSERT INTO `student_course_relation` VALUES ('358', '7', '353', null, 'F');
INSERT INTO `student_course_relation` VALUES ('359', '7', '354', null, 'F');
INSERT INTO `student_course_relation` VALUES ('360', '7', '355', null, 'F');
INSERT INTO `student_course_relation` VALUES ('361', '7', '356', null, 'F');
INSERT INTO `student_course_relation` VALUES ('362', '7', '357', null, 'F');
INSERT INTO `student_course_relation` VALUES ('363', '7', '358', null, 'F');
INSERT INTO `student_course_relation` VALUES ('364', '7', '359', null, 'F');
INSERT INTO `student_course_relation` VALUES ('365', '7', '360', null, 'F');
INSERT INTO `student_course_relation` VALUES ('366', '7', '361', null, 'F');
INSERT INTO `student_course_relation` VALUES ('367', '7', '362', null, 'F');
INSERT INTO `student_course_relation` VALUES ('368', '7', '363', null, 'F');
INSERT INTO `student_course_relation` VALUES ('369', '7', '364', null, 'F');
INSERT INTO `student_course_relation` VALUES ('370', '7', '365', null, 'F');
INSERT INTO `student_course_relation` VALUES ('371', '7', '366', null, 'F');
INSERT INTO `student_course_relation` VALUES ('372', '7', '367', null, 'F');
INSERT INTO `student_course_relation` VALUES ('373', '7', '368', null, 'F');
INSERT INTO `student_course_relation` VALUES ('374', '7', '369', null, 'F');
INSERT INTO `student_course_relation` VALUES ('375', '7', '370', null, 'F');
INSERT INTO `student_course_relation` VALUES ('376', '7', '371', null, 'F');
INSERT INTO `student_course_relation` VALUES ('377', '7', '372', null, 'F');
INSERT INTO `student_course_relation` VALUES ('378', '7', '373', null, 'F');
INSERT INTO `student_course_relation` VALUES ('379', '7', '374', null, 'F');
INSERT INTO `student_course_relation` VALUES ('380', '7', '375', null, 'F');
INSERT INTO `student_course_relation` VALUES ('381', '7', '376', null, 'F');
INSERT INTO `student_course_relation` VALUES ('382', '7', '377', null, 'F');
INSERT INTO `student_course_relation` VALUES ('383', '7', '378', null, 'F');
INSERT INTO `student_course_relation` VALUES ('384', '7', '379', null, 'F');
INSERT INTO `student_course_relation` VALUES ('385', '7', '380', null, 'F');
INSERT INTO `student_course_relation` VALUES ('386', '7', '321', null, 'F');
INSERT INTO `student_course_relation` VALUES ('387', '7', '322', null, 'F');
INSERT INTO `student_course_relation` VALUES ('388', '7', '323', null, 'F');
INSERT INTO `student_course_relation` VALUES ('389', '7', '324', null, 'F');
INSERT INTO `student_course_relation` VALUES ('390', '7', '325', null, 'F');
INSERT INTO `student_course_relation` VALUES ('391', '7', '326', null, 'F');
INSERT INTO `student_course_relation` VALUES ('392', '7', '327', null, 'F');
INSERT INTO `student_course_relation` VALUES ('393', '7', '328', null, 'F');
INSERT INTO `student_course_relation` VALUES ('394', '7', '329', null, 'F');
INSERT INTO `student_course_relation` VALUES ('395', '7', '330', null, 'F');
INSERT INTO `student_course_relation` VALUES ('396', '7', '331', null, 'F');
INSERT INTO `student_course_relation` VALUES ('397', '7', '332', null, 'F');
INSERT INTO `student_course_relation` VALUES ('398', '7', '333', null, 'F');
INSERT INTO `student_course_relation` VALUES ('399', '7', '334', null, 'F');
INSERT INTO `student_course_relation` VALUES ('400', '7', '335', null, 'F');
INSERT INTO `student_course_relation` VALUES ('401', '7', '336', null, 'F');
INSERT INTO `student_course_relation` VALUES ('402', '7', '337', null, 'F');
INSERT INTO `student_course_relation` VALUES ('403', '7', '338', null, 'F');
INSERT INTO `student_course_relation` VALUES ('404', '7', '339', null, 'F');
INSERT INTO `student_course_relation` VALUES ('405', '7', '340', null, 'F');
INSERT INTO `student_course_relation` VALUES ('406', '7', '341', null, 'F');
INSERT INTO `student_course_relation` VALUES ('407', '7', '342', null, 'F');
INSERT INTO `student_course_relation` VALUES ('408', '7', '343', null, 'F');
INSERT INTO `student_course_relation` VALUES ('409', '7', '344', null, 'F');
INSERT INTO `student_course_relation` VALUES ('410', '7', '345', null, 'F');
INSERT INTO `student_course_relation` VALUES ('411', '7', '346', null, 'F');
INSERT INTO `student_course_relation` VALUES ('412', '7', '347', null, 'F');
INSERT INTO `student_course_relation` VALUES ('413', '7', '348', null, 'F');
INSERT INTO `student_course_relation` VALUES ('414', '7', '349', null, 'F');
INSERT INTO `student_course_relation` VALUES ('415', '7', '1', null, 'F');
INSERT INTO `student_course_relation` VALUES ('416', '7', '2', null, 'F');
INSERT INTO `student_course_relation` VALUES ('417', '7', '3', null, 'F');
INSERT INTO `student_course_relation` VALUES ('418', '7', '4', null, 'F');
INSERT INTO `student_course_relation` VALUES ('419', '7', '5', null, 'F');
INSERT INTO `student_course_relation` VALUES ('420', '7', '6', null, 'F');
INSERT INTO `student_course_relation` VALUES ('421', '7', '7', null, 'F');
INSERT INTO `student_course_relation` VALUES ('422', '7', '8', null, 'F');
INSERT INTO `student_course_relation` VALUES ('423', '7', '9', null, 'F');
INSERT INTO `student_course_relation` VALUES ('424', '7', '10', null, 'F');
INSERT INTO `student_course_relation` VALUES ('425', '7', '11', null, 'F');
INSERT INTO `student_course_relation` VALUES ('426', '7', '12', null, 'F');
INSERT INTO `student_course_relation` VALUES ('427', '7', '13', null, 'F');
INSERT INTO `student_course_relation` VALUES ('428', '7', '14', null, 'F');
INSERT INTO `student_course_relation` VALUES ('429', '7', '15', null, 'F');
INSERT INTO `student_course_relation` VALUES ('430', '7', '16', null, 'F');
INSERT INTO `student_course_relation` VALUES ('431', '7', '17', null, 'F');
INSERT INTO `student_course_relation` VALUES ('432', '7', '18', null, 'F');
INSERT INTO `student_course_relation` VALUES ('433', '7', '19', null, 'F');
INSERT INTO `student_course_relation` VALUES ('434', '7', '20', null, 'F');
INSERT INTO `student_course_relation` VALUES ('435', '7', '21', null, 'F');
INSERT INTO `student_course_relation` VALUES ('436', '7', '22', null, 'F');
INSERT INTO `student_course_relation` VALUES ('437', '7', '23', null, 'F');
INSERT INTO `student_course_relation` VALUES ('438', '7', '24', null, 'F');
INSERT INTO `student_course_relation` VALUES ('439', '7', '25', null, 'F');
INSERT INTO `student_course_relation` VALUES ('440', '7', '26', null, 'F');
INSERT INTO `student_course_relation` VALUES ('441', '7', '27', null, 'F');
INSERT INTO `student_course_relation` VALUES ('442', '7', '28', null, 'F');
INSERT INTO `student_course_relation` VALUES ('443', '7', '29', null, 'F');
INSERT INTO `student_course_relation` VALUES ('444', '8', '1', null, 'F');
INSERT INTO `student_course_relation` VALUES ('445', '8', '2', null, 'F');
INSERT INTO `student_course_relation` VALUES ('446', '8', '3', null, 'F');
INSERT INTO `student_course_relation` VALUES ('447', '8', '4', null, 'F');
INSERT INTO `student_course_relation` VALUES ('448', '8', '5', null, 'F');
INSERT INTO `student_course_relation` VALUES ('449', '8', '6', null, 'F');
INSERT INTO `student_course_relation` VALUES ('450', '8', '7', null, 'F');
INSERT INTO `student_course_relation` VALUES ('451', '8', '8', null, 'F');
INSERT INTO `student_course_relation` VALUES ('452', '8', '9', null, 'F');
INSERT INTO `student_course_relation` VALUES ('453', '8', '10', null, 'F');
INSERT INTO `student_course_relation` VALUES ('454', '8', '11', null, 'F');
INSERT INTO `student_course_relation` VALUES ('455', '8', '12', null, 'F');
INSERT INTO `student_course_relation` VALUES ('456', '8', '13', null, 'F');
INSERT INTO `student_course_relation` VALUES ('457', '8', '14', null, 'F');
INSERT INTO `student_course_relation` VALUES ('458', '8', '15', null, 'F');
INSERT INTO `student_course_relation` VALUES ('459', '8', '16', null, 'F');
INSERT INTO `student_course_relation` VALUES ('460', '8', '17', null, 'F');
INSERT INTO `student_course_relation` VALUES ('461', '8', '18', null, 'F');
INSERT INTO `student_course_relation` VALUES ('462', '8', '19', null, 'F');
INSERT INTO `student_course_relation` VALUES ('463', '8', '20', null, 'F');
INSERT INTO `student_course_relation` VALUES ('464', '8', '21', null, 'F');
INSERT INTO `student_course_relation` VALUES ('465', '8', '22', null, 'F');
INSERT INTO `student_course_relation` VALUES ('466', '8', '23', null, 'F');
INSERT INTO `student_course_relation` VALUES ('467', '8', '24', null, 'F');
INSERT INTO `student_course_relation` VALUES ('468', '8', '25', null, 'F');
INSERT INTO `student_course_relation` VALUES ('469', '8', '26', null, 'F');
INSERT INTO `student_course_relation` VALUES ('470', '8', '27', null, 'F');
INSERT INTO `student_course_relation` VALUES ('471', '8', '28', null, 'F');
INSERT INTO `student_course_relation` VALUES ('472', '8', '29', null, 'F');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `SI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UL_ID` int(11) DEFAULT NULL,
  `CI_ID` int(11) DEFAULT NULL,
  `SI_Num` varchar(20) DEFAULT NULL,
  `SI_RealName` varchar(20) DEFAULT NULL,
  `SI_Sex` varchar(20) DEFAULT NULL,
  `SI_Sign` varchar(20) DEFAULT NULL,
  `SI_Information` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SI_ID`),
  KEY `FK_Reference_22` (`UL_ID`),
  KEY `FK_Reference_23` (`CI_ID`),
  CONSTRAINT `FK_Reference_22` FOREIGN KEY (`UL_ID`) REFERENCES `user_login` (`UL_ID`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`CI_ID`) REFERENCES `class_info` (`CI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=381 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES ('1', '3', '1', '1006010001', '范甜甜', '女', 'T', '');
INSERT INTO `student_info` VALUES ('2', '4', '1', '1006010002', '沈吉', '男', 'T', '');
INSERT INTO `student_info` VALUES ('3', '5', '1', '1006010003', '羊晓芳D', '女', 'T', '');
INSERT INTO `student_info` VALUES ('4', '6', '1', '1006010004', '周漳荣', '男', 'T', '');
INSERT INTO `student_info` VALUES ('5', '7', '1', '1006010005', '吴倩倩', '女', 'T', '团召');
INSERT INTO `student_info` VALUES ('6', '8', '1', '1006010006', '江翀', '男', 'T', '');
INSERT INTO `student_info` VALUES ('7', '9', '1', '1006010007', '钟璐嘉', '女', 'T', '');
INSERT INTO `student_info` VALUES ('8', '10', '1', '1006010008', '孔成良', '男', 'T', '');
INSERT INTO `student_info` VALUES ('9', '11', '1', '1006010009', '赖东东', '女', 'T', '');
INSERT INTO `student_info` VALUES ('10', '12', '1', '1006010010', '钱可威D', '男', 'T', '');
INSERT INTO `student_info` VALUES ('11', '13', '1', '1006010011', '李娜', '女', 'T', '');
INSERT INTO `student_info` VALUES ('12', '14', '1', '1006010012', '张月岭', '男', 'T', '');
INSERT INTO `student_info` VALUES ('13', '15', '1', '1006010013', '许挽澜', '女', 'T', '');
INSERT INTO `student_info` VALUES ('14', '16', '1', '1006010014', '徐靖', '男', 'T', '');
INSERT INTO `student_info` VALUES ('15', '17', '1', '1006010015', '陈双双', '女', 'T', '');
INSERT INTO `student_info` VALUES ('16', '18', '1', '1006010017', '汪咏梅', '女', 'T', '');
INSERT INTO `student_info` VALUES ('17', '19', '1', '1006010018', '张耀文', '男', 'T', '');
INSERT INTO `student_info` VALUES ('18', '20', '1', '1006010019', '彭粟梦', '女', 'T', '');
INSERT INTO `student_info` VALUES ('19', '21', '1', '1006010020', '张凯', '男', 'T', '');
INSERT INTO `student_info` VALUES ('20', '22', '1', '1006010021', '薛露萍', '女', 'T', '');
INSERT INTO `student_info` VALUES ('21', '23', '1', '1006010022', '孙旭阳', '男', 'T', '');
INSERT INTO `student_info` VALUES ('22', '24', '1', '1006010023', '叶雪芳', '女', 'T', '');
INSERT INTO `student_info` VALUES ('23', '25', '1', '1006010024', '林程浩', '男', 'T', '');
INSERT INTO `student_info` VALUES ('24', '26', '1', '1006010025', '蓝梦婷', '女', 'T', '');
INSERT INTO `student_info` VALUES ('25', '27', '1', '1006010026', '丁新新', '男', 'T', '班召');
INSERT INTO `student_info` VALUES ('26', '28', '1', '1006010027', '罗丽', '女', 'T', '');
INSERT INTO `student_info` VALUES ('27', '29', '1', '1006010028', '施旭辉E', '男', 'T', '');
INSERT INTO `student_info` VALUES ('28', '30', '1', '1006010029', '金鑫', '女', 'T', '');
INSERT INTO `student_info` VALUES ('29', '31', '1', '1006010030', '胡建', '男', 'T', '');
INSERT INTO `student_info` VALUES ('321', '325', '2', '1006010031', '袁美君', '女', 'T', '');
INSERT INTO `student_info` VALUES ('322', '326', '2', '1006010032', '刘义伟', '男', 'T', '');
INSERT INTO `student_info` VALUES ('323', '327', '2', '1006010033', '李庆春', '女', 'T', '');
INSERT INTO `student_info` VALUES ('324', '328', '2', '1006010034', '羊阳', '男', 'T', '');
INSERT INTO `student_info` VALUES ('325', '329', '2', '1006010035', '冯琴梅', '女', 'T', '');
INSERT INTO `student_info` VALUES ('326', '330', '2', '1006010036', '阮航A', '男', 'T', '');
INSERT INTO `student_info` VALUES ('327', '331', '2', '1006010037', '周樱', '女', 'T', '');
INSERT INTO `student_info` VALUES ('328', '332', '2', '1006010038', '龚江隆', '男', 'T', '');
INSERT INTO `student_info` VALUES ('329', '333', '2', '1006010039', '沈良珍', '女', 'T', '');
INSERT INTO `student_info` VALUES ('330', '334', '2', '1006010040', '李佳达', '男', 'T', '');
INSERT INTO `student_info` VALUES ('331', '335', '2', '1006010041', '郑夏卉', '女', 'T', '');
INSERT INTO `student_info` VALUES ('332', '336', '2', '1006010042', '杭睿', '男', 'T', '');
INSERT INTO `student_info` VALUES ('333', '337', '2', '1006010043', '潘阿香', '女', 'T', '');
INSERT INTO `student_info` VALUES ('334', '338', '2', '1006010044', '蔡德志', '男', 'T', '');
INSERT INTO `student_info` VALUES ('335', '339', '2', '1006010045', '俞林艳', '女', 'T', '团召');
INSERT INTO `student_info` VALUES ('336', '340', '2', '1006010046', '许国魂', '男', 'T', '');
INSERT INTO `student_info` VALUES ('337', '341', '2', '1006010047', '周丹宁', '女', 'T', '');
INSERT INTO `student_info` VALUES ('338', '342', '2', '1006010048', '葛冬冬D', '男', 'T', '');
INSERT INTO `student_info` VALUES ('339', '343', '2', '1006010049', '吴琳羚', '女', 'T', '');
INSERT INTO `student_info` VALUES ('340', '344', '2', '1006010050', '黄豪帅', '男', 'T', '');
INSERT INTO `student_info` VALUES ('341', '345', '2', '1006010051', '唐丹丹', '女', 'T', '');
INSERT INTO `student_info` VALUES ('342', '346', '2', '1006010052', '程曦', '男', 'T', '');
INSERT INTO `student_info` VALUES ('343', '347', '2', '1006010053', '王雨扬', '女', 'T', '');
INSERT INTO `student_info` VALUES ('344', '348', '2', '1006010054', '沈亮', '男', 'T', '');
INSERT INTO `student_info` VALUES ('345', '349', '2', '1006010055', '刘垚', '女', 'T', '');
INSERT INTO `student_info` VALUES ('346', '350', '2', '1006010056', '张勇', '男', 'T', '班召');
INSERT INTO `student_info` VALUES ('347', '351', '2', '1006010057', '顾孝红', '女', 'T', '');
INSERT INTO `student_info` VALUES ('348', '352', '2', '1006010058', '黄维', '男', 'T', '');
INSERT INTO `student_info` VALUES ('349', '353', '2', '1006010059', '李亚楠', '女', 'T', '');
INSERT INTO `student_info` VALUES ('350', '354', '3', '1019150001', '郭天雨', '男', 'T', '');
INSERT INTO `student_info` VALUES ('351', '355', '3', '1019150002', '姬昂', '男', 'T', '');
INSERT INTO `student_info` VALUES ('352', '356', '3', '1019150003', '蔡如意', '女', 'T', '团召');
INSERT INTO `student_info` VALUES ('353', '357', '3', '1019150004', '唐海河', '男', 'T', '');
INSERT INTO `student_info` VALUES ('354', '358', '3', '1019150005', '蔡九旭', '男', 'T', '');
INSERT INTO `student_info` VALUES ('355', '359', '3', '1019150006', '胡星月', '女', 'T', '');
INSERT INTO `student_info` VALUES ('356', '360', '3', '1019150007', '徐杰', '男', 'T', '');
INSERT INTO `student_info` VALUES ('357', '361', '3', '1019150008', '丁晖', '男', 'T', '3');
INSERT INTO `student_info` VALUES ('358', '362', '3', '1019150009', '方馨露', '女', 'T', '');
INSERT INTO `student_info` VALUES ('359', '363', '3', '1019150010', '赵洲杰', '男', 'T', '');
INSERT INTO `student_info` VALUES ('360', '364', '3', '1019150011', '徐佳乐', '男', 'T', '-3');
INSERT INTO `student_info` VALUES ('361', '365', '3', '1019150012', '励慧莹', '女', 'T', '-3');
INSERT INTO `student_info` VALUES ('362', '366', '3', '1019150013', '何健', '男', 'T', '');
INSERT INTO `student_info` VALUES ('363', '367', '3', '1019150014', '陈永双', '男', 'T', '');
INSERT INTO `student_info` VALUES ('364', '368', '3', '1019150015', '罗晶', '女', 'T', '');
INSERT INTO `student_info` VALUES ('365', '369', '3', '1019150016', '王峰', '男', 'T', '');
INSERT INTO `student_info` VALUES ('366', '370', '3', '1019150017', '徐邵文', '男', 'T', '');
INSERT INTO `student_info` VALUES ('367', '371', '3', '1019150018', '许亚楠', '女', 'T', '');
INSERT INTO `student_info` VALUES ('368', '372', '3', '1019150019', '朱伟斌', '男', 'T', '');
INSERT INTO `student_info` VALUES ('369', '373', '3', '1019150020', '汤冬明', '男', 'T', '');
INSERT INTO `student_info` VALUES ('370', '374', '3', '1019150021', '童露娴', '女', 'T', '');
INSERT INTO `student_info` VALUES ('371', '375', '3', '1019150022', '童正群', '男', 'T', '');
INSERT INTO `student_info` VALUES ('372', '376', '3', '1019150023', '吴冰翔', '男', 'T', '班召');
INSERT INTO `student_info` VALUES ('373', '377', '3', '1019150024', '汪清雨', '女', 'T', '');
INSERT INTO `student_info` VALUES ('374', '378', '3', '1019150025', '金泽冬', '男', 'T', '');
INSERT INTO `student_info` VALUES ('375', '379', '3', '1019150026', '陈龙', '男', 'T', '');
INSERT INTO `student_info` VALUES ('376', '380', '3', '1019150027', '沈荷婷', '女', 'T', '');
INSERT INTO `student_info` VALUES ('377', '381', '3', '1019150028', '蒋垚遥', '男', 'T', '');
INSERT INTO `student_info` VALUES ('378', '382', '3', '1019150029', '邹建武', '男', 'T', '');
INSERT INTO `student_info` VALUES ('379', '383', '3', '1019150030', '张珊珊', '女', 'T', '');
INSERT INTO `student_info` VALUES ('380', '384', '3', '1019150031', '胡庭翔', '男', 'T', '5');

-- ----------------------------
-- Table structure for subject_infor
-- ----------------------------
DROP TABLE IF EXISTS `subject_infor`;
CREATE TABLE `subject_infor` (
  `SI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TI_ID` int(11) DEFAULT NULL,
  `CC_Id` int(11) DEFAULT NULL,
  `SI_State` varchar(20) DEFAULT NULL,
  `SI_UseScope` varchar(20) DEFAULT NULL,
  `CS_Title` varchar(500) DEFAULT NULL,
  `CS_A` varchar(200) DEFAULT NULL,
  `CS_B` varchar(200) DEFAULT NULL,
  `CS_C` varchar(200) DEFAULT NULL,
  `CS_D` varchar(200) DEFAULT NULL,
  `CS_E` varchar(200) DEFAULT NULL,
  `CS_Answer` varchar(50) DEFAULT NULL,
  `CS_AnswerExplain` varchar(500) DEFAULT NULL,
  `CS_Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SI_ID`),
  KEY `FK_Reference_35` (`TI_ID`),
  KEY `FK_Reference_39` (`CC_Id`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`TI_ID`) REFERENCES `teacher_info` (`TI_ID`),
  CONSTRAINT `FK_Reference_39` FOREIGN KEY (`CC_Id`) REFERENCES `course_chapter` (`CC_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject_infor
-- ----------------------------
INSERT INTO `subject_infor` VALUES ('1', '2', '6', null, null, '1. 在数据库设计中，将ER图转换成关系数据模型的过程属于（）', 'A. 需求分析阶段', 'B. 逻辑设计阶段', 'C. 概念设计阶段', 'D. 物理设计阶段', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('2', '2', '6', null, null, '2.在数据库设计中，表示用户业务流程的常用方法是( )', 'A.DFD', 'B.ER图', 'C.程序流程图', 'D.数据结构图', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('3', '2', '6', null, null, '3.把ER模型转换成关系模型的过程，属于数据库的( )', 'A.需求分析', 'B.概念设计', 'C.逻辑设计', 'D.物理设计', 'Chinese', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('4', '2', '6', null, null, '4.在ER模型中，如果有6个不同实体集，有9个不同的二元联系，其中3个1∶N联系，3个1∶1联系，3个M∶N联系，根据ER模型转换成关系模型的规则，转换成关系的数目是( )', 'A.6', 'B.9', 'C.12', 'D.15', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('5', '2', '6', null, null, '5.数据库设计属于（）。', 'A、程序设计范畴', 'B、管理科学范畴', 'C、系统工程范畴', 'D、软件工程范畴', '5', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('6', '2', '6', null, null, '6.设计数据流程图(DFD)属于数据库设计的（）', 'A、可行性分析阶段的任务', 'B、需求分析阶段的任务', 'C、概念设计阶段的任务', 'D、逻辑设计阶段的任务', '屌爆了', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('7', '2', '6', null, null, '7.在数据库的概念设计中，最常用的模型是（）', 'A、实体联系模型', 'B、数学模型', 'C、逻辑模型', 'D、物理模型', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('8', '2', '6', null, null, '8.数据库设计中，概念模型是（）', 'A、依赖于DBMS和硬件', 'B、依赖于DBMS独立于硬件', 'C、独立于DBMS依赖于硬件', 'D、独立于DBMS和硬件', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('9', '2', '6', null, null, '9.ER图是数据库设计的工具之一，它适用于建立数据库的（）。', 'A、需求模型', 'B、概念模型', 'C、逻辑模型', 'D、物理模型', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('10', '2', '6', null, null, '10.ER方法中用属性描述事物的特征，属性在ER图中表示为（）', 'A、椭圆形', 'B、矩形', 'C、菱形', 'D、有向边', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('11', '2', '6', null, null, '11.在关系数据库设计中，设计关系模式的任务属于（）', 'A、需求设计', 'B、概念设计', 'C、逻辑设计', 'D、物理设计', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('12', '2', '6', null, null, '12.设计子模式属于数据库设计的（）', 'A、需求设计', 'B、概念设计', 'C、逻辑设计', 'D、物理设计', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('13', '2', '6', null, null, '13.设计DB的存储结构属于数据库设计的（）', 'A、需求设计', 'B、概念设计', 'C、逻辑设计', 'D、物理设计', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('14', '2', '6', null, null, '14.数据库设计中，外模型是指（）', 'A、用户使用的数据模型', 'B、DB以外的数据模型', 'C、系统外面的模型', 'D、磁盘上数据的组织', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('15', '2', '6', null, null, '15.概念结构设计的目标是产生数据库的概念结构，这结构主要反映（）', 'A、组织机构的信息需求', 'B、应用程序员的编程需求', 'C、DBA的管理信息需求', 'D、DBS的维护需求', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('16', '2', '6', null, null, '16.在DB的概念设计和逻辑设计之间起桥梁作用的是（）', 'A、数据结构图', 'B、功能模块图', 'C、ER图', 'D、DFD', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('17', '2', '6', null, null, '17.在DB的需求分析和概念设计之间起桥梁作用的是（）', 'A、DFD', 'B、ER图', 'C、数据结构图', 'D、功能模块图', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('18', '2', '6', null, null, '18.ER模型转换成关系模型时，一个M:N联系转换为一个关系模式，该关系模型的关键字是（）', 'A、M端实体的关键字', 'B、N端实体的关键字', 'C、M端实体的关键字和N端实体的关键字的组合', 'D、重新选取的其他属性', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('19', '2', '6', null, null, '19.在一个ER图中，如果共有20个不同实体类型，在这些实体类型之间存在着9个不同的二元联系(二元联系是指两个实体之间的联系)其中3个是1:N联系，6个是M:N联系，还存在1个M:N的三元联系，那么根据ER模型转换成关系模型的规则，这个ER结构转换成的关系模型个数为（）', 'A、24', 'B、27', '', '', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('20', '2', '6', null, null, '20.在数据库设计中，弱实体是指（）', 'A、属性只有一个的实体', 'B、不存在关键码的实体', 'C、只能短时期存在的实体', 'D、以其他实体存在为先决条件的实体', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('21', '2', '6', null, null, '21.关于ER图，下列说法中错误的是（）', 'A、现实世界的事物表示为实体，事物的性质表示为属性', 'B、事物之间的自然联系表示为实体之间的联系', 'C、在ER图中，实体和属性的划分是绝对的、一成不变的', 'D、在ER图中，实体之间可以有联系，属性和实体之间不能有联系', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('22', '2', '6', null, null, '23.在数据库设计中，超类实体与子类实体的关系是（）', 'A、前者继承后者的所有属性', 'B、后者继承前者的所有属性', 'C、前者只继承后者的主键', 'D、后者只继承前者的主键', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('23', '2', '6', null, null, '24.综合局部ER图生成总体ER图过程中，下列说法错误的是（）', 'A、不同局部ER图中出现的相同实体，在总体ER图中只能出现一次。', 'B、在总体ER图中可以添加属于不同局部ER图实体间的联系', 'C、在总体ER图中可以添加局部ER图中不存在的联系', 'D、在总体ER图中不可以删除任何实体间的联系。', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('24', '2', '6', null, null, '25.在关系数据库中，通过（）来表示概念记录之间的关系。', 'A、外来关键字', 'B、关键字', 'C、数据字典', 'D、元组', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('25', '2', '6', null, null, '26.学生(学号，姓名，年龄)，老师(教师号，姓名，专业)如果一个老师可以教多个学生，那么学生与老师的关系是（）', 'A、1:1', 'B、2:2', 'C、M:N', 'D、1:N', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('26', '2', '6', null, null, '27.在需求分析阶段，通过调查要从用户处获得对数据库的下列要求（）', 'A、输入需求和输出需求', 'B、信息需求和处理需求', 'C、存储需求和结构需求', 'D、信息需求和结构需求', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('27', '2', '6', null, null, '28.从ER模型向关系数据模型转换要遵循一定的规则，下面四项中哪一个不是这种转换所 要求的规则？', 'A、一个实体类型转换成一个关系模型', 'B、一个联系类型转换成一个关系模式', 'C、对于M:N联系来说，转换成的关系的键为诸相关实体的键的组合', 'D、对于1:N联系来说，转换成的关系的键为N+1实体的键', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('28', '2', '6', null, null, '29.对数据库的物理设计优劣评价的重点是（）', 'A、时间和空间效率', 'B、动态和静态性能', 'C、用户界面的友好性', 'D、成本和效益', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('29', '2', '6', null, null, '30.下列关于数据库设计的说法正确的是（）', 'A、在数据库设计过程中，数据库的安全性、完整性、一致性和可恢复性等的设计总是以牺牲效率为代价的', 'B、数据库的实现只是对数据库结构装入实际的数据、建立实际的数据库的过程。', 'C、对数据库的概念、逻辑和物理结构的改变称为再构造', 'D、数据库设计中考虑的故障恢复方案，一般都是基于数据库的逻辑模型提出的。', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('30', '2', '6', null, null, '31.数据库的物理设计的目的是（）', 'A、找到一个有效、可实现的数据库存储结构', 'B、导出特定的DBMS可以处理的数据库模式和外模式', '', '', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('31', '2', '6', null, null, '32.在关系数据库中实现了数据表示的单一性，实体和实体之间的联系都用一种什么数据结构表示', 'A、数据字典', 'B、文件', 'C、表', 'D、数据库', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('32', '2', '6', null, null, '33.数据字典可以（）', 'A、用SQL语言查询', 'B、由任何用户使用', 'C、没有', '', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('33', '2', '6', null, null, '35.聚集是一种（）措施', 'A、DDL', 'B、DML', 'C、优化', 'D、面向对象', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('34', '2', '6', null, null, '36.逻辑设计阶段包括下面四个步骤：', 'a、形成初始模式', 'b、子模式设计', 'c、修正模式', 'd、模式评价', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('35', '2', '6', null, null, '这四个步骤的先后顺序是（）', 'A、abcd', 'B、bacd', 'C、badc', 'D、abdc', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('36', '2', '6', null, null, '37.数据库的设计包括两个方面的设计内容，它们是（）', 'A、概念设计和逻辑设计', 'B、模式设计和内模式设计', 'C、内模式设计和物理设计', 'D、结构特性设计和行为特性设计', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('37', '1', '1', null, null, '1. 如果事务T获得了数据项Q上的排它锁，则T对Q （）', 'A. 只能读不能写', 'B. 只能写不能读', 'C. 既可读又可写', 'D. 不能读不能写', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('38', '1', '1', null, null, '2. DBMS中实现事务持久性的子系统是 （）', 'A. 安全性管理子系统', 'B. 完整性管理子系统', 'C. 并发控制子系统', 'D. 恢复管理子系统', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('39', '1', '1', null, null, '3. SQL的全局约束是指基于元组的检查子句和 （）', 'A. 非空值约束', 'B. 域约束子句', 'C. 断言', 'D. 外键子句', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('40', '1', '1', null, null, '4.如果事务T获得了数据对象R上的排它锁，那么( )', 'A.T对R只能读不能写', 'B.T对R只能写不能读', 'C.其它事务对过R只能读不能写', 'D.其它事务对R既不能读也不能写', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('41', '1', '1', null, null, '5.事务的隔离性是由DBMS的( )', 'A.恢复子系统出现', 'B.完整性子系统实现', 'C.安全性子系统实现', 'D.并发控制子系统实现', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('42', '1', '1', null, null, '6.在数据库系统中死锁属于( )', 'A.系统故障', 'B.程序故障', 'C.事务故障', 'D.介质故障', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('43', '1', '1', null, null, '7.SQL中的视图提高了数据库系统的( )', 'A.完整性', 'B.并发控制', 'C.隔离性', 'D.安全性', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('44', '1', '1', null, null, '8.数据库中数据的正确性、有效性和相容性称为（）', 'A、恢复', 'B、并发控制', 'C、完整性', 'D、安全性', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('45', '1', '1', null, null, '9.授权是DBS采用的（）', 'A、恢复措施', 'B、并发控制措施', 'C、完整性措施', 'D、安全性措施', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('46', '1', '1', null, null, '10.DB的转储属于DBS的（）', 'A、完整性措施', 'B、安全行措施', 'C、并发控制措施', 'D、恢复措施', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('47', '1', '1', null, null, '11.事务的原子性是指（）', 'A、事务中包括的所有操作要么多做，要么都不做', 'B、事务一旦提交，对数据库的改变就是永久的', 'C、一个事务内部的操作及使用的数据对并发的其他事务是隔离的', 'D、事务必须是使数据库从一个一致性状态变到另一个一致性状态', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('48', '1', '1', null, null, '12.若一个事务执行成功，则它的全部更新被提交；若一个事务执行失败，则DB中被其更新过的数据恢复原状，就象这些更新从未发生过，这保持了数据库处于（）', 'A、安全性状态', 'B、一致性状态', 'C、完整性状态', 'D、可靠性状态', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('49', '1', '1', null, null, '13.事务的并发执行不会破坏DB的完整性，这个性质称为事务的（）', 'A、原子性', 'B、隔离性', 'C、持久性', 'D、一致性', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('50', '1', '1', null, null, '14.事务的执行次序称为（）', 'A、调度', 'B、过程', 'C、优先级', 'D、步骤', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('51', '1', '1', null, null, '15.“运行记录优先原则”只指应该在对数据库更新（）', 'A、前先写一个日志记录到日志文件', 'B、后写一个日志记录到日志文件', 'C、前先运行记录', 'D、后在日志缓冲区运行记录', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('52', '1', '1', null, null, '16.以下四个概念中，可用来解决“一个并发调度是否正确”问题的是（）', 'A、串行调度', 'B、并发事务的可串行化', 'C、并发事务的可并行化', 'D、并发事务的有效调度', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('53', '1', '1', null, null, '17.在正常的DBS中，“所有事务都是两段式的”与“事务的并发调度是可串行化的”（）', 'A、前者是后者的必要条件', 'B、后者是前者的充要条件', 'C、前者是后者的必然结果', 'D、后者是前者的必然结果', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('54', '1', '1', null, null, '18.DB技术中，“脏数据”是指（）', 'A、未提交的数据', 'B、未提交的随后又被撤消的数据', 'C、违反访问权而写人DB的数据', 'D、输入时就有错的数据', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('55', '1', '1', null, null, '19.数据恢复的基本策略是数据冗余，被转储的冗余数据包括（）', 'A、日志文件和数据库副本', 'B、应用程序和数据库副本', 'C、数据字典、日志文件和数据库副本', 'D、应用程序、数据字典、日志文件和数据库副本', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('56', '1', '1', null, null, '20.设有两个事务T1和T2，它们的并发操作如下所示，则正确的结论是（）\nT1 T2 \n--------------------- \n从DB读S \n从DB读S \nS=S-2 \nS=S-4 \n向DB回写S \n向DB回写S', 'A、不存在问题', 'B、丢失修改', 'C、读了“脏”数据', 'D、产生不一致分析', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('57', '1', '1', null, null, '21.设有两个事务T1和T2，它们的并发操作如下所示，则正确的结论是\nT1 T2 \n-----------------------\n从DB读S\nS=S-2 \n向DB回写S\n从DB读S \nS=S-4 \nROLLBACK', 'A、不存在问题', 'B、丢失修改', 'C、读了“脏”数据', 'D、产生不一致分析', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('58', '1', '1', null, null, '22.SQL的ROLLBACK语句的主要作用是（）', 'A、终止程序', 'B、中断程序', 'C、事务提交', 'D、事务回退', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('59', '1', '1', null, null, '23.SQL中的主键子句和外键子句属于DBS的（）', 'A、完整性措施', 'B、安全性措施', 'C、并发性措施', 'D、恢复措施', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('60', '1', '1', null, null, '24.ORACLE系统中“COMMIT WORK RELEASE”语句表示事务提交并且（）', 'A、释放封锁', 'B、撤消DB已作的修改', 'C、释放所有数据资源', 'D、释放所有资源', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('61', '1', '1', null, null, '25.视图的概念具有很多优点，下面哪一个不是它的优点（）', 'A、视图对于数据库的重构造提供了一定程度的逻辑独立性', 'B、简化了用户观点', 'C、视图机制方便了不同的用户以同样的方式看待同一数据', 'D、视图机制对机密数据提供了自动的安全保护功能', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('62', '1', '1', null, null, '在数据库的如下两个表中，若雇员信息的主键是雇员号，部门信息表的主键是部门号，在下列所给的操作中，哪个操作不能执行？ \n雇员信息表 部门信息表 \n雇员号 雇员名 部门号 工资 部门号 部门名 主任 \n001 张山 02 2000 01 业务部 李建 \n010 王宏达 01 1200 02 销售部 应伟东 \n056 马林生 02 1000 03 服务部 周垠 \n101 赵敏 04 1500 04 财务部 陈力胜 \n键是雇员号，部门信息表的主键是部门号，在下列所给的操作中，哪个操作不能执行？', 'A、从雇员信息表中删除行(\'010\'，\'王宏达\'，\'01\'，1200)', 'B、将行(\'102\',\'赵敏\'，\'01\'，1500)插入到雇员信息表中', 'C、将雇员信息表中雇员号=\'010\'的工资改为1600元', 'D、将雇员信息表中雇员号=\'101\'的部门号改为\'05\'', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('63', '1', '1', null, null, '27.授权定义经过编译后存储在（）中', 'A、文件系统', 'B、数据字典', 'C、表', 'D、数据库', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('64', '1', '1', null, null, '28.下列有关数据库的恢复的说法中不正确的是（）', 'A、应定期将数据库做成档案文件', 'B、在进行事务处理过程时数据库更新的全部内容写入日志文件', 'C、发生故障时用当时数据内容和档案文件更新前的映象，将文件恢复到最近的检查点文件状态。', 'D、数据库恢复，还可用最新的档案文件和日志文件的更新映象，将文件恢复到最新的检查点文件状态。', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('65', '1', '1', null, null, '29.当多个用户的（）同时存取或修改数据库时，可能发生相互干扰而造成的错误的操作结果或死锁。', 'A、应用程序', 'B、并发进程', 'C、数据库', 'D、操作', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('66', '1', '1', null, null, '30.所谓的冗余数据是指（）的数据', 'A、产生错误', 'B、由基本数据导出', 'C、删除', 'D、提高性能', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('67', '2', '4', null, null, '1. 关系模式中，满足2NF的模式___ 。', 'A.可能是1NF', 'B.必定是1NF', 'C.必定是3NF', 'D.必定是BCNF', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('68', '2', '4', null, null, '2. 在关系数据库设计中，设计关系模式是_____ 的任务。', 'A.需求分析阶段', 'B.概念设计阶段', 'C.逻辑设计阶段', 'D.物理设计阶段', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('69', '2', '4', null, null, '3. 数据库管理系统通常提供授权功能来控制不同用户访问数据的权限，这主要是为了实现数据库的____。', 'A.可靠性', 'B.一致性', 'C.完整性', 'D.安全性', 'Chinese', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('70', '2', '4', null, null, '4. 日志文件用于记录______。', 'A.程序运行过程', 'B.数据操作', 'C.对数据的所有更新操作', 'D.程序执行的结果', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('71', '2', '4', null, null, '5．SQL语言是______语言。', 'A. 层次数据库', 'B.网络数据库', 'C. 关系数据库', 'D.非数据库', '5', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('72', '2', '4', null, null, '6．并发操作会带来哪些数据不一致性______。', 'A.丢失修改、不可重复读、读脏数据', 'B.不可重复读、读脏数据、死锁', 'C.丢失修改、读脏数据、死锁', 'D.丢失修改、不可重复读、读脏数据、死锁', '屌爆了', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('73', '2', '4', null, null, '7．下列四项中说法不正确的是_____。', 'A.数据库减少了数据冗余', 'B. 数据库避免了一切数据的重复', 'C. 数据库中的数据可以共享', 'D.数据库具有较高的数据独立性', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('74', '2', '4', null, null, '8．在数据库系统中，保证数据及语义正确和有效的功能是_____。', 'A.并发控制', 'B.存取控制', 'C.安全控制', 'D.完整性控制', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('75', '2', '4', null, null, '9．使用二维表格结构表达实体及实体间联系的数据模型是______。', 'A.层次模型', 'B.网状模型', 'C.对象模型', 'D.关系模型', '', 'B', null, null);
INSERT INTO `subject_infor` VALUES ('76', '2', '4', null, null, '10．在关系数据库系统中，当关系的型改变时，用户程序也可以不变，这是______。', 'A.数据的物理独立性', 'B.数据的逻辑独立性', 'C.数据的位置独立性', 'D.数据的存储独立性', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('77', '2', '4', null, null, '11．公司中有多个部门和多名职员，每个职员只能属于一个部门，一个部门可以有多名职员，从部门到职员的联系类型是______。', 'A. 一对多', 'B.一对一', 'C.多对一', 'D. 多对多', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('78', '2', '4', null, null, '12．一个m:n联系换为一个关系模式，关系的码为______。', 'A.某个实体的码', 'B.各实体码的组合', 'C.n端实体的码', 'D.任意一个实体的码', '', 'C', null, null);
INSERT INTO `subject_infor` VALUES ('79', '2', '4', null, null, '13．概念数据模型依赖于哪个数据库管理系统（       ）', 'A、DB2', 'B、MS SQL Server', 'C、Oracle', 'D、不依赖于任何数据库管理系统', '', 'D', null, null);
INSERT INTO `subject_infor` VALUES ('80', '2', '4', null, null, '14．需求分析说明书在哪个阶段生成（       ）', 'A.需求分析阶段', 'B.概念设计阶段', 'C.逻辑设计阶段', 'D.物理设计阶段', '', 'A', null, null);
INSERT INTO `subject_infor` VALUES ('81', '2', '4', null, null, '15．在SQL语言中，属于定义功能的命令是（）。', 'A .UPDATE', 'B.GRANT', 'C.CREATE', 'D.DROP', '', 'A', null, null);

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `TI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UL_ID` int(11) DEFAULT NULL,
  `PI_ID` int(11) DEFAULT NULL,
  `TI_Name` varchar(20) DEFAULT NULL,
  `TI_Job` varchar(20) DEFAULT NULL,
  `TI_Age` varchar(20) DEFAULT NULL,
  `TI_Sign` varchar(20) DEFAULT NULL,
  `TI_Skills` varchar(20) DEFAULT NULL,
  `TI_Address` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`TI_ID`),
  KEY `FK_Reference_21` (`UL_ID`),
  KEY `FK_Reference_32` (`PI_ID`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`UL_ID`) REFERENCES `user_login` (`UL_ID`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`PI_ID`) REFERENCES `profession_info` (`PI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES ('1', '2', '2', '杨美', '讲师', '31', 'T', '无', null);
INSERT INTO `teacher_info` VALUES ('2', '32', '2', '刘婷', '教授', '25', 'T', '全能', null);
INSERT INTO `teacher_info` VALUES ('3', '33', '4', '楚生辉', '教授', '22', 'T', '无', null);

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `UL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UL_Name` varchar(20) DEFAULT NULL,
  `UL_Password` varchar(32) DEFAULT NULL,
  `UL_Sign` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`UL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'adt');
INSERT INTO `user_login` VALUES ('2', '100000', 'e10adc3949ba59abbe56e057f20f883e', 'tea');
INSERT INTO `user_login` VALUES ('3', '1006010001', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('4', '1006010002', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('5', '1006010003', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('6', '1006010004', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('7', '1006010005', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('8', '1006010006', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('9', '1006010007', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('10', '1006010008', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('11', '1006010009', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('12', '1006010010', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('13', '1006010011', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('14', '1006010012', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('15', '1006010013', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('16', '1006010014', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('17', '1006010015', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('18', '1006010017', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('19', '1006010018', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('20', '1006010019', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('21', '1006010020', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('22', '1006010021', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('23', '1006010022', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('24', '1006010023', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('25', '1006010024', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('26', '1006010025', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('27', '1006010026', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('28', '1006010027', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('29', '1006010028', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('30', '1006010029', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('31', '1006010030', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('32', '100001', 'e10adc3949ba59abbe56e057f20f883e', 'tea');
INSERT INTO `user_login` VALUES ('33', '100002', 'e10adc3949ba59abbe56e057f20f883e', 'tea');
INSERT INTO `user_login` VALUES ('325', '1006010031', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('326', '1006010032', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('327', '1006010033', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('328', '1006010034', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('329', '1006010035', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('330', '1006010036', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('331', '1006010037', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('332', '1006010038', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('333', '1006010039', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('334', '1006010040', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('335', '1006010041', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('336', '1006010042', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('337', '1006010043', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('338', '1006010044', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('339', '1006010045', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('340', '1006010046', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('341', '1006010047', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('342', '1006010048', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('343', '1006010049', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('344', '1006010050', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('345', '1006010051', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('346', '1006010052', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('347', '1006010053', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('348', '1006010054', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('349', '1006010055', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('350', '1006010056', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('351', '1006010057', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('352', '1006010058', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('353', '1006010059', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('354', '1019150001', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('355', '1019150002', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('356', '1019150003', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('357', '1019150004', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('358', '1019150005', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('359', '1019150006', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('360', '1019150007', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('361', '1019150008', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('362', '1019150009', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('363', '1019150010', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('364', '1019150011', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('365', '1019150012', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('366', '1019150013', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('367', '1019150014', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('368', '1019150015', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('369', '1019150016', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('370', '1019150017', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('371', '1019150018', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('372', '1019150019', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('373', '1019150020', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('374', '1019150021', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('375', '1019150022', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('376', '1019150023', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('377', '1019150024', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('378', '1019150025', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('379', '1019150026', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('380', '1019150027', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('381', '1019150028', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('382', '1019150029', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('383', '1019150030', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
INSERT INTO `user_login` VALUES ('384', '1019150031', 'e10adc3949ba59abbe56e057f20f883e', 'stu');
