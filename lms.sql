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
(1,	'J.K. Rolling'),
(2,	'George Martin');

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
(1,	'Harry Potter and the Goblet of Fire',	1),
(2,	'hello world',	1),
(3,	'hello world',	1),
(7,	'Game of Thrones',	1),
(8,	'Wheel of Time',	1),
(9,	'Powder Mage  book 1 Promise of Blood',	1),
(10,	'Powder Mage book 2',	1);

DROP TABLE IF EXISTS `book_authors`;
CREATE TABLE `book_authors` (
  `book_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `FK78gu95lglhc6cs2u5jfudx6e5` (`author_id`),
  CONSTRAINT `FK78gu95lglhc6cs2u5jfudx6e5` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FKs4xm7q8i3uxvaiswj1c35nnxw` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `book_authors` (`book_id`, `author_id`) VALUES
(7,	2),
(8,	2),
(9,	2),
(10,	2);

DROP TABLE IF EXISTS `book_copy`;
CREATE TABLE `book_copy` (
  `book_id` bigint(20) NOT NULL,
  `branch_id` bigint(20) NOT NULL,
  `amount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`book_id`,`branch_id`),
  KEY `FKnrnrxe9isytf7at2eclgen7mo` (`branch_id`),
  CONSTRAINT `FKnrnrxe9isytf7at2eclgen7mo` FOREIGN KEY (`branch_id`) REFERENCES `library_branch` (`id`),
  CONSTRAINT `FKpqftp65hd66ae8wsx7pp2cxcs` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `book_copy` (`book_id`, `branch_id`, `amount`) VALUES
(1,	1,	12),
(2,	1,	100),
(7,	1,	48);

DROP TABLE IF EXISTS `book_genres`;
CREATE TABLE `book_genres` (
  `book_id` bigint(20) NOT NULL,
  `genre_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`genre_id`),
  KEY `FK9h3nddtxgapfvc95fjt1x146m` (`genre_id`),
  CONSTRAINT `FK6soimqwnss59p5wt6m3afnuoo` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK9h3nddtxgapfvc95fjt1x146m` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `book_genres` (`book_id`, `genre_id`) VALUES
(7,	1),
(8,	1),
(9,	1),
(10,	1);

DROP TABLE IF EXISTS `book_loan`;
CREATE TABLE `book_loan` (
  `book_id` bigint(20) NOT NULL,
  `borrower_id` bigint(20) NOT NULL,
  `branch_id` bigint(20) NOT NULL,
  `date_in` date DEFAULT NULL,
  `date_out` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  PRIMARY KEY (`book_id`,`borrower_id`,`branch_id`),
  KEY `FK5tedwdmib2nudnd4tm0u9sej0` (`borrower_id`),
  KEY `FKlyufp6fqdud9tfeqvm1es51w` (`branch_id`),
  CONSTRAINT `FK5tedwdmib2nudnd4tm0u9sej0` FOREIGN KEY (`borrower_id`) REFERENCES `borrower` (`id`),
  CONSTRAINT `FKd0pola0sj2fp9719x3b8lpb7s` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKlyufp6fqdud9tfeqvm1es51w` FOREIGN KEY (`branch_id`) REFERENCES `library_branch` (`id`)
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
(1,	'Fiction'),
(2,	'Suspense Fiction');

DROP TABLE IF EXISTS `library_branch`;
CREATE TABLE `library_branch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `library_branch` (`id`, `address`, `name`) VALUES
(1,	'172 1st St. Seattle Wa 172673',	'Seattle Library'),
(2,	'na',	'test'),
(3,	NULL,	'Suspense Fiction');

DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `publisher` (`id`, `address`, `name`, `phone_number`) VALUES
(1,	'1234 Arlington Ave. Fairfax Virginia 27363',	'Penguin House Inc.',	'762-282-8787');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(1,	'Admin user - Has permission to create, read, update, and delete tasks for all entities.',	'ROLE_ADMIN'),
(2,	'Librarian user - has permissions to create book copies, read book copies, update book copies, delete book copies, create books, get books, and update library branches.',	'ROLE_LIBRARIAN'),
(3,	'Borrower user  - Has basic access for viewing books, and checking in and out books.',	'ROLE_BORROWER');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` (`id`, `password`, `username`) VALUES
(1,	'$2a$10$Pg82j2E7r1TX8Ipn7gDp7.9qf9nqw2dJv8C5wks.GZi4/Kp4aVwJi',	'admin'),
(2,	'sdlkfj',	'test'),
(6,	'$2a$10$zD/LCGqLpho05J19oinGj.HlYvGZ.I9fdAbqQUnhFwbdPMcNHSQ0K',	'borrower'),
(7,	'$2a$10$si1a5ZlWCiUduUbI7VQ3euTDAsC/rX3ezNcuKE5OffQmY8BQUW6e2',	'librarian');

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1,	1),
(1,	2),
(1,	3),
(6,	3),
(7,	2),
(7,	3);

-- 2020-02-03 03:09:52
