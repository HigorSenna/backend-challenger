CREATE TABLE `backend_challenger`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  `number` INT(6) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `neighborhood` VARCHAR(80) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`));