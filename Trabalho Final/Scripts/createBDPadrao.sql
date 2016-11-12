CREATE DATABASE  IF NOT EXISTS `trab_bd` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `trab_bd`;
-- MySQL dump 10.13  Distrib 5.5.53, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: trab_bd
-- ------------------------------------------------------
-- Server version	5.5.53-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='-03:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ACAO`
--

DROP TABLE IF EXISTS `ACAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACAO` (
  `codigo` varchar(255) NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  `linguagem_cidada` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACAO`
--

LOCK TABLES `ACAO` WRITE;
/*!40000 ALTER TABLE `ACAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FUNCAO`
--

DROP TABLE IF EXISTS `FUNCAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FUNCAO` (
  `codigo` int(255) unsigned NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  `cod_superior` int(255) unsigned NOT NULL DEFAULT '0',
  `cod_subordinado` int(255) unsigned NOT NULL DEFAULT '0',
  `cod_unidade_gestora` int(255) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`,`cod_superior`,`cod_subordinado`,`cod_unidade_gestora`),
  KEY `fk_cod_superior2` (`cod_superior`),
  KEY `fk_cod_subordinado2` (`cod_subordinado`),
  KEY `fk_cod_unidade_gestora` (`cod_unidade_gestora`),
  CONSTRAINT `fk_cod_superior2` FOREIGN KEY (`cod_superior`) REFERENCES `ORGAO_SUPERIOR` (`codigo`),
  CONSTRAINT `fk_cod_subordinado2` FOREIGN KEY (`cod_subordinado`) REFERENCES `ORGAO_SUBORDINADO` (`codigo`),
  CONSTRAINT `fk_cod_unidade_gestora` FOREIGN KEY (`cod_unidade_gestora`) REFERENCES `UNIDADE_GESTORA` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FUNCAO`
--

LOCK TABLES `FUNCAO` WRITE;
/*!40000 ALTER TABLE `FUNCAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `FUNCAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FUN_SUBFUN`
--

DROP TABLE IF EXISTS `FUN_SUBFUN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FUN_SUBFUN` (
  `cod_fun` int(255) unsigned NOT NULL DEFAULT '0',
  `cod_sub_fun` int(255) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`cod_fun`,`cod_sub_fun`),
  KEY `fk_cod_sub_fun` (`cod_sub_fun`),
  CONSTRAINT `fk_cod_fun` FOREIGN KEY (`cod_fun`) REFERENCES `FUNCAO` (`codigo`),
  CONSTRAINT `fk_cod_sub_fun` FOREIGN KEY (`cod_sub_fun`) REFERENCES `SUB_FUNCAO` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FUN_SUBFUN`
--

LOCK TABLES `FUN_SUBFUN` WRITE;
/*!40000 ALTER TABLE `FUN_SUBFUN` DISABLE KEYS */;
/*!40000 ALTER TABLE `FUN_SUBFUN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORGAO_SUBORDINADO`
--

DROP TABLE IF EXISTS `ORGAO_SUBORDINADO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORGAO_SUBORDINADO` (
  `codigo` int(255) unsigned NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  `cod_superior` int(255) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`,`cod_superior`),
  KEY `fk_cod_superior` (`cod_superior`),
  CONSTRAINT `fk_cod_superior` FOREIGN KEY (`cod_superior`) REFERENCES `ORGAO_SUPERIOR` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORGAO_SUBORDINADO`
--

LOCK TABLES `ORGAO_SUBORDINADO` WRITE;
/*!40000 ALTER TABLE `ORGAO_SUBORDINADO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORGAO_SUBORDINADO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORGAO_SUPERIOR`
--

DROP TABLE IF EXISTS `ORGAO_SUPERIOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORGAO_SUPERIOR` (
  `codigo` int(255) unsigned NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORGAO_SUPERIOR`
--

LOCK TABLES `ORGAO_SUPERIOR` WRITE;
/*!40000 ALTER TABLE `ORGAO_SUPERIOR` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORGAO_SUPERIOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAGAMENTO`
--

DROP TABLE IF EXISTS `PAGAMENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAGAMENTO` (
  `documento_pagamento` varchar(255) NOT NULL DEFAULT '0',
  `gestao_pagamento` int(255) NOT NULL,
  PRIMARY KEY (`documento_pagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAGAMENTO`
--

LOCK TABLES `PAGAMENTO` WRITE;
/*!40000 ALTER TABLE `PAGAMENTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PAGAMENTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PESSOA`
--

DROP TABLE IF EXISTS `PESSOA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PESSOA` (
  `cpf` varchar(20) NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PESSOA`
--

LOCK TABLES `PESSOA` WRITE;
/*!40000 ALTER TABLE `PESSOA` DISABLE KEYS */;
/*!40000 ALTER TABLE `PESSOA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PESSOA_PAG`
--

DROP TABLE IF EXISTS `PESSOA_PAG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PESSOA_PAG` (
  `cpf_pessoa` varchar(20) NOT NULL DEFAULT '0',
  `doc_pag` varchar(255) NOT NULL DEFAULT '0',
  `data_pagamento` date DEFAULT NULL,
  `valor` float DEFAULT NULL,
  PRIMARY KEY (`cpf_pessoa`,`doc_pag`),
  KEY `fk_doc_pag` (`doc_pag`),
  CONSTRAINT `fk_cpf_pessoa` FOREIGN KEY (`cpf_pessoa`) REFERENCES `PESSOA` (`cpf`),
  CONSTRAINT `fk_doc_pag` FOREIGN KEY (`doc_pag`) REFERENCES `PAGAMENTO` (`documento_pagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PESSOA_PAG`
--

LOCK TABLES `PESSOA_PAG` WRITE;
/*!40000 ALTER TABLE `PESSOA_PAG` DISABLE KEYS */;
/*!40000 ALTER TABLE `PESSOA_PAG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROGRAMA`
--

DROP TABLE IF EXISTS `PROGRAMA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROGRAMA` (
  `codigo` int(255) unsigned NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROGRAMA`
--

LOCK TABLES `PROGRAMA` WRITE;
/*!40000 ALTER TABLE `PROGRAMA` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROGRAMA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROG_ACAO`
--

DROP TABLE IF EXISTS `PROG_ACAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROG_ACAO` (
  `cod_prog` int(255) unsigned NOT NULL DEFAULT '0',
  `cod_acao` VARCHAR(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cod_prog`,`cod_acao`),
  KEY `fk_cod_acao` (`cod_acao`),
  CONSTRAINT `fk_cod_prog` FOREIGN KEY (`cod_prog`) REFERENCES `PROGRAMA` (`codigo`),
  CONSTRAINT `fk_cod_acao` FOREIGN KEY (`cod_acao`) REFERENCES `ACAO` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROG_ACAO`
--

LOCK TABLES `PROG_ACAO` WRITE;
/*!40000 ALTER TABLE `PROG_ACAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROG_ACAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROG_ORGAO`
--

DROP TABLE IF EXISTS `PROG_ORGAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROG_ORGAO` (
  `cod_prog` int(255) unsigned NOT NULL DEFAULT '0',
  `cod_sub` int(255) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`cod_prog`,`cod_sub`),
  KEY `fk_cod_sub2` (`cod_sub`),
  CONSTRAINT `fk_cod_prog2` FOREIGN KEY (`cod_prog`) REFERENCES `PROGRAMA` (`codigo`),
  CONSTRAINT `fk_cod_sub2` FOREIGN KEY (`cod_sub`) REFERENCES `ORGAO_SUBORDINADO` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROG_ORGAO`
--

LOCK TABLES `PROG_ORGAO` WRITE;
/*!40000 ALTER TABLE `PROG_ORGAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROG_ORGAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUB_FUNCAO`
--

DROP TABLE IF EXISTS `SUB_FUNCAO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUB_FUNCAO` (
  `codigo` int(255) unsigned NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUB_FUNCAO`
--

LOCK TABLES `SUB_FUNCAO` WRITE;
/*!40000 ALTER TABLE `SUB_FUNCAO` DISABLE KEYS */;
/*!40000 ALTER TABLE `SUB_FUNCAO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UNIDADE_GESTORA`
--

DROP TABLE IF EXISTS `UNIDADE_GESTORA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UNIDADE_GESTORA` (
  `codigo` int(255) unsigned NOT NULL DEFAULT '0',
  `nome` varchar(255) NOT NULL,
  `cod_superior` int(255) unsigned NOT NULL DEFAULT '0',
  `cod_subordinado` int(255) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`,`cod_superior`,`cod_subordinado`),
  KEY `fk_cod_superior_1` (`cod_superior`),
  KEY `fk_cod_subordinado` (`cod_subordinado`),
  CONSTRAINT `fk_cod_superior_1` FOREIGN KEY (`cod_superior`) REFERENCES `ORGAO_SUPERIOR` (`codigo`),
  CONSTRAINT `fk_cod_subordinado` FOREIGN KEY (`cod_subordinado`) REFERENCES `ORGAO_SUBORDINADO` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UNIDADE_GESTORA`
--

LOCK TABLES `UNIDADE_GESTORA` WRITE;
/*!40000 ALTER TABLE `UNIDADE_GESTORA` DISABLE KEYS */;
/*!40000 ALTER TABLE `UNIDADE_GESTORA` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-04 11:35:26
