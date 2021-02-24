/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : petmanage_test

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 29/05/2020 11:09:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `billID` int(11) NOT NULL AUTO_INCREMENT,
  `billTime` timestamp(0) NULL DEFAULT NULL,
  `freshID` int(11) NULL DEFAULT NULL,
  `userID` int(11) NULL DEFAULT NULL,
  `billState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(255) NULL DEFAULT NULL,
  `totalprice` decimal(10, 2) NULL DEFAULT NULL,
  `logistics` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logisticsID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`billID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (9, '2019-05-15 02:29:34', 2, 3, '已退货', '123', '13425409272', '123', NULL, NULL, '已经到达北京，明天派送', '123', '顺丰快递');
INSERT INTO `bill` VALUES (10, '2019-05-22 23:10:46', 2, 3, '已取消', '小陈', '13425409272', '沈阳市xxx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `bill` VALUES (11, '2019-05-22 23:10:46', 3, 3, '已付款', '小陈', '13425409272', '沈阳市xxx', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `carID` int(11) NOT NULL AUTO_INCREMENT,
  `freshID` int(11) NULL DEFAULT NULL,
  `userID` int(11) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`carID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, 1, 1, NULL);
INSERT INTO `car` VALUES (3, 2, 1, NULL);
INSERT INTO `car` VALUES (4, 1, 1, NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentID` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userID` int(11) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `billID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`commentID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '啊', 3, '2019-04-12 00:00:00', NULL);
INSERT INTO `comment` VALUES (2, '很好', 3, '2019-05-15 00:00:00', NULL);
INSERT INTO `comment` VALUES (3, '很好', 3, '2019-05-15 00:52:46', 8);

-- ----------------------------
-- Table structure for fresh
-- ----------------------------
DROP TABLE IF EXISTS `fresh`;
CREATE TABLE `fresh`  (
  `freshID` int(11) NOT NULL AUTO_INCREMENT,
  `freshName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `freshSize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `freshPrice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `freshDetail` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`freshID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fresh
-- ----------------------------
INSERT INTO `fresh` VALUES (2, '哈士奇', '一只', '2000', '西伯利亚雪橇犬（俄语：Сибирский хаски，英语：Siberian husky），常见别名哈士奇，昵称为二哈。西伯利亚雪橇犬体重，雄犬介于20-27公斤，雌犬16-23公斤，身高大约雄犬肩高53-58厘米，雌犬51-56厘米，是一种中型犬。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559140617&di=0e702804db6a8a7be4de94d1dab5a6a4&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.aichong.cn%2Fuploads%2Fallimg%2F131101%2F1609456238-0.jpg', '狗');
INSERT INTO `fresh` VALUES (3, '萨摩耶', '一只', '2000', '萨摩耶犬（英文：Samoyed），别称萨摩耶，原是西伯利亚的原住民萨摩耶族培育出的犬种，一岁前调皮、灵动。它机警、强壮、灵活、美丽、高贵优雅、乖巧可爱，有着非常引人注目的外表，有“微笑天使”的称号，也有着“微笑天使面孔，捣蛋魔鬼内心”之称。萨摩耶犬的颜色为白色；部分带有很浅的浅棕色、奶酪色、浅棕色。此外其他颜色都属于失格。世界上曾出现过一只灰白色萨摩，FCI承认他是具有纯种血统萨摩耶基因的返祖萨摩，黑色萨摩耶犬极为罕见。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558546128159&di=e9b9defbae294071b2e334bf6790e852&imgtype=0&src=http%3A%2F%2Fpic8.58cdn.com.cn%2Fzhuanzh%2Fn_v27159f0b7098b4c93a737a9dd08b6eae1.jpg%3Fw%3D750%26h%3D0', '狗');
INSERT INTO `fresh` VALUES (4, '阿拉斯加犬', '一只', '2000', '阿拉斯加雪橇犬也叫阿拉斯加犬，是最古老的极地雪橇犬之一，它的名字取自爱斯基摩人的伊努伊特族的一个叫做马拉缪特的部落。这个部落生活在阿拉斯加西部一个叫做科策布(Kotzebue)的岸边。成年阿拉斯加犬有着安静、高雅的气质，对主人非常忠心。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558546430674&di=b552600425ff89403975203167fcf3b4&imgtype=0&src=http%3A%2F%2Fimg18.3lian.com%2Fd%2Ffile%2F201710%2F16%2Ff91ab6baab6d8cee325b1d44ef37ea5d.jpg', '狗');
INSERT INTO `fresh` VALUES (20, '美国短毛猫', '一只', '1000', '美国短毛猫，又称美洲短毛虎纹猫，是美国人把欧洲猫与美洲大陆的土种猫加以改良而育成，是家猫中的传统品种。1971年，其被选为美国最好的猫种之一。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558546564717&di=431d00fe979c1ebc6b2b9cf5bf0245c2&imgtype=0&src=http%3A%2F%2Fimg.11665.com%2Fimg01_p%2Fi1%2F19549024606704579%2FT1_Hw8XflbXXXXXXXX_%2521%25210-item_pic.jpg', '猫');
INSERT INTO `fresh` VALUES (21, '折耳猫', '一只', '1000', '苏格兰折耳猫的耳朵竟是整齐地扣在头上，于是很自然地人们在头脑中把它们划到了‘精灵族’的一边，猫中的折耳‘精灵’。据说在1961年，苏格兰一户猎人家里的一只母猫产下了一窝小猫，其中有一只可爱的小猫，它有着白色的皮毛、紧扣着的耳朵和像小精灵一样的脸，因为原产地靠近苏格兰的库泊安格斯，从此便根据出生地及耳朵下折的现象，命名这个品种为“苏格兰折耳猫”。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558546614285&di=68117fbbaf63ceefce702f8bbe41e0d4&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F12%2F20150512091521_UfGJh.thumb.700_0.jpeg', '猫');
INSERT INTO `fresh` VALUES (22, '仓鼠', '一只', '100', '仓鼠亚科（学名：Cricetinae）：在生物分类学上是仓鼠科中的一个亚科。共7属18种，其中中国有有3属8种，通称仓鼠。除分布于中亚地区的小仓鼠外，臼两颊均有颊囊，可将食物暂存口内，搬运到洞内贮藏，故又称腮鼠、搬仓。眼小 ，耳朵被毛，耳壳显露毛外。体长范围从50毫米到340毫米，腿短，脚宽，尾巴粗短。温带陆生动物，在开阔的地区最为常见。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558546742039&di=0dffc54ec4c2fe1be365d6d4a63fa2ac&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201501%2F06%2F20150106221451_QXAAv.thumb.700_0.jpeg', '鼠');

-- ----------------------------
-- Table structure for income
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income`  (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `orderName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderMoney` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderDate` datetime(0) NULL DEFAULT NULL,
  `userID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of income
-- ----------------------------
INSERT INTO `income` VALUES (1, '5月收入', '6000', '哈士奇2只共4000元，萨摩耶一只2000元。', '2020-05-26 22:37:19', '3', '1');
INSERT INTO `income` VALUES (3, '四月收入', '20000', '5只哈士奇一万，5只萨摩耶一万', '2020-05-26 23:09:06', '7', '2');
INSERT INTO `income` VALUES (4, '测试一', '2000', '一只哈士奇', '2020-05-29 10:33:36', NULL, '5');
INSERT INTO `income` VALUES (5, '测试二', '4000', '月底售卖出2只萨摩耶', '2020-05-29 11:07:01', NULL, '5');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`typeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '狗');
INSERT INTO `type` VALUES (2, '猫');
INSERT INTO `type` VALUES (3, '鼠');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, '123', '123', '小明', '1234', '13425404444');
INSERT INTO `user` VALUES (6, '23123123', '11111', '', '', '13455555555');
INSERT INTO `user` VALUES (7, 'abcabc', '123', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
