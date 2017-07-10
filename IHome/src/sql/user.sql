/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : ihome

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-07-10 23:06:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '�û�id',
  `name` varchar(20) DEFAULT NULL COMMENT '�ü���',
  `age` int(4) DEFAULT NULL COMMENT '����',
  `job` varchar(255) DEFAULT NULL COMMENT '����',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ��',
  `weixin` varchar(255) DEFAULT NULL COMMENT '΢�ź�',
  `phone` varchar(11) NOT NULL COMMENT '�ֻ���',
  `address` varchar(255) DEFAULT NULL COMMENT '��ַ',
  `type` varchar(255) NOT NULL COMMENT '�û�����',
  PRIMARY KEY (`id`,`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'me', '22', '�������ʦ', '1990041527', 'veichang', '1509055227', '�й�һ�������ĵط�', '����Ա');
INSERT INTO `user` VALUES ('2', 'me', '22', '�������ʦ', '1990041527', null, '1509055227', '�ܶ��ɣ��ֵܣ�', '��ͨ�û�');
INSERT INTO `user` VALUES ('10', 'zhong', null, 'work', 'qq', 'weixin', '15090552277', '������������', '����Ա');
INSERT INTO `user` VALUES ('12', '��ά��', null, 'RD', null, 'veichang', '15090552277', 'ɽ�����ѧԺ', '����Ա');