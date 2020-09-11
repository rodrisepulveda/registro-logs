CREATE TABLE `awlog_logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `host` varchar(100) NOT NULL,
  `origin` varchar(100) NOT NULL,
  `details` text NOT NULL,
  `stacktrace` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8

CREATE TABLE `awlog_hashtag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `awlog_hashtag_description_IDX` (`description`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8

CREATE TABLE `awlog_logger_hashtag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_id` int(11) NOT NULL,
  `hastag_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `awlog_logger_hashtag_FK` (`log_id`),
  KEY `awlog_logger_hashtag_FK_1` (`hastag_id`),
  CONSTRAINT `awlog_logger_hashtag_FK` FOREIGN KEY (`log_id`) REFERENCES `awlog_logger` (`id`),
  CONSTRAINT `awlog_logger_hashtag_FK_1` FOREIGN KEY (`hastag_id`) REFERENCES `awlog_hashtag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8
