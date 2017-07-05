-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2017 at 01:26 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `call_storage`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_call`
--

CREATE TABLE `t_call` (
  `record_id` int(11) NOT NULL,
  `caller` int(11) NOT NULL,
  `receiver` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_event`
--

CREATE TABLE `t_event` (
  `record_id` int(11) NOT NULL,
  `record_event_id` varchar(30) NOT NULL,
  `record_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `call_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_event_type`
--

CREATE TABLE `t_event_type` (
  `EVENT_ID` varchar(30) NOT NULL,
  `EVENT_NAME` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_event_type`
--

INSERT INTO `t_event_type` (`EVENT_ID`, `EVENT_NAME`) VALUES
('EVENT_PICK_UP', 'Pick-up'),
('EVENT_DIAL', 'Dialling'),
('EVENT_CALL_ESTABLISHED', 'Call Established'),
('EVENT_CALL_END', 'Call End'),
('EVENT_HANG_UP', 'Hang-up');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_call`
--
ALTER TABLE `t_call`
  ADD PRIMARY KEY (`record_id`);

--
-- Indexes for table `t_event`
--
ALTER TABLE `t_event`
  ADD PRIMARY KEY (`record_id`),
  ADD KEY `record_event_id` (`record_event_id`);

--
-- Indexes for table `t_event_type`
--
ALTER TABLE `t_event_type`
  ADD PRIMARY KEY (`EVENT_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_call`
--
ALTER TABLE `t_call`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_event`
--
ALTER TABLE `t_event`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_call`
--
ALTER TABLE `t_call`
  ADD CONSTRAINT `t_call_ibfk_1` FOREIGN KEY (`record_id`) REFERENCES `t_event` (`record_id`);

--
-- Constraints for table `t_event`
--
ALTER TABLE `t_event`
  ADD CONSTRAINT `t_event_ibfk_1` FOREIGN KEY (`record_event_id`) REFERENCES `t_event_type` (`EVENT_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
