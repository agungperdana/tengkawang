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

-- Dumping data for table tengkawang.attendance: ~1 rows (approximately)
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`id`, `device`, `event_date`, `event_time`, `employee_number`, `employee_name`, `event_type`, `verification_type`, `event_location`, `organization`, `version`) VALUES
	('32748beb-917c-4948-b7db-69fe598dd73f', '3397002440486', '2020-12-25', '16:08:38', '800001', '800001', 'IN', 'Fingerprint', 'PT Armas Logistic Service', 'PT Armas Logistic Service', 0);
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
  `event_type` char(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`serial`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='attendance device';

-- Dumping data for table tengkawang.device: ~0 rows (approximately)
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.employee: ~4 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `number`, `on_device_name`, `password`, `card`, `privilege`, `version`, `full_name`, `department`, `employee_group`, `organization`) VALUES
	('2611b3a1-cc1e-44ab-b95d-b55daaceee68', '555555', 'simu', '555555', NULL, 'User', 0, 'Siti Munawaroh', NULL, '0', 'DEFAULT'),
	('4d54fc4b-6605-4bfe-b141-5df546d25a07', '15739', 'Siti', '15739', '', 'User', 0, 'Siti', NULL, '0', 'Iclock Cloud Indonesia'),
	('5a281af4-803b-4e14-b960-fb02814592a3', '800003', 'iis', 'null', '', 'User', 0, 'iis', NULL, '0', 'PT Armas Logistic Service'),
	('f27214e2-4cce-4734-b584-113161d69da6', '800001', '800001', NULL, NULL, 'User', 0, '800001', NULL, '0', 'PT Armas Logistic Service');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table tengkawang.finger_info
DROP TABLE IF EXISTS `finger_info`;
CREATE TABLE IF NOT EXISTS `finger_info` (
  `id` char(50) NOT NULL,
  `FID` char(50) NOT NULL,
  `FTMP` longtext NOT NULL,
  `size` char(50) NOT NULL DEFAULT '0',
  `valid` char(50) NOT NULL DEFAULT '0',
  `fk_employee` char(50) NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT 0,
  KEY `Index 1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.finger_info: ~4 rows (approximately)
/*!40000 ALTER TABLE `finger_info` DISABLE KEYS */;
INSERT INTO `finger_info` (`id`, `FID`, `FTMP`, `size`, `valid`, `fk_employee`, `version`) VALUES
	('77eb2d70-2f9e-4a6a-82f4-2769f4f3a8e4', '0', 'THdTUzIxAAAFNDsECAUHCc7QAAAdNWkBAAAAhdkzvjQhAJgPUQD+AA47iABHAI0PHgBNNKEPwQBOAF4PQjRXAHsPZQCTAII7MwBeAPoPpgBbNAsPjABqAEgPXDRzAH4PvQC2AJA7NwB8AP0PugCPNIQPTgCNAL4PvDSMAJAPQQBnAP87eACsAIAPgACoNHgP7wC0AFMPTDS4AP0PvwB8AIs7lgC6AAwPXQDBNIgPYgDHAMYP3zTZAJIPWQAcAHI7MwDfAHYOWADoNIcPHgDuAKsOhDTwAH0P/QDDAaU69gAKAZkOMQASNZoOJAAaAS0PDTUbAcEOPgDvAek61gA2AX4MiQA8Ne4NvgA+AUIOqzQ+AYcOVQCEAfg5mQBBAYQPzgFENc8PdwBDAccNwjRCAfMO3QCGAd0/6ABLAdEO/gBLNZ4OTwBOAUsNgDRQAYcOJ/zK+Uo7xJOehaIC9wFjPl+HWQeHCf4N2MsrCaMEeYIQcxRHRX56DEoIvILHyJt/QIbJjsSTQz2zAYKH1XaUB9PMMAey/XcJWIc7MHuBRgXO+VMLWLbU/6GDuf/rB0qyuYO5/l2C13TPyV99uIM8fxMARzNmCIcLOIfwBpzLc4ZqBhb+dYcDuHeCtH+WCiv+1LkQC1qFkgYD/xLD3HW6fYYCtAoDNuN7dQu2/nJ9/M5Piwf7/fteCN7BSQo1BQ3iFfo1NwTasAe5/Sjdt8hXf7f5RSmoIlgetP0uBLbw9KhsuXT7sPc1BQhhCaN4AO0L+QCwBgSiIRPVcPFjPP5kynuEKv1yDvb4pEl/gAIFIG+0dYgqWFzBCCnmjPhQYzvh4RG9sxSnwKR08a2hmYUYBu83Cp8Pz5qPwiBJNQOrInsEAFQOCQUQAF8R/fyDRFdmcgcAkRQXpUYNNNEYJML+/wZcDzRSHPr+/f+BWQw0yB8iwP/ATv8RNDUiBsJA//v/xgLBa8EEAL/hIlwhAS0rA2X++FjFYWVdBABVOMxZATRMOoZ9CQCQPxVE/MBODQAggfrFz8D//8P+/gRaAzTfTCBqwgXFQlFJwkQHAGBWTP/F8VoDAGdbBjoEBQJc/T0DAGakDMU4AZBsEP9TBcD7Qg8ADG7wM5JCQTMBVW+DwXgHCAVpcwY+SQkABHYf9MM/eAQAMrl6fTsBOn8AwTg7/2zKUAoAeomAn//E9moGAEqOfbHBADS8kBZbEgCBnvL0/ME1/0fBklMBNDyjenIKAICk+PU0TMAXAAli7Sr2MsL+/v//mP/E9fx0BAB1qEV9ADRAqnf/hBDFfag9/8T9VMD/BcD6zjAJAHSvgLw3+jEBQbF3wFfGAPaCG8AMAE25P0b6yMPAUwYAmn8MUcsKAJLDjIyLZAs0Z8T9/WhEBP/6y/0GAF7HgEPACDRnyQP/YEwF/0w3AVXWfcIOxV3cNMFAwP5dwPIFBerbF8DA+xPFGdnDaTZHRFU0wwBQ6XvDwHEDAOvecvQCADDkdP/OAJ7ah/+MbmgIxX3qt8Nqhx4AhTcGx8hFT+71UsAEwsX2wcHBfMXGbQgFTvSAfsGDE9UVBtPBPjVAREoFBBQhFVODBhD+z0A69BEQKBfk/jo4Q/X9Vf8EEPbeT4UwEAceTHsF1R0nDsGmDhA8JiEoOMo/cgwQQig0wEUERRMQ3CqTBf9yt3PCwMuqA9U7KwXJAxAXMT0GCxVwN+QpM/9FzBBKA+gxM/8HEG87ivWKxAQQZjzGxPkxEApFU/74OggV0UyAwf52wMEQVnsHbAUQV124wjc0AAAAAAAA\n', '1784', '1', 'f27214e2-4cce-4734-b584-113161d69da6', 0),
	('2142dc1b-2677-4226-9ddf-45679ea2308d', '0', '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', '2168', '1', '4d54fc4b-6605-4bfe-b141-5df546d25a07', 0),
	('267c9d31-c401-4bc5-9dce-087938403211', '1', 'TGFTUzIxAAAFIiMECAUHCc7QAAAdI2kBAAAAhc80fiIcAGgPVgDbAOEtswAlAH4NWgAjIv8NdwBPAJkOlSJpAFgPwgCrABksIABwANIPFgB4IpkOLQCKABUPeCKLANcPbgBOAEItjwCNAEUPagC/IjIPWwDLAAYP2iLXALAPmQAfAD0tfADqAEYOPgDzIkcPPQD3APULfCL4AMQOxwA5ALQvVgABAa8JBAAEI0oNVgANAVoLaCIPATgOjADVAbEsSAAWATYLAgASI3kMYgAYAXMN3SIZAcoOMgDaASgtAgEfAU4PeAAhI3cN6wAlAaUPeyIoATQMnwDuAf0vYQAsATYMqwApI7UM1wAsARgODSMsAUgOkADwARIuqwA9AXsNfAA7I3cOiwBAAVAL0iJAAeINIgCNAaMvOwBJAaEOMABMI9INcgBMAW8MuCJNAfQOoACKAfYum4UmbV8NW33CqaLrgYGLGq4nhKMPmeOmRgUu9QJAy/1uFX6CRIbu59Lb3Qe/j2Z7gFx2Q2NXYQN2i94tKJCxlH6C/HA7JhrwxXC+ADvto9xmhvfy33zSlQY0928qAytlc/QnJKt3hYQmEEsSc9nfm598YYNH/GuyiIARjuIQ8WoUGfvoFBMpesT6Hbxs1laApPOMaATKrITJmh125IN30lN8MZi9CoiCYCYMsL0urYCsGBSjOFT57dFugPpHsCP+ZQip8isL4CJQAxmblYV8mhw0xIBlHnmjNOG0X+hnTYQRgY8ESKLEgHkCZRUg/ZBf7PzG6JJ1eYdUAwDmCQRZiXSFRKaM/NEDvIPAp5xBYAUN8yZsGAavWbZzYf6adwJsP9KHjWZ1xRdX+IdXQIApen0XpHksQYgD01aU0U1cCAI+AQKNIq/AAGUmcZQGAJcERZ3FJQGtCf02Mc4AZyhowsB4Z8LJAHo59kb9aMAuyAByPmrBeHB1/8oAsT6CgMT/eMEFwmsxAa0gfYjBtMB7RXULAHkhZ6xqeSsBvCEGOMA4QQ0iUyJgccCG0AD+BpJ4acHB/wbBxavBaQQAMS+bjAsiekzpOzNG/g8FUU5iwYPAeAXC+7oCAHZTV//fAQhxo8GDwYTAU8L6oWrCwW4aAcxYpeB1wsCJkMFBwcVaagMAjWteBhAFn2yJxcLEwQTExeD+wcDCPxPFxWgx/DhESi8xBwoFfXJWi8DC/5gEBQN1Sf7EGwHQf6FEw4WSwcKQvcJs4lgSAIGI0zv/+QH8MP9ETxHFkYz4/Pz6///+OMD64v7AwDcJAL6OQ+J1wDwNAG1KSXF4wML8lgsAT5FMqWRpCwCQkYN+UuFkCQCulVYAw2N2CgCzmUnEBv/FfML3BgC/muE++ikBu50tWTc7HxwjF5+twP6dQcKJ4cHDeIR1CsW3oxNlYMD5GAHXqajdwHyIwZHCTcF54sMfARK7raDBxODBk8SLxcEHdsTiwMD/wsF1wwCqnzHAwMD/BsVbymJmwQUAmd7ywG0lATjnScKIOhkF2PO6w1DAwgWbwOfHkML+w0IFBwXd+D0iwfsFxfn/ZCsIAHr9OgPEmOMHEQcDRv06bx8ytwa6fv5TOfv/2v/+/8D+/wTA+N3//P8FED7CN8Ti/goQRw0gOvrE59LKagQQbdYx++ICERMVTMHDEQoYV8H9+f4H1X4cC6aoBhDFG7H/xDwEECkhN8EDwwEzAyJTwsAD1TAmEsAEECQnPW4FFcgpYP9EAxHCMEncAhAJOjDCwRAjaQ2RAAAAAAAA\n', '1760', '1', '4d54fc4b-6605-4bfe-b141-5df546d25a07', 0),
	('42a3ab89-04f1-4d12-9b36-6789e858ef70', '2', 'TZ9TUzIxAAAE3OEECAUHCc7QAAAc3WkBAAAAhAEtjtwXAHgPNADSAODTvwAfAA0PlwAj3F4PfQAqADgP1dwvAI0PXgD6AF/T6gBEACgP3wBM3NkPggBMAKAPrdxjAIAPcAC2ANvTVgB1AEQP7wCG3M0P0gCJAOIPudyMAC8PhgBcADDTIgCcAEEPagCa3K8PNgClAAcPfdymADMPiwBxALLTNwDVADQPDgDn3K8PpgD1ANUPiNz3ACQPcADEAS7S0QADAXgPFQAd3V4NagAaAeAOsNweAZgOrADjAZHSywAoAVULpQAu3a8PvQAsAbILOtw0AasPUwDyAaHTxgA3AdMLHAA83dMJ8gA6AZkLsNw8AeoN2wCFAe7V5wBAAd4JMwBO3cwMmwBPAUcOZaISbVMWsoU2DrtXkIL/lZPnlAeyoSdnXYa+mSullV4TZU8P1f/LYTIm9533H8uruvbb05Z71mmqDquLksMyUCNaHZ6zf8pzOGazeAPqxI/u0aqI0fnGej6qTdWsggOwrQFAg1ZWUIEJd3cPdIK6Vlt+UYFy+caQKd1ofgtVZYZAh7LXDnQD92sPyzhGfhJS8e9eZrubWcvP/L8BoQePCKKjSBrCzFIltAzlNoDrsXkl/3+BSNsQIPlGzP88IRlyoOB1hP348AmtVUcF/SQtpHTg3dXL/cOJvfmk/b5dUFx1gLEBIerBKDABkINdkFx2bamIGUlWTBpdDy3GJfeQhzkMrHBNLUDn5pjS7xoMwE2OYFQYIDvEApj4tgUAIwBiB2QP3EUAZ8NJe7UKBKsBd8PAwXEEwALcLwJkfsIIxTYGvMDAb8AHAJYJdFV8CAB7C/06VUPAAdgMj8LDOHudHcGLwcB+wgT/xLgPAIcWesEHwcccwHiEBADC2AxOwAHbHYnAaqfDxh3+w3xywlmX/xTcux6GbHyEB8DGuQUAwiITXNYAS/RhfGfCUv++wIvVAYEq+v81BT0b3Motk8Nnw7GRxBx2wmjCX2IFwwrcYzvrKf//+/75twgAWj9gg6zCDdxcQ17CwH66CQTLTVPCbkoUxX9JscLEjGLD/zrCxRz+wok9AKWlhsAfwp/BX2lqBsLNHZOMw8HB/wWDwHnEw8LAw8E6wMUdw8LJxMTEAcDGnRMAcW/a/uX9Orj///1KFACdcNTy+v/A/f/+Ov9IHMExEgBUeIzCardq/8KSwxLFWXyfa8L/cHPDOsSS1wGcemvKw7JWxvgB/3aiZHmswsUdxYBvwcB1BcHEHFPAhwMApboi+NUBl4Q3wnOvCARniaTBxcW0wgDQUCM1/sENAASQLZYv/MD6/PvPAI5BMWLBwMLFDgwE/J5DXG7/i8gAIkM8RcFceMDkAQ9DqMBnZMHEtMLFH8LBwcLAwgXCxKv+wGoGALFnLcQcwP4LADephXlAHH8LAHmrMAXAxKLAxMYjAQMJvcZdwv9iwcHDB8PAHaZiwsDBwAf/+hjAmAoANtj0wFYdhQgA8NhJOjNG1QH22EP+MgVdA9z77E//RcLBAIkmJo0EEO8SllkDzHAELcLAxATBAMz1FExQBBHPHFdVCRBuHSeNBqENzGgeLcLCnUwEFO0rK6IEECvuNK3fESQwNMUD1RMw68UFEFc5GgTD+dgRCTowiATVzUqhZAAAAAAAAAA', '1668', '1', '4d54fc4b-6605-4bfe-b141-5df546d25a07', 0);
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
  `xenditurl` varchar(500) DEFAULT NULL,
  `version` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.invoice: ~0 rows (approximately)
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
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

-- Dumping data for table tengkawang.menu: ~12 rows (approximately)
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
	('ca846d31-2e32-44c6-8b9c-dc5c73259d33', 'Api V1 Organization', 'Organization Api Version 1', 0),
	('d81f8a8c-60ec-41b6-9dc9-b76ea00fe3ef', 'Api V1 Device', 'Device Api v1', 0),
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

-- Dumping data for table tengkawang.role_access: ~12 rows (approximately)
/*!40000 ALTER TABLE `role_access` DISABLE KEYS */;
INSERT INTO `role_access` (`id`, `fk_role`, `fk_menu`, `can_create`, `can_read`, `can_update`, `can_delete`, `can_print`, `version`) VALUES
	('01616a3d-9f39-4588-81e1-fc3151c20a22', '00000000', '4a98e06f-6e19-41f5-b4b2-5e6ce236ed8e', '1', '1', '1', '1', '1', 1),
	('10a43759-f113-4a1a-afbe-78e1a7a8f93b', '00000000', '93336971-bd75-48b0-90fc-0c327f0a50f5', '1', '1', '1', '1', '1', 0),
	('16b9c8c0-62bc-423d-b900-5ae34f33c2ed', '00000000', 'ca846d31-2e32-44c6-8b9c-dc5c73259d33', '1', '1', '1', '1', '0', 2),
	('1f6f5956-7f36-461a-86d3-d05e7e33dc88', '00000000', '077ebe5f-6707-47e9-bd7b-0111b1ed71fc', '1', '1', '1', '1', '1', 0),
	('2b3b4d79-1ada-4b15-a393-1b6a0ebf3869', '00000000', '7ced3936-e225-4a44-9f8d-c05e9e3185f6', '1', '1', '1', '1', '1', 0),
	('35aaa1e5-1546-4467-adea-d291dfa994ae', '00000000', 'f1c16f65-bd9b-4579-8867-1d0b94815ab5', '1', '1', '1', '1', '1', 0),
	('3c07ae5e-4160-451b-a4e2-da12861ffcdf', '00000000', '8a676d7f-bd60-48ec-933d-8e3a3a08fade', '1', '1', '1', '1', '1', 0),
	('6944f654-253c-4dca-a84a-658c8842c373', '00000000', '309ae3b5-6f66-4011-a33c-82ad36966ff7', '1', '1', '1', '1', '1', 0),
	('9ab4a37f-b785-40ed-a3a0-af45e19cfd0f', '00000000', '33fe9067-f922-48e1-8ad0-6a984d965211', '1', '1', '1', '1', '1', 0),
	('a6d4da2d-63ca-4c63-86c1-6e798bb3a23e', '00000000', 'd81f8a8c-60ec-41b6-9dc9-b76ea00fe3ef', '1', '1', '1', '1', '0', 1),
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
