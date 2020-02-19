
--ROLES
INSERT INTO easymprest.role (role_id, role) VALUES ('1', 'ADMIN');
INSERT INTO easymprest.role (role_id, role) VALUES ('2', 'USER');


--FIRST ADMIN Senha oiadmin
INSERT INTO easymprest.user (user_id, active, email, name, password, registration) VALUES ('1', '1', 'admin@admin.com', 'Administrador', '$2a$10$SjMN1Oy.oDOeNBcSssoP9eqK/UOEpmw/PAo/BZU8xLEkFe2rEFP/O', 'Admin');
INSERT INTO easymprest.user_role (user_id, role_id) VALUES ('1', '1');

--FIRST USER senha oiuser
INSERT INTO easymprest.user (user_id, active, email, name, password, registration) VALUES ('2', '1', 'user@user.com', 'usuário', '$2a$10$9vgBEfY..rRbZj47/APbTuCqYVb/0DVrKbhNcKrE/CyQ6iV6RTQW2', 'User');
INSERT INTO easymprest.user_role (user_id, role_id) VALUES ('2', '2');



