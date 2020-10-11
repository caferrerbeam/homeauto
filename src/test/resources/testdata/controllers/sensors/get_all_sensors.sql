INSERT into rooms(id,name) VALUES (1,'sala');
INSERT INTO sensors(id, brand, "name", "type", room_id) VALUES(1, 'marca', 'sensor1', 'tipo', 1);
INSERT INTO sensors(id, brand, "name", "type", room_id) VALUES(2, 'marca', 'sensor1', 'tipo', 1);

INSERT INTO public.channels(id, max, min, "name_channel", sensor_id) VALUES(1, 10, 1, 'canal1', 1);
INSERT INTO public.channels(id, max, min, "name_channel", sensor_id) VALUES(2, 10, 1, 'canal1', 1);

INSERT INTO public.channels(id, max, min, "name_channel", sensor_id) VALUES(3, 10, 1, 'canal1', 2);
INSERT INTO public.channels(id, max, min, "name_channel", sensor_id) VALUES(4, 10, 1, 'canal1', 2);

INSERT INTO public.measures(id, date_time, value, channel_id) VALUES(1, now(), 10, 1);
INSERT INTO public.measures(id, date_time, value, channel_id) VALUES(2, now(), 10, 2);
INSERT INTO public.measures(id, date_time, value, channel_id) VALUES(3, now(), 10, 3);
INSERT INTO public.measures(id, date_time, value, channel_id) VALUES(4, now(), 10, 4);