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
    `clientID` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`privateInfoID`),
      FOREIGN KEY (`clientID`)
      REFERENCES `zarex`.`client` (`id`) ON DELETE CASCADE);