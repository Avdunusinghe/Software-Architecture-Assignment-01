CREATE TABLE `bookstoredb`.`supplier` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(100) NULL,
  `lastName` VARCHAR(100) NULL,
  `email` VARCHAR(45) NULL,
  `nic` VARCHAR(15) NULL,
  `address` VARCHAR(100) NULL,
  `mobileNumber` VARCHAR(15) NULL,
  `companyName` VARCHAR(100) NULL,
  `password` VARCHAR(45) NULL,
  `isActive` TINYINT NULL,
  PRIMARY KEY (`id`));
supplier