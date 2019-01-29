# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.24)
# Database: neuq_info
# Generation Time: 2019-01-29 06:09:07 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `post_id` bigint(20) NOT NULL COMMENT '文章id',
  `avatar_url` varchar(200) NOT NULL DEFAULT '' COMMENT '头像url',
  `user_id` bigint(20) NOT NULL COMMENT '创建者id',
  `like_count` int(4) NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_like_count` (`like_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';



# Dump of table dict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dict`;

CREATE TABLE `dict` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  `remark` varchar(11) DEFAULT NULL,
  `p_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table gpa
# ------------------------------------------------------------

DROP TABLE IF EXISTS `gpa`;

CREATE TABLE `gpa` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_Id` bigint(11) DEFAULT NULL,
  `course_name` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `course_id` varchar(50) DEFAULT NULL COMMENT '课程id',
  `score` varchar(11) DEFAULT NULL COMMENT '分数',
  `gpa` float(2,1) DEFAULT NULL COMMENT '绩点',
  `credit` float(2,1) DEFAULT NULL COMMENT '学分',
  `class_id` varchar(50) DEFAULT NULL COMMENT '班级ID',
  `student_id` varchar(20) DEFAULT NULL,
  `semester` varchar(20) DEFAULT NULL COMMENT '学期',
  `exam_type` varchar(20) DEFAULT NULL COMMENT '成绩类型',
  `is_exam_invalid` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `uk_userId_courseId_examType` (`user_Id`,`course_id`,`exam_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `user_id` bigint(20) NOT NULL COMMENT '创建者id',
  `title` varchar(30) NOT NULL COMMENT '文章标题',
  `content` varchar(120) NOT NULL COMMENT '文章内容',
  `secret` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否匿名 0：匿名 1：非匿名',
  `comment_count` int(4) NOT NULL DEFAULT '0' COMMENT '评论数量',
  `like_count` int(4) NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_like_count` (`like_count`),
  KEY `idx_comment_count` (`comment_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `open_id` varchar(100) NOT NULL DEFAULT '' COMMENT 'openId',
  `union_id` varchar(100) DEFAULT '' COMMENT 'unionId',
  `avatar_url` varchar(200) DEFAULT '' COMMENT '头像url',
  `nick_name` varchar(50) DEFAULT '' COMMENT '用户昵称',
  `gender` int(4) DEFAULT NULL COMMENT '用户性别 性别 0：未知、1：男、2：女',
  `city` varchar(50) DEFAULT '' COMMENT '用户城市',
  `province` varchar(50) DEFAULT '' COMMENT '用户省份',
  `country` varchar(50) DEFAULT '' COMMENT '用户国家',
  `status` int(11) DEFAULT NULL COMMENT '用户状态',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



# Dump of table user_jw_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_jw_info`;

CREATE TABLE `user_jw_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL COMMENT '班级号',
  `student_id` varchar(11) DEFAULT NULL COMMENT '学号',
  `student_name` varchar(11) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL COMMENT '年级：如2015级',
  `profession` varchar(50) DEFAULT NULL COMMENT '专业',
  `college` varchar(20) DEFAULT NULL COMMENT '学院',
  `user_type` varchar(11) DEFAULT NULL COMMENT '用户类型',
  `weight_ average_gpa` float(6,4) DEFAULT NULL COMMENT '加权平均绩点',
  `jw_username` varchar(50) DEFAULT NULL,
  `jw_password` varchar(50) DEFAULT NULL,
  `is_jw_auth` int(11) DEFAULT NULL COMMENT '是否教务认证',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_like_post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_like_post`;

CREATE TABLE `user_like_post` (
  `post_id` bigint(20) NOT NULL COMMENT '文章id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`post_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
