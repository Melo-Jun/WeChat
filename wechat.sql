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

 Date: 26/05/2021 14:21:19
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
INSERT INTO `chat` VALUES (17, '好友间私聊', '41d3552088', '群组.png', 'friend', NULL, '2021-05-26 09:21:55', '2021-05-26 09:21:55');
INSERT INTO `chat` VALUES (33, '好友间私聊', '81d0589144', '群组.png', 'friend', NULL, '2021-05-26 11:25:58', '2021-05-26 11:25:58');
INSERT INTO `chat` VALUES (34, '好友间私聊', '5d74313116', '群组.png', 'friend', NULL, '2021-05-26 11:28:31', '2021-05-26 11:28:31');
INSERT INTO `chat` VALUES (35, '不凑字', 'a945582383', 'ba187f37-1526-4c41-b082-43202eb182b8.png', 'group', 41, '2021-05-26 11:32:58', '2021-05-26 11:33:36');
INSERT INTO `chat` VALUES (36, '好友间私聊', 'e928457322', '群组.png', 'friend', NULL, '2021-05-26 11:34:45', '2021-05-26 11:34:45');

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
INSERT INTO `emoji` VALUES (19, 41, '396d272d-8d41-4ab7-8f08-f6f90c63141c.png', '2021-05-26 11:04:53', '2021-05-26 11:04:53');
INSERT INTO `emoji` VALUES (20, 49, '66382417-73c2-4599-aae1-120aff95f128.png', '2021-05-26 11:29:22', '2021-05-26 11:29:22');

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
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (27, 0, 41, 17, '小号', '未设置', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', NULL, 0, '2021-05-26 09:21:55', '2021-05-26 11:11:23');
INSERT INTO `friend` VALUES (28, 41, 0, 17, '微信团队', '未设置', '9ebc8732-608b-44c1-b177-a308a213e65d.png', NULL, 0, '2021-05-26 09:21:55', '2021-05-26 09:22:03');
INSERT INTO `friend` VALUES (49, 0, 49, 33, 'melo', '未设置', 'e5c881eb-8464-439c-8faf-e53e543b46bc.png', NULL, 0, '2021-05-26 11:25:58', '2021-05-26 11:35:35');
INSERT INTO `friend` VALUES (50, 49, 0, 33, '微信团队', '未设置', '9ebc8732-608b-44c1-b177-a308a213e65d.png', NULL, 0, '2021-05-26 11:25:58', '2021-05-26 11:27:24');
INSERT INTO `friend` VALUES (51, 49, 41, 34, '工具人', '....', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', NULL, 0, '2021-05-26 11:28:10', '2021-05-26 11:28:43');
INSERT INTO `friend` VALUES (52, 41, 49, 34, '大号', '....', 'e5c881eb-8464-439c-8faf-e53e543b46bc.png', NULL, 1, '2021-05-26 11:28:31', '2021-05-26 11:30:36');
INSERT INTO `friend` VALUES (53, 50, 41, 36, '小号', '....', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', NULL, 0, '2021-05-26 11:34:39', '2021-05-26 11:35:03');
INSERT INTO `friend` VALUES (54, 41, 50, 36, '游客', '....', '游客.png', NULL, 0, '2021-05-26 11:34:45', '2021-05-26 11:34:47');

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
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `message` VALUES (158, '不错', 'text', 41, 34, '2021-05-26 11:28:48', '2021-05-26 11:28:48');
INSERT INTO `message` VALUES (159, '<img class=\"emoji\" src=\"upload/emoji/66382417-73c2-4599-aae1-120aff95f128.png\" alt=\"\">', 'photo', 49, 34, '2021-05-26 11:29:24', '2021-05-26 11:29:24');
INSERT INTO `message` VALUES (160, '<img class=\"emoji\" src=\"upload/emoji/66382417-73c2-4599-aae1-120aff95f128.png\" alt=\"\">', 'photo', 49, 34, '2021-05-26 11:29:27', '2021-05-26 11:29:27');
INSERT INTO `message` VALUES (161, '<img class=\"emoji\" src=\"upload/emoji/396d272d-8d41-4ab7-8f08-f6f90c63141c.png\" alt=\"\">', 'photo', 41, 34, '2021-05-26 11:29:33', '2021-05-26 11:29:33');
INSERT INTO `message` VALUES (162, '<a href=\"download?method=downloadFile&&path=upload/file/34/&&fileName=9d19cbf6-b995-4d38-af7e-c08519fc0b2e.xmind\"  class=\"emoji\" >文件名:<br> 不错</a>', 'file', 41, 34, '2021-05-26 11:29:57', '2021-05-26 11:29:57');
INSERT INTO `message` VALUES (163, '1', 'text', 49, 33, '2021-05-26 11:42:03', '2021-05-26 13:58:38');
INSERT INTO `message` VALUES (164, '2', 'text', 49, 33, '2021-05-26 11:42:03', '2021-05-26 13:58:39');
INSERT INTO `message` VALUES (165, '3', 'text', 49, 33, '2021-05-26 13:53:59', '2021-05-26 13:58:40');
INSERT INTO `message` VALUES (166, '4', 'text', 49, 33, '2021-05-26 13:54:04', '2021-05-26 13:58:41');
INSERT INTO `message` VALUES (167, '5', 'text', 49, 33, '2021-05-26 13:54:08', '2021-05-26 13:58:43');
INSERT INTO `message` VALUES (168, '6', 'text', 49, 33, '2021-05-26 13:54:20', '2021-05-26 13:58:44');
INSERT INTO `message` VALUES (169, '7', 'text', 49, 33, '2021-05-26 13:54:20', '2021-05-26 13:58:45');
INSERT INTO `message` VALUES (170, '8', 'text', 49, 33, '2021-05-26 13:54:20', '2021-05-26 13:58:46');
INSERT INTO `message` VALUES (171, '9', 'text', 49, 33, '2021-05-26 13:54:48', '2021-05-26 13:58:48');

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
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moment
-- ----------------------------
INSERT INTO `moment` VALUES (18, '<div style=\"text-align: center;\"><b>好爽阿</b>,不<strike>错不错不错不错,</strike><u>但是<i>好像<font color=\"#00b050\">没意思</font></i></u><img src=\"http://img.baidu.com/hi/jx2/j_0004.gif\" style=\"max-width: 100%; max-height: 100%;\"></div>', 'b260d473-cbcc-4332-a2da-17741c50df70.png', 0, 0, '2021-05-23 16:31:51', '2021-05-26 11:21:54');
INSERT INTO `moment` VALUES (27, '<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"><u>不<font color=\"#00b0f0\">错子</font></u>', 'efb3ea08-877a-4ac4-bc1b-8e1fdd3a035a.png', 41, 0, '2021-05-26 11:07:29', '2021-05-26 11:07:29');
INSERT INTO `moment` VALUES (28, '<img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\">啊<font color=\"#9bbb59\">啊<u>啊啊</u></font>', '259f86c0-6cd8-4cf3-9992-645c1dc0381a.png', 41, 0, '2021-05-26 11:32:09', '2021-05-26 11:32:09');

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (11, '....', 49, 41, NULL, 'friendNotice', 1, '2021-05-26 11:28:10', '2021-05-26 11:28:29');
INSERT INTO `notice` VALUES (12, '....', 50, 41, NULL, 'friendNotice', 1, '2021-05-26 11:34:39', '2021-05-26 11:34:44');

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of remark
-- ----------------------------

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
  `avatar` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '游客.png' COMMENT '用户头像',
  `validity` int(0) NULL DEFAULT 1 COMMENT '账户有效性',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'user' COMMENT '身份',
  `status` int(0) NULL DEFAULT 0 COMMENT '在线状态',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0, 'admin@qq.com', '1', '微信团队', 'xMpCOKC5I4INzFCab3WEmw==', '9ebc8732-608b-44c1-b177-a308a213e65d.png', 1, 'admin', 0, '2021-05-23 09:11:47', '2021-05-25 23:56:52');
INSERT INTO `user` VALUES (41, '3509442374@qq.com', '未设置', '小号', 'xMpCOKC5I4INzFCab3WEmw==', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', 1, 'user', 0, '2021-05-26 09:21:55', '2021-05-26 11:24:54');
INSERT INTO `user` VALUES (49, '1158280627@qq.com', 'melo', 'melo', 'tZxnvxlqR1gZHkL3ZnDOug==', 'e5c881eb-8464-439c-8faf-e53e543b46bc.png', 1, 'user', 0, '2021-05-26 11:25:58', '2021-05-26 11:36:32');
INSERT INTO `user` VALUES (50, 'visitor@qq.com', 'cb38184242', '游客', '', '游客.png', 1, 'user', 0, '2021-05-26 11:34:18', '2021-05-26 11:34:18');

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
INSERT INTO `user_chat` VALUES (21, 41, 17, '小号', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', '普通成员', 0, 'friend', '2021-05-26 09:21:55', '2021-05-26 09:54:35');
INSERT INTO `user_chat` VALUES (22, 0, 17, '微信团队', NULL, '普通成员', 0, 'friend', '2021-05-26 09:21:55', '2021-05-26 09:22:03');
INSERT INTO `user_chat` VALUES (57, 49, 33, 'melo', 'e5c881eb-8464-439c-8faf-e53e543b46bc.png', '普通成员', 0, 'friend', '2021-05-26 11:25:58', '2021-05-26 11:42:03');
INSERT INTO `user_chat` VALUES (58, 0, 33, '微信团队', NULL, '普通成员', 0, 'friend', '2021-05-26 11:25:58', '2021-05-26 11:27:24');
INSERT INTO `user_chat` VALUES (59, 41, 34, '小号', 'fa65abc4-cce2-48ae-87df-0558091a2fdb.png', '普通成员', 0, 'friend', '2021-05-26 11:28:31', '2021-05-26 11:28:48');
INSERT INTO `user_chat` VALUES (60, 49, 34, '大号', 'e5c881eb-8464-439c-8faf-e53e543b46bc.png', '普通成员', 0, 'friend', '2021-05-26 11:28:31', '2021-05-26 11:30:30');
INSERT INTO `user_chat` VALUES (61, 41, 35, '未设置', NULL, '群主', 0, 'group', '2021-05-26 11:32:58', '2021-05-26 11:32:58');
INSERT INTO `user_chat` VALUES (62, 49, 35, '未设置', NULL, '普通成员', 0, 'group', '2021-05-26 11:32:58', '2021-05-26 11:33:27');
INSERT INTO `user_chat` VALUES (63, 41, 36, '小号', NULL, '普通成员', 0, 'friend', '2021-05-26 11:34:45', '2021-05-26 11:35:03');
INSERT INTO `user_chat` VALUES (64, 50, 36, '游客', NULL, '普通成员', 0, 'friend', '2021-05-26 11:34:45', '2021-05-26 11:34:47');

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
