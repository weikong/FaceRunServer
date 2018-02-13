/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : facerun

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-02-13 16:20:51
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
  `thumbs` varchar(1000) DEFAULT NULL COMMENT '缩略图集',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `run_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('1', '坚持锻炼身体，坚持好好学习，坚持每天读书，好习惯养成中，加油。ps，今天没跑够4公里，明天加油。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a21/0n3fcdlde7r9.jpg,\r\nhttp://pic.melinked.com/me2017/a16/1uq0wi7wv3eo.jpg,\r\nhttp://pic.melinked.com/me2017/a25/hi37bcfsf8cq.jpg,\r\nhttp://pic.melinked.com/me2017/a26/p5k93rqx8ci7.jpg,\r\nhttp://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,', null, '2017-12-15 10:21:30', null, '成都天府软件园D区', null);
INSERT INTO `circle` VALUES ('2', '许多人，你离开他，更像是种解脱。没错，的确会有痛，也有伤害。但从人生的角度来看，这就是放自己一条生路。因为留在这个人身边，你会受伤一辈子。离开了，只是受伤一时。所以，不要被错误的人捕获。我们不仅要找到对的人，更要学会离开错的人。离开，也会是一种正确。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,\r\nhttp://pic.melinked.com/me2017/a13/0j8jdyi715ht.jpg,', null, '2017-12-15 10:21:58', null, '成都高新区石羊公园', null);
INSERT INTO `circle` VALUES ('3', '第一天、50个仰卧起坐，最后十个略困难，估计明天只能做四十个，身体不复当年，需要坚持锻炼。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,', null, '2017-12-15 10:22:59', null, '成都高新区火车南站地铁口', null);
INSERT INTO `circle` VALUES ('4', '这两个月每天锻炼坚持下来了，下一步就是戒掉零食和饮料了，感觉要出人命了。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,', null, '2017-12-15 10:23:49', null, '成都高新区火车南站地铁口', null);
INSERT INTO `circle` VALUES ('5', '晚上在宿舍做瑜伽，感觉自己的身体好僵硬啊，要坚持锻炼啊～睡觉睡觉，眉开眼笑～', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,\r\nhttp://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg', null, '2017-12-15 10:24:19', null, '成都高新区石羊公园', null);
INSERT INTO `circle` VALUES ('6', '第13天.虽然这几天没锻炼。但是还是控制住了。今晚稍微超了点。下不为例。明天继续坚持锻炼。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,\r\nhttp://pic.melinked.com/me2017/a13/0j8jdyi715ht.jpg,\r\nhttp://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,\r\nhttp://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg', null, '2017-12-15 10:25:06', null, '成都高新区锦城湖公园', null);
INSERT INTO `circle` VALUES ('7', '有哪份感情不是自己争取会自己找上门来的呢，不要再等到你眼睁睁的看着他去爱了别人之后，才发现嫉妒是一件多么恶心的事。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a16/1uq0wi7wv3eo.jpg,\r\nhttp://pic.melinked.com/me2017/a25/hi37bcfsf8cq.jpg,\r\nhttp://pic.melinked.com/me2017/a26/p5k93rqx8ci7.jpg,\r\nhttp://pic.melinked.com/me2017/a25/dh1hqy99ahjs.jpg,\r\nhttp://pic.melinked.com/me2017/a13/0j8jdyi715ht.jpg,\r\nhttp://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,\r\nhttp://pic.melinked.com/me2017/a11/ne4ya1p4m5bc.jpg,\r\nhttp://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg', null, '2017-12-15 10:26:07', null, '吉林红石国家森林公园', null);
INSERT INTO `circle` VALUES ('8', '恋人之间，最怕的不是情绪化，而是彻底回归平静。一旦万籁俱寂，也就是情之尽头了。越厮缠，才越厮爱。那些正在折腾着的男男女女们，别嫌烦：生命在于运动，爱情注定折腾!', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,', null, '2017-12-15 10:27:48', null, '美国黄石国家公园', null);
INSERT INTO `circle` VALUES ('9', '一个人怕孤独，两个人怕辜负，心情反反覆覆，感情似有若无。', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a26/p5k93rqx8ci7.jpg,http://pic.melinked.com/me2017/a5/nzf9q6t6qx1k.jpg,\r\nhttp://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,http://pic.melinked.com/me2017/a21/0n3fcdlde7r9.jpg,http://pic.melinked.com/me2017/a26/edk57fyz5f2z.jpg,http://pic.melinked.com/me2017/a16/1uq0wi7wv3eo.jpg,http://pic.melinked.com/me2017/a8/q3nngefwjdx1.jpg,', null, '2017-12-15 10:29:08', null, '四川九寨沟国家级自然保护区', null);
INSERT INTO `circle` VALUES ('10', '跑步，永不止步！', 'weikong555@gmail.com', '3', 'http://pic.melinked.com/me2017/a19/6ep2168ni8ek.jpg', null, '2017-12-15 15:25:23', null, '美国夏威夷群岛', null);
INSERT INTO `circle` VALUES ('44', '我还能跑10公里', 'weikong555@gmail.com', '3', '59dab2f5-79f2-47cc-a8bb-74e971b85c3d.jpg,f590503c-e0d5-4a64-9ad9-25e4742ab168.jpg,536b4abd-f23c-4615-bbe2-07c8639d40dc.jpg,233fc985-1763-4b03-aa7a-4e1641a50f34.jpg,023065ae-3110-4eb7-bbaf-8d59a3ff6367.jpg,98162959-8307-4475-9c2c-0160c4228116.jpg,40a74b46-280a-4493-9918-18f0f4192477.jpg', '59dab2f5-79f2-47cc-a8bb-74e971b85c3d_thumb.jpg,f590503c-e0d5-4a64-9ad9-25e4742ab168_thumb.jpg,536b4abd-f23c-4615-bbe2-07c8639d40dc_thumb.jpg,233fc985-1763-4b03-aa7a-4e1641a50f34_thumb.jpg,023065ae-3110-4eb7-bbaf-8d59a3ff6367_thumb.jpg,98162959-8307-4475-9c2c-0160c4228116_thumb.jpg,40a74b46-280a-4493-9918-18f0f4192477_thumb.jpg', '2017-12-26 13:23:33', null, '美国夏威夷群岛', null);

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
-- Table structure for petbrowsehistory
-- ----------------------------
DROP TABLE IF EXISTS `petbrowsehistory`;
CREATE TABLE `petbrowsehistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览历史';

-- ----------------------------
-- Records of petbrowsehistory
-- ----------------------------

-- ----------------------------
-- Table structure for petcategary
-- ----------------------------
DROP TABLE IF EXISTS `petcategary`;
CREATE TABLE `petcategary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petcategary
-- ----------------------------
INSERT INTO `petcategary` VALUES ('1', '1', '主粮');
INSERT INTO `petcategary` VALUES ('2', '2', '零食');
INSERT INTO `petcategary` VALUES ('3', '3', '服饰');
INSERT INTO `petcategary` VALUES ('4', '4', '玩具');
INSERT INTO `petcategary` VALUES ('5', '5', '牵引');
INSERT INTO `petcategary` VALUES ('6', '6', '保健');
INSERT INTO `petcategary` VALUES ('7', '7', '医疗');
INSERT INTO `petcategary` VALUES ('8', '8', '美容');
INSERT INTO `petcategary` VALUES ('9', '9', '清洁');
INSERT INTO `petcategary` VALUES ('10', '10', '国产');
INSERT INTO `petcategary` VALUES ('11', '11', '进口');
INSERT INTO `petcategary` VALUES ('12', '12', '干粮');
INSERT INTO `petcategary` VALUES ('13', '13', '湿粮');
INSERT INTO `petcategary` VALUES ('14', '14', '磨牙');
INSERT INTO `petcategary` VALUES ('15', '15', '洁齿');
INSERT INTO `petcategary` VALUES ('16', '16', '配饰');
INSERT INTO `petcategary` VALUES ('17', '17', '美毛');
INSERT INTO `petcategary` VALUES ('18', '18', '增毛');
INSERT INTO `petcategary` VALUES ('19', '19', '补钙');
INSERT INTO `petcategary` VALUES ('20', '20', '肠胃');
INSERT INTO `petcategary` VALUES ('21', '21', '增强免疫');
INSERT INTO `petcategary` VALUES ('22', '22', '体内驱虫');
INSERT INTO `petcategary` VALUES ('23', '23', '体外驱虫');
INSERT INTO `petcategary` VALUES ('24', '24', '眼部');
INSERT INTO `petcategary` VALUES ('25', '25', '护理');
INSERT INTO `petcategary` VALUES ('26', '26', '新品');
INSERT INTO `petcategary` VALUES ('27', '27', '寄养');
INSERT INTO `petcategary` VALUES ('28', '28', '婚配');
INSERT INTO `petcategary` VALUES ('29', '29', '寻找');

-- ----------------------------
-- Table structure for petcomment
-- ----------------------------
DROP TABLE IF EXISTS `petcomment`;
CREATE TABLE `petcomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relation_id` int(11) DEFAULT NULL COMMENT '关联id（人/商品/服务）',
  `type` varchar(255) DEFAULT NULL COMMENT '类型（人/商品/服务）',
  `description` varchar(255) DEFAULT NULL COMMENT '评价描述',
  `pictures` varchar(255) DEFAULT NULL COMMENT '图集',
  `commentator_id` int(11) DEFAULT NULL COMMENT '评论者ID',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `stars` varchar(255) DEFAULT NULL COMMENT '几星',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of petcomment
-- ----------------------------

-- ----------------------------
-- Table structure for petdating
-- ----------------------------
DROP TABLE IF EXISTS `petdating`;
CREATE TABLE `petdating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `age` float(11,1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `requirement` varchar(255) DEFAULT NULL,
  `headurl` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL COMMENT '1、寻找挚友；-1、暂时不找',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petdating
-- ----------------------------
INSERT INTO `petdating` VALUES ('1', 'Circle', '柯基', '2.5', '新北2期', '1', '绝对直男，来者不拒', '', '20180205144806-zaoxing.jpg', '2018-02-06 14:44:57', '1');
INSERT INTO `petdating` VALUES ('2', 'Look', '哈士奇', '1.2', '美洲花园', '2', '风一样的女子', null, '20180205144805-xiujia.jpg', '2018-02-06 14:44:55', '1');
INSERT INTO `petdating` VALUES ('4', '芒果', '金毛', '2.0', '新北润新花园', '1', '贴心宝贝', null, '20180205144804-xiyu.jpg', '2018-02-06 14:44:49', '1');

-- ----------------------------
-- Table structure for petlookfor
-- ----------------------------
DROP TABLE IF EXISTS `petlookfor`;
CREATE TABLE `petlookfor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `age` float(11,1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `requirement` varchar(255) DEFAULT NULL,
  `headurl` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL COMMENT '1、寻找中；2、冻结；9、已找到；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of petlookfor
-- ----------------------------
INSERT INTO `petlookfor` VALUES ('1', '牛奶', '萨摩耶', '0.6', '成都市高新区神仙树缤纷', '1', '脖子有项圈，叫它牛奶有反应', null, '20180205144806-zaoxing.jpg', '028-123455', null, '2018-02-06 15:15:44', '1');
INSERT INTO `petlookfor` VALUES ('2', '巴弟', '金毛寻回犬', '2.0', '成都市高新区润新花园2期', '1', '不乱叫，脖子有狗牌，陌生人叫他几乎不予理会', null, '20180205144804-xiyu.jpg', '028-123456', null, '2018-02-06 16:15:15', '1');

-- ----------------------------
-- Table structure for petproduct
-- ----------------------------
DROP TABLE IF EXISTS `petproduct`;
CREATE TABLE `petproduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `type` varchar(255) DEFAULT NULL COMMENT '商品类型',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品价格',
  `pictures` varchar(255) DEFAULT NULL COMMENT '图集',
  `html_url` varchar(255) DEFAULT NULL COMMENT '商品详情网页',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  `shop_id` int(11) DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `recommend` varchar(255) DEFAULT NULL COMMENT '推荐信息',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of petproduct
-- ----------------------------
INSERT INTO `petproduct` VALUES ('1', 'name1', 'desc1', '1,2,3', '123', null, null, null, '1', '囧囧汪', '1', '2018-02-02 15:45:14');

-- ----------------------------
-- Table structure for petproductcategary
-- ----------------------------
DROP TABLE IF EXISTS `petproductcategary`;
CREATE TABLE `petproductcategary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` int(11) NOT NULL,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petproductcategary
-- ----------------------------

-- ----------------------------
-- Table structure for petservice
-- ----------------------------
DROP TABLE IF EXISTS `petservice`;
CREATE TABLE `petservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `thumb` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petservice
-- ----------------------------
INSERT INTO `petservice` VALUES ('1', '8', '洗浴', '美容', '20180205144804-xiyu.jpg', null, '20180205144804-xiyu.jpg');
INSERT INTO `petservice` VALUES ('2', '8', '修甲', '美容', '20180205144805-xiujia.jpg', null, '20180205144805-xiujia.jpg');
INSERT INTO `petservice` VALUES ('3', '8', '造型', '美容', '20180205144806-zaoxing.jpg', null, '20180205144806-zaoxing.jpg');
INSERT INTO `petservice` VALUES ('4', '27', '标准间', '寄养', null, null, null);
INSERT INTO `petservice` VALUES ('5', '27', '豪华房', '寄养', null, null, null);
INSERT INTO `petservice` VALUES ('6', '27', '总统房', '寄养', null, null, null);

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
