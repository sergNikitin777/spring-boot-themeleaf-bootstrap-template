INSERT INTO user (email,enabled,locked,password,username) VALUES ('admin@test.com', true, false, '123456', 'admin');
INSERT INTO user_role (role,user_id) VALUES ( '0', '1'),( '1', '1'),( '2', '1');