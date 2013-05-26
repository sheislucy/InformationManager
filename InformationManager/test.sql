-- MySQL dump 10.13  Distrib 5.6.10, for Win32 (x86)
--
-- Host: localhost    Database: test_schema
-- ------------------------------------------------------
-- Server version	5.6.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `pid` int(11) NOT NULL,
  `psortId` int(5) DEFAULT NULL COMMENT '分类',
  `idcard` varchar(50) DEFAULT NULL COMMENT '身份证',
  `lname` varchar(50) DEFAULT NULL COMMENT '全名',
  `nname` varchar(50) DEFAULT NULL COMMENT '小名',
  `gender` bit(1) DEFAULT NULL,
  `education` varchar(50) DEFAULT NULL COMMENT '教育程度',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `comeTime` datetime DEFAULT NULL COMMENT '来本地时间',
  `job` varchar(50) DEFAULT NULL COMMENT '工作',
  `cellphone` varchar(50) DEFAULT NULL COMMENT '手机',
  `phone` varchar(50) DEFAULT NULL COMMENT '固定电话',
  `workPlace` varchar(50) DEFAULT NULL COMMENT '工作地点',
  `physicalCharact` varchar(255) DEFAULT NULL COMMENT '体貌特征',
  `incomeSource` varchar(255) DEFAULT NULL COMMENT '收入来源',
  `psocialId` int(5) DEFAULT '0' COMMENT '社会身份',
  `relation` varchar(50) DEFAULT NULL COMMENT '和户主的关系',
  `skill` varchar(50) DEFAULT NULL COMMENT '技能',
  `photo` varchar(100) DEFAULT NULL COMMENT '照片地址',
  `countryId` varchar(50) DEFAULT NULL COMMENT '所属镇ID',
  `village` varchar(50) DEFAULT NULL COMMENT '所属村ID',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '最后修改时间',
  `hostId` int(11) DEFAULT NULL COMMENT '户主ID',
  `isShow` bit(1) DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`pid`),
  KEY `fk_hostId_pid_idx` (`hostId`),
  KEY `fk_psortId_sortId` (`psortId`),
  KEY `fk_psocialId_socialId` (`psocialId`),
  CONSTRAINT `fk_hostId_pid` FOREIGN KEY (`hostId`) REFERENCES `person` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_psocialId_socialId` FOREIGN KEY (`psocialId`) REFERENCES `social_identity` (`socialId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_psortId_sortId` FOREIGN KEY (`psortId`) REFERENCES `person_sort` (`sortId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (224,0,'330821111122331000','林恭裕',NULL,'','初中','上方镇煤山底村3号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',224,''),(225,1,'330821111122331000','林宽锡',NULL,'','小学','上方镇煤山底村2号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,1,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',225,''),(226,1,'330821111122331000','宋信木',NULL,'','初中','上方镇煤山底村55号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,1,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',226,''),(227,0,'330821111122331000','舒炎标',NULL,'','初中','上方镇煤山底村3号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',227,''),(229,0,'330821111122331000','林恭木',NULL,'','未受过教育','上方镇煤山底村18号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',229,''),(231,0,'330821111122331000','林恭平',NULL,'','初中','上方镇煤山底村19号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',231,''),(235,0,'330821111122331000','林晓彩',NULL,'','初中','上方镇煤山底村19号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',235,''),(236,0,'330821111122331000','吴国花',NULL,'','初中','上方镇煤山底村19号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'其他',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',235,''),(237,0,'330821111122331000','徐锦福',NULL,'','初中','上方镇煤山底村1号',NULL,'经商(衢州至十里丰公交线路)',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',237,''),(238,0,'330821111122331000','林恭定',NULL,'','初中','上方镇煤山底村21号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',238,''),(239,0,'330821111122331000','林宽银',NULL,'','初中','上方镇煤山底村16号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',239,''),(240,0,'330821111122331000','林恭彪',NULL,'','初中','上方镇煤山底村12号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',240,''),(243,0,'330821111122331000','林恭全',NULL,'','初中','上方镇煤山底村3号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',243,''),(245,0,'330821111122331000','林宽飞',NULL,'','初中','上方镇煤山底村3号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',245,''),(246,0,'330821111122331000','林恭满',NULL,'','初中','上方镇煤山底村4号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',246,''),(248,0,'330821111122331000','吴利英',NULL,'','初中','上方镇煤山底村6号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',248,''),(249,0,'330821111122331000','林恭立',NULL,'','初中','上方镇煤山底村7号',NULL,'经营石灰窑',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',249,''),(250,0,'330821111122331000','林恭虎',NULL,'','初中','上方镇煤山底村11号',NULL,'杭州开饭店',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',250,''),(266,0,'330821111122331000','舒境焕',NULL,'','小学','浙江省衢州市衢江区上方镇煤山底村９１号',NULL,'',NULL,'2910929',NULL,NULL,NULL,0,'子',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',227,''),(267,0,'330821111122331000','查永珍',NULL,'','初中','浙江省衢州市衢江区上方镇煤山底村９１号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'妻',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',227,''),(268,0,'330821111122331000','付爱英',NULL,'','初中','浙江省衢州市衢江区上方镇煤山底村１８号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',229,''),(269,0,'330821111122331000','林宽新',NULL,'','高中','浙江省衢州市衢江区上方镇煤山底村１８号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'子',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',229,''),(270,0,'330821111122331000','邱月莲',NULL,'','高中','浙江省衢州市衢江区上方镇煤山底村１８号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'儿媳',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',229,''),(271,0,'330821111122331000','林信凯',NULL,'','初中','浙江省衢州市衢江区上方镇煤山底村１８号',NULL,'读书',NULL,'2910929',NULL,NULL,NULL,0,'孙子',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',229,''),(273,0,'330821111122331000','汪贤华',NULL,'','初中','浙江省衢州市衢江区上方镇煤山底村６６号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'本人',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',273,''),(274,0,'330821111122331000','林翠连',NULL,'','初中','浙江省衢州市衢江区上方镇煤山底村６６号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'妻',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',273,''),(275,0,'330821111122331000','姚金香',NULL,'','未受过教育','浙江省衢州市衢江区上方镇煤山底村６６号',NULL,'务农',NULL,'2910929',NULL,NULL,NULL,0,'母',NULL,'/1.JPG','1','上方镇','2010-04-27 11:02:00','2010-04-27 11:02:00',273,'');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_sort`
--

DROP TABLE IF EXISTS `person_sort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_sort` (
  `sortId` int(5) NOT NULL COMMENT '人员分类ID',
  `sortText` varchar(50) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`sortId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_sort`
--

LOCK TABLES `person_sort` WRITE;
/*!40000 ALTER TABLE `person_sort` DISABLE KEYS */;
INSERT INTO `person_sort` VALUES (0,'常住人口'),(1,'暂住人口');
/*!40000 ALTER TABLE `person_sort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_identity`
--

DROP TABLE IF EXISTS `social_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_identity` (
  `socialId` int(5) NOT NULL COMMENT '社会身份ID',
  `socialText` varchar(50) DEFAULT NULL COMMENT '社会身份名称',
  PRIMARY KEY (`socialId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社会身份表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_identity`
--

LOCK TABLES `social_identity` WRITE;
/*!40000 ALTER TABLE `social_identity` DISABLE KEYS */;
INSERT INTO `social_identity` VALUES (0,'群众'),(1,'党员');
/*!40000 ALTER TABLE `social_identity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-26 21:33:43
