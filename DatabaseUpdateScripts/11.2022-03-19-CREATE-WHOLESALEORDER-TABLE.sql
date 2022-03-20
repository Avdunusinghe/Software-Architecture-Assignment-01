CREATE TABLE `bookstoredb`.`wholesaleorder` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `noOfBooks` INT NULL,
  `bookName` VARCHAR(100) NULL,
  `supplierCompany` VARCHAR(100) NULL,
  `orderDate` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));
