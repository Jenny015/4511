-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： localhost
-- 產生時間： 2025 年 03 月 25 日 04:44
-- 伺服器版本： 10.4.28-MariaDB
-- PHP 版本： 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `4511EA`
--

-- --------------------------------------------------------

--
-- 資料表結構 `Emp`
--

CREATE TABLE `Emp` (
  `empID` varchar(5) NOT NULL,
  `pwd` varchar(16) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `phone` char(8) NOT NULL,
  `role` char(1) NOT NULL,
  `city` varchar(3) NOT NULL,
  `locID` varchar(5) DEFAULT NULL,
  `eStatus` char(1) DEFAULT 'A'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `Fruit`
--

CREATE TABLE `Fruit` (
  `fruitID` int(3) NOT NULL,
  `fruitName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `Inventory`
--

CREATE TABLE `Inventory` (
  `locID` varchar(5) NOT NULL,
  `fruitID` int(3) NOT NULL,
  `qty` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `Location`
--

CREATE TABLE `Location` (
  `locID` varchar(5) NOT NULL,
  `type` char(1) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `locName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `Transfer`
--

CREATE TABLE `Transfer` (
  `transferID` varchar(22) NOT NULL,
  `fromLoc` varchar(5) NOT NULL,
  `toLoc` varchar(5) NOT NULL,
  `tStatus` varchar(10) NOT NULL,
  `estArrial` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `TransferQty`
--

CREATE TABLE `TransferQty` (
  `transferID` varchar(22) NOT NULL,
  `fruitID` int(3) NOT NULL,
  `qty` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `Emp`
--
ALTER TABLE `Emp`
  ADD PRIMARY KEY (`empID`),
  ADD KEY `fk_EmpLocation` (`locID`);

--
-- 資料表索引 `Fruit`
--
ALTER TABLE `Fruit`
  ADD PRIMARY KEY (`fruitID`);

--
-- 資料表索引 `Inventory`
--
ALTER TABLE `Inventory`
  ADD PRIMARY KEY (`locID`,`fruitID`),
  ADD KEY `fk_InventoryFruit` (`fruitID`);

--
-- 資料表索引 `Location`
--
ALTER TABLE `Location`
  ADD PRIMARY KEY (`locID`);

--
-- 資料表索引 `Transfer`
--
ALTER TABLE `Transfer`
  ADD PRIMARY KEY (`transferID`),
  ADD KEY `fk_Transfer1` (`fromLoc`),
  ADD KEY `fk_Transfer2` (`toLoc`);

--
-- 資料表索引 `TransferQty`
--
ALTER TABLE `TransferQty`
  ADD PRIMARY KEY (`fruitID`,`transferID`),
  ADD KEY `fk_TransferTransferQty` (`transferID`);

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `Emp`
--
ALTER TABLE `Emp`
  ADD CONSTRAINT `fk_EmpLocation` FOREIGN KEY (`locID`) REFERENCES `Location` (`locID`);

--
-- 資料表的限制式 `Inventory`
--
ALTER TABLE `Inventory`
  ADD CONSTRAINT `fk_InventoryFruit` FOREIGN KEY (`fruitID`) REFERENCES `Fruit` (`fruitID`),
  ADD CONSTRAINT `fk_InventoryLocation` FOREIGN KEY (`locID`) REFERENCES `Location` (`locID`);

--
-- 資料表的限制式 `Transfer`
--
ALTER TABLE `Transfer`
  ADD CONSTRAINT `fk_Transfer1` FOREIGN KEY (`fromLoc`) REFERENCES `Location` (`locID`),
  ADD CONSTRAINT `fk_Transfer2` FOREIGN KEY (`toLoc`) REFERENCES `Location` (`locID`);

--
-- 資料表的限制式 `TransferQty`
--
ALTER TABLE `TransferQty`
  ADD CONSTRAINT `fk_FruitTransferQty` FOREIGN KEY (`fruitID`) REFERENCES `Fruit` (`fruitID`),
  ADD CONSTRAINT `fk_TransferTransferQty` FOREIGN KEY (`transferID`) REFERENCES `Transfer` (`transferID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
