CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookName` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `isActive` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
