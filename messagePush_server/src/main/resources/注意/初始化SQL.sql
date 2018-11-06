CREATE TABLE `message_detail` (
  `id` varchar(40) NOT NULL,
  `message` longtext,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;