-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema todolist_app
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema todolist_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `todolist_app` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `todolist_app` ;

-- -----------------------------------------------------
-- Table `todolist_app`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist_app`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password_hash` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `todolist_app`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist_app`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `categories_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `todolist_app`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `todolist_app`.`tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist_app`.`tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `todolist_app`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist_app`.`tasks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `category_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `due_date` DATE NULL DEFAULT NULL,
  `is_completed` TINYINT(1) NULL DEFAULT '0',
  `priority` ENUM('low', 'medium', 'high') NULL DEFAULT 'medium',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  CONSTRAINT `tasks_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `todolist_app`.`users` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `tasks_ibfk_2`
    FOREIGN KEY (`category_id`)
    REFERENCES `todolist_app`.`categories` (`id`)
    ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `todolist_app`.`task_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist_app`.`task_history` (
  `id` INT NOT NULL,
  `task_id` INT NOT NULL,
  `action` VARCHAR(255) NULL DEFAULT NULL,
  `old_value` TEXT NULL DEFAULT NULL,
  `new_value` TEXT NULL DEFAULT NULL,
  `changed_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `task_id` (`task_id` ASC) VISIBLE,
  CONSTRAINT `task_history_ibfk_1`
    FOREIGN KEY (`task_id`)
    REFERENCES `todolist_app`.`tasks` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `todolist_app`.`task_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist_app`.`task_tags` (
  `task_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`task_id`, `tag_id`),
  INDEX `tag_id` (`tag_id` ASC) VISIBLE,
  CONSTRAINT `task_tags_ibfk_1`
    FOREIGN KEY (`task_id`)
    REFERENCES `todolist_app`.`tasks` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `task_tags_ibfk_2`
    FOREIGN KEY (`tag_id`)
    REFERENCES `todolist_app`.`tags` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
