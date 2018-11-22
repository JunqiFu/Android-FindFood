/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : xw_sql

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-05 23:42:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_foods
-- ----------------------------
DROP TABLE IF EXISTS `tb_foods`;
CREATE TABLE `tb_foods` (
  `F_id` int(11) NOT NULL AUTO_INCREMENT,
  `F_number` varchar(11) NOT NULL,
  `S_name` varchar(100) NOT NULL,
  `F_name` varchar(100) NOT NULL,
  `F_price` decimal(10,2) NOT NULL,
  `F_introduce` varchar(250) NOT NULL,
  `F_images` varchar(100) NOT NULL,
  PRIMARY KEY (`F_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19303 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_foods
-- ----------------------------
INSERT INTO `tb_foods` VALUES ('1', '201', '一面之缘', '红灯照牛肉面', '15.00', '非常好吃，非常好吃，非常好吃，非常好吃，非常好吃', 'c1');
INSERT INTO `tb_foods` VALUES ('2', '202', 'Mr.龙虾先生（新南店）', '小龙虾', '58.00', '非常好吃，非常好吃，非常好吃，非常好吃，非常好吃', 'c2');
INSERT INTO `tb_foods` VALUES ('3', '203', '锦城印象火锅酒店（高新店）', '火锅', '107.00', '非常好吃，非常好吃，非常好吃，非常好吃，非常好吃', 'c3');
INSERT INTO `tb_foods` VALUES ('4', '204', '万州烤鱼', '万州烤鱼', '63.00', '非常好吃，非常好吃，非常好吃，非常好吃，非常好吃', 'c4');
INSERT INTO `tb_foods` VALUES ('5', '205', '大蓉和（拉德芳斯精品店）', '鱼香茄子', '12.00', '非常好吃，非常好吃，非常好吃，非常好吃，非常好吃', 'c5');

-- ----------------------------
-- Table structure for tb_older
-- ----------------------------
DROP TABLE IF EXISTS `tb_older`;
CREATE TABLE `tb_older` (
  `O_id` int(11) NOT NULL,
  `O_number` varchar(50) NOT NULL,
  `U_account` varchar(20) NOT NULL,
  `O_num` int(11) NOT NULL,
  `O_price` decimal(10,2) NOT NULL,
  `O_total` decimal(10,2) NOT NULL,
  `S_name` varchar(100) NOT NULL,
  `O_state` varchar(20) NOT NULL,
  `F_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`O_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_older
-- ----------------------------
INSERT INTO `tb_older` VALUES ('1', '2017.07.01.160352', '123567', '2', '30.00', '15.00', '一面之缘', '正在交易', '456');
INSERT INTO `tb_older` VALUES ('2', '2017.6.29.123055', '585697', '1', '63.00', '63.00', '万州烤鱼', '正在交易', '456');
INSERT INTO `tb_older` VALUES ('3', '20180530130036', '612588', '1', '58.00', '58.00', 'Mr.龙虾先生（新南店）', '正在交易', '456');

-- ----------------------------
-- Table structure for tb_shops
-- ----------------------------
DROP TABLE IF EXISTS `tb_shops`;
CREATE TABLE `tb_shops` (
  `S_id` int(11) NOT NULL AUTO_INCREMENT,
  `S_number` varchar(20) NOT NULL,
  `U_account` varchar(20) NOT NULL,
  `S_introduction` varchar(250) NOT NULL,
  `S_address` varchar(100) NOT NULL,
  `S_phone` varchar(12) NOT NULL,
  `S_headP` varchar(20) NOT NULL,
  `S_tuiJianP` varchar(20) NOT NULL,
  `S_workTime` varchar(5) NOT NULL,
  `S_type` varchar(30) NOT NULL,
  `S_name` varchar(100) NOT NULL,
  `S_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`S_id`,`S_number`)
) ENGINE=InnoDB AUTO_INCREMENT=16306 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shops
-- ----------------------------
INSERT INTO `tb_shops` VALUES ('16301', '1', '812567', '川菜，中餐', '高新区锦城大道', '13892856781', 'z1', 'b1', '15', '4', '大蓉和（拉德芳斯精品店）', '87.00');
INSERT INTO `tb_shops` VALUES ('16302', '2', '987568', '面食，抄手', '高新区苏宁广场', '13755612857', 'z2', 'b2', '12', '4', '一面之缘', '15.00');
INSERT INTO `tb_shops` VALUES ('16303', '3', '555667', '小龙虾，湘菜', '火车南站，凯德广场', '13688621357', 'z3', 'b3', '15', '1', 'Mr.龙虾先生（新南店）', '58.00');
INSERT INTO `tb_shops` VALUES ('16304', '4', '666338', '火锅，中餐', '成都南站', '13955321758', 'z4', 'b4', '12', '1', '锦城印象火锅酒店（高新店）', '107.00');
INSERT INTO `tb_shops` VALUES ('16305', '5', '925681', '川菜，烤鱼', '华西医院，省体育馆', '13566289213', 'z5', 'b5', '12', '1', '万州烤鱼', '63.00');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `U_id` int(11) NOT NULL AUTO_INCREMENT,
  `U_account` varchar(20) NOT NULL,
  `U_password` varchar(20) NOT NULL,
  `U_name` varchar(25) DEFAULT NULL,
  `U_sex` varchar(4) DEFAULT NULL,
  `U_phone` varchar(12) DEFAULT NULL,
  `U_address` varchar(100) DEFAULT NULL,
  `U_balance` decimal(10,2) DEFAULT NULL,
  `U_birthday` varchar(20) DEFAULT NULL,
  `U_headP` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`U_id`,`U_account`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '123567', 'mmd2', 'hhc', 'w', '13655135769', '1栋2楼', '100.00', '2018-7-3', ' d1');
INSERT INTO `tb_user` VALUES ('2', '585697', 'ccm', 'cao', 'w', '13866912585', '成都市郫都区', '0.00', ' 1998.05.20', ' d2');
INSERT INTO `tb_user` VALUES ('3', '612588', 'cck', 'wlk', 'm', '13955368257', '成都市高新区天益二街', '50.00', '1899.09.23', 'd3');
INSERT INTO `tb_user` VALUES ('4', '812567', 'k56', 'lig', 'm', '13892856781', '高新区锦城大道', '500.00', ' 1900.06.15', ' d4');
INSERT INTO `tb_user` VALUES ('5', '987568', 'll3', 'cch', 'm', '13755612857', '高新区苏宁广场', '300.00', ' 1980.03.12', ' d5');
INSERT INTO `tb_user` VALUES ('6', '555667', '555', 'xh', 'w', '13688621357', '火车南站，凯德广场', '1000.00', ' 1986.10.17', ' d6');
INSERT INTO `tb_user` VALUES ('7', '666338', '668', 'ful', 'm', '13955321758', '成都南站', '2000.00', '1979.11.19', 'd7');
INSERT INTO `tb_user` VALUES ('8', '925681', '777', 'yuw', 'w', '13566289213', '华西医院，省体育馆', '3000.00', '1993.06.05', 'd8');
