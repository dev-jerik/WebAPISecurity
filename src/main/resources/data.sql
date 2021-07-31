/** USER **/
INSERT INTO `user` (`enabled`, `password`, `username`) VALUES (b'1', '{noop}myplaintextpassword', 'user1');
INSERT INTO `user` (`enabled`, `password`, `username`) VALUES (b'1', '{noop}myplaintextpassword', 'user2');


/** AUTHORITY **/
INSERT INTO `authority` (`code`, `description`) VALUES ('book:add', 'Add Book');
INSERT INTO `authority` (`code`, `description`) VALUES ('book:view', 'View Book');

/** USER AUTHORITY LINK **/
INSERT INTO `user_authority_link` (`authority_id`, `user_id`) VALUES (1, 1);
INSERT INTO `user_authority_link` (`authority_id`, `user_id`) VALUES (2, 1);
