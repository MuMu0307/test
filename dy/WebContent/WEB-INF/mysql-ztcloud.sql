/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : mysql-ztcloud

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-03-09 12:29:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `ticket_id` int(11) NOT NULL,
  `ticket_nums` int(11) NOT NULL,
  `have_done` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('2', '26', '2017-03-08 13:08:04', '5', '15', '1');
INSERT INTO `cart` VALUES ('3', '26', '2017-03-08 13:08:26', '6', '1', '1');
INSERT INTO `cart` VALUES ('4', '26', '2017-03-08 13:08:31', '4', '3', '1');
INSERT INTO `cart` VALUES ('5', '24', '2017-03-08 13:12:13', '5', '13', '1');
INSERT INTO `cart` VALUES ('6', '8', '2017-03-09 12:19:30', '6', '1', '1');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `scenic_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '不错，下次再来游玩。', '26', '4', '2017-03-08 13:09:55');
INSERT INTO `comment` VALUES ('2', 'haoping', '24', '4', '2017-03-08 13:12:43');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `suffix` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '1', '1', '2017-02-25 20:15:31', '1', '1');

-- ----------------------------
-- Table structure for order_cart
-- ----------------------------
DROP TABLE IF EXISTS `order_cart`;
CREATE TABLE `order_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_cart
-- ----------------------------
INSERT INTO `order_cart` VALUES ('1', '1', '2');
INSERT INTO `order_cart` VALUES ('2', '2', '3');
INSERT INTO `order_cart` VALUES ('3', '2', '4');
INSERT INTO `order_cart` VALUES ('4', '3', '5');
INSERT INTO `order_cart` VALUES ('5', '4', '6');

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `have_done` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_form
-- ----------------------------
INSERT INTO `order_form` VALUES ('1', '26', '2017-03-08 13:08:08', '1');
INSERT INTO `order_form` VALUES ('2', '26', '2017-03-08 13:08:37', '1');
INSERT INTO `order_form` VALUES ('3', '24', '2017-03-08 13:12:19', '1');
INSERT INTO `order_form` VALUES ('4', '8', '2017-03-09 12:19:34', '1');

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `praise` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `scenic_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of praise
-- ----------------------------
INSERT INTO `praise` VALUES ('2', '1', '17', '1', '2017-03-05 15:27:08');
INSERT INTO `praise` VALUES ('3', '2', '17', '2', '2017-03-05 15:33:47');
INSERT INTO `praise` VALUES ('4', '3', '15', '2', '2017-03-05 15:34:37');
INSERT INTO `praise` VALUES ('5', '6', '26', '4', '2017-03-08 13:09:09');
INSERT INTO `praise` VALUES ('6', '8', '24', '4', '2017-03-08 13:12:33');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超管', '2016-12-25 14:39:33');
INSERT INTO `role` VALUES ('2', '文件管理员', '2016-12-25 14:39:42');
INSERT INTO `role` VALUES ('3', '人事部部长', '2016-12-25 14:40:02');
INSERT INTO `role` VALUES ('4', '财务部部长', '2016-12-25 14:40:18');
INSERT INTO `role` VALUES ('5', '资源部主管', '2016-12-25 14:40:32');
INSERT INTO `role` VALUES ('6', '秘书', '2016-12-25 14:40:41');
INSERT INTO `role` VALUES ('7', '工人', '2016-12-25 14:40:47');
INSERT INTO `role` VALUES ('8', '普通用户', '2016-12-25 14:41:01');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scenic_name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `ticket_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `praise` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES ('3', '1', '1', '4', null, '2017-03-07 19:33:24', '0');
INSERT INTO `scenic` VALUES ('4', '1', '12', '5', '25', '2017-03-07 19:45:44', '14');
INSERT INTO `scenic` VALUES ('5', 'panda', '熊猫园haha', '6', '26', '2017-03-08 13:06:29', '0');

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_name` varchar(255) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `unit_price` float(10,1) NOT NULL,
  `description` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `sales_count` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('4', '1', '/goods-imgs/1e30e924b899a9014ead76081e950a7b0208f577.png', '1.0', '1', '2017-03-07 19:33:23', '3');
INSERT INTO `ticket` VALUES ('5', '1', '/goods-imgs/4618a628gb42d45594f8c.jpg', '1.0', '1', '2017-03-07 20:26:41', '28');
INSERT INTO `ticket` VALUES ('6', '大熊猫门票', '/goods-imgs/Mypsd_2526_201305251347530218B.jpg', '15.9', '很好玩', '2017-03-09 12:18:06', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  `email` varchar(30) NOT NULL,
  `tel` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8', 'root', '63a9f0ea7bb98050796b649e85481845', '2017-02-25 16:26:21', '0', '22', '1090909881');
INSERT INTO `user` VALUES ('21', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2017-03-07 19:21:06', '0', '1', '1');
INSERT INTO `user` VALUES ('22', '2', 'c81e728d9d4c2f636f067f89cc14862c', '2017-03-07 19:21:14', '0', '2', '2');
INSERT INTO `user` VALUES ('23', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '2017-03-07 19:21:20', '0', '3', '3');
INSERT INTO `user` VALUES ('24', '5', 'e4da3b7fbbce2345d7772b0674a318d5', '2017-03-07 19:21:29', '0', '5', '5');
INSERT INTO `user` VALUES ('25', '6', '1679091c5a880faf6fb5e6087eb1b2dc', '2017-03-07 19:21:45', '0', '6', '6');
INSERT INTO `user` VALUES ('26', 'pan', '96ac0342a3ccf9553e3d4c9da9b821b0', '2017-03-08 13:07:16', '0', 'pan', 'pan');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('97', '10', '3');
INSERT INTO `user_role` VALUES ('98', '10', '1');
INSERT INTO `user_role` VALUES ('100', '8', '2');
INSERT INTO `user_role` VALUES ('101', '8', '5');
