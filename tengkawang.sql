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
  `event_time` timestamp NULL DEFAULT NULL,
  `employee_number` char(8) NOT NULL DEFAULT '',
  `employee_name` varchar(150) NOT NULL DEFAULT '',
  `event_type` char(3) NOT NULL DEFAULT 'IN',
  `verification_type` char(50) NOT NULL DEFAULT 'IN',
  `event_location` varchar(150) NOT NULL DEFAULT 'IN',
  `version` bigint(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.attendance: ~106 rows (approximately)
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`id`, `device`, `event_time`, `employee_number`, `employee_name`, `event_type`, `verification_type`, `event_location`, `version`) VALUES
	('22696b87-0330-4ddd-90df-0b60ab2177a7', '3397002440486', '2020-08-10 21:36:39', '2', '1', 'IN', 'Fingerprint', 'CABANG B', 0),
	('2f228ba6-dc26-4bdc-9434-f16c8bc08979', '3397002440486', '2020-12-09 07:13:35', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('b006b3e7-559b-4bdc-b98d-0c1f0060f6ac', '3397002440486', '2020-12-09 07:13:42', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('44c17006-8889-49ec-ba04-1f58ec2e32a1', '3397002440486', '2020-12-09 07:13:51', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('7e0b4c53-385f-4c25-9f68-6aaf33a0ab90', '3397002440486', '2020-12-09 10:48:59', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('66bf4160-8d22-4c06-a21e-282d8149b686', '3397002440486', '2020-12-11 15:03:06', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('328347ef-8c7a-40ce-94ee-27f0a36b45ee', '3397002440486', '2020-12-11 15:29:22', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('afa29647-65d8-4b27-b3ac-a7e2707b6573', '3397002440486', '2020-12-11 15:35:49', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('02b2f9ab-4347-4f13-821f-2440d929ec29', '3397002440486', '2020-12-11 15:36:10', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('5a619abd-aa96-42d1-b422-acc488247e4f', '3397002440486', '2020-12-11 15:40:20', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('1e1e6ef1-2c92-40a3-b009-a6f9974faef6', '3397002440486', '2020-12-11 17:08:44', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('8b36e373-0c15-49a1-88da-3d1864f567b7', '3397002440486', '2020-12-11 17:15:49', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('8d49d168-1234-4c37-ae7b-97e5c0afe14e', '3397002440486', '2020-12-11 17:17:59', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('96430bd9-a235-4b79-ae9c-c99b71351e27', '3397002440486', '2020-12-11 17:20:16', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('e2899033-dcf8-4981-a71e-8aefaa9e647e', '3397002440486', '2020-12-11 17:22:30', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('f6bb1055-2eaa-4c1b-89a0-af86cab18e8c', '3397002440486', '2020-12-11 20:29:43', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('93b0d6d2-12cd-4314-a40a-10343710702c', '3397002440486', '2020-12-11 20:32:01', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('9d438385-d51c-4916-bff5-f1233b21f308', '3397002440486', '2020-12-11 20:37:57', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('f0a4f8e0-fb94-4b95-91aa-ea5e4145a499', '3397002440486', '2020-12-11 20:44:52', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('2d017ee9-4662-4042-befa-2cbf8c137d20', '3397002440486', '2020-12-11 20:59:24', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('c9ecc142-26b0-43ae-93c4-fec748b67f5f', '3397002440486', '2020-12-11 21:00:54', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('fc45d4b9-ad28-4e9c-9b00-b8d2835d4d93', '3397002440486', '2020-12-11 21:03:07', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('b129e2b8-9723-4e47-aefa-f965535b6613', '3397002440486', '2020-12-11 21:06:22', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('f8474273-8c91-49f5-a82a-ca5f8b34bb6a', '3397002440486', '2020-12-12 09:16:45', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('23c81df8-e707-49e1-a2bc-c933b946fde7', '3397002440486', '2020-12-12 09:18:11', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('fae3addf-c863-4bf5-b5fe-bdbf9af8ff4b', '3397002440486', '2020-12-12 09:19:41', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('532cbf3c-65e2-442c-bee5-79d7c9d1fc65', '3397002440486', '2020-12-12 09:25:37', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('e29e304e-0cd7-4671-b1dc-730ea8809fbb', '3397002440486', '2020-12-12 09:30:47', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('010ccbb6-6978-48cb-89d8-850d900a82c2', '3397002440486', '2020-12-12 09:34:59', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('15c8a6d6-4695-43d8-9e76-7ede53403f7c', '3397002440486', '2020-12-12 09:49:42', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('db35687d-849a-449f-8a3f-b27b29305eef', '3397002440486', '2020-12-12 09:51:00', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('d0bd276e-f787-4fe4-988f-e43e19e1b60c', '3397002440486', '2020-12-12 10:00:10', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('2e119ae9-532f-4e41-809e-df9efb3d605f', '3397002440486', '2020-12-12 10:01:00', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('d1001945-40a5-4469-80f9-72340c5d2ae6', '3397002440486', '2020-12-12 10:05:32', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('9d8d6d82-77c3-46fe-acc3-ee39eeef80ad', '3397002440486', '2020-12-12 14:34:59', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('bc59ceeb-2367-4bfd-8653-28c88a9580f3', '3397002440486', '2020-12-12 15:07:25', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('c140877b-19af-4886-af87-004d7998714a', '3397002440486', '2020-12-12 15:07:56', '222222', '222222', 'IN', 'Password', 'CABANG B', 0),
	('2a25858d-1263-404b-b374-aeeb8a14d15b', '3397002440486', '2020-12-12 15:08:46', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('c7395c6c-be89-49d5-a046-735db8072391', '3397002440486', '2020-12-12 14:14:29', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('7aaba227-2b35-4a88-b288-65330ae195d6', '3397002440486', '2020-12-13 06:46:06', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('f94148a7-00c6-4e40-b6a6-848cca3e446d', '3397002440486', '2020-12-13 06:47:29', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('9fb571d3-c866-4d68-8b3f-16ee5b97da74', '3397002440486', '2020-12-13 14:47:02', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('a3b365f7-c2cd-4833-8145-1ddb8471550c', '3397002440486', '2020-12-13 14:49:29', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('be5eb77c-ec16-4cbd-b3a8-4c7543c92ed3', '3397002440486', '2020-12-13 14:53:28', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('a2976162-5fec-4cbe-9762-dacaa3a9ead4', '3397002440486', '2020-12-13 14:58:23', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('29c8444d-8e67-4cfc-92ac-8185249ae603', '3397002440486', '2020-12-13 15:01:39', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('1e1ddbfe-08ad-4296-94c6-600176875898', '3397002440486', '2020-12-13 15:16:35', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('59cef8ea-b588-489f-9541-f74cc415b4b1', '3397002440486', '2020-12-13 15:23:25', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('d876525b-7237-40aa-a3fb-8957af3903ac', '3397002440486', '2020-12-13 15:23:36', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('bd9b3ccb-2689-4eb8-adab-befd002992f3', '3397002440486', '2020-12-13 15:54:43', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('5e73cb0e-fc22-4c00-8e03-28f131b3fe2e', '3397002440486', '2020-12-13 16:29:48', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('60259131-837e-4648-a74c-2769998dee64', '3397002440486', '2020-12-13 16:30:36', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('15594995-7816-410a-9357-ba920780eb9c', '3397002440486', '2020-12-13 16:35:03', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('f267f68e-6b89-4d8c-942c-6886e96c725b', '3397002440486', '2020-08-10 21:36:39', '2', '1', 'IN', 'Fingerprint', 'CABANG B', 0),
	('bda5d789-8b26-4641-b84c-9e7593778d1f', '3397002440486', '2020-12-09 07:13:35', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('70059bc2-2f7d-406c-b50e-22c647490754', '3397002440486', '2020-12-09 07:13:42', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('d8f5073c-f7c5-488d-977a-9d7ad77c1693', '3397002440486', '2020-12-09 07:13:51', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('0625ea7c-4463-4b99-ba3e-ef39e1182ca1', '3397002440486', '2020-12-09 10:48:59', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('c4c94cde-bc02-4ebd-a5e3-265b9fead42b', '3397002440486', '2020-12-11 15:03:06', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('bcd70fb5-6a25-435d-b968-12932e7bb6f3', '3397002440486', '2020-12-11 15:29:22', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('76931d7f-388c-46d8-b08d-6ce2b3120051', '3397002440486', '2020-12-11 15:35:49', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('b3f59000-4826-4f0a-813c-e5ad4240e21f', '3397002440486', '2020-12-11 15:36:10', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('8735eb0e-98dc-41bb-ba09-06ab4addc5fd', '3397002440486', '2020-12-11 15:40:20', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('1dbd4e96-6043-4fda-afdf-cb1ad684c247', '3397002440486', '2020-12-11 17:08:44', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('d79843bc-5377-4797-8128-87d6d5206144', '3397002440486', '2020-12-11 17:15:49', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('0766b5fe-f9d0-4cd6-816e-5cf4d7503381', '3397002440486', '2020-12-11 17:17:59', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('96eaf3a9-893d-4134-b287-55fd42db3360', '3397002440486', '2020-12-11 17:20:16', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('c6793a9d-0590-44b1-af6c-f3dfe508aa03', '3397002440486', '2020-12-11 17:22:30', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('f4802780-8b7a-4e71-aac1-3d04cc77e948', '3397002440486', '2020-12-11 20:29:43', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('97014383-6b44-4704-ae52-d40e484d237c', '3397002440486', '2020-12-11 20:32:01', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('8c8613f8-bcda-4f3f-a17c-8a30cc7e5856', '3397002440486', '2020-12-11 20:37:57', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('87765c48-d7c1-447a-a504-6bad63a90c19', '3397002440486', '2020-12-11 20:44:52', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('06f70907-6d9a-4d3d-acb7-88e79a15c1cc', '3397002440486', '2020-12-11 20:59:24', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('dc274bd8-5515-44b9-8cb8-534b4270a45e', '3397002440486', '2020-12-11 21:00:54', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('12ad13a6-75f1-44d0-8a63-4bcb7e86605e', '3397002440486', '2020-12-11 21:03:07', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('d6bb593c-72e2-40bf-8003-9068656f00cd', '3397002440486', '2020-12-11 21:06:22', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('d9c7a7b3-ca0b-477b-bd74-d1b47305290f', '3397002440486', '2020-12-12 09:16:45', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('89783d25-7cd8-4b4c-b19c-0d5465b84ecb', '3397002440486', '2020-12-12 09:18:11', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('593a4c2c-97db-4ca0-bf5a-9591351de65b', '3397002440486', '2020-12-12 09:19:41', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('db516ab6-e0f4-41f3-9ff7-0f5aaaa51088', '3397002440486', '2020-12-12 09:25:37', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('ba0d4479-4c50-4251-b459-79c67c98551c', '3397002440486', '2020-12-12 09:30:47', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('38e99d4a-3538-4c4b-bff5-be346515d52a', '3397002440486', '2020-12-12 09:34:59', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('eff682d2-3587-4361-9a4f-ff481d33e2de', '3397002440486', '2020-12-12 09:49:42', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('9163f3c8-a97e-48d3-a271-2470d46c6a2d', '3397002440486', '2020-12-12 09:51:00', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('c1732973-01d3-4408-b814-8732f138377d', '3397002440486', '2020-12-12 10:00:10', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('f47e4f07-df32-4c20-bc19-82ca8be3fa32', '3397002440486', '2020-12-12 10:01:00', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('4530f1cb-4c22-4981-9db4-02380afcb6ff', '3397002440486', '2020-12-12 10:05:32', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('0c1ac186-92e2-4840-9ab2-b16da1bd37cf', '3397002440486', '2020-12-12 14:34:59', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('7259febe-66fa-4acd-a427-d9b59c0e925b', '3397002440486', '2020-12-12 15:07:25', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('00099023-0e7f-4711-9b0e-ab5140f40f30', '3397002440486', '2020-12-12 15:07:56', '222222', '222222', 'IN', 'Password', 'CABANG B', 0),
	('16a32c95-fc81-4360-9b50-edfb33328662', '3397002440486', '2020-12-12 15:08:46', '111112', '22222', 'IN', 'Fingerprint', 'CABANG B', 0),
	('9c21714c-bc2a-4463-ad48-6371fbf5b131', '3397002440486', '2020-12-12 14:14:29', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('095b4298-deed-4be9-b1f1-8b4375d974a4', '3397002440486', '2020-12-13 06:46:06', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('03c5ec56-8dc4-4b56-aa21-7e2b73085d90', '3397002440486', '2020-12-13 06:47:29', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('1bdc5d70-bf8d-4696-8905-ce2b59839e5c', '3397002440486', '2020-12-13 14:47:02', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('66638fe9-0a4d-42a3-ad97-ad2b5f7c5393', '3397002440486', '2020-12-13 14:49:29', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('69c27c35-ab57-4884-aa17-9acd24de7e32', '3397002440486', '2020-12-13 14:53:28', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('80ef70cb-f13c-41eb-b041-0342fa7652eb', '3397002440486', '2020-12-13 14:58:23', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('127437c2-a53c-43b2-a880-ec1ca277fda1', '3397002440486', '2020-12-13 15:01:39', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('1491900e-1036-4ab2-a621-3bf45f2a7716', '3397002440486', '2020-12-13 15:16:35', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('b225c569-4607-4dc5-beb4-a8f7061798cb', '3397002440486', '2020-12-13 15:23:25', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('da161ad1-19b1-46f4-84c3-df62437506f1', '3397002440486', '2020-12-13 15:23:36', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('732f5081-353a-4be7-8874-ffcd11fe0888', '3397002440486', '2020-12-13 15:54:43', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('8ee5cd52-ac4b-44b1-8a61-17a9d9507b2e', '3397002440486', '2020-12-13 16:29:48', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('94f72e83-a3b2-487c-ba63-4ca7e37720db', '3397002440486', '2020-12-13 16:30:36', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0),
	('b9b2c8d0-5cf9-4697-ba90-a053bb32f2a6', '3397002440486', '2020-12-13 16:35:03', '111111', '12345', 'IN', 'Fingerprint', 'CABANG B', 0);
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

-- Dumping data for table tengkawang.department: ~1 rows (approximately)
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

-- Dumping data for table tengkawang.device: ~2 rows (approximately)
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` (`id`, `serial`, `name`, `ip`, `comment`, `version`, `organization`, `option`, `status`, `type`, `event_type`) VALUES
	('708bbe02-312a-4c44-b2da-751d5cd9293c', '123456', '123456', '127.0.0.1', 'Auto Generated on first handshake', 0, 'DEFT ORG', NULL, 'Online', NULL, NULL),
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.employee: ~5 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `number`, `on_device_name`, `password`, `card`, `privilege`, `version`, `full_name`, `department`, `employee_group`) VALUES
	('1a854ec2-4579-4282-a44c-ea2538757ae4', '2', '1', NULL, NULL, 'User', 0, '1', NULL, '0'),
	('366499e8-9a53-4939-a8b4-910e19bf5999', '222222', '222222', NULL, NULL, 'Super_Administrator', 0, '222222', NULL, '0'),
	('3f904f8a-36ee-44a9-a404-3098df6c6d93', '111112', '22222', NULL, NULL, 'User', 0, '22222', NULL, '0'),
	('60fa6bf1-d149-4b50-a098-14a1c7eff9a6', '11111', '123', NULL, NULL, 'User', 0, '123', NULL, '0'),
	('d6bd401f-204d-49d0-b0ad-414cad105dad', '111111', '12345', NULL, NULL, 'User', 0, '12345', NULL, '0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

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
  `type` char(50) NOT NULL DEFAULT '',
  `version` bigint(20) NOT NULL DEFAULT 0,
  `name` varchar(200) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tengkawang.work_time: ~0 rows (approximately)
/*!40000 ALTER TABLE `work_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_time` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
