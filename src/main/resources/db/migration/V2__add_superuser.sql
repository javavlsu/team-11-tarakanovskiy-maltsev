insert into usr(first_name, last_name, password, patronymic, username)
values ('Stepan', 'Maltsev', '$2a$12$IqNwme0Njo36OHRakTNe.OcFo.kpO0YVCV/ClfTCbHJvwkF0PKoTe', 'Batkovich', 'admin');
insert into user_role(user_id, roles)
values (1, 'ADMIN'), (1, 'TEACHER'), (1, 'STUDENT');