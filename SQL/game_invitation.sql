/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.2_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : partya

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-12-07 20:50:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for game_invitation
-- ----------------------------
DROP TABLE IF EXISTS `game_invitation`;
CREATE TABLE `game_invitation` (
  `game_inviter` varchar(255) DEFAULT NULL,
  `game_invitee` varchar(255) DEFAULT NULL,
  `invitation_time` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_invitation
-- ----------------------------
INSERT INTO `game_invitation` VALUES ('123', '222', '2019-11-20 01:22:30');
INSERT INTO `game_invitation` VALUES ('1', '2', '3');
INSERT INTO `game_invitation` VALUES ('123', '123', '123');
INSERT INTO `game_invitation` VALUES ('eee', '11', '11');
INSERT INTO `game_invitation` VALUES ('', '', '');
