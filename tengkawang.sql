-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for tengkawang
DROP DATABASE IF EXISTS `tengkawang`;
CREATE DATABASE IF NOT EXISTS `tengkawang` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tengkawang`;

-- Dumping structure for table tengkawang.attendance
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `id` char(50) NOT NULL,
  `device` varchar(150) NOT NULL DEFAULT '',
  `event_date` date NOT NULL,
  `event_time` time NOT NULL,
  `employee_number` char(8) NOT NULL DEFAULT '',
  `employee_name` varchar(150) NOT NULL DEFAULT '',
  `event_type` char(12) NOT NULL,
  `verification_type` char(50) NOT NULL DEFAULT 'IN',
  `event_location` varchar(150) NOT NULL DEFAULT 'IN',
  `version` bigint(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.attendance: ~3 rows (approximately)
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`id`, `device`, `event_date`, `event_time`, `employee_number`, `employee_name`, `event_type`, `verification_type`, `event_location`, `version`) VALUES
	('13b3ab59-e2c6-470d-a9e3-d6e26bce607c', '3397002440486', '2020-12-16', '06:06:31', '121212', 'Udin Samsudin', 'UNKNOWN', 'Password', 'CABANG B', 0),
	('1ff9ccd6-1ad1-486d-9f9f-3ae3949c80d6', '3397002440486', '2020-12-16', '10:35:14', '121212', 'Udin Samsudin', 'OUT', 'Password', 'CABANG B', 0),
	('4b886f3a-b51d-4dbc-90d4-a11afc5a91cb', '3397002440486', '2020-12-16', '16:23:23', '800002', 'Jasmin', 'IN', 'Fingerprint', 'CABANG B', 0);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;

-- Dumping structure for table tengkawang.department
DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `id` char(50) NOT NULL,
  `name` char(16) NOT NULL,
  `comment` varchar(200) DEFAULT '',
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.department: ~2 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `name`, `comment`, `version`) VALUES
	('e81a35f7-c77a-4019-adc9-646ae93fdf98', 'D1', '', 0),
	('fe7b79a6-665a-4c11-85e1-f1414252ecbc', 'D2', '', 0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- Dumping structure for table tengkawang.device
DROP TABLE IF EXISTS `device`;
CREATE TABLE IF NOT EXISTS `device` (
  `id` char(50) NOT NULL,
  `serial` char(50) NOT NULL,
  `name` char(50) DEFAULT NULL,
  `ip` char(50) DEFAULT NULL,
  `comment` varchar(150) DEFAULT '',
  `version` bigint(20) NOT NULL DEFAULT 0,
  `organization` varchar(150) DEFAULT NULL,
  `option` varchar(250) DEFAULT NULL,
  `status` char(25) DEFAULT 'Offline',
  `type` char(25) DEFAULT 'X100C',
  `event_type` char(3) DEFAULT 'IN',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`serial`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='attendance device';

-- Dumping data for table tengkawang.device: ~1 rows (approximately)
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` (`id`, `serial`, `name`, `ip`, `comment`, `version`, `organization`, `option`, `status`, `type`, `event_type`) VALUES
	('9a574352-e8d5-4561-b5fa-fda00d9a6ea6', '3397002440486', '3397002440486', '192.168.1.201', 'Auto Generated on first handshake', 1, 'CABANG B', NULL, 'Online', 'X100C', 'IN');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;

-- Dumping structure for table tengkawang.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` char(50) NOT NULL,
  `number` char(8) DEFAULT NULL,
  `on_device_name` char(50) DEFAULT NULL,
  `password` char(8) DEFAULT NULL,
  `card` char(10) DEFAULT NULL,
  `privilege` varchar(100) NOT NULL DEFAULT 'User',
  `version` bigint(20) NOT NULL DEFAULT 0,
  `full_name` varchar(150) DEFAULT NULL,
  `department` char(50) DEFAULT NULL,
  `employee_group` char(50) DEFAULT 'User',
  `finger_info_id` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.employee: ~2 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `number`, `on_device_name`, `password`, `card`, `privilege`, `version`, `full_name`, `department`, `employee_group`, `finger_info_id`) VALUES
	('2b8f3573-0241-4a0f-8a63-37dc1edf9612', '121212', 'UdinS', '123456', '', 'User', 2, 'Udin Samsudin', 'D1', '0', NULL),
	('eba5ff1c-799b-4a3c-a261-aa4dc088030b', '800002', 'Jas', '123456', '', 'User', 0, 'Jasmin', 'D1', '0', NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table tengkawang.finger_info
DROP TABLE IF EXISTS `finger_info`;
CREATE TABLE IF NOT EXISTS `finger_info` (
  `id` char(50) NOT NULL,
  `FID` char(50) NOT NULL,
  `FTMP` longtext NOT NULL,
  `size` char(50) NOT NULL DEFAULT '0',
  `valid` char(50) NOT NULL DEFAULT '0',
  `version` bigint(20) NOT NULL DEFAULT 0,
  KEY `Index 1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.finger_info: ~0 rows (approximately)
/*!40000 ALTER TABLE `finger_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `finger_info` ENABLE KEYS */;

-- Dumping structure for table tengkawang.organization
DROP TABLE IF EXISTS `organization`;
CREATE TABLE IF NOT EXISTS `organization` (
  `id` char(50) NOT NULL,
  `name` varchar(150) NOT NULL DEFAULT '',
  `parent` varchar(150) DEFAULT '',
  `comment` varchar(200) DEFAULT '',
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Company structure';

-- Dumping data for table tengkawang.organization: ~3 rows (approximately)
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`id`, `name`, `parent`, `comment`, `version`) VALUES
	('000000', 'PT A', '', '', 0),
	('000001', 'CABANG B', 'PT A', 'Update 2', 1),
	('01555143-0fce-48d4-a17c-91f54bcdd667', 'Cabang C', 'PT A', 'Update 1', 1);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;

-- Dumping structure for table tengkawang.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` char(50) NOT NULL,
  `name` char(50) NOT NULL,
  `password` varchar(200) NOT NULL DEFAULT '',
  `version` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `password`, `version`) VALUES
	('00000', 'admin', '1233LIlbNUu9VzhQkzcV7ZNRXx+Q7Z4Z9Tl7lJx/AS++K/mAsHibxuwoZ696eSxU', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table tengkawang.work_time
DROP TABLE IF EXISTS `work_time`;
CREATE TABLE IF NOT EXISTS `work_time` (
  `id` char(50) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date DEFAULT NULL,
  `type` char(10) NOT NULL DEFAULT 'REGULER',
  `version` bigint(20) NOT NULL DEFAULT 0,
  `name` varchar(200) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.work_time: ~2 rows (approximately)
/*!40000 ALTER TABLE `work_time` DISABLE KEYS */;
INSERT INTO `work_time` (`id`, `start_time`, `end_time`, `valid_from`, `valid_to`, `type`, `version`, `name`, `comment`) VALUES
	('279bab63-20c4-4542-8d03-46fd2d960d8e', '08:00:00', '17:00:00', '2019-12-01', NULL, 'REGULER', 0, 'Jam Kerja Reguler', ''),
	('519ffd46-ab54-43fc-83b5-e0d3c051d6a4', '17:01:00', '23:59:00', '2019-12-01', NULL, 'OVERTIME', 0, 'Jam Kerja Lembur', '');
/*!40000 ALTER TABLE `work_time` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
