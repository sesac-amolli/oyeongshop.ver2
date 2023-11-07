
--h2데이터베이스 명령문
--insert into "user" (username, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
--insert into "user" (username, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);
--
--insert into authority (authority_name) values ('ROLE_USER');
--insert into authority (authority_name) values ('ROLE_ADMIN');
--
--insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
--insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
--insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');


--mysql 명령문
INSERT INTO `user` (`user_Id`, `password`, `user_grade`, `user_name`,
`email`, `user_phone`, `user_point`, `user_status`)
VALUES ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin','ABC',
'admin@google.com', '01022341234', '1234', '1');
INSERT INTO `user` (`user_Id`, `password`, `user_grade`, `user_name`,
`email`, `user_phone`, `user_point`, `user_status`)
VALUES ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'VVV',
       'user@google.com', '01024213222', '4321', '1');

INSERT INTO `authority` (`authority_id`) VALUES ('ROLE_USER');
INSERT INTO `authority` (`authority_id`) VALUES ('ROLE_ADMIN');

INSERT INTO `user_authority` (`user_id`, `authority_id`) VALUES ('admin', 'ROLE_USER');
INSERT INTO `user_authority` (`user_id`, `authority_id`) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `user_authority` (`user_id`, `authority_id`) VALUES ('user', 'ROLE_USER');
