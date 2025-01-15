CREATE SCHEMA `fleet` ;

CREATE TABLE `fleet`.`squadron` (
  `squadron_id` INT NOT NULL AUTO_INCREMENT,
  `squadron_name` VARCHAR(64) NOT NULL,
  `max_capacity` INT NOT NULL,
  PRIMARY KEY (`squadron_id`),
  UNIQUE INDEX `squadron_name_UNIQUE` (`squadron_name` ASC) VISIBLE);

CREATE TABLE `fleet`.`personnel` (
  `personnel_id` INT NOT NULL AUTO_INCREMENT,
  `personnel_name` VARCHAR(50) NOT NULL,
  `isCommander` TINYINT NOT NULL DEFAULT 0,
  `squadron_id` INT NOT NULL,
  PRIMARY KEY (`personnel_id`),
  INDEX `personnel_squadron_idx` (`squadron_id` ASC) VISIBLE,
  CONSTRAINT `personnel_squadron`
    FOREIGN KEY (`squadron_id`)
    REFERENCES `fleet`.`squadron` (`squadron_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `fleet`.`ship` (
  `ship_id` INT NOT NULL AUTO_INCREMENT,
  `ship_name` VARCHAR(50) NOT NULL,
  `ship_type` ENUM('destroyer', 'carrier', 'battleship', 'cruiser', 'frigate') NOT NULL,
  `squadron_id` INT NOT NULL,
  PRIMARY KEY (`ship_id`),
  UNIQUE INDEX `ship_name_UNIQUE` (`ship_name` ASC) VISIBLE,
  INDEX `ship_squadron_idx` (`squadron_id` ASC) VISIBLE,
  CONSTRAINT `ship_squadron`
    FOREIGN KEY (`squadron_id`)
    REFERENCES `fleet`.`squadron` (`squadron_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO `fleet`.`squadron` (squadron_name, max_capacity) VALUES 
('Unassigned', 0), -- ID 1 for unassigned purposes
('Alpha Squadron', 20),
('Bravo Squadron', 20),
('Charlie Squadron', 20),
('Delta Squadron', 20),
('Echo Squadron', 20);

-- Alpha Squadron
INSERT INTO `fleet`.`personnel` (personnel_name, isCommander, squadron_id) VALUES
('John Smith', 1, 2), -- Commander
('Jane Doe', 0, 2),
('Mike Johnson', 0, 2),
('Emily Davis', 0, 2),
('James Brown', 0, 2),
('Sarah Wilson', 0, 2),
('David Moore', 0, 2),
('Laura Taylor', 0, 2);

-- Bravo Squadron
INSERT INTO `fleet`.`personnel` (personnel_name, isCommander, squadron_id) VALUES
('Robert King', 1, 3), -- Commander
('Patricia Harris', 0, 3),
('William Clark', 0, 3),
('Linda Lewis', 0, 3),
('Michael Walker', 0, 3),
('Elizabeth Hall', 0, 3),
('Christopher Allen', 0, 3),
('Barbara Young', 0, 3);

-- Charlie Squadron
INSERT INTO `fleet`.`personnel` (personnel_name, isCommander, squadron_id) VALUES
('Thomas Wright', 1, 4), -- Commander
('Angela Scott', 0, 4),
('Kevin Harris', 0, 4),
('Donna Adams', 0, 4),
('Matthew Parker', 0, 4),
('Susan Martinez', 0, 4),
('Daniel Rodriguez', 0, 4),
('Karen Perez', 0, 4);

-- Delta Squadron
INSERT INTO `fleet`.`personnel` (personnel_name, isCommander, squadron_id) VALUES
('Paul Garcia', 1, 5), -- Commander
('Betty Martinez', 0, 5),
('Mark White', 0, 5),
('Dorothy Anderson', 0, 5),
('Charles Nelson', 0, 5),
('Ruth Harris', 0, 5),
('George Turner', 0, 5),
('Deborah Carter', 0, 5);

-- Echo Squadron
INSERT INTO `fleet`.`personnel` (personnel_name, isCommander, squadron_id) VALUES
('Richard Lewis', 1, 6), -- Commander
('Helen Walker', 0, 6),
('Jason Hall', 0, 6),
('Sandra Allen', 0, 6),
('Donald Wright', 0, 6),
('Carol Scott', 0, 6),
('Anthony Green', 0, 6),
('Sharon Campbell', 0, 6);

-- Alpha Squadron
INSERT INTO `fleet`.`ship` (ship_name, ship_type, squadron_id) VALUES
('USS Alpha One', 'destroyer', 2),
('USS Alpha Two', 'carrier', 2),
('USS Alpha Three', 'battleship', 2);

-- Bravo Squadron
INSERT INTO `fleet`.`ship` (ship_name, ship_type, squadron_id) VALUES
('USS Bravo One', 'destroyer', 3),
('USS Bravo Two', 'carrier', 3),
('USS Bravo Three', 'cruiser', 3);

-- Charlie Squadron
INSERT INTO `fleet`.`ship` (ship_name, ship_type, squadron_id) VALUES
('USS Charlie One', 'carrier', 4),
('USS Charlie Two', 'battleship', 4),
('USS Charlie Three', 'frigate', 4);

-- Delta Squadron
INSERT INTO `fleet`.`ship` (ship_name, ship_type, squadron_id) VALUES
('USS Delta One', 'cruiser', 5),
('USS Delta Two', 'destroyer', 5),
('USS Delta Three', 'battleship', 5);

-- Echo Squadron
INSERT INTO `fleet`.`ship` (ship_name, ship_type, squadron_id) VALUES
('USS Echo One', 'battleship', 6),
('USS Echo Two', 'carrier', 6),
('USS Echo Three', 'destroyer', 6);

SELECT squadron_name, max_capacity, personnel_name, isCommander, squadron.squadron_id, ship_id, ship_name, ship_type
FROM squadron JOIN personnel ON personnel.squadron_id = squadron.squadron_id 
JOIN ship ON squadron.squadron_id = ship.squadron_id;