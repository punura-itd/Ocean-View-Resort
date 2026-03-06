-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 06, 2026 at 08:38 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ocean_view_resort`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `bill_id` int NOT NULL AUTO_INCREMENT,
  `reservation_no` varchar(20) NOT NULL,
  `nights` int NOT NULL,
  `rate_per_night` decimal(10,2) NOT NULL,
  `total` decimal(12,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bill_id`),
  UNIQUE KEY `uq_bills_reservation_no` (`reservation_no`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`bill_id`, `reservation_no`, `nights`, `rate_per_night`, `total`, `created_at`) VALUES
(1, 'RES-0001', 7, 25000.00, 175000.00, '2026-02-26 06:14:17'),
(2, 'RES-0002', 7, 25000.00, 175000.00, '2026-02-26 06:48:45'),
(3, 'RES-0005', 2, 25000.00, 50000.00, '2026-03-03 09:34:02'),
(4, 'RES-0003', 7, 40000.00, 280000.00, '2026-03-05 17:42:07'),
(5, 'RES-0004', 7, 40000.00, 280000.00, '2026-03-06 11:21:26');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `reservation_no` varchar(20) NOT NULL,
  `guest_name` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `room_type` varchar(20) DEFAULT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  PRIMARY KEY (`reservation_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_no`, `guest_name`, `address`, `contact`, `room_type`, `check_in`, `check_out`) VALUES
('RES-0018', 'dulan', '199, Galle road, Galle', '0771234567', 'DELUXE', '2026-03-03', '2026-03-04'),
('RES-0019', 'dulan', '199, Galle road, Galle', '0771234567', 'STANDARD', '2026-03-03', '2026-03-04'),
('RES-0004', 'Ramiru', 'battaramulla', '0732748373', 'SUITE', '2026-03-05', '2026-03-12'),
('RES-0005', 'Chandupa', '155', '0732748373', 'DELUXE', '2026-03-03', '2026-03-05'),
('RES-0007', 'dulan', 'sdvwv', '0771234567', 'SUITE', '2026-03-03', '2026-03-04'),
('RES-0008', 'dulan', 'sdvwv', '0771234567', 'STANDARD', '2026-03-03', '2026-03-06'),
('RES-0009', 'dulan', 'sdvwv', '0771234567', 'DELUXE', '2026-03-04', '2026-03-05'),
('RES-0010', 'dulan', 'sdvwv', '0771234567', 'DELUXE', '2026-03-03', '2026-03-04'),
('RES-0011', 'dulan', 'sdvwv', '0771234567', 'DELUXE', '2026-03-03', '2026-03-04'),
('RES-0012', 'dulan', 'sdvwv', '0771234567', 'DELUXE', '2026-03-03', '2026-03-04'),
('RES-0013', 'dulan', 'sdvwv', '0771234567', 'STANDARD', '2026-03-02', '2026-03-04'),
('RES-0014', 'dulan', 'sdvwv', '0771234567', 'DELUXE', '2026-03-03', '2026-03-04'),
('RES-0015', 'dulan', 'sdvwv', '0771234567', 'STANDARD', '2026-03-03', '2026-03-04'),
('RES-0016', 'dulan', 'sdvwv', '0771234567', 'DELUXE', '2026-03-03', '2026-03-04'),
('RES-0017', 'dulan', '199, Galle road, Galle', '0771234567', 'DELUXE', '2026-03-03', '2026-03-04');

-- --------------------------------------------------------

--
-- Table structure for table `room_rates`
--

DROP TABLE IF EXISTS `room_rates`;
CREATE TABLE IF NOT EXISTS `room_rates` (
  `room_type` varchar(20) NOT NULL,
  `rate_per_night` decimal(10,2) NOT NULL,
  PRIMARY KEY (`room_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `room_rates`
--

INSERT INTO `room_rates` (`room_type`, `rate_per_night`) VALUES
('STANDARD', 15000.00),
('DELUXE', 25000.00),
('SUITE', 40000.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(20) DEFAULT 'staff',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', '1234', 'admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
