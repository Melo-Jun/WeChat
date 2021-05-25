/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : wechat

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 26/05/2021 02:21:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '好友间私聊' COMMENT '聊天名称',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群号/聊天号',
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '群组.png' COMMENT '群头像',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'friend' COMMENT '聊天类型',
  `master` int(0) NULL DEFAULT NULL COMMENT '群主id',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `master`(`master`) USING BTREE,
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`master`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (12, '好友间私聊', '35e6445869', '群组.png', 'friend', NULL, '2021-05-25 22:37:44', '2021-05-25 22:37:44');
INSERT INTO `chat` VALUES (13, '吹水小组', 'e29f322373', '群组.png', 'group', 0, '2021-05-26 00:01:32', '2021-05-26 00:01:32');

-- ----------------------------
-- Table structure for emoji
-- ----------------------------
DROP TABLE IF EXISTS `emoji`;
CREATE TABLE `emoji`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表情包路径',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `emoji_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emoji
-- ----------------------------
INSERT INTO `emoji` VALUES (1, 0, '070d0794-fead-4387-a269-0249cefb3dfb.png', '2021-05-23 21:48:08', '2021-05-23 21:48:08');
INSERT INTO `emoji` VALUES (2, 0, '070d0794-fead-4387-a269-0249cefb3dfb.png', '2021-05-23 21:48:08', '2021-05-23 21:48:08');
INSERT INTO `emoji` VALUES (5, 0, '8ccce7f7-bc87-408d-b91e-becbbd1ea747.png', '2021-05-23 22:03:53', '2021-05-23 22:03:53');
INSERT INTO `emoji` VALUES (7, 0, 'e0ed55e0-93d5-4af7-917b-1870d53566f3.png', '2021-05-23 23:34:14', '2021-05-23 23:34:14');
INSERT INTO `emoji` VALUES (8, 0, '3110a691-69c1-46d9-a54b-220b2b7b8829.png', '2021-05-24 00:32:19', '2021-05-24 00:32:19');
INSERT INTO `emoji` VALUES (9, 0, 'fc2607d3-b46b-4694-b91c-7a92c3fe3805.gif', '2021-05-24 09:01:51', '2021-05-24 09:01:51');
INSERT INTO `emoji` VALUES (10, 0, '583ebb64-7d84-41a1-935b-69c3ca859647.gif', '2021-05-24 09:30:34', '2021-05-24 09:30:34');
INSERT INTO `emoji` VALUES (16, 0, '9288c539-8a33-4525-9fc3-b2bbd3b1f406.png', '2021-05-26 00:17:56', '2021-05-26 00:17:56');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表情包路径',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '本人id',
  `friend_id` int(0) NULL DEFAULT NULL COMMENT '好友id',
  `chat_id` int(0) NULL DEFAULT NULL COMMENT '好友会话id',
  `alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未设置' COMMENT '好友备注',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未设置' COMMENT '好友描述',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '好友头像',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '在线状态',
  `is_block` int(0) NULL DEFAULT 0 COMMENT '拉黑状态',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `friend_id`(`friend_id`) USING BTREE,
  INDEX `chat_id`(`chat_id`) USING BTREE,
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_ibfk_3` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (21, 0, 38, 12, '小号', '未设置', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', NULL, 0, '2021-05-25 22:37:44', '2021-05-25 22:54:26');
INSERT INTO `friend` VALUES (22, 38, 0, 12, '微信团队', '未设置', '9ebc8732-608b-44c1-b177-a308a213e65d.png', NULL, 0, '2021-05-25 22:37:44', '2021-05-26 00:22:26');

-- ----------------------------
-- Table structure for like_list
-- ----------------------------
DROP TABLE IF EXISTS `like_list`;
CREATE TABLE `like_list`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `moment_id` int(0) NULL DEFAULT NULL COMMENT '朋友圈id',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `moment_id`(`moment_id`) USING BTREE,
  CONSTRAINT `like_list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_list_ibfk_2` FOREIGN KEY (`moment_id`) REFERENCES `moment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of like_list
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'text' COMMENT '消息类型',
  `sender_id` int(0) NULL DEFAULT NULL COMMENT '发送者id',
  `chat_id` int(0) NULL DEFAULT NULL COMMENT '所在聊天',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `chat_id`(`chat_id`) USING BTREE,
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (68, '1', 'text', 0, 12, '2021-05-25 22:38:06', '2021-05-25 22:38:06');
INSERT INTO `message` VALUES (69, '<img class=\"emoji\" src=\"upload/emoji/fc2607d3-b46b-4694-b91c-7a92c3fe3805.gif\" alt=\"\">', 'photo', 0, 12, '2021-05-26 00:17:42', '2021-05-26 00:17:42');
INSERT INTO `message` VALUES (70, '<img class=\"emoji\" src=\"upload/emoji/9288c539-8a33-4525-9fc3-b2bbd3b1f406.png\" alt=\"\">', 'photo', 0, 12, '2021-05-26 00:17:58', '2021-05-26 00:17:58');
INSERT INTO `message` VALUES (71, '2', 'text', 0, 12, '2021-05-26 02:21:25', '2021-05-26 02:21:25');
INSERT INTO `message` VALUES (72, '<img class=\"emoji\" src=\"upload/emoji/8ccce7f7-bc87-408d-b91e-becbbd1ea747.png\" alt=\"\">', 'photo', 0, 12, '2021-05-26 02:21:29', '2021-05-26 02:21:29');

-- ----------------------------
-- Table structure for moment
-- ----------------------------
DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '发布者id',
  `like_count` int(0) NULL DEFAULT 0 COMMENT '点赞数',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `moment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moment
-- ----------------------------
INSERT INTO `moment` VALUES (18, '<div style=\"text-align: center;\"><b>好爽阿</b>,不<strike>错不错不错不错,</strike><u>但是<i>好像<font color=\"#00b050\">没意思</font></i></u><img src=\"http://img.baidu.com/hi/jx2/j_0004.gif\" style=\"max-width: 100%; max-height: 100%;\"></div>', 'a111d921-5aa1-4027-a139-391b8a4ff9e4.png', 0, 0, '2021-05-23 16:31:51', '2021-05-26 02:15:59');
INSERT INTO `moment` VALUES (24, '<img src=\"http://img.baidu.com/hi/jx2/j_0019.gif\">不错        ', '', 38, 0, '2021-05-25 22:44:00', '2021-05-26 02:19:55');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知内容',
  `sender_id` int(0) NULL DEFAULT NULL COMMENT '发送者',
  `receiver_id` int(0) NULL DEFAULT NULL COMMENT '接受者',
  `chat_id` int(0) NULL DEFAULT NULL COMMENT '聊天会话id',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `status` int(0) NULL DEFAULT 0 COMMENT '已读未读',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `chat_id`(`chat_id`) USING BTREE,
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (2, '我是小号', 36, 37, NULL, 'friendNotice', 1, '2021-05-25 21:37:37', '2021-05-25 21:37:45');

-- ----------------------------
-- Table structure for remark
-- ----------------------------
DROP TABLE IF EXISTS `remark`;
CREATE TABLE `remark`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '评论者id',
  `moment_id` int(0) NULL DEFAULT NULL COMMENT '朋友圈id',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `moment_id`(`moment_id`) USING BTREE,
  CONSTRAINT `remark_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `remark_ibfk_2` FOREIGN KEY (`moment_id`) REFERENCES `moment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of remark
-- ----------------------------
INSERT INTO `remark` VALUES (29, '还不错', 0, 24, '2021-05-25 22:45:00', '2021-05-25 22:45:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `wechat_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未设置' COMMENT '微信号',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '游客' COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'b03288cd-90fd-42f1-bd8a-f2543e737350.png' COMMENT '用户头像',
  `validity` int(0) NULL DEFAULT 1 COMMENT '账户有效性',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'user' COMMENT '身份',
  `status` int(0) NULL DEFAULT 0 COMMENT '在线状态',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0, 'admin@qq.com', '1', '微信团队', 'xMpCOKC5I4INzFCab3WEmw==', '9ebc8732-608b-44c1-b177-a308a213e65d.png', 1, 'admin', 0, '2021-05-23 09:11:47', '2021-05-25 23:56:52');
INSERT INTO `user` VALUES (38, '3509442374@qq.com', '1', '小号', 'xMpCOKC5I4INzFCab3WEmw==', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', 1, 'user', 0, '2021-05-25 22:37:44', '2021-05-25 22:43:33');

-- ----------------------------
-- Table structure for user_chat
-- ----------------------------
DROP TABLE IF EXISTS `user_chat`;
CREATE TABLE `user_chat`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `chat_id` int(0) NULL DEFAULT NULL COMMENT '聊天id',
  `member_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未设置' COMMENT '群成员名称',
  `member_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群成员头像',
  `role` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '普通成员' COMMENT '所属身份',
  `is_block` int(0) NULL DEFAULT 0 COMMENT '禁言状态',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'friend' COMMENT '聊天类型',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `chat_id`(`chat_id`) USING BTREE,
  CONSTRAINT `user_chat_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_chat_ibfk_2` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_chat
-- ----------------------------
INSERT INTO `user_chat` VALUES (11, 38, 12, '小号', '9ebc8732-608b-44c1-b177-a308a213e65d.png', '普通成员', 0, 'friend', '2021-05-25 22:37:44', '2021-05-25 23:56:57');
INSERT INTO `user_chat` VALUES (12, 0, 12, '微信团队', NULL, '普通成员', 0, 'friend', '2021-05-25 22:37:44', '2021-05-25 22:38:08');
INSERT INTO `user_chat` VALUES (13, 0, 13, '未设置', NULL, '群主', 0, 'group', '2021-05-26 00:01:32', '2021-05-26 00:01:32');
INSERT INTO `user_chat` VALUES (14, 38, 13, '未设置', NULL, '普通成员', 0, 'group', '2021-05-26 00:01:32', '2021-05-26 00:01:32');

-- ----------------------------
-- Triggers structure for table friend
-- ----------------------------
DROP TRIGGER IF EXISTS `ins_user_chat`;
delimiter ;;
CREATE TRIGGER `ins_user_chat` AFTER UPDATE ON `friend` FOR EACH ROW BEGIN
		IF(new.chat_id IS NOT NULL AND old.chat_id IS NULL)
			THEN
			INSERT INTO user_chat(user_id,chat_id,type)
										values(old.user_id,new.chat_id,'friend');		
		END IF;	
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table friend
-- ----------------------------
DROP TRIGGER IF EXISTS `update_member_name`;
delimiter ;;
CREATE TRIGGER `update_member_name` AFTER UPDATE ON `friend` FOR EACH ROW BEGIN
		IF(new.alias <> old.alias )
			THEN
			update user_chat set member_name = new.alias WHERE user_id= old.friend_id AND chat_id = old.chat_id;
		END IF;	
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
