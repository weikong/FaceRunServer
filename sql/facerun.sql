/*
Navicat MySQL Data Transfer

Source Server         : wei.kong
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : facerun

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-25 15:14:08
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('3', 'weikong555', 'weikong555@gmail.com', '123123', null, '1', '1', '2017-12-01 18:10:17');
INSERT INTO `account` VALUES ('4', '平行线', '383983853@qq.com', '123123', 'http://172.16.0.173:9090/download_file?filename=023b154c-14a9-49f3-a86d-dcaf0900b715.jpg', '1', '1', '2018-06-14 15:52:01');

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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

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
INSERT INTO `circle` VALUES ('45', '向前冲～NO.1', 'weikong555@gmail.com', '3', '1fd6a0fb-02b7-4eb8-8589-8f6b7470cd18.jpg', '1fd6a0fb-02b7-4eb8-8589-8f6b7470cd18_thumb.jpg', '2018-06-11 14:58:59', null, '', null);
INSERT INTO `circle` VALUES ('46', 'KEEP GOING!!', '383983853@qq.com', '4', '362257bc-5b64-4d08-b68e-f0d35f85606a.jpg,515d67cf-54c7-4740-920d-d8445e9abe12.jpg,68bfd977-29e2-45e7-878c-589d268894d5.jpg', '362257bc-5b64-4d08-b68e-f0d35f85606a_thumb.jpg,515d67cf-54c7-4740-920d-d8445e9abe12_thumb.jpg,68bfd977-29e2-45e7-878c-589d268894d5_thumb.jpg', '2018-06-14 15:55:19', null, '', null);
INSERT INTO `circle` VALUES ('47', '足球世界', '383983853@qq.com', '4', 'bc777ee5-6840-470e-ad1d-632c2db92b23.jpg', 'bc777ee5-6840-470e-ad1d-632c2db92b23_thumb.jpg', '2018-06-29 17:19:22', null, '', null);
INSERT INTO `circle` VALUES ('48', '看遍世间美景，疼我一生最爱，老婆，我们可以过的更好，相信我！', 'weikong555@gmail.com', '3', '911b2174-9878-433b-b5a5-1468e92493b8.jpg,fe5b5091-3a4a-4621-8c35-22d5b8c9e80e.jpg,13bb2019-3aab-4f09-aeed-84cf373135c1.jpg', '911b2174-9878-433b-b5a5-1468e92493b8_thumb.jpg,fe5b5091-3a4a-4621-8c35-22d5b8c9e80e_thumb.jpg,13bb2019-3aab-4f09-aeed-84cf373135c1_thumb.jpg', '2018-07-09 17:37:46', null, '', null);
INSERT INTO `circle` VALUES ('49', '让我们看遍世间繁华，让我们携手走完余生，爱你老婆', 'weikong555@gmail.com', '3', 'ac217d86-457f-4fab-8ea9-e6bdd9d36ecb.jpg,79f28e17-0766-4c0f-bc07-989c1ebfd6e0.jpg,4060f2ca-566c-43a0-aacf-89260e3725c9.jpg,5925013c-cf72-4dc5-841a-fa4f1bca56a9.jpg,4287f216-e8f8-436a-bf08-37b79ab11909.jpg', 'ac217d86-457f-4fab-8ea9-e6bdd9d36ecb_thumb.jpg,79f28e17-0766-4c0f-bc07-989c1ebfd6e0_thumb.jpg,4060f2ca-566c-43a0-aacf-89260e3725c9_thumb.jpg,5925013c-cf72-4dc5-841a-fa4f1bca56a9_thumb.jpg,4287f216-e8f8-436a-bf08-37b79ab11909_thumb.jpg', '2018-07-09 17:49:58', null, '', null);
INSERT INTO `circle` VALUES ('50', '自然的神奇与人文的独特', '383983853@qq.com', '4', 'd8d93963-1675-4fb7-8e03-435cf33dccfd.jpg,4ca5e639-cb85-444d-baa4-f67957c86540.jpg,21554bd2-e7f3-4433-840d-92e0c660352a.jpg,dee1226a-e7d5-4204-979f-e9438b5223b8.jpg,d24142cb-2866-48cb-97fd-0c149fd2347d.jpg,027e1ae3-5b82-41a0-8036-b9bb18d7a582.jpg,c3d88f69-86de-4f78-af6a-684443f9d402.jpg,7ea245d0-a008-461d-b3fb-aac013db65bc.jpg,e6215066-60e7-409e-b5fd-3ac7e429bc7c.jpg', 'd8d93963-1675-4fb7-8e03-435cf33dccfd_thumb.jpg,4ca5e639-cb85-444d-baa4-f67957c86540_thumb.jpg,21554bd2-e7f3-4433-840d-92e0c660352a_thumb.jpg,dee1226a-e7d5-4204-979f-e9438b5223b8_thumb.jpg,d24142cb-2866-48cb-97fd-0c149fd2347d_thumb.jpg,027e1ae3-5b82-41a0-8036-b9bb18d7a582_thumb.jpg,c3d88f69-86de-4f78-af6a-684443f9d402_thumb.jpg,7ea245d0-a008-461d-b3fb-aac013db65bc_thumb.jpg,e6215066-60e7-409e-b5fd-3ac7e429bc7c_thumb.jpg', '2018-07-19 16:01:58', null, '', null);
INSERT INTO `circle` VALUES ('51', '开始吧，boy！', '383983853@qq.com', '4', '66c9331b-b419-4b8a-880f-a704f5bf389c.jpg,b4212d78-3cd3-44d9-8b3f-bbaae67746c9.jpg', '66c9331b-b419-4b8a-880f-a704f5bf389c_thumb.jpg,b4212d78-3cd3-44d9-8b3f-bbaae67746c9_thumb.jpg', '2018-07-19 17:02:07', null, '', null);
INSERT INTO `circle` VALUES ('52', '日出，日落', '383983853@qq.com', '4', 'a7bf97ac-70ca-4024-a7ac-57699dabd152.jpg,268d122a-ef1a-43ef-8fa2-658248ee17ea.jpg', 'a7bf97ac-70ca-4024-a7ac-57699dabd152_thumb.jpg,268d122a-ef1a-43ef-8fa2-658248ee17ea_thumb.jpg', '2018-07-19 17:16:06', null, '', null);
INSERT INTO `circle` VALUES ('53', '广告', '383983853@qq.com', '4', '48960d1f-fe9f-433d-909a-82ddce4c22cc.jpg,ba5dca7f-15a8-4978-91f8-591e5247d3a5.jpg,eee77d4c-deed-4a46-a3af-732a4ae27eab.jpg', '48960d1f-fe9f-433d-909a-82ddce4c22cc_thumb.jpg,ba5dca7f-15a8-4978-91f8-591e5247d3a5_thumb.jpg,eee77d4c-deed-4a46-a3af-732a4ae27eab_thumb.jpg', '2018-07-19 17:24:52', null, '', null);

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
-- Table structure for fitplan
-- ----------------------------
DROP TABLE IF EXISTS `fitplan`;
CREATE TABLE `fitplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fitid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `planid` int(11) DEFAULT NULL COMMENT '计划名称id',
  `times` int(11) DEFAULT NULL,
  `counts` int(11) DEFAULT NULL,
  `countstype` int(11) DEFAULT NULL COMMENT '计时计数类型（1、计时；2、计数）',
  `accountid` int(11) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fitplan
-- ----------------------------
INSERT INTO `fitplan` VALUES ('1', '25', '全身训练', '1', '3', '60', '1', '-999', '2018-02-27 10:42:43', '1');
INSERT INTO `fitplan` VALUES ('2', '1', '全身训练', '1', '3', '20', '2', '-999', '2018-02-27 10:42:45', '2');
INSERT INTO `fitplan` VALUES ('3', '22', '全身训练', '1', '3', '20', '2', '-999', '2018-02-27 10:42:46', '3');
INSERT INTO `fitplan` VALUES ('4', '12', '全身训练', '1', '3', '60', '1', '-999', '2018-02-27 17:53:37', '4');
INSERT INTO `fitplan` VALUES ('5', '25', '恢复训练', '2', '2', '60', '1', '-999', '2018-02-27 10:42:49', '1');
INSERT INTO `fitplan` VALUES ('6', '1', '恢复训练', '2', '2', '15', '2', '-999', '2018-02-27 10:42:50', '2');
INSERT INTO `fitplan` VALUES ('7', '12', '恢复训练', '2', '2', '60', '1', '-999', '2018-02-27 17:53:32', '3');
INSERT INTO `fitplan` VALUES ('8', '1', '上肢训练', '3', '3', '20', '2', '-999', '2018-02-27 10:42:54', '1');
INSERT INTO `fitplan` VALUES ('9', '7', '上肢训练', '3', '3', '60', '1', '-999', '2018-02-27 10:42:55', '2');
INSERT INTO `fitplan` VALUES ('10', '9', '上肢训练', '3', '3', '5', '2', '-999', '2018-02-27 10:42:57', '3');
INSERT INTO `fitplan` VALUES ('11', '10', '腰腹训练', '4', '3', '20', '2', '-999', '2018-02-27 10:42:59', '1');
INSERT INTO `fitplan` VALUES ('12', '12', '腰腹训练', '4', '3', '60', '1', '-999', '2018-02-27 10:43:00', '2');
INSERT INTO `fitplan` VALUES ('13', '18', '腰腹训练', '4', '3', '20', '2', '-999', '2018-02-27 10:43:01', '3');
INSERT INTO `fitplan` VALUES ('14', '25', '下肢训练', '5', '3', '60', '1', '-999', '2018-02-27 10:43:02', '1');
INSERT INTO `fitplan` VALUES ('15', '24', '下肢训练', '5', '3', '20', '2', '-999', '2018-02-27 10:43:03', '2');
INSERT INTO `fitplan` VALUES ('16', '27', '下肢训练', '5', '3', '20', '2', '-999', '2018-02-27 10:43:03', '3');
INSERT INTO `fitplan` VALUES ('17', '22', '下肢训练', '5', '3', '20', '2', '-999', '2018-02-27 10:43:05', '4');

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

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
INSERT INTO `petcategary` VALUES ('30', '30', '哺乳期');
INSERT INTO `petcategary` VALUES ('31', '31', '幼犬期');
INSERT INTO `petcategary` VALUES ('32', '32', '成犬期');
INSERT INTO `petcategary` VALUES ('33', '33', '老犬期');
INSERT INTO `petcategary` VALUES ('34', '34', '全犬期');
INSERT INTO `petcategary` VALUES ('35', '35', '小颗粒');
INSERT INTO `petcategary` VALUES ('36', '36', '标准颗粒');
INSERT INTO `petcategary` VALUES ('37', '37', '大颗粒');
INSERT INTO `petcategary` VALUES ('38', '38', '在售');
INSERT INTO `petcategary` VALUES ('39', '39', '售罄');
INSERT INTO `petcategary` VALUES ('40', '40', '下架');
INSERT INTO `petcategary` VALUES ('41', '41', '迷你犬');
INSERT INTO `petcategary` VALUES ('42', '42', '小型犬');
INSERT INTO `petcategary` VALUES ('43', '43', '中型犬');
INSERT INTO `petcategary` VALUES ('44', '44', '大型犬');
INSERT INTO `petcategary` VALUES ('45', '45', '全体型');

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
-- Table structure for petcompany
-- ----------------------------
DROP TABLE IF EXISTS `petcompany`;
CREATE TABLE `petcompany` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `nickname` varchar(150) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `domestic` int(11) DEFAULT NULL COMMENT '国产',
  `logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petcompany
-- ----------------------------
INSERT INTO `petcompany` VALUES ('1', 'RoyalCanin', '皇家', '创立于1967年法国，宠物食品十大品牌，专为居家小型犬定制的精准营养方案，全球宠物食品行业的知名企业', '2', null);
INSERT INTO `petcompany` VALUES ('2', '宠优', 'purina', '创立于1894年美国，世界知名宠物食品品牌，雀巢集团旗下，专业从事宠物饲养研究、宠物食品开发销售的企业', '2', null);
INSERT INTO `petcompany` VALUES ('3', '宝路', 'Pedigree', '宠物食品十大品牌，狗粮-猫粮十大品牌，全球知名的高端宠物食品品牌，专业从事生产销售宠物食品及宠物护理产品的企业', null, null);
INSERT INTO `petcompany` VALUES ('4', '冠能', 'ProPlan', '雀巢普瑞纳旗下高端宠物食品品牌，创立于1986年，以犬干粮和猫干粮为主，其科学的营养配比受到了全球广大顶级繁育者及养宠人士的喜爱', null, null);
INSERT INTO `petcompany` VALUES ('5', '比瑞吉', 'Biruiji', '上海比瑞吉宠物用品有限公司，知名天然宠物食品品牌，致力于全系列猫狗粮的研发、生产、销售于一体的现代化大型企业', null, null);
INSERT INTO `petcompany` VALUES ('6', '好主人', 'Care', '成都好主人宠物食品有限公司，知名宠物食品品牌，通威集团旗下，四川省著名商标，宠物食品行业标准起草单位，专业从事猫粮狗粮研发、生产和销售的企业', null, null);
INSERT INTO `petcompany` VALUES ('7', '麦富迪', 'MYFOODIE', '乖宝集团旗下知名宠物食品品牌，专业生产犬用猫用干粮、烘干类和冻干类零食、湿粮罐头、咬胶类、洁齿骨和各种创新产品', null, null);
INSERT INTO `petcompany` VALUES ('8', '顽皮', 'Wanpy', '宠物食品十大品牌，高新技术企业，宠物饲料行业国家标准制定委员会委员，极具规模的专业化的高端宠物食品企业', null, null);
INSERT INTO `petcompany` VALUES ('9', '路斯', 'LUSCIOUS', '山东路斯宠物食品股份有限公司，宠物食品十大品牌，高品质宠物零食制造专家，集生产、加工、销售为一体的宠物食品专业公司', null, null);
INSERT INTO `petcompany` VALUES ('10', '雷米高', 'Ramical', '佛山市雷米高动物营养保健科技有限公司，澳洲高科集团旗下，十大宠物营养品品牌，专业从事宠物食品、用品、药品的研发、生产和销售的企业', null, null);

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
-- Table structure for petproductfood
-- ----------------------------
DROP TABLE IF EXISTS `petproductfood`;
CREATE TABLE `petproductfood` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `type` varchar(255) DEFAULT NULL COMMENT '商品类型',
  `price` float(11,2) DEFAULT NULL COMMENT '商品价格',
  `priceoriginal` float(11,2) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL COMMENT '图集',
  `html_url` varchar(255) DEFAULT NULL COMMENT '商品详情网页',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  `shop_id` int(11) DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `recommend` varchar(255) DEFAULT NULL COMMENT '推荐信息',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `brand` varchar(255) DEFAULT NULL COMMENT '品牌',
  `age` float(11,1) DEFAULT NULL COMMENT '适宜年龄',
  `bodytype` varchar(255) DEFAULT NULL COMMENT '体型大小',
  `keli` varchar(255) DEFAULT NULL COMMENT '颗粒大小',
  `weight` varchar(255) DEFAULT NULL COMMENT '重量',
  `formula` varchar(255) DEFAULT NULL COMMENT '配方',
  `salestate` int(11) DEFAULT '38' COMMENT '38、在售；\r\n39、售罄；\r\n40、下架；',
  `sourcefrom` int(11) DEFAULT NULL COMMENT '进口，国产',
  `classification` int(11) DEFAULT NULL COMMENT '二级分类（食物即为干粮，湿粮）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of petproductfood
-- ----------------------------
INSERT INTO `petproductfood` VALUES ('1', '天然牛肉金枪鱼全犬粮', '金枪鱼富含不饱和脂肪酸 调理狗狗肠胃 缓解泪痕', '1,10,12', '129.00', '171.00', '0aec5ab600d801cb579e6fd25eafe8b1.jpg,eeab7fc225403ed5a086688447c9a996.jpg,a7c9690fbcccf4e57e53d1ee246a0d89.jpg,ea16e521a61baede20f74c482b6cf143.jpg,d451451fd024902ba88d8dd8b49e8caa.jpg', 'https://wap.epet.com/goods.html?do=detailNew&gid=117849&fw=0', null, null, '囧囧汪', '1', '2018-02-24 17:39:04', '蓝氏LegendSandy', '34.0', '45', '36', '1磅', '鱼肉、牛肉', '38', null, null);
INSERT INTO `petproductfood` VALUES ('2', '鸭肉&梨配方', '清凉配方 清火气 助消化 营养好吸收', '1,10,12', '179.00', '215.00', 'a0c83bf73be7132655275370e7bdde89.jpg,b1827e37e4ddc4c0e4959c8f817ae9fc.jpg,5ccdfd7bb26abe19b493a1884b77db59.jpg,bdcabd53149699f9f562116ef88520c3.jpg', 'https://wap.epet.com/goods.html?do=detailNew&gid=163645', null, null, '囧囧汪', '1', '2018-02-24 17:39:05', '伯纳天纯', '34.0', '45', '35', '2kg', '鸭肉', '38', null, null);
INSERT INTO `petproductfood` VALUES ('3', '牛肉肝蔬菜营养成犬粮', '荤素搭配 补充能量 活力每天', '1,10,12', '90.00', '100.00', '409294368f9dc7fd92cc8d08b4dcf49c.jpg,4ecea60685e61d57afb1ac88f87826ea.jpg', 'https://wap.epet.com/goods.html?do=detailNew&gid=126410&fw=0', null, null, '囧囧汪', '1', '2018-02-24 17:39:07', '雷米高', '32.0', '45', '36', '5kg', '鸡肉、牛肉', '38', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petservice
-- ----------------------------
INSERT INTO `petservice` VALUES ('1', '8', '洗浴', '美容', '20180205144804-xiyu.jpg', null, '20180205144804-xiyu.jpg');
INSERT INTO `petservice` VALUES ('2', '8', '修甲', '美容', '20180205144805-xiujia.jpg', null, '20180205144805-xiujia.jpg');
INSERT INTO `petservice` VALUES ('3', '8', '造型', '美容', '20180205144806-zaoxing.jpg', null, '20180205144806-zaoxing.jpg');
INSERT INTO `petservice` VALUES ('4', '27', '观景洋房', '寄养', 'pet_jy_1.jpg', null, 'pet_jy_1.jpg');
INSERT INTO `petservice` VALUES ('5', '27', '商务套房', '寄养', 'pet_jy_6.jpg', null, 'pet_jy_6.jpg');
INSERT INTO `petservice` VALUES ('6', '27', '豪华套房', '寄养', 'pet_jy_2.jpg', null, 'pet_jy_2.jpg');
INSERT INTO `petservice` VALUES ('7', '27', '豪华套房', '寄养', 'pet_jy_3.jpg', null, 'pet_jy_3.jpg');
INSERT INTO `petservice` VALUES ('8', '27', '豪华套房', '寄养', 'pet_jy_4.jpg', null, 'pet_jy_4.jpg');
INSERT INTO `petservice` VALUES ('9', '27', '普通套房', '寄养', 'pet_jy_5.jpg', null, 'pet_jy_5.jpg');

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
