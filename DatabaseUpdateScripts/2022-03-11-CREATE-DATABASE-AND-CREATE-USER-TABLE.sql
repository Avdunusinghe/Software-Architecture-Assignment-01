
-- =========================================================== --
-- Software-Architecture-Assignment-01--
-- My Sql Database Details -- 
-- Step 01  Create Schema and Schema Name is  "booksstoredb" --
-- Step 02 Execute Following Quesries -- 
-- ========================================================== --


CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(200) DEFAULT NULL,
  `lastName` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `mobileNumber` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
