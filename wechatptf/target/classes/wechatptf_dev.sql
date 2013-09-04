# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: wechatptf_dev
# ------------------------------------------------------
# Server version 5.1.52-community

#
# Source for table company
#

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(40) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `address` text NOT NULL COMMENT '地址',
  `weiid` varchar(32) NOT NULL DEFAULT '' COMMENT '微信号',
  `director` varchar(255) NOT NULL DEFAULT '' COMMENT '负责人',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话',
  `description` text NOT NULL COMMENT '简介',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业';

#
# Dumping data for table company
#

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table member
#

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weiid` varchar(32) NOT NULL DEFAULT '' COMMENT '微信号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `gender` int(2) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `address` text COMMENT '住址',
  `mail` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `points` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='会员';

#
# Dumping data for table member
#

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table member_company
#

DROP TABLE IF EXISTS `member_company`;
CREATE TABLE `member_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) NOT NULL DEFAULT '0' COMMENT '会员id',
  `weiid` varchar(32) NOT NULL DEFAULT '' COMMENT '微信号',
  `companyid` int(11) NOT NULL DEFAULT '0' COMMENT '商家id',
  `points` int(11) NOT NULL DEFAULT '0' COMMENT '会员在该商家的积分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `weiid_companyid` (`weiid`,`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='会员商家对应关系';

#
# Dumping data for table member_company
#

LOCK TABLES `member_company` WRITE;
/*!40000 ALTER TABLE `member_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_company` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table product
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) NOT NULL DEFAULT '0' COMMENT '所属商家',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `price` double NOT NULL DEFAULT '0' COMMENT '价格',
  `description` text NOT NULL COMMENT '简介',
  `cover` varchar(255) DEFAULT '' COMMENT '封面图片存放路径',
  `start_date` date NOT NULL DEFAULT '0000-00-00' COMMENT '开始日期',
  `end_date` date NOT NULL DEFAULT '0000-00-00' COMMENT '结束日期',
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品';

#
# Dumping data for table product
#

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table product_picture
#

DROP TABLE IF EXISTS `product_picture`;
CREATE TABLE `product_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` int(11) NOT NULL DEFAULT '0' COMMENT '商品id',
  `picture` varchar(255) NOT NULL DEFAULT '' COMMENT '图片存放路径',
  PRIMARY KEY (`id`),
  KEY `productid` (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品拥有的图片';

#
# Dumping data for table product_picture
#

LOCK TABLES `product_picture` WRITE;
/*!40000 ALTER TABLE `product_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_picture` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table product_type
#

DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` int(11) NOT NULL DEFAULT '0' COMMENT '商品id',
  `typeid` int(11) NOT NULL DEFAULT '0' COMMENT '类型id',
  PRIMARY KEY (`id`),
  KEY `productid` (`productid`),
  KEY `typeid` (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品所属类型';

#
# Dumping data for table product_type
#

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table type
#

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '类型名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品类型';

#
# Dumping data for table type
#

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (4,'Food');
INSERT INTO `type` VALUES (5,'Snack');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
