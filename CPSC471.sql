-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 25, 2017 at 08:25 AM
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
(1, 'John', ' Steinbeck', 14, 0x00, 1),
(2, 'George', 'Orwell', 9, 0x00, 1),
(3, 'Scott', 'Fitzgerald', 11, 0x30, 1),
(4, 'J.R.R.', 'Tolkien', 1, 0x31, 1),
(5, 'Jane', 'Austen', 5, 0x31, 2),
(6, 'Jennifer', 'Keishin', 3, 0x30, 2),
(7, 'Mel', 'Brooks', 9, 0x30, 1),
(8, 'Janet', 'Benton', 6, 0x30, 2),
(9, 'Estelle', 'Laure', 2, 0x30, 2),
(10, 'Craig', 'Spector', 7, 0x30, 1),
(11, 'Robert', 'McCammon', 4, 0x30, 1),
(12, 'Vincent', 'Bugliosi', 15, 0x00, 1),
(13, 'Erik ', 'Larson', 8, 0x30, 1),
(14, 'John', 'Green', 13, 0x30, 1),
(15, 'Laurie', 'Halse', 11, 0x30, 2),
(16, 'Sandhya', 'Menon', 12, 0x00, 2),
(17, 'Andrew', 'Shvarts', 10, 0x00, 1),
(18, 'George', 'Orwell', 1, 0x01, 1),
(19, 'Joseph', 'Heller', 2, 0x00, 1),
(20, 'William', 'Shakespeare', 7, 0x01, 1);

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
(1, 'The Grapes of Wrath', 1, 1, 1939, 1, 1),
(2, 'The Great Gatsby', 3, 1, 1925, 3, 1),
(3, 'Nineteen Eighty - Four', 2, 4, 1949, 2, 1),
(4, 'The Lord of the Rings', 4, 9, 1954, 10, 11),
(5, 'Pride and Perjudice', 5, 12, 1920, 12, 7),
(6, 'Seinfeldia', 6, 8, NULL, 4, 2),
(7, 'Young Frankenstein', 7, 2, 2011, 1, 2),
(8, 'Lilli De Jong', 8, 6, 2017, 1, 3),
(9, 'But Then I Came Back', 9, 10, 2017, 1, 3),
(10, 'The Light at the End', 10, 11, 2015, 2, 4),
(11, 'Swan Song', 11, 10, 2002, 3, 4),
(12, 'The True Story of the Manson Murders', 12, 8, 2002, 3, 5),
(13, 'The Devil in the White City', 13, 6, 2003, 3, 5),
(14, 'The Fault in Our Starts', 14, 9, 2015, 2, 6),
(15, 'Speak', 15, 4, 2015, 2, 6),
(16, 'When Dimple Met Rishi', 16, 7, 2001, 6, 7),
(17, 'Royal Bastards', 17, 1, 1990, 2, 7),
(18, 'Animal Farm', 18, 12, 1945, 3, 8),
(19, 'Catch-22', 19, 11, 1961, 2, 8),
(20, 'Romeo and Juliet', 20, 10, 1595, 10, 9),
(21, 'Hamlet', 20, 10, 1600, 12, 9);

-- --------------------------------------------------------

--
-- Table structure for table `BOOK_LIBRARY_XREF`
--

CREATE TABLE `BOOK_LIBRARY_XREF` (
  `BOOK_ID` int(100) NOT NULL,
  `LIBRARY_ID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BOOK_LIBRARY_XREF`
--

INSERT INTO `BOOK_LIBRARY_XREF` (`BOOK_ID`, `LIBRARY_ID`) VALUES
(1, 1);

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
(5, 'Denmark'),
(6, 'Dominican Republic'),
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
-- Table structure for table `LIBRARY`
--

CREATE TABLE `LIBRARY` (
  `ID` int(100) NOT NULL,
  `NAME` varchar(55) NOT NULL,
  `COUNTRY_ID` int(100) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LIBRARY`
--

INSERT INTO `LIBRARY` (`ID`, `NAME`, `COUNTRY_ID`, `ADDRESS`) VALUES
(1, 'Calgary Public Library', 3, 'Auburn Meadows');

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
(1, 'Penguin Random House', 13),
(2, 'Hachette Livre', 2),
(3, 'Pan Macmillan', 4),
(4, 'HarperCollins', 8),
(5, 'Pearson Education', 10),
(6, 'Bloomsbury', 14),
(7, 'SourceBooks', 1),
(8, 'Chronicle', 3),
(9, 'B&H Publishing', 7),
(10, 'Dover', 9),
(11, 'Abrams', 5),
(12, 'Tyndale House', 6);

-- --------------------------------------------------------

--
-- Table structure for table `USER_CONF`
--

CREATE TABLE `USER_CONF` (
  `ID` int(100) NOT NULL,
  `USER_NAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `PRIVILEDGE_ID` int(1) NOT NULL,
  `EMAIL` varchar(55) DEFAULT NULL,
  `COUNTRY_ID` int(100) NOT NULL,
  `GENDER_ID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER_CONF`
--

INSERT INTO `USER_CONF` (`ID`, `USER_NAME`, `PASSWORD`, `PRIVILEDGE_ID`, `EMAIL`, `COUNTRY_ID`, `GENDER_ID`) VALUES
(1, 'anaka', '123456', 1, 'akarina1390@gmail.com', 15, 2),
(2, 'alegar', '123456', 2, 'ale.sieu@ucalgary.ca', 3, 1);

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
-- Indexes for table `BOOK_LIBRARY_XREF`
--
ALTER TABLE `BOOK_LIBRARY_XREF`
  ADD KEY `BOOK_ID` (`BOOK_ID`),
  ADD KEY `LIBRARY_ID` (`LIBRARY_ID`);

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
-- Indexes for table `LIBRARY`
--
ALTER TABLE `LIBRARY`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `COUNTRY_ID` (`COUNTRY_ID`);

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
  ADD KEY `COUNTRY_ID` (`COUNTRY_ID`),
  ADD KEY `GENDER_ID` (`GENDER_ID`);

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
-- Constraints for table `BOOK_LIBRARY_XREF`
--
ALTER TABLE `BOOK_LIBRARY_XREF`
  ADD CONSTRAINT `book_library_xref_ibfk_1` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`),
  ADD CONSTRAINT `book_library_xref_ibfk_2` FOREIGN KEY (`LIBRARY_ID`) REFERENCES `LIBRARY` (`ID`);

--
-- Constraints for table `BOOK_USER_XREF`
--
ALTER TABLE `BOOK_USER_XREF`
  ADD CONSTRAINT `book_user_xref_ibfk_1` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`),
  ADD CONSTRAINT `book_user_xref_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `USER_CONF` (`ID`);

--
-- Constraints for table `LIBRARY`
--
ALTER TABLE `LIBRARY`
  ADD CONSTRAINT `library_ibfk_1` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY` (`ID`);

--
-- Constraints for table `USER_CONF`
--
ALTER TABLE `USER_CONF`
  ADD CONSTRAINT `user_conf_ibfk_1` FOREIGN KEY (`PRIVILEDGE_ID`) REFERENCES `PRIVILEDGE` (`ID`),
  ADD CONSTRAINT `user_conf_ibfk_2` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY` (`ID`),
  ADD CONSTRAINT `user_conf_ibfk_3` FOREIGN KEY (`GENDER_ID`) REFERENCES `GENDER` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
