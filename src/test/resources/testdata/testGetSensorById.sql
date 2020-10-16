INSERT into rooms(id,name) VALUES (1,'sala');
INSERT INTO sensors(id, brand, "name", "type", room_id) VALUES(1, 'marca', 'sensor1', 'tipo', 1);
INSERT INTO public.channels(id, max, min, "name_channel", sensor_id) VALUES(3, 10, 1, 'canal1', 1);
INSERT INTO public.channels(id, max, min, "name_channel", sensor_id) VALUES(4, 10, 1, 'canal1', 1);
