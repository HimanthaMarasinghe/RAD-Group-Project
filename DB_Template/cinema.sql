-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2024 at 05:15 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinema`
--
CREATE DATABASE IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `cinema`;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `name` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `dateOfBirth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`name`, `phone`, `dateOfBirth`) VALUES
('Tom Adams', '1234567800', '1982-11-09'),
('Nancy Brown', '1234567801', '1989-12-24'),
('Alex Turner', '1234567802', '1994-01-10'),
('Sara Long', '1234567803', '1987-02-20'),
('Sophia Rose', '1234567805', '1992-04-01'),
('Liam Miller', '1234567806', '1985-05-17'),
('Olivia Clark', '1234567807', '1991-06-06'),
('Emma Lewis', '1234567808', '1983-07-04'),
('James Walker', '1234567809', '1990-08-28'),
('John Doe', '1234567890', '1990-01-01'),
('Jane Smith', '1234567891', '1985-02-14'),
('Alice Johnson', '1234567893', '1993-04-18'),
('Chris Lee', '1234567894', '1988-05-30'),
('Karen White', '1234567895', '1991-06-15'),
('Dave Black', '1234567896', '1984-07-25'),
('Eva Green', '1234567897', '1995-08-08'),
('Paul Scott', '1234567898', '1990-09-12');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `Mid` varchar(5) NOT NULL,
  `MName` varchar(255) NOT NULL,
  `Duration` varchar(10) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`Mid`, `MName`, `Duration`, `price`) VALUES
('AEG', 'Avengers End Game', '3 hours', 800),
('AM1', 'Ant-Man', '1h 57 m', 700),
('BP', 'Black Panther', '2 hours 14', 750),
('DRS', 'Doctor Strange', '1 hour 55 ', 750),
('HUL', 'The Incredible Hulk', '1 hour 52 ', 650),
('IM1', 'Iron Man', '2 hours 6 ', 700),
('TH1', 'Thor', '1 hour 55 ', 650),
('TH2', 'Thor: The Dark World', '1 hour 52 ', 650);

-- --------------------------------------------------------

--
-- Table structure for table `showtime`
--

CREATE TABLE `showtime` (
  `sid` int(11) NOT NULL,
  `date` date NOT NULL,
  `timeslot` varchar(20) NOT NULL,
  `mid` varchar(5) NOT NULL,
  `availableSeats` int(11) NOT NULL DEFAULT 200
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `showtime`
--

INSERT INTO `showtime` (`sid`, `date`, `timeslot`, `mid`, `availableSeats`) VALUES
(31, '2024-11-04', '10:30 to 12:30', 'AM1', 200),
(32, '2024-11-03', '10:30 to 12:30', 'AM1', 200),
(33, '2024-11-02', '10:30 to 12:30', 'AM1', 200),
(35, '2024-11-11', '10:30 to 12:30', 'AM1', 185),
(36, '2024-11-10', '10:30 to 12:30', 'AM1', 200),
(37, '2024-11-09', '10:30 to 12:30', 'AM1', 200),
(40, '2024-12-07', '13:30 to 15:30', 'AEG', 199),
(41, '2024-11-06', '13:30 to 15:30', 'AEG', 200),
(42, '2024-11-05', '16:30 to 18:30', 'AEG', 200),
(43, '2024-11-04', '13:30 to 15:30', 'AEG', 200),
(44, '2024-11-03', '13:30 to 15:30', 'AEG', 200),
(45, '2024-11-02', '13:30 to 15:30', 'AEG', 200),
(46, '2024-11-01', '13:30 to 15:30', 'AEG', 198),
(47, '2024-11-09', '20:00 to 22:00', 'BP', 200),
(48, '2024-11-08', '20:00 to 22:00', 'BP', 200),
(49, '2024-11-07', '20:00 to 22:00', 'BP', 200),
(50, '2024-11-06', '20:00 to 22:00', 'BP', 200),
(51, '2024-11-05', '20:00 to 22:00', 'BP', 200),
(52, '2024-11-04', '20:00 to 22:00', 'BP', 200),
(53, '2024-11-03', '20:00 to 22:00', 'BP', 200),
(54, '2024-12-31', '13:30 to 15:30', 'AEG', 200),
(56, '2025-11-22', '20:00 to 22:00', 'HUL', 200),
(58, '2025-11-20', '20:00 to 22:00', 'HUL', 200),
(132, '2024-11-23', '10:30 to 12:30', 'AM1', 200),
(133, '2024-11-22', '10:30 to 12:30', 'AM1', 200),
(134, '2024-11-21', '10:30 to 12:30', 'AM1', 200),
(135, '2024-11-20', '10:30 to 12:30', 'AM1', 200),
(136, '2024-11-19', '10:30 to 12:30', 'AM1', 200),
(137, '2024-11-18', '10:30 to 12:30', 'AM1', 200),
(138, '2024-11-17', '10:30 to 12:30', 'AM1', 200),
(139, '2024-11-30', '10:30 to 12:30', 'AM1', 200),
(141, '2024-12-07', '10:30 to 12:30', 'AM1', 200),
(142, '2024-12-06', '10:30 to 12:30', 'AM1', 200),
(143, '2024-12-05', '10:30 to 12:30', 'AM1', 200),
(144, '2024-12-04', '10:30 to 12:30', 'AM1', 200),
(145, '2024-12-03', '10:30 to 12:30', 'AM1', 200),
(146, '2024-12-02', '10:30 to 12:30', 'AM1', 200),
(147, '2024-12-01', '10:30 to 12:30', 'AM1', 200),
(148, '2024-11-02', '20:00 to 22:00', 'DRS', 200),
(149, '2024-11-01', '20:00 to 22:00', 'DRS', 200),
(150, '2024-11-20', '13:30 to 15:30', 'DRS', 200),
(154, '2022-11-04', '10:30 to 12:30', 'AM1', 200),
(155, '2022-11-03', '10:30 to 12:30', 'AM1', 200),
(156, '2022-11-02', '10:30 to 12:30', 'AM1', 200),
(157, '2022-11-01', '10:30 to 12:30', 'AM1', 200);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `ticketID` int(11) NOT NULL,
  `showTimeId` int(11) NOT NULL,
  `customerPhone` varchar(10) DEFAULT NULL,
  `empUserName` varchar(5) DEFAULT NULL,
  `seatNo` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`ticketID`, `showTimeId`, `customerPhone`, `empUserName`, `seatNo`) VALUES
(24, 40, '1234567890', 'a', 'A1'),
(25, 46, '1234567890', 'a', 'A1'),
(26, 46, '1234567891', 'a', 'A2'),
(36, 35, '1234567893', 'a', 'D12'),
(37, 35, '1234567894', 'a', 'G6'),
(38, 35, '1234567895', 'a', 'G8'),
(39, 35, '1234567896', 'a', 'F10'),
(40, 35, '1234567897', 'a', 'I10'),
(41, 35, '1234567898', 'a', 'I6'),
(42, 35, '1234567898', 'a', 'F14'),
(43, 35, '1234567890', 'a', 'I14'),
(44, 35, '1234567895', 'a', 'J13'),
(45, 35, '1234567898', 'a', 'H16'),
(46, 35, '1234567897', 'a', 'F9'),
(47, 35, '1234567897', 'a', 'F8'),
(48, 35, '1234567891', 'a', 'G9'),
(49, 35, '1234567891', 'a', 'G10'),
(50, 35, '1234567891', 'a', 'E10');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT 'DEFAULT',
  `role` enum('Manager','Employee') NOT NULL DEFAULT 'Employee'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `name`, `address`, `phone`, `password`, `role`) VALUES
('a', 'Himantha Marasinghe', 'Polgahawela', '0987654321', 'a', 'Manager'),
('ANJ', 'Anjana Nadeeshan', 'Anuradhapura', '0987654321', 'Default', 'Manager'),
('emp', 'Marasinghe Himantha', 'Kurunegala', '1234567890', 'DEFAULT', 'Employee'),
('emp2', 'Ranasinghe Seniru', 'Nugegoda', '1212121212', 'DEFAULT', 'Employee'),
('emp3', 'Yudhara Noji', 'Nugegoda', '2323232323', 'DEFAULT', 'Employee'),
('emp4', 'Herath Rangika', 'Colombo', '3434343434', 'DEFAULT', 'Employee'),
('emp5', 'Nadeeshan Anjana', 'Anuradhapura', '8787878787', 'DEFAULT', 'Employee'),
('n', 'Noji Yudhara', 'Nugegoda', '1234567890', 'DEFAULT', 'Manager'),
('r', 'Rangika Herath', 'Colombo', '0123456789', 'r', 'Manager'),
('s', 'Seniru Ranasinghe', 'Nugegoda', '0123456789', 's', 'Manager');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`phone`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`Mid`);

--
-- Indexes for table `showtime`
--
ALTER TABLE `showtime`
  ADD PRIMARY KEY (`sid`),
  ADD UNIQUE KEY `date` (`date`,`timeslot`),
  ADD KEY `mid` (`mid`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticketID`),
  ADD KEY `showTimeId` (`showTimeId`) USING BTREE,
  ADD KEY `tickets_ibfk_1` (`customerPhone`),
  ADD KEY `empUserName` (`empUserName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `showtime`
--
ALTER TABLE `showtime`
  MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=158;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `ticketID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `showtime`
--
ALTER TABLE `showtime`
  ADD CONSTRAINT `showtime_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `movie` (`Mid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`customerPhone`) REFERENCES `customer` (`phone`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`empUserName`) REFERENCES `users` (`username`) ON DELETE SET NULL,
  ADD CONSTRAINT `tickets_ibfk_3` FOREIGN KEY (`showTimeId`) REFERENCES `showtime` (`sid`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
