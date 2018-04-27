CREATE DATABASE  IF NOT EXISTS `ticketdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ticketdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ticketdb
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `ticket_id` int(20) NOT NULL DEFAULT '0',
  `username` varchar(45) NOT NULL DEFAULT '0',
  `time` datetime NOT NULL DEFAULT '1960-01-01 00:00:00',
  `station` varchar(45) NOT NULL DEFAULT '武汉',
  PRIMARY KEY (`ticket_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'admin','2018-04-24 23:06:15','北京'),(4,'czqmike','2018-04-25 23:27:10','武昌');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refund` (
  `ticket_id` int(20) NOT NULL DEFAULT '0',
  `username` varchar(45) NOT NULL DEFAULT '0',
  `time` datetime NOT NULL DEFAULT '1960-01-01 00:00:00',
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticket_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `ticket_id` int(20) NOT NULL AUTO_INCREMENT,
  `train_number` varchar(45) NOT NULL DEFAULT '0' COMMENT '车次号',
  `seat_number` varchar(45) NOT NULL DEFAULT '0' COMMENT '座位号',
  `price` double NOT NULL DEFAULT '0' COMMENT '票价',
  `bought` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticket_id`),
  KEY `train_number_idx` (`train_number`),
  CONSTRAINT `train_number` FOREIGN KEY (`train_number`) REFERENCES `ticket_count` (`train_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'K599','A-1',152.5,1),(2,'K599','A-2',152.5,0),(3,'K599','A-3',152.5,0),(4,'K968','A-1',153.5,1),(5,'K968','A-2',153.5,0),(6,'K968','A-3',153.5,0),(7,'K600','A-1',152.5,0),(8,'K600','A-2',152.5,0),(9,'K600','A-3',152.5,0),(10,'Z36','A-1',1058,0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_count`
--

DROP TABLE IF EXISTS `ticket_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_count` (
  `train_number` varchar(45) NOT NULL DEFAULT '0',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '总票数',
  `refund` int(11) NOT NULL DEFAULT '0' COMMENT '退票数',
  `remain` int(11) NOT NULL DEFAULT '0' COMMENT '剩余票数',
  PRIMARY KEY (`train_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_count`
--

LOCK TABLES `ticket_count` WRITE;
/*!40000 ALTER TABLE `ticket_count` DISABLE KEYS */;
INSERT INTO `ticket_count` VALUES ('K599',3,0,2),('K600',3,0,3),('K968',3,0,2),('Z36',1,0,1);
/*!40000 ALTER TABLE `ticket_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train` (
  `train_number` varchar(45) NOT NULL DEFAULT '‘0’' COMMENT '车次号',
  `start_station` varchar(45) NOT NULL DEFAULT '武汉' COMMENT '始发站',
  `end_station` varchar(45) NOT NULL DEFAULT '武汉' COMMENT '终点站',
  `start_date` date NOT NULL DEFAULT '1960-01-01' COMMENT '发车时间',
  `start_time` time NOT NULL DEFAULT '00:00:00',
  `timecost` int(11) NOT NULL DEFAULT '0' COMMENT '用时（分钟）',
  PRIMARY KEY (`train_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES ('K599','北京','武昌','2018-05-01','05:08:00',1021),('K600','武昌','北京','2018-05-01','03:52:00',1028),('K968','武昌','北京','2018-05-01','00:13:00',1035),('Z36','武昌','北京','2018-05-01','03:14:00',811);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL DEFAULT 'null' COMMENT '用户名',
  `password` varchar(45) NOT NULL DEFAULT 'null',
  `name` varchar(45) NOT NULL DEFAULT 'null' COMMENT '姓名',
  `permission` varchar(20) NOT NULL DEFAULT 'user' COMMENT '用户权限',
  `phonenumber` varchar(45) NOT NULL DEFAULT 'null',
  `citizenID` varchar(45) NOT NULL DEFAULT 'null' COMMENT '身份证号',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','123','陈','admin','null','null'),('czqmike','123','MikeChen','user','1871','4208'),('signup','123','注册','user','1800','4100'),('test','test','测试','user','111','1111');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ticketdb'
--

--
-- Dumping routines for database 'ticketdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-27  9:42:10
