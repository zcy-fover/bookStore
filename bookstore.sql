/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50704
Source Host           : 127.0.0.1:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2016-07-09 10:02:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `accountid` int(11) NOT NULL AUTO_INCREMENT,
  `balance` float DEFAULT NULL,
  PRIMARY KEY (`accountid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '390');

-- ----------------------------
-- Table structure for mybooks
-- ----------------------------
DROP TABLE IF EXISTS `mybooks`;
CREATE TABLE `mybooks` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Author` varchar(30) NOT NULL,
  `Title` varchar(30) NOT NULL,
  `Price` float NOT NULL,
  `Publishingdate` date NOT NULL,
  `Salesamount` int(11) NOT NULL,
  `Storenumber` int(11) NOT NULL,
  `Remark` text NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of mybooks
-- ----------------------------
INSERT INTO `mybooks` VALUES ('1', 'Tom', 'Java 编程思想', '50', '2009-06-22', '115', '30', 'Good Java Book');
INSERT INTO `mybooks` VALUES ('2', 'Jerry', 'Oracle DBA 教材', '60', '2009-06-22', '16', '14', 'Good Oracle Book');
INSERT INTO `mybooks` VALUES ('3', 'Bob', 'Ruby', '50', '2009-06-22', '16', '14', 'Good 0');
INSERT INTO `mybooks` VALUES ('4', 'Mike', 'Javascript', '51', '2009-06-22', '20', '90', 'Good 1');
INSERT INTO `mybooks` VALUES ('5', 'Rose', 'Ajax', '52', '2009-06-22', '15', '15', 'Good 2');
INSERT INTO `mybooks` VALUES ('6', 'Backham', 'Struts', '53', '2009-06-22', '5', '5', 'Good 3');
INSERT INTO `mybooks` VALUES ('7', 'Zidon', 'Hibernate', '54', '2009-06-22', '5', '5', 'Good 4');
INSERT INTO `mybooks` VALUES ('8', 'Ronaerdo', 'Spring', '55', '2009-06-22', '2', '13', 'Good 5');
INSERT INTO `mybooks` VALUES ('9', 'Clinsman', 'Cvs', '56', '2009-06-22', '0', '16', 'Good 6');
INSERT INTO `mybooks` VALUES ('10', 'Kaka', 'Seo', '57', '2009-06-22', '0', '17', 'Good 7');
INSERT INTO `mybooks` VALUES ('11', 'Lauer', 'Lucence', '58', '2009-06-22', '0', '18', 'Good 8');
INSERT INTO `mybooks` VALUES ('12', 'Kasi', 'Guice', '59', '2009-06-22', '0', '19', 'Good 9');
INSERT INTO `mybooks` VALUES ('13', 'Prierlo', 'Mysql', '60', '2009-06-22', '6', '14', 'Good 10');
INSERT INTO `mybooks` VALUES ('14', 'Haohaidong', 'DB2', '61', '2009-06-22', '9', '12', 'Good 11');
INSERT INTO `mybooks` VALUES ('15', 'Feige', 'Sybase', '62', '2009-06-22', '8', '14', 'Good 12');
INSERT INTO `mybooks` VALUES ('16', 'Tuoleisi', 'DBDesign', '63', '2009-06-22', '0', '23', 'Good 13');
INSERT INTO `mybooks` VALUES ('17', 'Jielade', 'Eclipse', '64', '2009-06-22', '0', '24', 'Good 14');
INSERT INTO `mybooks` VALUES ('18', 'Teli', 'Netbeans', '65', '2009-06-22', '0', '25', 'Good 15');
INSERT INTO `mybooks` VALUES ('19', 'Lapade', 'C#', '66', '2009-06-22', '0', '26', 'Good 16');
INSERT INTO `mybooks` VALUES ('20', 'Runi', 'JDBC', '67', '2009-06-22', '0', '27', 'Good 17');
INSERT INTO `mybooks` VALUES ('21', 'JoeKeer', 'Php', '68', '2009-06-22', '0', '28', 'Good 18');
INSERT INTO `mybooks` VALUES ('22', 'Jordan', 'MysqlFront', '69', '2009-06-22', '5', '24', 'Good 19');
INSERT INTO `mybooks` VALUES ('23', 'Yaoming', 'NoteBook', '70', '2009-06-22', '5', '25', 'Good 20');
INSERT INTO `mybooks` VALUES ('24', 'Yi', 'C', '71', '2009-06-22', '5', '26', 'Good 21');
INSERT INTO `mybooks` VALUES ('25', 'Sun', 'Css', '72', '2009-06-22', '0', '32', 'Good 22');
INSERT INTO `mybooks` VALUES ('26', 'Xuliang', 'JQuery', '73', '2009-06-22', '0', '33', 'Good 23');
INSERT INTO `mybooks` VALUES ('27', 'Meixi', 'Ext', '74', '2009-06-22', '0', '34', 'Good 24');
INSERT INTO `mybooks` VALUES ('28', 'Apple', 'iphone', '75', '2009-06-22', '0', '35', 'Good 25');
INSERT INTO `mybooks` VALUES ('29', 'Aigo', 'dc', '76', '2009-06-22', '0', '36', 'Good 26');
INSERT INTO `mybooks` VALUES ('30', 'Sony', 'psp', '77', '2009-06-22', '0', '100', 'Good 27');
INSERT INTO `mybooks` VALUES ('31', 'IRiver', 'mp3', '78', '2009-06-22', '0', '100', 'Good 28');
INSERT INTO `mybooks` VALUES ('32', 'Sansing', 'TV', '79', '2009-06-22', '0', '100', 'Good 29');

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `tradeid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `tradetime` datetime NOT NULL,
  PRIMARY KEY (`tradeid`),
  KEY `user_id_fk` (`userid`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of trade
-- ----------------------------
INSERT INTO `trade` VALUES ('12', '1', '2012-11-01 00:00:00');
INSERT INTO `trade` VALUES ('13', '1', '2012-11-01 00:00:00');
INSERT INTO `trade` VALUES ('14', '1', '2012-11-01 00:00:00');
INSERT INTO `trade` VALUES ('15', '1', '2012-12-20 00:00:00');
INSERT INTO `trade` VALUES ('16', '1', '2012-12-20 00:00:00');
INSERT INTO `trade` VALUES ('17', '1', '2016-04-26 00:00:00');
INSERT INTO `trade` VALUES ('18', '3', '2016-05-03 00:00:00');
INSERT INTO `trade` VALUES ('19', '1', '2016-05-03 00:00:00');
INSERT INTO `trade` VALUES ('20', '1', '2016-05-03 00:00:00');
INSERT INTO `trade` VALUES ('21', '1', '2016-05-03 00:00:00');
INSERT INTO `trade` VALUES ('22', '1', '2016-05-03 00:00:00');
INSERT INTO `trade` VALUES ('23', '1', '2016-05-03 00:00:00');
INSERT INTO `trade` VALUES ('24', '1', '2016-05-04 00:00:00');
INSERT INTO `trade` VALUES ('25', '1', '2016-05-04 00:00:00');
INSERT INTO `trade` VALUES ('26', '1', '2016-05-04 00:00:00');
INSERT INTO `trade` VALUES ('27', '1', '2016-05-04 00:00:00');
INSERT INTO `trade` VALUES ('28', '1', '2016-05-12 00:00:00');

-- ----------------------------
-- Table structure for tradeitem
-- ----------------------------
DROP TABLE IF EXISTS `tradeitem`;
CREATE TABLE `tradeitem` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `tradeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `book_id_fk` (`bookid`),
  KEY `trade_id_fk` (`tradeid`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`bookid`) REFERENCES `mybooks` (`Id`),
  CONSTRAINT `trade_id_fk` FOREIGN KEY (`tradeid`) REFERENCES `trade` (`tradeid`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tradeitem
-- ----------------------------
INSERT INTO `tradeitem` VALUES ('22', '1', '10', '12');
INSERT INTO `tradeitem` VALUES ('23', '2', '10', '12');
INSERT INTO `tradeitem` VALUES ('24', '3', '10', '12');
INSERT INTO `tradeitem` VALUES ('25', '1', '1', '13');
INSERT INTO `tradeitem` VALUES ('26', '13', '2', '13');
INSERT INTO `tradeitem` VALUES ('27', '14', '3', '13');
INSERT INTO `tradeitem` VALUES ('28', '15', '4', '13');
INSERT INTO `tradeitem` VALUES ('29', '1', '1', '14');
INSERT INTO `tradeitem` VALUES ('30', '13', '2', '14');
INSERT INTO `tradeitem` VALUES ('31', '14', '3', '14');
INSERT INTO `tradeitem` VALUES ('32', '15', '4', '14');
INSERT INTO `tradeitem` VALUES ('33', '22', '5', '14');
INSERT INTO `tradeitem` VALUES ('34', '23', '5', '14');
INSERT INTO `tradeitem` VALUES ('35', '24', '5', '14');
INSERT INTO `tradeitem` VALUES ('36', '2', '1', '15');
INSERT INTO `tradeitem` VALUES ('37', '1', '2', '15');
INSERT INTO `tradeitem` VALUES ('38', '3', '1', '15');
INSERT INTO `tradeitem` VALUES ('39', '2', '1', '16');
INSERT INTO `tradeitem` VALUES ('40', '1', '3', '16');
INSERT INTO `tradeitem` VALUES ('41', '3', '1', '16');
INSERT INTO `tradeitem` VALUES ('52', '1', '10', '12');
INSERT INTO `tradeitem` VALUES ('53', '2', '20', '12');
INSERT INTO `tradeitem` VALUES ('54', '3', '30', '12');
INSERT INTO `tradeitem` VALUES ('55', '4', '40', '12');
INSERT INTO `tradeitem` VALUES ('56', '5', '50', '12');
INSERT INTO `tradeitem` VALUES ('58', '1', '1', '23');
INSERT INTO `tradeitem` VALUES ('59', '1', '1', '24');
INSERT INTO `tradeitem` VALUES ('60', '1', '1', '25');
INSERT INTO `tradeitem` VALUES ('61', '1', '1', '26');
INSERT INTO `tradeitem` VALUES ('62', '1', '1', '27');
INSERT INTO `tradeitem` VALUES ('63', '1', '1', '28');
INSERT INTO `tradeitem` VALUES ('64', '2', '1', '28');
INSERT INTO `tradeitem` VALUES ('65', '3', '1', '28');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `accountid` int(11) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `account_id_fk` (`accountid`),
  CONSTRAINT `account_id_fk` FOREIGN KEY (`accountid`) REFERENCES `account` (`accountid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'Tom', '1', '123');
INSERT INTO `userinfo` VALUES ('2', 'AAA', '1', '123');
INSERT INTO `userinfo` VALUES ('3', 'BB', '1', '123');
INSERT INTO `userinfo` VALUES ('4', 'CC', '1', '123');
INSERT INTO `userinfo` VALUES ('5', 'DD', '1', '123');
INSERT INTO `userinfo` VALUES ('6', 'EE', '1', '123');
