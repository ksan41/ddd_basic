CREATE TABLE `user` (
                        `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `create_date` datetime(6) DEFAULT NULL,
                        `delete_date` datetime(6) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `grade` enum('NORMAL','PREMIUM') DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `updated_date` datetime(6) DEFAULT NULL,
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `circle` (
                          `circle_id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `create_date` datetime(6) DEFAULT NULL,
                          `delete_date` datetime(6) DEFAULT NULL,
                          `circle_name` varchar(255) DEFAULT NULL,
                          `owner_user_id` bigint(20) DEFAULT NULL,
                          `updated_date` datetime(6) DEFAULT NULL,
                          PRIMARY KEY (`circle_id`),
                          KEY `FK3acuq0xgtvoly8mm1ap58f4bj` (`owner_user_id`),
                          CONSTRAINT `FK3acuq0xgtvoly8mm1ap58f4bj` FOREIGN KEY (`owner_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `circle_user` (
                               `circle_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `circle_id` bigint(20) DEFAULT NULL,
                               `user_id` bigint(20) DEFAULT NULL,
                               PRIMARY KEY (`circle_user_id`),
                               KEY `FK6x3sj6nhfi3gaox615robjnaq` (`circle_id`),
                               KEY `FKcg7ts5r0dmgsx4eooextuhhqf` (`user_id`),
                               CONSTRAINT `FK6x3sj6nhfi3gaox615robjnaq` FOREIGN KEY (`circle_id`) REFERENCES `circle` (`circle_id`),
                               CONSTRAINT `FKcg7ts5r0dmgsx4eooextuhhqf` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `circle_invitation` (
                                     `invitation_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                     `invited_date` datetime(6) DEFAULT NULL,
                                     `is_accept` bit(1) NOT NULL,
                                     `response_date` datetime(6) DEFAULT NULL,
                                     `circle_circle_id` bigint(20) DEFAULT NULL,
                                     `from_user_user_id` bigint(20) DEFAULT NULL,
                                     `invited_user_user_id` bigint(20) DEFAULT NULL,
                                     PRIMARY KEY (`invitation_id`),
                                     KEY `FKid97ppt7n00oj7n7ko393ol77` (`circle_circle_id`),
                                     KEY `FKrcvyp091p0xaae30cg34uhqi8` (`from_user_user_id`),
                                     KEY `FKnxsiumu1ri2r27hmdbystngwc` (`invited_user_user_id`),
                                     CONSTRAINT `FKid97ppt7n00oj7n7ko393ol77` FOREIGN KEY (`circle_circle_id`) REFERENCES `circle` (`circle_id`),
                                     CONSTRAINT `FKnxsiumu1ri2r27hmdbystngwc` FOREIGN KEY (`invited_user_user_id`) REFERENCES `user` (`user_id`),
                                     CONSTRAINT `FKrcvyp091p0xaae30cg34uhqi8` FOREIGN KEY (`from_user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;