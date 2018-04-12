INSERT INTO user (email,enabled,locked,password,username) VALUES ('admin@test.com', true, false, '123456', 'admin');
INSERT INTO user_role (role,user_id) VALUES ( '0', '1'),( '1', '1'),( '2', '1');
INSERT INTO address(id,name,latitude,longitude) VALUES (1,'Perm1',87.4234,87.4234),(2,'Perm2',85.4235,83.4434) ;