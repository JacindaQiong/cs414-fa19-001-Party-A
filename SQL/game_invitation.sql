/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mysql

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/12/2019 22:11:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game_invitation
-- ----------------------------
DROP TABLE IF EXISTS `game_invitation`;
CREATE TABLE `game_invitation` (
  `game_inviter` varchar(255) DEFAULT NULL,
  `game_invitee` varchar(255) DEFAULT NULL,
  `invitation_time` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
