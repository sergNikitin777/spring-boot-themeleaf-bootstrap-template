;

--INSERT INTO adm_user (email,isenabled,islocked,pwd,username) VALUES ('admin@test.com', true, false, 'e10adc3949ba59abbe56e057f20f883e', 'admin'); -- 123456 md5
--INSERT INTO adm_user_role (role,user_id) VALUES ( '0', '1'),( '1', '1'),( '2', '1');
--INSERT INTO device_status (name, description)
--VALUES ('Работает', 'Устройство работает'), ('Регламентные работы', 'Выполняются регламентные работы'),  ('Отключено', 'Устройство отключено');
--INSERT INTO address (name, latitude, longitude)
--VALUES ('Пермь, ул. Свободы 1', 87.4234, 87.4234), ('Пермь, ул. Революции 12', 85.4235, 83.4434);
--INSERT INTO device_group ( name, description)
--VALUES ('Объект Пермь 1', 'Описание'), ('Объект Пермь 2', 'Описание');
--INSERT INTO device (device_group_ID,address_id, name, status_id) VALUES (1, 1, 'Устройство №1', 1), (2, 2, 'Устройство №2', 2);
