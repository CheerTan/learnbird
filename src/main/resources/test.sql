-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: testdatabase
-- ------------------------------------------------------
-- Server version	5.7.21-1

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
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `Sno` char(9) NOT NULL,
  `Sname` char(20) DEFAULT NULL,
  `Ssex` char(2) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  UNIQUE KEY `Sname` (`Sname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES ('002','Mike','M'),('003','Jane','F');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PERMISSION`
--

DROP TABLE IF EXISTS `T_PERMISSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PERMISSION` (
  `ID` int(10) NOT NULL,
  `URL` varchar(256) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PERMISSION`
--

LOCK TABLES `T_PERMISSION` WRITE;
/*!40000 ALTER TABLE `T_PERMISSION` DISABLE KEYS */;
INSERT INTO `T_PERMISSION` VALUES (1,'/user','user:user'),(2,'/user/add','user:add'),(3,'/user/delete','user:delete');
/*!40000 ALTER TABLE `T_PERMISSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ROLE`
--

DROP TABLE IF EXISTS `T_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ROLE` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL,
  `MEMO` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ROLE`
--

LOCK TABLES `T_ROLE` WRITE;
/*!40000 ALTER TABLE `T_ROLE` DISABLE KEYS */;
INSERT INTO `T_ROLE` VALUES (1,'admin','superadmin'),(2,'mrbird','MRBIRD');
/*!40000 ALTER TABLE `T_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ROLE_PERMISSION`
--

DROP TABLE IF EXISTS `T_ROLE_PERMISSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ROLE_PERMISSION` (
  `RID` int(10) DEFAULT NULL,
  `PID` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ROLE_PERMISSION`
--

LOCK TABLES `T_ROLE_PERMISSION` WRITE;
/*!40000 ALTER TABLE `T_ROLE_PERMISSION` DISABLE KEYS */;
INSERT INTO `T_ROLE_PERMISSION` VALUES (1,2),(1,3),(2,1),(1,1),(2,2);
/*!40000 ALTER TABLE `T_ROLE_PERMISSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_USER`
--

DROP TABLE IF EXISTS `T_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_USER` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWD` varchar(128) NOT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `STATUS` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_USER`
--

LOCK TABLES `T_USER` WRITE;
/*!40000 ALTER TABLE `T_USER` DISABLE KEYS */;
INSERT INTO `T_USER` VALUES (1,'admin','e22c0bdd27a023d68737452a4ab937ba','2017-11-19','1'),(2,'mrbird','b7a66ac479d6c4025635a8b1e29bffc0','2017-12-11','1');
/*!40000 ALTER TABLE `T_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_USER_ROLE`
--

DROP TABLE IF EXISTS `T_USER_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_USER_ROLE` (
  `USER_ID` int(10) DEFAULT NULL,
  `RID` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_USER_ROLE`
--

LOCK TABLES `T_USER_ROLE` WRITE;
/*!40000 ALTER TABLE `T_USER_ROLE` DISABLE KEYS */;
INSERT INTO `T_USER_ROLE` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `T_USER_ROLE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-03 19:55:34
