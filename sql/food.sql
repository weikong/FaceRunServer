/*
Navicat MySQL Data Transfer

Source Server         : wei.kong
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : facerun

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-17 15:38:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `kind` varchar(255) DEFAULT NULL COMMENT '品种（不辣，微辣，爆辣）',
  `sales` varchar(255) DEFAULT NULL COMMENT '销量',
  `price` float DEFAULT NULL COMMENT '价格',
  `unit` varchar(255) DEFAULT NULL COMMENT '价格单位（**/根，**/斤，...）',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `video` varchar(255) DEFAULT NULL COMMENT '视频',
  `type` varchar(255) DEFAULT '1' COMMENT '食物种类（卤菜：1，其他：2）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '狂暴鸭脖', '爆辣', '月销量1254份', '3.5', '元/根', '../../static/image/yabo3.jpg', '', '1');
INSERT INTO `food` VALUES ('2', '狂暴鸭头', '爆辣', '月销量1254份', '5', '元/头', '../../static/image/yatou.jpg', '', '1');
INSERT INTO `food` VALUES ('3', '狂暴鸭翅', '爆辣', '月销量1254份', '15', '元/份', '../../static/image/yachi.jpg', '', '1');
INSERT INTO `food` VALUES ('4', '狂暴鸡尖', '爆辣', '月销量1254份', '15', '元/份', '../../static/image/jichi.jpg', '', '1');
INSERT INTO `food` VALUES ('5', '狂暴鸭脖', '微辣', '月销量1254份', '3.5', '元/根', '../../static/image/yabo3.jpg', '', '1');
INSERT INTO `food` VALUES ('6', '狂暴鸭头', '微辣', '月销量1254份', '5', '元/头', '../../static/image/yatou.jpg', '', '1');
INSERT INTO `food` VALUES ('7', '狂暴鸭翅', '微辣', '月销量1254份', '15', '元/份', '../../static/image/yachi.jpg', '', '1');
INSERT INTO `food` VALUES ('8', '狂暴鸡尖', '微辣', '月销量1254份', '15', '元/份', '../../static/image/jichi.jpg', '', '1');
INSERT INTO `food` VALUES ('9', '狂暴鸭脖', '五香', '月销量1254份', '3.5', '元/根', '../../static/image/yabo3.jpg', '', '1');
INSERT INTO `food` VALUES ('10', '狂暴鸭头', '五香', '月销量1254份', '5', '元/头', '../../static/image/yatou.jpg', '', '1');
INSERT INTO `food` VALUES ('11', '狂暴鸭翅', '五香', '月销量1254份', '15', '元/份', '../../static/image/yachi.jpg', '', '1');
INSERT INTO `food` VALUES ('12', '狂暴鸡尖', '五香', '月销量1254份', '15', '元/份', '../../static/image/jichi.jpg', '', '1');
