CREATE SCHEMA ZAREX;

CREATE TABLE `zarex`.`client` (
  `id` VARCHAR(100) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `isAdmin` TINYINT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `zarex`.`privateinfo` (
    `privateInfoID` VARCHAR(100) NOT NULL,
    `firstName` VARCHAR(45) NOT NULL,
    `lastName` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `phoneNumber` VARCHAR(45) NOT NULL,
    `clientID` VARCHAR(45),
    `courierID` VARCHAR(45),
    PRIMARY KEY (`privateInfoID`),
      FOREIGN KEY (`clientID`)
      REFERENCES `zarex`.`client` (`id`) ON DELETE CASCADE,
      FOREIGN KEY (`courierID`)
      REFERENCES `zarex`.`courier` (`id`) ON DELETE CASCADE);

CREATE TABLE `zarex`.`product` (
  `id` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `rating` FLOAT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `zarex`.`courier` (
  `id` VARCHAR(100) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `zarex`.`couriercar` (
  `carID` VARCHAR(100) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `courierID` VARCHAR(45),
  PRIMARY KEY (`carID`),
  FOREIGN KEY (`courierID`)
  REFERENCES `zarex`.`courier` (`id`) ON DELETE CASCADE);



