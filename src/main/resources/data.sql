INSERT INTO user (email,enabled,locked,password,username) VALUES ('admin@test.com', true, false, '123456', 'admin');
INSERT INTO user_role (role,user_id) VALUES ( '0', '1'),( '1', '1'),( '2', '1');
INSERT INTO device_status (name, description)
VALUES ('Работает', 'Устройство работает'), ('Регламентные работы', 'Выполняются регламентные работы'),
  ('Отключено', 'Устройство отключено');
INSERT INTO address (name, latitude, longitude) VALUES ('Perm1', 87.4234, 87.4234), ('Perm2', 85.4235, 83.4434);
INSERT INTO device (ADDRESS_ID, name, status_id) VALUES (1, 'Устройство №1', 1), (2, 'Устройство №2', 2);