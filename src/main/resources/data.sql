INSERT INTO user (version,email,enabled,locked,password,username) VALUES (0, 'admin@test.com', true, false, '123456', 'admin');
INSERT INTO user_role (role,user_id) VALUES ( '0', '1'),( '1', '1'),( '2', '1');
INSERT INTO address(version,name,latitude,longitude) VALUES (0,'Perm1',87.4234,87.4234);