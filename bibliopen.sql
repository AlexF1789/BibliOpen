-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliopen
-- ------------------------------------------------------
-- Server version	11.4.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autori`
--

DROP TABLE IF EXISTS `autori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autori` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `cognome` varchar(100) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `annoNascita` int(11) DEFAULT NULL,
  `descrizione` tinytext DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autori`
--

LOCK TABLES `autori` WRITE;
/*!40000 ALTER TABLE `autori` DISABLE KEYS */;
INSERT INTO `autori` VALUES (1,'Manzoni','Alessandro',1785,NULL),(2,'Alighieri','Dante',1265,NULL),(3,'Svevo','Italo',1861,'pseudonimo di Aron Hector Schmitz'),(4,'Pirandello','Luigi',1867,''),(5,'Saba','Umberto',1883,'pseudonimo di Umberto Poli'),(6,'Dostoevskij','Fëdor',1821,NULL);
/*!40000 ALTER TABLE `autori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editori`
--

DROP TABLE IF EXISTS `editori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editori` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `luogo` varchar(100) DEFAULT NULL,
  `descrizione` tinytext DEFAULT NULL,
  `annoFondazione` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editori`
--

LOCK TABLES `editori` WRITE;
/*!40000 ALTER TABLE `editori` DISABLE KEYS */;
INSERT INTO `editori` VALUES (1,'Mondadori','Segrate',NULL,1907),(2,'LaFeltrinelli','Milano',NULL,1949),(3,'Einaudi','Torino','parte del gruppo Mondadori',1933),(4,'Newton-Compton','Roma',NULL,1969);
/*!40000 ALTER TABLE `editori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libri`
--

DROP TABLE IF EXISTS `libri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libri` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `titolo` varchar(100) NOT NULL,
  `autore` int(11) NOT NULL,
  `editore` int(11) NOT NULL,
  `annoPubblicazione` int(11) NOT NULL,
  `descrizione` tinytext DEFAULT NULL,
  `copie` int(11) NOT NULL DEFAULT 1,
  `ISBN` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `libri_autori_FK` (`autore`),
  KEY `libri_editori_FK` (`editore`),
  CONSTRAINT `libri_autori_FK` FOREIGN KEY (`autore`) REFERENCES `autori` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `libri_editori_FK` FOREIGN KEY (`editore`) REFERENCES `editori` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libri`
--

LOCK TABLES `libri` WRITE;
/*!40000 ALTER TABLE `libri` DISABLE KEYS */;
INSERT INTO `libri` VALUES (1,'I Promessi Sposi',1,1,2016,NULL,1,'9788804672340'),(2,'La Divina Commedia',2,1,2023,NULL,1,'9788804781721'),(3,'Il Canzoniere',5,3,1961,'il codice non è un ISBN ma un EAN',1,'2562814024262'),(4,'Uno, nessuno e centomila',4,4,2015,NULL,1,'9788854172609'),(5,'Le notti bianche',6,2,2015,NULL,1,'9788807901874');
/*!40000 ALTER TABLE `libri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestiti`
--

DROP TABLE IF EXISTS `prestiti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestiti` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `libro` int(11) NOT NULL,
  `utente` int(11) NOT NULL,
  `dataInizio` date NOT NULL,
  `dataFine` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `prestiti_libri_FK` (`libro`),
  KEY `prestiti_utenti_FK` (`utente`),
  CONSTRAINT `prestiti_libri_FK` FOREIGN KEY (`libro`) REFERENCES `libri` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `prestiti_utenti_FK` FOREIGN KEY (`utente`) REFERENCES `utenti` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestiti`
--

LOCK TABLES `prestiti` WRITE;
/*!40000 ALTER TABLE `prestiti` DISABLE KEYS */;
INSERT INTO `prestiti` VALUES (1,1,2,'2024-06-28','2024-09-15'),(2,3,3,'2024-12-06','2025-02-15'),(3,4,3,'2024-11-30',NULL);
/*!40000 ALTER TABLE `prestiti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `cognome` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `telefono` varchar(100) NOT NULL,
  `ruolo` int(11) NOT NULL DEFAULT 0,
  `password` varchar(100) NOT NULL DEFAULT '6258a5e0eb772911d4f92be5b5db0e14511edbe01d1d0ddd1d5a2cb9db9a56ba', /* codifica SHA256 della parola 'prova' */
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES (1,'Admin','Admin','admin@localhost','1234567890',1,'6258a5e0eb772911d4f92be5b5db0e14511edbe01d1d0ddd1d5a2cb9db9a56ba'),(2,'Prova','Utente','utente@localhost','1234567890',0,'6258a5e0eb772911d4f92be5b5db0e14511edbe01d1d0ddd1d5a2cb9db9a56ba'),(3,'Secondo','Utente','secondo@localhost','1234567890',0,'6258a5e0eb772911d4f92be5b5db0e14511edbe01d1d0ddd1d5a2cb9db9a56ba');
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bibliopen'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-20 14:19:33
