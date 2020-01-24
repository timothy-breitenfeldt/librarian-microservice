-- Adminer 4.7.5 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `author` (`id`, `name`) VALUES
(1,	'George Martin'),
(2,	'J.K. Rolling');

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `publisher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`),
  CONSTRAINT `FKgtvt7p649s4x80y6f4842pnfq` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `book` (`id`, `title`, `publisher_id`) VALUES
(1,	NULL,	1);

DROP TABLE IF EXISTS `book_authors`;
CREATE TABLE `book_authors` (
  `books_id` bigint(20) NOT NULL,
  `authors_id` bigint(20) NOT NULL,
  PRIMARY KEY (`books_id`,`authors_id`),
  KEY `FK551i3sllw1wj7ex6nir16blsm` (`authors_id`),
  CONSTRAINT `FK551i3sllw1wj7ex6nir16blsm` FOREIGN KEY (`authors_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FKmuhqocx8etx13u6jrtutnumek` FOREIGN KEY (`books_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `book_authors` (`books_id`, `authors_id`) VALUES
(1,	2);

DROP TABLE IF EXISTS `book_copy`;
CREATE TABLE `book_copy` (
  `book_id` bigint(20) NOT NULL,
  `branch_id` bigint(20) NOT NULL,
  `amount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`book_id`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `book_copy` (`book_id`, `branch_id`, `amount`) VALUES
(1,	1,	99),
(2,	1,	4);

DROP TABLE IF EXISTS `book_genres`;
CREATE TABLE `book_genres` (
  `books_id` bigint(20) NOT NULL,
  `genres_id` bigint(20) NOT NULL,
  PRIMARY KEY (`books_id`,`genres_id`),
  KEY `FKkemwddl6cxkebe21lsqa2ob4q` (`genres_id`),
  CONSTRAINT `FKkemwddl6cxkebe21lsqa2ob4q` FOREIGN KEY (`genres_id`) REFERENCES `genre` (`id`),
  CONSTRAINT `FKlbdkit5k0gr9g1w5l791qcamg` FOREIGN KEY (`books_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `book_loan`;
CREATE TABLE `book_loan` (
  `book_id` bigint(20) NOT NULL,
  `borrower_id` bigint(20) NOT NULL,
  `branch_id` bigint(20) NOT NULL,
  `date_in` date DEFAULT NULL,
  `date_out` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  PRIMARY KEY (`book_id`,`borrower_id`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `borrower`;
CREATE TABLE `borrower` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `genre` (`id`, `name`) VALUES
(1,	'Fantasy Fiction');

DROP TABLE IF EXISTS `library_branch`;
CREATE TABLE `library_branch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `library_branch` (`id`, `address`, `name`) VALUES
(1,	'6172 Arlington Ave. Seattle, Wa 98661',	'Library');

DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `publisher` (`id`, `address`, `name`, `phone_number`) VALUES
(1,	'123 1st St. Fairfax Virginia 22033 ',	'Penguin House Inc.',	'872-182-1287');

-- 2020-01-20 20:16:44
