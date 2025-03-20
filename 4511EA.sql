-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： localhost
-- 產生時間： 2025 年 03 月 20 日 10:09
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
  `qty` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `Location`
--

CREATE TABLE `Location` (
  `locID` varchar(5) NOT NULL,
  `city` varchar(3) NOT NULL,
  `type` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 已傾印資料表的索引
--

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
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `Inventory`
--
ALTER TABLE `Inventory`
  ADD CONSTRAINT `fk_InventoryFruit` FOREIGN KEY (`fruitID`) REFERENCES `Fruit` (`fruitID`),
  ADD CONSTRAINT `fk_InventoryLocation` FOREIGN KEY (`locID`) REFERENCES `Location` (`locID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
