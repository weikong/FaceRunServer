/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : facerun

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-12-21 18:11:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `head_portrait` varchar(255) DEFAULT NULL,
  `type` int(2) NOT NULL DEFAULT '1',
  `status` int(2) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('3', 'weikong555', 'weikong555@gmail.com', '123123', null, '1', '1', '2017-12-01 18:10:17');

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `photos` varchar(1000) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `run_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('1', '坚持锻炼身体，坚持好好学习，坚持每天读书，好习惯养成中，加油。ps，今天没跑够4公里，明天加油。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a21/0n3fcdlde7r9.jpg,\r\nhttp://pic.melinked.com/me2017/a16/1uq0wi7wv3eo.jpg,\r\nhttp://pic.melinked.com/me2017/a25/hi37bcfsf8cq.jpg,\r\nhttp://pic.melinked.com/me2017/a26/p5k93rqx8ci7.jpg,\r\nhttp://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,', '2017-12-15 10:21:30', null, '成都天府软件园D区', null);
INSERT INTO `circle` VALUES ('2', '许多人，你离开他，更像是种解脱。没错，的确会有痛，也有伤害。但从人生的角度来看，这就是放自己一条生路。因为留在这个人身边，你会受伤一辈子。离开了，只是受伤一时。所以，不要被错误的人捕获。我们不仅要找到对的人，更要学会离开错的人。离开，也会是一种正确。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,\r\nhttp://pic.melinked.com/me2017/a13/0j8jdyi715ht.jpg,', '2017-12-15 10:21:58', null, '成都高新区石羊公园', null);
INSERT INTO `circle` VALUES ('3', '第一天、50个仰卧起坐，最后十个略困难，估计明天只能做四十个，身体不复当年，需要坚持锻炼。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,', '2017-12-15 10:22:59', null, '成都高新区火车南站地铁口', null);
INSERT INTO `circle` VALUES ('4', '这两个月每天锻炼坚持下来了，下一步就是戒掉零食和饮料了，感觉要出人命了。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,', '2017-12-15 10:23:49', null, '成都高新区火车南站地铁口', null);
INSERT INTO `circle` VALUES ('5', '晚上在宿舍做瑜伽，感觉自己的身体好僵硬啊，要坚持锻炼啊～睡觉睡觉，眉开眼笑～', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,\r\nhttp://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg', '2017-12-15 10:24:19', null, '成都高新区石羊公园', null);
INSERT INTO `circle` VALUES ('6', '第13天.虽然这几天没锻炼。但是还是控制住了。今晚稍微超了点。下不为例。明天继续坚持锻炼。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,\r\nhttp://pic.melinked.com/me2017/a13/0j8jdyi715ht.jpg,\r\nhttp://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,\r\nhttp://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg', '2017-12-15 10:25:06', null, '成都高新区锦城湖公园', null);
INSERT INTO `circle` VALUES ('7', '有哪份感情不是自己争取会自己找上门来的呢，不要再等到你眼睁睁的看着他去爱了别人之后，才发现嫉妒是一件多么恶心的事。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a16/1uq0wi7wv3eo.jpg,\r\nhttp://pic.melinked.com/me2017/a25/hi37bcfsf8cq.jpg,\r\nhttp://pic.melinked.com/me2017/a26/p5k93rqx8ci7.jpg,\r\nhttp://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,\r\nhttp://pic.melinked.com/me2017/a13/0j8jdyi715ht.jpg,\r\nhttp://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,\r\nhttp://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg', '2017-12-15 10:26:07', null, '吉林红石国家森林公园', null);
INSERT INTO `circle` VALUES ('8', '恋人之间，最怕的不是情绪化，而是彻底回归平静。一旦万籁俱寂，也就是情之尽头了。越厮缠，才越厮爱。那些正在折腾着的男男女女们，别嫌烦：生命在于运动，爱情注定折腾!', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,', '2017-12-15 10:27:48', null, '美国黄石国家公园', null);
INSERT INTO `circle` VALUES ('9', '一个人怕孤独，两个人怕辜负，心情反反覆覆，感情似有若无。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a26/p5k93rqx8ci7.jpg,http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,http://pic.melinked.com/me2017/a21/0n3fcdlde7r9.jpg,http://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg,http://pic.melinked.com/me2017/a16/1uq0wi7wv3eo.jpg,http://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,', '2017-12-15 10:29:08', null, '四川九寨沟国家级自然保护区', null);
INSERT INTO `circle` VALUES ('10', '跑步，永不止步！', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a19/6ep2168ni8ek.jpg', '2017-12-15 15:25:23', null, '美国夏威夷群岛', null);
INSERT INTO `circle` VALUES ('11', '我还能跑10公里', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a19/6ep2168ni8ek.jpg', '2017-12-15 15:43:17', null, '美国夏威夷群岛', null);
INSERT INTO `circle` VALUES ('24', '明天我还能跑', 'weikong555@gmail.com', '3', 'd9c2c5b3-c99f-4a41-bc8e-50d21bda2c9a.jpg,bdfb086f-8bac-440e-a73c-6d30b3e25b5b.jpg', '2017-12-19 13:49:02', null, '美国夏威夷群岛', null);
INSERT INTO `circle` VALUES ('25', 'i like', 'weikong555@gmail.com', '3', 'de48b020-0193-421e-ad40-22ffd69ddbb5.jpg', '2017-12-19 16:36:53', null, '美国夏威夷群岛', null);

-- ----------------------------
-- Table structure for fit
-- ----------------------------
DROP TABLE IF EXISTS `fit`;
CREATE TABLE `fit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `videocover` varchar(255) DEFAULT NULL,
  `videoduration` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fit
-- ----------------------------
INSERT INTO `fit` VALUES ('1', '俯卧撑', null, 'push_up.gif', null, null, null, '2017-12-21 14:15:49');
INSERT INTO `fit` VALUES ('2', '宽距俯卧撑', null, 'kuanjufuwocheng.gif', null, null, null, '2017-12-21 18:10:02');
INSERT INTO `fit` VALUES ('3', '钻石俯卧撑', null, 'fuwocheng.gif', null, null, null, '2017-12-21 14:09:09');
INSERT INTO `fit` VALUES ('4', '击掌俯卧撑', null, 'jizhangfuwocheng.gif', null, null, null, '2017-12-21 14:09:47');
INSERT INTO `fit` VALUES ('5', '单手俯卧撑', null, 'single_push_up.gif', null, null, null, '2017-12-21 14:15:40');
INSERT INTO `fit` VALUES ('6', '倒立俯卧撑', null, 'daolifuwocheng.gif', null, null, null, '2017-12-21 14:08:26');
INSERT INTO `fit` VALUES ('7', '倒立支撑', null, 'daolizhicheng.gif', null, null, null, '2017-12-21 14:08:48');
INSERT INTO `fit` VALUES ('8', '倒立爬墙', null, 'daolipaqiang.gif', null, null, null, '2017-12-21 14:08:33');
INSERT INTO `fit` VALUES ('9', '引体向上', null, 'yintixiangshang.gif', null, null, null, '2017-12-21 14:11:27');
INSERT INTO `fit` VALUES ('10', '仰卧起坐', null, 'yangwoqizuo.gif', null, null, null, '2017-12-21 14:11:20');
INSERT INTO `fit` VALUES ('11', 'V字两头起', null, 'v_up.gif', null, null, null, '2017-12-21 14:15:33');
INSERT INTO `fit` VALUES ('12', '平板支撑', null, 'pingbanzhicheng.gif', null, null, null, '2017-12-21 14:10:36');
INSERT INTO `fit` VALUES ('13', '侧平板支撑', null, 'cepingbanzhicheng.gif', null, null, null, '2017-12-21 14:05:30');
INSERT INTO `fit` VALUES ('14', 'Inch Worm', null, 'inch_worm.gif', null, null, null, '2017-12-21 14:09:19');
INSERT INTO `fit` VALUES ('15', 'Walkout', null, 'walk_out.gif', null, null, null, '2017-12-21 14:10:57');
INSERT INTO `fit` VALUES ('16', '仰卧腿举', null, 'yangwotuiju.gif', null, null, null, '2017-12-21 14:11:51');
INSERT INTO `fit` VALUES ('17', '飞行式腿举', null, 'feixingshituiju.gif', null, null, null, '2017-12-21 14:08:57');
INSERT INTO `fit` VALUES ('18', '交替腿举', null, 'jiaotituiju.gif', null, null, null, '2017-12-21 14:09:37');
INSERT INTO `fit` VALUES ('19', '超人式两头起', null, 'chaorenshiliangtouqi.gif', null, null, null, '2017-12-21 14:07:55');
INSERT INTO `fit` VALUES ('20', '开合跳', null, 'kaihetiao.gif', null, null, null, '2017-12-21 14:10:02');
INSERT INTO `fit` VALUES ('21', '波比跳', null, 'bobitiao.gif', null, null, null, '2017-12-21 14:05:21');
INSERT INTO `fit` VALUES ('22', '深蹲', null, 'shendun.gif', null, null, null, '2017-12-21 14:10:41');
INSERT INTO `fit` VALUES ('23', '跳跃深蹲', null, 'tiaoyueshendun.gif', null, null, null, '2017-12-21 14:10:49');
INSERT INTO `fit` VALUES ('24', '箭步蹲', null, 'jianbudun.gif', null, null, null, '2017-12-21 14:09:27');
INSERT INTO `fit` VALUES ('25', '靠墙支撑', null, 'kaoqiangzhicheng.gif', null, null, null, '2017-12-21 14:10:22');
INSERT INTO `fit` VALUES ('26', '单腿深蹲', null, 'dantuishendun.gif', null, null, null, '2017-12-21 14:08:14');
INSERT INTO `fit` VALUES ('27', '摸地速跑', null, 'modisupao.gif', null, null, null, '2017-12-21 14:10:30');
INSERT INTO `fit` VALUES ('28', '鸭子步', null, 'yazibu.gif', null, null, null, '2017-12-21 14:11:45');
INSERT INTO `fit` VALUES ('29', '移动箭步蹲', null, 'yidongjianbudun.gif', null, null, null, '2017-12-21 14:11:29');

-- ----------------------------
-- Table structure for run
-- ----------------------------
DROP TABLE IF EXISTS `run`;
CREATE TABLE `run` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `address` varchar(255) DEFAULT NULL COMMENT '地点',
  `nearby` varchar(255) DEFAULT NULL COMMENT '附近',
  `continue_days` int(11) DEFAULT NULL COMMENT '持续天数',
  `distance` bigint(20) DEFAULT NULL COMMENT '路程',
  `speed` double DEFAULT NULL COMMENT '速度',
  `use_time` varchar(255) DEFAULT NULL COMMENT '用时',
  `heat` varchar(255) DEFAULT NULL COMMENT '热量',
  `coordinate_list` longtext COMMENT '跑步坐标列表',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `district` varchar(255) DEFAULT NULL COMMENT '城区信息',
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of run
-- ----------------------------
INSERT INTO `run` VALUES ('1', '3', '111', '222', '阿斯蒂芬', '阿斯蒂芬', '2', '234', '12', null, null, null, null, null, null, '2017-12-04 15:15:32');
