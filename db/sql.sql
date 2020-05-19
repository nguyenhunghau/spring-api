CREATE DATABASE  IF NOT EXISTS `business` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `business`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: business
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONTACT_PERSON` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL COMMENT '1 -> Provider\n2 -> Customer\n',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'phu','TabBinh','21313',NULL,NULL,NULL,1),(3,'long','q10','43242',NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_note`
--

DROP TABLE IF EXISTS `delivery_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `delivery_note` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMER_KEY_idx` (`CUSTOMER_ID`),
  KEY `DELIVERY_CUSTOMER_KEY_idx` (`CUSTOMER_ID`),
  CONSTRAINT `DELIVERY_CUSTOMER_KEY` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_note`
--

LOCK TABLES `delivery_note` WRITE;
/*!40000 ALTER TABLE `delivery_note` DISABLE KEYS */;
INSERT INTO `delivery_note` VALUES (7,1,'2020-05-19'),(10,1,'2020-05-19');
/*!40000 ALTER TABLE `delivery_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_note_detail`
--

DROP TABLE IF EXISTS `delivery_note_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `delivery_note_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DELIVERY_NOTE_ID` int(11) DEFAULT NULL,
  `PRODUCT_ID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `DELIVERY_KEY_idx` (`DELIVERY_NOTE_ID`),
  KEY `PRODUCT_KEY_idx` (`PRODUCT_ID`),
  CONSTRAINT `DELIVERY_KEY` FOREIGN KEY (`DELIVERY_NOTE_ID`) REFERENCES `delivery_note` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `DELIVERY_PRODUCT_KEY` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_note_detail`
--

LOCK TABLES `delivery_note_detail` WRITE;
/*!40000 ALTER TABLE `delivery_note_detail` DISABLE KEYS */;
INSERT INTO `delivery_note_detail` VALUES (10,10,1,900);
/*!40000 ALTER TABLE `delivery_note_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(11) DEFAULT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORDER_DETAIL_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `ORDER_KEY_idx` (`ORDER_ID`),
  CONSTRAINT `ORDER_DETAIL_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ORDER_KEY` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,1,1,10,100000),(2,NULL,2,10,100000),(3,NULL,NULL,10,100000),(4,NULL,NULL,100,100);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` datetime DEFAULT NULL,
  `STAFF_ID` int(11) DEFAULT NULL,
  `PROVIDER_ID` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `staff_key_idx` (`STAFF_ID`),
  CONSTRAINT `staff_key` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2020-05-06 00:00:00',3,'1'),(2,'2020-05-09 00:00:00',3,'1');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `ID` int(11) NOT NULL,
  `NAME` char(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODEL` char(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BRAND` char(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COMPANY` char(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` char(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CATEGORY_ID` int(11) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `category_key_idx` (`CATEGORY_ID`),
  CONSTRAINT `category_key` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'banh',NULL,NULL,NULL,NULL,NULL,NULL),(2,'tien',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiving_note`
--

DROP TABLE IF EXISTS `receiving_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `receiving_note` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` date DEFAULT NULL,
  `PROVIDER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PROVIDER_KEY_idx` (`PROVIDER_ID`),
  CONSTRAINT `PROVIDER_KEY` FOREIGN KEY (`PROVIDER_ID`) REFERENCES `customer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiving_note`
--

LOCK TABLES `receiving_note` WRITE;
/*!40000 ALTER TABLE `receiving_note` DISABLE KEYS */;
INSERT INTO `receiving_note` VALUES (1,'2020-05-16',1),(10,'2020-05-16',NULL),(11,'2020-05-18',NULL),(16,'2020-05-18',3),(18,'2020-05-18',1);
/*!40000 ALTER TABLE `receiving_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiving_note_detail`
--

DROP TABLE IF EXISTS `receiving_note_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `receiving_note_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECEIVING_NOTE_ID` int(11) DEFAULT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `RECEVING_KEY_idx` (`RECEIVING_NOTE_ID`),
  KEY `PRODUCT_KEY_idx` (`PRODUCT_ID`),
  CONSTRAINT `PRODUCT_KEY` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`),
  CONSTRAINT `RECEVING_KEY` FOREIGN KEY (`RECEIVING_NOTE_ID`) REFERENCES `receiving_note` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiving_note_detail`
--

LOCK TABLES `receiving_note_detail` WRITE;
/*!40000 ALTER TABLE `receiving_note_detail` DISABLE KEYS */;
INSERT INTO `receiving_note_detail` VALUES (3,1,1,250),(13,1,2,1100),(14,1,1,200),(20,16,2,20),(22,NULL,1,40),(23,18,2,20),(24,NULL,1,40),(26,NULL,1,40);
/*!40000 ALTER TABLE `receiving_note_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_invoice`
--

DROP TABLE IF EXISTS `sale_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sale_invoice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` date DEFAULT NULL,
  `STAFF_ID` int(11) DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `INVOICE_STAFF_KEY_idx` (`STAFF_ID`),
  KEY `INVOICE_CUSTOMER_KEY_idx` (`CUSTOMER_ID`),
  CONSTRAINT `INVOICE_CUSTOMER_KEY` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`),
  CONSTRAINT `INVOICE_STAFF_KEY` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_invoice`
--

LOCK TABLES `sale_invoice` WRITE;
/*!40000 ALTER TABLE `sale_invoice` DISABLE KEYS */;
INSERT INTO `sale_invoice` VALUES (2,'2020-04-30',3,1),(5,'2020-05-17',8,3);
/*!40000 ALTER TABLE `sale_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_invoice_detail`
--

DROP TABLE IF EXISTS `sale_invoice_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sale_invoice_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SALE_INVOICE_ID` int(11) DEFAULT NULL,
  `PRODUCT_ID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SALE_INVOCIE_KEY_idx` (`SALE_INVOICE_ID`),
  KEY `SALE_PRODUCT_KEY_idx` (`PRODUCT_ID`),
  CONSTRAINT `SALE_INVOCIE_KEY` FOREIGN KEY (`SALE_INVOICE_ID`) REFERENCES `sale_invoice` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SALE_PRODUCT_KEY` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_invoice_detail`
--

LOCK TABLES `sale_invoice_detail` WRITE;
/*!40000 ALTER TABLE `sale_invoice_detail` DISABLE KEYS */;
INSERT INTO `sale_invoice_detail` VALUES (1,2,1,150,1600),(4,5,1,150,100);
/*!40000 ALTER TABLE `sale_invoice_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (3,'hunghau','Ha Noi','123456','hau@xxx.com'),(5,'hunghau',NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL),(8,'hunghau','HCM',NULL,NULL),(9,'hunghau','Ha Noi',NULL,NULL),(10,'hunghau','Ha Noi',NULL,NULL),(11,'phu','TabBinh','32435435','phu@xxx.com'),(15,'phu-le','q10','32435435','phu@xxx.com');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'business'
--

--
-- Dumping routines for database 'business'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-19 17:20:02
