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
  `organization` varchar(150) NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.attendance: ~2 rows (approximately)
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`id`, `device`, `event_date`, `event_time`, `employee_number`, `employee_name`, `event_type`, `verification_type`, `event_location`, `organization`, `version`) VALUES
	('2c622a66-8d65-4edd-ba3c-02aec7326e98', '3397002440486', '2020-12-25', '15:56:30', '800002', 'Siti', 'UNKNOWN', 'Fingerprint', 'PT Armas Logistic Service', 'PT Armas Logistic Service', 0),
	('b7075e26-48a6-4fd9-b30b-637067380605', '3397002440486', '2020-12-25', '16:08:38', '800001', 'Admin', 'UNKNOWN', 'Fingerprint', 'PT Armas Logistic Service', 'Iclock Cloud Indonesia', 0);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;

-- Dumping structure for table tengkawang.department
DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `id` char(50) NOT NULL,
  `name` char(16) NOT NULL,
  `comment` varchar(200) DEFAULT '',
  `organization` varchar(200) NOT NULL DEFAULT '',
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.department: ~0 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
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
	('1c376799-ef7c-43ea-83e9-3eadd676053d', '3397002440486', '3397002440486', '192.168.1.201', 'Auto Generated on first handshake', 1, 'PT Armas Logistic Service', NULL, 'Online', NULL, NULL);
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
  `organization` varchar(150) NOT NULL,
  `finger_info_id` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.employee: ~3 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `number`, `on_device_name`, `password`, `card`, `privilege`, `version`, `full_name`, `department`, `employee_group`, `organization`, `finger_info_id`) VALUES
	('3fa97b91-b0f0-4999-bf94-2fe67ceda4ad', '800001', 'admin', NULL, '', 'Admin', 1, 'Admin', NULL, '0', 'Iclock Cloud Indonesia', '32ddb285-7d5f-46d8-b5cb-d44a852bf477'),
	('8053c10e-7b50-4e1c-86f5-5c60127f882c', '800002', 'siti', NULL, '', 'User', 2, 'Siti', NULL, '0', 'PT Armas Logistic Service', '67420701-5004-4914-8b6a-1243d9076f4f'),
	('f309a5a8-43f9-42bf-8a12-1d8c16465b60', '800003', 'iis', 'null', '', 'User', 2, 'Iis', NULL, '0', 'PT Armas Logistic Service', '2f54803e-63ca-4fe0-ae47-97a611b9a91c');
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

-- Dumping data for table tengkawang.finger_info: ~3 rows (approximately)
/*!40000 ALTER TABLE `finger_info` DISABLE KEYS */;
INSERT INTO `finger_info` (`id`, `FID`, `FTMP`, `size`, `valid`, `version`) VALUES
	('67420701-5004-4914-8b6a-1243d9076f4f', '0', 'THVTUzIxAAAFNjcECAUHCc7QAAAdN2kBAAAAhdsypDYRABkPQQDUAHE5ZgAfAIAP/QAhNmUPjgA2AOYPVDZCAFUOtACGACI5ewBJAI8PdgBTNjEPeABhAEoOlTZgAFIOpgCkADI5ygBjACcPQwBvNn0OdgBrAHYNHDZuADkPmQC1AF45JAB0ADAPhgCENiEPswCBAJEPMjaDACgP9QBXAJw43ACYAI4OHQClNnEOuwCuADUP5TavAHIPQwBwAKA5gQC+AIIP0AC6NqsP5QC/ADwPszbBAPMPNwAAABc5vADHAHMPpADZNosPLwDgAFMP1DbnAPkPIQDEASQ4AgECAW8MyAEBN1YMMgASAd8O+TYTAVYM8QDfAXM9PwAmAQgP4wAuNy4OCwEyAQoOdTYyAXcP9gD5AcE9VABBAfgPlQBIN40PPABOAVgPu82a9t+bYRHb9kYXkw5aLxZfNPFHJBrkwZdu/vcP18QzygLXnflr8Ic0NALVbxY8cPgQP2QLON8JFDQ+le1Y+DEeLQhs3czQ7Pve8bbWLS+E/pzslCfNOARjXT1jEVsY0QjAJbTX4fttCmIQGfv3xvLoxvjeHc8uUT30+vLxXQ6TKEcfSSItHun2LeY4Nv/Y3f3NfruAvDJEecnmNZWS/ComY/sDkveXG5n7Ic4EPYk6iYduqbVYBX+ASW18f/NfrYPJhjZ9HwuKTp7ktYc+dVfvN79fAy8IgQr3jZvBGRthGG74deuINI/hkRYJ+nzvlNb86A0C8SAjCEODJPLx2p4RjCogIX+WIQ5ieZdZc7Zv7pdxqfa/UYdF2W/1X3aAAZdwx9sW4RHRp2OWwvV2l9enAyCPAQePInIHAMQA1kY5JwFWAQArwDv/QGJ1CwCSAQz4TF4/AasBCf1VOkcGNtkBD/4EAPICf04EADwGdMC+EwVyBgPAODhDBVDFYQQAPQ50wFQKBZMOEzdFWgrFRRQ2wf1UQAoAZhQffMFYwQYAPdB0xfLBwhAAZBpGi5P1wImLBwBr2gz79sEqCgBjIkbDxPLBm8APADXja8ekgsJmxMAIxZE9KChWHwBNRJnExPCTwcPCw8QGxsT0wMLBwsPCB8PE9MPDwsMJAH1EJ2xYwQoAe0Vfw8Cuw6UIALRH7MD7ssINAHZJkwHGw/LHkcTCyATFWEnLHwkAfkwnO/ryzPokBQCxWfTAYTABt1kt/8FBCAVmXS3DwsHFcAoFcmAxwpLDw2sNBZFlNP/7+/87+sXI/D4FAMxl7sJ5JgGZa7eTwlrCmqkVABZyPcIEhJdIw8HB/8M/wwAZRDuAwQoAKbItY7SVBgAkeDdCwwg2OoMpwcHBAXfAWhEANoUrdQTFa/SCGgwAQ4XnwMTywMLCw8LCAQQFNJsrbAMA4V8G+jUBAqUwwwjFEaAbw8HBeQYBx6eDT8AFALexbYQEBeizcD0GAP93d3b3BQBHuReXwQACiyOABQACw+KSFjYYwx54wpSiwGj3KgsAO8QXTH7HbwUAOMkawwQHBY3KcD1GEQA6y39cV8BAVzMUxfrTTGj/ZUM9N/AEBc3eelsVAAYlFnFIwMHAwHRLBGcQNgfnHIfCwLvBXGdEwBEBAfu1K8RSwUZo/QgQ3QQo9mWlBRAqGOzB+vIQEQAGbf45wsVF/sL+VcH4xhD7IE3CDhDsGb9RdmJZCxDwHmI/TMbIwMD/BhEF5EP4FgUQPyoeyAQDFRc9NMgDECf2MME8EdM0d/5cmMAAJxA4V8L9+sIQ0wl7XsHDBxASTX96w/4', '1780', '1', 1),
	('32ddb285-7d5f-46d8-b5cb-d44a852bf477', '0', 'THdTUzIxAAAFNDsECAUHCc7QAAAdNWkBAAAAhdkzvjQhAJgPUQD+AA47iABHAI0PHgBNNKEPwQBOAF4PQjRXAHsPZQCTAII7MwBeAPoPpgBbNAsPjABqAEgPXDRzAH4PvQC2AJA7NwB8AP0PugCPNIQPTgCNAL4PvDSMAJAPQQBnAP87eACsAIAPgACoNHgP7wC0AFMPTDS4AP0PvwB8AIs7lgC6AAwPXQDBNIgPYgDHAMYP3zTZAJIPWQAcAHI7MwDfAHYOWADoNIcPHgDuAKsOhDTwAH0P/QDDAaU69gAKAZkOMQASNZoOJAAaAS0PDTUbAcEOPgDvAek61gA2AX4MiQA8Ne4NvgA+AUIOqzQ+AYcOVQCEAfg5mQBBAYQPzgFENc8PdwBDAccNwjRCAfMO3QCGAd0/6ABLAdEO/gBLNZ4OTwBOAUsNgDRQAYcOJ/zK+Uo7xJOehaIC9wFjPl+HWQeHCf4N2MsrCaMEeYIQcxRHRX56DEoIvILHyJt/QIbJjsSTQz2zAYKH1XaUB9PMMAey/XcJWIc7MHuBRgXO+VMLWLbU/6GDuf/rB0qyuYO5/l2C13TPyV99uIM8fxMARzNmCIcLOIfwBpzLc4ZqBhb+dYcDuHeCtH+WCiv+1LkQC1qFkgYD/xLD3HW6fYYCtAoDNuN7dQu2/nJ9/M5Piwf7/fteCN7BSQo1BQ3iFfo1NwTasAe5/Sjdt8hXf7f5RSmoIlgetP0uBLbw9KhsuXT7sPc1BQhhCaN4AO0L+QCwBgSiIRPVcPFjPP5kynuEKv1yDvb4pEl/gAIFIG+0dYgqWFzBCCnmjPhQYzvh4RG9sxSnwKR08a2hmYUYBu83Cp8Pz5qPwiBJNQOrInsEAFQOCQUQAF8R/fyDRFdmcgcAkRQXpUYNNNEYJML+/wZcDzRSHPr+/f+BWQw0yB8iwP/ATv8RNDUiBsJA//v/xgLBa8EEAL/hIlwhAS0rA2X++FjFYWVdBABVOMxZATRMOoZ9CQCQPxVE/MBODQAggfrFz8D//8P+/gRaAzTfTCBqwgXFQlFJwkQHAGBWTP/F8VoDAGdbBjoEBQJc/T0DAGakDMU4AZBsEP9TBcD7Qg8ADG7wM5JCQTMBVW+DwXgHCAVpcwY+SQkABHYf9MM/eAQAMrl6fTsBOn8AwTg7/2zKUAoAeomAn//E9moGAEqOfbHBADS8kBZbEgCBnvL0/ME1/0fBklMBNDyjenIKAICk+PU0TMAXAAli7Sr2MsL+/v//mP/E9fx0BAB1qEV9ADRAqnf/hBDFfag9/8T9VMD/BcD6zjAJAHSvgLw3+jEBQbF3wFfGAPaCG8AMAE25P0b6yMPAUwYAmn8MUcsKAJLDjIyLZAs0Z8T9/WhEBP/6y/0GAF7HgEPACDRnyQP/YEwF/0w3AVXWfcIOxV3cNMFAwP5dwPIFBerbF8DA+xPFGdnDaTZHRFU0wwBQ6XvDwHEDAOvecvQCADDkdP/OAJ7ah/+MbmgIxX3qt8Nqhx4AhTcGx8hFT+71UsAEwsX2wcHBfMXGbQgFTvSAfsGDE9UVBtPBPjVAREoFBBQhFVODBhD+z0A69BEQKBfk/jo4Q/X9Vf8EEPbeT4UwEAceTHsF1R0nDsGmDhA8JiEoOMo/cgwQQig0wEUERRMQ3CqTBf9yt3PCwMuqA9U7KwXJAxAXMT0GCxVwN+QpM/9FzBBKA+gxM/8HEG87ivWKxAQQZjzGxPkxEApFU/74OggV0UyAwf52wMEQVnsHbAUQV124wjc0AAAAAAAA\n', '1784', '1', 1),
	('2f54803e-63ca-4fe0-ae47-97a611b9a91c', '0', 'TWtTUzIxAAAEKC0ECAUHCc7QAAAcKWkBAAAAhNUhnigZAJEPRADnAIQn7wArAI8PlQA6KH8P4ABOAEsPoShjAJEPhABBAIMnEQCOAGcPOQCLKBAPgQCTAEYPNSiiAGIPgABrAIUnMwC7AFgPWADAKJIPqgDtAFMPUSjuADkObQAqAAAleQDzABsOBwABKQgPbwAIAewM5ygPAYcPdADTAaok3wAbAYMPpgAbKasOlwAfAUINgygiAQMM7ADvAX0nBQEvAXAPVQAxKY4PPQA2AVoPNyg8ARMOgACBAY4nhgBOAX4P7gNbP/oHMgT/897zRip+A1sDsgA27y/TwwJn/yN/lgxPO+IHQQQ+Cx74Ni5CEvfrI4fCgy+vxPxBByvymA6y1ibjwQMa8QP/7dLO8lMjihXn/586D42eB2d9jDZKCWsRjOv9znze8DO09urlGnVHhZKhiHwpEhUmjAQpO4eBhYRBBSiuydQgDZp9xf1sg9qBcIRF+obZsHypXRRWqfkRCnj3ndKn77PooY2QBrkiSY/O98/zkXdfQlpnHA/Z/geJrN/Q88Z/I5e2UwUIOgECeSSDyQB7KA02amb/FMUiBSvBN0NVWsA7w0AgAZcBEFH/vQcEgQAQUVcGAHgBFHDBAwB9BhMFBQRNBgz/WREA0Az+1v7A/sHAPwXAVukXAA4W+kCQN0FxXE0JAJ4b1sBhWAQAQB+GwgXCFShJIgnA/0SdalzrBABAJoBvwQBIFnxzFgAIQCH9K9Y/RFbAZYndAAVh/MYq//7AkUxsVsA8DQAIVSg2+mfBPg0ACV8o/0TXRv5ZCACpoBZS6cHAGAACbyzA+hPAQf9T/2KlbxwoB3brPTj/hcEy6GPA/8DADMV/h6F/kIvCGADDheTVwDE+//7BO1XE6MDAUgwAfUqDxKzDwcHBixnFApTP/sA4/ytGO8DESsD9wlwOAECUC9b+VcDBwIsEBgRVlIN4wgYA6aNjq8IZAAOk2js3+BX+NT3BwEkFaQwoMadkhIMTxXuqq8DBnsLBwgfAxuqTWxMANbgiNPsB//9ndIwZxQO+8jkwMv/A/IPBxdWKfAkALbublsShCgAxv1eMtMOXPQGZwpfBxb+glLrFwMGMDQCv3v7U/MBcw/7DmgUETuRMxYUIAGPpkkGnCgBs6/Q//vtZcgkAfPEXOnzGcxwBCPGJOI5lx9fFwcLAwsOswsZHdwQAUvNDAcIDKFnzN4yZBcV58wzAiRcA2/1JwPltw8DCw8PEBsVltXcnAQf+gwUr+26SwsLDoJMBiI/owv7CeMB2wRELLmgiBBEHFLI9ADiwHQNzCBHOHXDo/MHAPAoRziRp1sL8WDEIEE8nCOlwXQYRECmbwf8DAhA2PAb8wREEYHU7BRCLTsPCUCsRp1sA/wTVj1hVwf8', '1420', '1', 0);
/*!40000 ALTER TABLE `finger_info` ENABLE KEYS */;

-- Dumping structure for table tengkawang.invoice
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` char(50) NOT NULL,
  `number` char(50) NOT NULL,
  `issued_date` date NOT NULL,
  `due_date` date NOT NULL,
  `payment_date` datetime DEFAULT NULL,
  `total_device` decimal(10,0) NOT NULL DEFAULT 0,
  `unit_price` decimal(10,0) NOT NULL DEFAULT 0,
  `organization` varchar(250) NOT NULL DEFAULT '0',
  `invoice_status` char(15) NOT NULL DEFAULT 'UNPAID',
  `version` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.invoice: ~0 rows (approximately)
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` (`id`, `number`, `issued_date`, `due_date`, `payment_date`, `total_device`, `unit_price`, `organization`, `invoice_status`, `version`) VALUES
	('b2462740-b4e6-4ba1-8932-7b5acd9d208c', '2812200', '2020-12-28', '2020-12-05', NULL, 1, 500000, 'PT Armas Logistic Service', 'UNPAID', 0);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;

-- Dumping structure for table tengkawang.menu
DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id` char(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `comment` varchar(250) NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.menu: ~10 rows (approximately)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`, `name`, `comment`, `version`) VALUES
	('070176d3-3c9c-48d3-9284-f8f4138c3dc3', 'User', 'User management', 0),
	('077ebe5f-6707-47e9-bd7b-0111b1ed71fc', 'Attendance', 'Attendance Management', 0),
	('309ae3b5-6f66-4011-a33c-82ad36966ff7', 'Access Menu', 'Security/Access Menu', 0),
	('33fe9067-f922-48e1-8ad0-6a984d965211', 'Department', 'Department Management', 0),
	('4a98e06f-6e19-41f5-b4b2-5e6ce236ed8e', 'Invoice', 'Invoice Management', 0),
	('6f851baa-48a3-4b81-aa8a-edf0b1e32eae', 'Access Role', 'Security/Access Role', 0),
	('7ced3936-e225-4a44-9f8d-c05e9e3185f6', 'Company Structur', 'Company Structure Management', 1),
	('8a676d7f-bd60-48ec-933d-8e3a3a08fade', 'Device', 'Device Management', 0),
	('93336971-bd75-48b0-90fc-0c327f0a50f5', 'Employee', 'Employee Management', 0),
	('f1c16f65-bd9b-4579-8867-1d0b94815ab5', 'Worktime', 'Worktime Management', 0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

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

-- Dumping data for table tengkawang.organization: ~2 rows (approximately)
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`id`, `name`, `parent`, `comment`, `version`) VALUES
	('000000', 'Iclock Cloud Indonesia', '', '', 0),
	('000001', 'PT Armas Logistic Service', 'Iclock Cloud Indonesia', '', 1);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;

-- Dumping structure for table tengkawang.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` char(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `organization` varchar(150) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.role: ~1 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `comment`, `organization`, `version`) VALUES
	('00000000', 'System Administrator', 'root account', 'Iclock Cloud Indonesia', 0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table tengkawang.role_access
DROP TABLE IF EXISTS `role_access`;
CREATE TABLE IF NOT EXISTS `role_access` (
  `id` char(50) NOT NULL,
  `fk_role` char(50) NOT NULL,
  `fk_menu` char(50) NOT NULL,
  `can_create` char(1) NOT NULL,
  `can_read` char(1) NOT NULL,
  `can_update` char(1) NOT NULL,
  `can_delete` char(1) NOT NULL,
  `can_print` char(1) NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.role_access: ~10 rows (approximately)
/*!40000 ALTER TABLE `role_access` DISABLE KEYS */;
INSERT INTO `role_access` (`id`, `fk_role`, `fk_menu`, `can_create`, `can_read`, `can_update`, `can_delete`, `can_print`, `version`) VALUES
	('01616a3d-9f39-4588-81e1-fc3151c20a22', '00000000', '4a98e06f-6e19-41f5-b4b2-5e6ce236ed8e', '1', '1', '1', '1', '1', 1),
	('10a43759-f113-4a1a-afbe-78e1a7a8f93b', '00000000', '93336971-bd75-48b0-90fc-0c327f0a50f5', '1', '1', '1', '1', '1', 0),
	('1f6f5956-7f36-461a-86d3-d05e7e33dc88', '00000000', '077ebe5f-6707-47e9-bd7b-0111b1ed71fc', '1', '1', '1', '1', '1', 0),
	('2b3b4d79-1ada-4b15-a393-1b6a0ebf3869', '00000000', '7ced3936-e225-4a44-9f8d-c05e9e3185f6', '1', '1', '1', '1', '1', 0),
	('35aaa1e5-1546-4467-adea-d291dfa994ae', '00000000', 'f1c16f65-bd9b-4579-8867-1d0b94815ab5', '1', '1', '1', '1', '1', 0),
	('3c07ae5e-4160-451b-a4e2-da12861ffcdf', '00000000', '8a676d7f-bd60-48ec-933d-8e3a3a08fade', '1', '1', '1', '1', '1', 0),
	('6944f654-253c-4dca-a84a-658c8842c373', '00000000', '309ae3b5-6f66-4011-a33c-82ad36966ff7', '1', '1', '1', '1', '1', 0),
	('9ab4a37f-b785-40ed-a3a0-af45e19cfd0f', '00000000', '33fe9067-f922-48e1-8ad0-6a984d965211', '1', '1', '1', '1', '1', 0),
	('d7b3ff19-53f9-4ad7-a522-8ae25f7aaca3', '00000000', '6f851baa-48a3-4b81-aa8a-edf0b1e32eae', '1', '1', '1', '1', '1', 0),
	('d8a59c16-23a4-4553-a86c-60b3c56a8cdc', '00000000', '070176d3-3c9c-48d3-9284-f8f4138c3dc3', '1', '1', '1', '1', '1', 0);
/*!40000 ALTER TABLE `role_access` ENABLE KEYS */;

-- Dumping structure for table tengkawang.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` char(50) NOT NULL,
  `name` char(50) NOT NULL,
  `password` varchar(200) NOT NULL DEFAULT '',
  `role` varchar(200) NOT NULL,
  `organization` varchar(200) NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `password`, `role`, `organization`, `version`) VALUES
	('00000', 'admin', '1233LIlbNUu9VzhQkzcV7ZNRXx+Q7Z4Z9Tl7lJx/AS++K/mAsHibxuwoZ696eSxU', 'System Administrator', 'Iclock Cloud Indonesia', 0);
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
	('3a2452a3-8dcb-4020-8cce-1cf43bde0077', '09:00:00', '17:00:00', '2000-01-01', NULL, 'REGULER', 0, 'Reguler Worktime', ''),
	('8a2e31af-0ef5-401f-8131-428391cd9c4a', '17:01:00', '08:59:00', '2000-01-01', NULL, 'OVERTIME', 0, 'Work Overtime', '');
/*!40000 ALTER TABLE `work_time` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
