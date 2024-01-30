-- Insertar roles en la tabla "role"
INSERT INTO roles (name) VALUES ('GESTOR');
INSERT INTO roles (name) VALUES ('USUARIO');

-- Insertar usuarios en la tabla "users" con roles asignados
-- Usuario con rol GESTOR
INSERT INTO users (email, password) VALUES ('usuario1@example.com', 'contraseña1');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- Asignar rol GESTOR al usuario 1

-- Usuario con rol USUARIO
INSERT INTO users (email, password) VALUES ('usuario2@example.com', 'contraseña2');
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- Asignar rol USUARIO al usuario 2;
