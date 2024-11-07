-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2024 at 09:58 PM
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
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `dateOfBirth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerId`, `name`, `phone`, `dateOfBirth`) VALUES
(1, 'John Doe', '1234567890', '1990-01-01'),
(2, 'Jane Smith', '1234567891', '1985-02-14'),
(3, 'Mike Brown', '1234567892', '1992-03-22'),
(4, 'Alice Johnson', '1234567893', '1993-04-18'),
(5, 'Chris Lee', '1234567894', '1988-05-30'),
(6, 'Karen White', '1234567895', '1991-06-15'),
(7, 'Dave Black', '1234567896', '1984-07-25'),
(8, 'Eva Green', '1234567897', '1995-08-08'),
(9, 'Paul Scott', '1234567898', '1990-09-12'),
(10, 'Laura Hill', '1234567899', '1996-10-05'),
(11, 'Tom Adams', '1234567800', '1982-11-09'),
(12, 'Nancy Brown', '1234567801', '1989-12-24'),
(13, 'Alex Turner', '1234567802', '1994-01-10'),
(14, 'Sara Long', '1234567803', '1987-02-20'),
(16, 'Sophia Rose', '1234567805', '1992-04-01'),
(17, 'Liam Miller', '1234567806', '1985-05-17'),
(18, 'Olivia Clark', '1234567807', '1991-06-06'),
(19, 'Emma Lewis', '1234567808', '1983-07-04'),
(20, 'James Walker', '1234567809', '1990-08-28');

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
(27, '2024-11-08', '10:30 to 12:30', 'AM1', 200),
(28, '2024-11-07', '10:30 to 12:30', 'AM1', 200),
(29, '2024-11-06', '10:30 to 12:30', 'AM1', 200),
(30, '2024-11-05', '10:30 to 12:30', 'AM1', 200),
(31, '2024-11-04', '10:30 to 12:30', 'AM1', 200),
(32, '2024-11-03', '10:30 to 12:30', 'AM1', 200),
(33, '2024-11-02', '10:30 to 12:30', 'AM1', 200),
(34, '2024-11-01', '10:30 to 12:30', 'AM1', 200),
(35, '2024-11-11', '10:30 to 12:30', 'AM1', 200),
(36, '2024-11-10', '10:30 to 12:30', 'AM1', 200),
(37, '2024-11-09', '10:30 to 12:30', 'AM1', 200);

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
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerId`);

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
  ADD UNIQUE KEY `date` (`date`,`timeslot`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `showtime`
--
ALTER TABLE `showtime`
  MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
