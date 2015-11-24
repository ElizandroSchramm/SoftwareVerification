CREATE DATABASE  IF NOT EXISTS `suseg_banco` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `suseg_banco`;
-- MySQL dump 10.13  Distrib 5.6.10, for osx10.7 (x86_64)
--
-- Host: localhost    Database: suseg_banco
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
-- Table structure for table `Apolice`
--

DROP TABLE IF EXISTS `Apolice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Apolice` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigocotacao` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigocotacao_idx` (`codigocotacao`),
  CONSTRAINT `codigocotacao` FOREIGN KEY (`codigocotacao`) REFERENCES `Cotacao` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Clausula`
--

DROP TABLE IF EXISTS `Clausula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Clausula` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `descricao` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`codigo`,`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Condutor`
--

DROP TABLE IF EXISTS `Condutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Condutor` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codcotacao` int(11) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `idade` int(11) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL COMMENT 'F ou M',
  `casado` varchar(1) DEFAULT NULL COMMENT 'S ou N',
  `temfilho` varchar(1) DEFAULT NULL COMMENT 'S ou N',
  PRIMARY KEY (`codigo`),
  KEY `codcotacao_idx` (`codcotacao`),
  CONSTRAINT `codcotacao` FOREIGN KEY (`codcotacao`) REFERENCES `Cotacao` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Cotacao`
--

DROP TABLE IF EXISTS `Cotacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cotacao` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `comissao` decimal(9,2) DEFAULT NULL,
  `dataCriacao` datetime DEFAULT NULL,
  `valor` decimal(9,2) DEFAULT NULL,
  `vigencia` datetime DEFAULT NULL,
  `codveiculo` int(11) DEFAULT NULL,
  `codlocalizacao` int(11) DEFAULT NULL,
  `codsegurado` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codveiculo_idx` (`codveiculo`),
  KEY `codlocalizacao_idx` (`codlocalizacao`),
  KEY `codsegurado_idx` (`codsegurado`),
  CONSTRAINT `codsegurado` FOREIGN KEY (`codsegurado`) REFERENCES `Segurado` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codlocalizacao` FOREIGN KEY (`codlocalizacao`) REFERENCES `Localizacao` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codveiculo` FOREIGN KEY (`codveiculo`) REFERENCES `Veiculo` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemClausula`
--

DROP TABLE IF EXISTS `ItemClausula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemClausula` (
  `idItemClausula` int(11) NOT NULL,
  `codcot` int(11) DEFAULT NULL COMMENT 'Código da cotação',
  `codclau` int(11) DEFAULT NULL COMMENT 'Código da cláusula',
  PRIMARY KEY (`idItemClausula`),
  KEY `codcot_idx` (`codcot`),
  KEY `codclau_idx` (`codclau`),
  CONSTRAINT `codcot` FOREIGN KEY (`codcot`) REFERENCES `Cotacao` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codclau` FOREIGN KEY (`codclau`) REFERENCES `Clausula` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Localizacao`
--

DROP TABLE IF EXISTS `Localizacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Localizacao` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `rua` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Segurado`
--

DROP TABLE IF EXISTS `Segurado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Segurado` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(14) DEFAULT NULL,
  `datanascimento` date DEFAULT NULL,
  `telefone` varchar(10) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL COMMENT 'F ou M',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Veiculo`
--

DROP TABLE IF EXISTS `Veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Veiculo` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `anofabricacao` int(11) DEFAULT NULL,
  `anomodelo` int(11) DEFAULT NULL,
  `chassi` varchar(75) DEFAULT NULL,
  `cor` varchar(45) DEFAULT NULL,
  `mediakmmes` int(11) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `placa` varchar(8) DEFAULT NULL,
  `renavam` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'suseg_banco'
--

--
-- Dumping routines for database 'suseg_banco'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-23 23:46:04
