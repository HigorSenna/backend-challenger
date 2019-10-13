CREATE TABLE `backend_challenger`.`store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_store_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `backend_challenger`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);