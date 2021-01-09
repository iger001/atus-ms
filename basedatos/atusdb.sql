-- MySQL dump 10.13  Distrib 5.6.11, for Win64 (x86_64)
--
-- Host: localhost    Database: atusdb
-- ------------------------------------------------------
-- Server version	5.6.11

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
-- Table structure for table `atus_rol`
--

DROP TABLE IF EXISTS `atus_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atus_rol` (
  `CVE_ROL` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_NOMBRE` varchar(45) NOT NULL,
  `FEC_EXPIRA` date DEFAULT NULL,
  `FEC_ALTA` date NOT NULL,
  `FEC_ACTUALIZACION` date DEFAULT NULL,
  `FEC_BAJA` date DEFAULT NULL,
  PRIMARY KEY (`CVE_ROL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atus_rol`
--

LOCK TABLES `atus_rol` WRITE;
/*!40000 ALTER TABLE `atus_rol` DISABLE KEYS */;
INSERT INTO `atus_rol` VALUES (1,'ADMINISTRADOR',NULL,'2020-12-15',NULL,NULL),(2,'VENDEDOR',NULL,'2020-12-15',NULL,NULL),(3,'CLIENTE',NULL,'2020-12-15',NULL,NULL);
/*!40000 ALTER TABLE `atus_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atus_usuario`
--

DROP TABLE IF EXISTS `atus_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atus_usuario` (
  `CVE_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `CVE_CORREO` varchar(45) NOT NULL,
  `CVE_CONTRASENA` varchar(60) NOT NULL,
  `NOM_NOMBRE` varchar(45) NOT NULL,
  `NOM_APELLIDOPATERNO` varchar(45) NOT NULL,
  `NOM_APELLIDOMATERNO` varchar(45) NOT NULL,
  `DES_COMENTARIO` text,
  `FEC_EXPIRA` date DEFAULT NULL,
  `FEC_ALTA` date NOT NULL,
  `FEC_ACTUALIZACION` date DEFAULT NULL,
  `FEC_BAJA` date DEFAULT NULL,
  PRIMARY KEY (`CVE_USUARIO`),
  UNIQUE KEY `uk_SRHC_USUARIO_CORREO` (`CVE_CORREO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atus_usuario`
--

LOCK TABLES `atus_usuario` WRITE;
/*!40000 ALTER TABLE `atus_usuario` DISABLE KEYS */;
INSERT INTO `atus_usuario` VALUES (1,'minucita@gmail.com','$2a$10$fHZa2jSbU3lQLW9081Gg5er586xYRORXX5NrTGhbKzRBG3LlmBw3K','Minu','Martinez','Ramirez','Comentario prueba',NULL,'2020-12-15',NULL,NULL),(2,'iger@gmail.com','$2a$10$fHZa2jSbU3lQLW9081Gg5er586xYRORXX5NrTGhbKzRBG3LlmBw3K','Gerardo','Ornelas','Rodriguez',NULL,NULL,'2020-12-15',NULL,NULL);
/*!40000 ALTER TABLE `atus_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atus_usuario_rol`
--

DROP TABLE IF EXISTS `atus_usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atus_usuario_rol` (
  `CVE_USUARIO` int(11) NOT NULL,
  `CVE_ROL` int(11) NOT NULL,
  PRIMARY KEY (`CVE_USUARIO`,`CVE_ROL`),
  KEY `fk_atus_usuario_has_atus_rol_atus_rol1_idx` (`CVE_ROL`),
  KEY `fk_atus_usuario_has_atus_rol_atus_usuario_idx` (`CVE_USUARIO`),
  CONSTRAINT `fk_atus_usuario_has_atus_rol_atus_rol1` FOREIGN KEY (`CVE_ROL`) REFERENCES `atus_rol` (`CVE_ROL`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_atus_usuario_has_atus_rol_atus_usuario` FOREIGN KEY (`CVE_USUARIO`) REFERENCES `atus_usuario` (`CVE_USUARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atus_usuario_rol`
--

LOCK TABLES `atus_usuario_rol` WRITE;
/*!40000 ALTER TABLE `atus_usuario_rol` DISABLE KEYS */;
INSERT INTO `atus_usuario_rol` VALUES (1,1),(1,2),(2,2);
/*!40000 ALTER TABLE `atus_usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-09 10:46:14
