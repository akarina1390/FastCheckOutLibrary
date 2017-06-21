-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 21, 2017 at 06:43 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `CPSC471`
--

-- --------------------------------------------------------

--
-- Table structure for table `AUTHOR`
--

CREATE TABLE `AUTHOR` (
  `ID` int(100) NOT NULL,
  `FIRST_NAME` varchar(55) NOT NULL,
  `LAST_NAME` varchar(55) NOT NULL,
  `COUNTRY_ID` int(100) NOT NULL,
  `DEATH_STATUS` binary(1) DEFAULT NULL,
  `GENDER_ID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AUTHOR`
--

INSERT INTO `AUTHOR` (`ID`, `FIRST_NAME`, `LAST_NAME`, `COUNTRY_ID`, `DEATH_STATUS`, `GENDER_ID`) VALUES
(1, 'Sonny', 'Ng', 15, 0x30, 1);

-- --------------------------------------------------------

--
-- Table structure for table `BOOK`
--

CREATE TABLE `BOOK` (
  `ID` int(50) NOT NULL,
  `NAME` varchar(55) NOT NULL,
  `AUTHOR_ID` int(10) NOT NULL,
  `PUBLISHER_ID` int(10) NOT NULL,
  `YEAR` int(4) DEFAULT NULL,
  `EDITION` int(10) DEFAULT NULL,
  `GENRE_ID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BOOK`
--

INSERT INTO `BOOK` (`ID`, `NAME`, `AUTHOR_ID`, `PUBLISHER_ID`, `YEAR`, `EDITION`, `GENRE_ID`) VALUES
(1, 'Frosted Flakes', 1, 1, 2017, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `BOOK_USER_XREF`
--

CREATE TABLE `BOOK_USER_XREF` (
  `BOOK_ID` int(100) NOT NULL,
  `USER_ID` int(100) NOT NULL,
  `BORROWING_DATE` date NOT NULL,
  `RETURNING_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BOOK_USER_XREF`
--

INSERT INTO `BOOK_USER_XREF` (`BOOK_ID`, `USER_ID`, `BORROWING_DATE`, `RETURNING_DATE`) VALUES
(1, 1, '2017-06-20', '2017-07-19');

-- --------------------------------------------------------

--
-- Table structure for table `COUNTRY`
--

CREATE TABLE `COUNTRY` (
  `ID` int(100) NOT NULL,
  `NAME` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `COUNTRY`
--

INSERT INTO `COUNTRY` (`ID`, `NAME`) VALUES
(1, 'Argentina'),
(2, 'Brazil'),
(3, 'Canada'),
(4, 'Colombia'),
(5, 'Dominican Republic'),
(6, 'Denmark'),
(7, 'England'),
(8, 'Germany'),
(9, 'Italy'),
(10, 'Japan'),
(11, 'Mexico'),
(12, 'Portugal'),
(13, 'Spain'),
(14, 'United States'),
(15, 'Venezuela');

-- --------------------------------------------------------

--
-- Table structure for table `GENDER`
--

CREATE TABLE `GENDER` (
  `ID` int(100) NOT NULL,
  `NAME` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `GENDER`
--

INSERT INTO `GENDER` (`ID`, `NAME`) VALUES
(1, 'Male'),
(2, 'Female');

-- --------------------------------------------------------

--
-- Table structure for table `GENRE`
--

CREATE TABLE `GENRE` (
  `ID` int(100) NOT NULL,
  `NAME` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `GENRE`
--

INSERT INTO `GENRE` (`ID`, `NAME`) VALUES
(1, 'Fiction'),
(2, 'Comedy'),
(3, 'Drama'),
(4, 'Horror'),
(5, 'Non-fiction'),
(6, 'Realistic'),
(7, 'Romantic'),
(8, 'Satire'),
(9, 'Tragedy'),
(10, 'Tragicomedy'),
(11, 'Fantasy'),
(12, 'Mythology');

-- --------------------------------------------------------

--
-- Table structure for table `PRIVILEDGE`
--

CREATE TABLE `PRIVILEDGE` (
  `ID` int(1) NOT NULL,
  `NAME` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PRIVILEDGE`
--

INSERT INTO `PRIVILEDGE` (`ID`, `NAME`) VALUES
(1, 'Admin'),
(2, 'Library Member');

-- --------------------------------------------------------

--
-- Table structure for table `PUBLISHER`
--

CREATE TABLE `PUBLISHER` (
  `ID` int(100) NOT NULL,
  `NAME` varchar(55) NOT NULL,
  `COUNTRY_ID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PUBLISHER`
--

INSERT INTO `PUBLISHER` (`ID`, `NAME`, `COUNTRY_ID`) VALUES
(1, 'Real Madrid printing co', 13);

-- --------------------------------------------------------

--
-- Table structure for table `USER_CONF`
--

CREATE TABLE `USER_CONF` (
  `ID` int(100) NOT NULL,
  `USER_NAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `PRIVILEDGE_ID` int(1) NOT NULL,
  `FIRST_NAME` varchar(55) NOT NULL,
  `LAST_NAME` varchar(55) NOT NULL,
  `EMAIL` varchar(55) DEFAULT NULL,
  `COUNTRY_ID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER_CONF`
--

INSERT INTO `USER_CONF` (`ID`, `USER_NAME`, `PASSWORD`, `PRIVILEDGE_ID`, `FIRST_NAME`, `LAST_NAME`, `EMAIL`, `COUNTRY_ID`) VALUES
(1, 'anaka', '123456', 1, 'Ana', 'Carrocci', 'akarina1390@gmail.com', 15),
(2, 'alegar', '123456', 2, 'Alejandro', 'Sieu', 'ale.sieu@ucalgary.ca', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AUTHOR`
--
ALTER TABLE `AUTHOR`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `COUNTRY_ID` (`COUNTRY_ID`),
  ADD KEY `GENDER_ID` (`GENDER_ID`);

--
-- Indexes for table `BOOK`
--
ALTER TABLE `BOOK`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `AUTHOR_ID` (`AUTHOR_ID`),
  ADD KEY `PUBLISHER_ID` (`PUBLISHER_ID`),
  ADD KEY `GENRE_ID` (`GENRE_ID`);

--
-- Indexes for table `BOOK_USER_XREF`
--
ALTER TABLE `BOOK_USER_XREF`
  ADD KEY `BOOK_ID` (`BOOK_ID`),
  ADD KEY `USER_ID` (`USER_ID`);

--
-- Indexes for table `COUNTRY`
--
ALTER TABLE `COUNTRY`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `GENDER`
--
ALTER TABLE `GENDER`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `GENRE`
--
ALTER TABLE `GENRE`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `PRIVILEDGE`
--
ALTER TABLE `PRIVILEDGE`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NAME` (`NAME`);

--
-- Indexes for table `PUBLISHER`
--
ALTER TABLE `PUBLISHER`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NAME` (`NAME`);

--
-- Indexes for table `USER_CONF`
--
ALTER TABLE `USER_CONF`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `USER_NAME` (`USER_NAME`),
  ADD KEY `PRIVILEDGE_ID` (`PRIVILEDGE_ID`),
  ADD KEY `COUNTRY_ID` (`COUNTRY_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AUTHOR`
--
ALTER TABLE `AUTHOR`
  ADD CONSTRAINT `author_ibfk_1` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY` (`ID`),
  ADD CONSTRAINT `author_ibfk_2` FOREIGN KEY (`GENDER_ID`) REFERENCES `GENDER` (`ID`);

--
-- Constraints for table `BOOK`
--
ALTER TABLE `BOOK`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `AUTHOR` (`ID`),
  ADD CONSTRAINT `book_ibfk_2` FOREIGN KEY (`PUBLISHER_ID`) REFERENCES `PUBLISHER` (`ID`),
  ADD CONSTRAINT `book_ibfk_3` FOREIGN KEY (`GENRE_ID`) REFERENCES `GENRE` (`ID`);

--
-- Constraints for table `BOOK_USER_XREF`
--
ALTER TABLE `BOOK_USER_XREF`
  ADD CONSTRAINT `book_user_xref_ibfk_1` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`),
  ADD CONSTRAINT `book_user_xref_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `USER_CONF` (`ID`);

--
-- Constraints for table `USER_CONF`
--
ALTER TABLE `USER_CONF`
  ADD CONSTRAINT `user_conf_ibfk_1` FOREIGN KEY (`PRIVILEDGE_ID`) REFERENCES `PRIVILEDGE` (`ID`),
  ADD CONSTRAINT `user_conf_ibfk_2` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
