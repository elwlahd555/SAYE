-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: saye
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `g_u_no` int NOT NULL AUTO_INCREMENT COMMENT 'u_no 연결',
  `g_genre` varchar(3000) NOT NULL COMMENT '장르문자열',
  PRIMARY KEY (`g_u_no`),
  CONSTRAINT `FK_Genre_g_u_no_User_u_no` FOREIGN KEY (`g_u_no`) REFERENCES `user` (`u_no`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `u_no` int NOT NULL AUTO_INCREMENT COMMENT '유저 번호',
  `u_id` varchar(300) NOT NULL COMMENT '유저 아이디(UK)',
  `u_password` varchar(3000) NOT NULL COMMENT '유저 비밀번호',
  `u_nickname` varchar(300) DEFAULT NULL COMMENT '유저 닉네임',
  `u_img` varchar(3000) DEFAULT NULL COMMENT '유저 이미지',
  `u_joindate` timestamp NOT NULL COMMENT '유저 가입일',
  `u_provider` varchar(300) DEFAULT NULL COMMENT '유저 SNS 연동 제공자',
  `u_authority` varchar(300) NOT NULL DEFAULT 'client' COMMENT '유저 권한',
  `u_salt` varchar(3000) DEFAULT NULL COMMENT '유저 고유 salt',
  `u_jwt` varchar(3000) DEFAULT NULL COMMENT '유저 토큰',
  PRIMARY KEY (`u_no`),
  UNIQUE KEY `u_id_UNIQUE` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'saye','5189b7865bbd20a28ceb804a5f18ca43bca0ba1f8b66880e5f6fbe17a5ea392b','곡추남',NULL,'2021-02-10 05:37:00',NULL,'client','a59978f7e2eb35ab',NULL),(2,'abcd','b53b83bcc00ebcafda2feccaa2d30929653e25a1121d7f942384172a11159007','abcd',NULL,'2021-03-09 12:05:30',NULL,'client','afb2a480a0017445',NULL),(3,'aaa','2dd4c16c700a2e60fa00c1263602549fedf8b5cf587860d7a9be998f306e9e0d','aaa',NULL,'2021-03-09 12:08:36',NULL,'client','0da80c4d8ed1ee6a',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'saye'
--

--
-- Dumping routines for database 'saye'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-10 14:29:36
