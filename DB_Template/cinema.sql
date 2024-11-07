-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2024 at 03:57 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `Mid` varchar(5) NOT NULL,
  `MName` varchar(20) NOT NULL,
  `Duration` varchar(10) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`Mid`, `MName`, `Duration`, `price`) VALUES
('AEG', 'Avengers End Game', '3 hours', 800),
('AM1', 'Ant-Man', '1 hour 57 ', 700),
('AM2', 'Ant-Man and The Wasp', '1 hour 58 ', 700),
('AV1', 'The Avengers', '2 hours 23', 800),
('BP', 'Black Panther', '2 hours 14', 750),
('DRS', 'Doctor Strange', '1 hour 55 ', 750),
('HUL', 'The Incredible Hulk', '1 hour 52 ', 650),
('IM1', 'Iron Man', '2 hours 6 ', 700),
('IM2', 'Iron Man 2', '2 hours 4 ', 700),
('IM3', 'Iron Man 3', '2 hours 10', 750),
('TH1', 'Thor', '1 hour 55 ', 650),
('TH2', 'Thor: The Dark World', '1 hour 52 ', 650);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(5) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `password` varchar(12) NOT NULL,
  `role` enum('Manager','Employee') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `name`, `address`, `phone`, `password`, `role`) VALUES
('a', 'Sam Smith', 'New York', '0123456789', 'a', 'Manager'),
('b', 'Peter Parker', 'New York', '0192837465', 'b', 'Employee');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`Mid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
