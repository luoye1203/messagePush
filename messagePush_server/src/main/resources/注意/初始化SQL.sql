CREATE TABLE `message_detail` (
  `id` varchar(40) NOT NULL,
  `message` longtext,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `visitlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moudelName` varchar(100) DEFAULT NULL,
  `apiName` varchar(100) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `visitTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

