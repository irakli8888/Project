-- MySQL Script generated by MySQL Workbench
-- Fri Oct 30 00:10:25 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mymodel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mymodel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mymodel` DEFAULT CHARACTER SET utf8 ;
USE `mymodel` ;

-- -----------------------------------------------------
-- Table `mymodel`.`word`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mymodel`.`word` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(245) NULL,
  `count` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
