ALTER TABLE `bookstoredb`.`user` 
ADD COLUMN `roleId` INT NULL AFTER `isActive`,
ADD INDEX `roleid_idx` (`roleId` ASC) VISIBLE;
;
ALTER TABLE `bookstoredb`.`user` 
ADD CONSTRAINT `ForeignKey_UserRole`
  FOREIGN KEY (`roleId`)
  REFERENCES `bookstoredb`.`userrole` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
