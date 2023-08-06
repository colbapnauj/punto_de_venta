CREATE DATABASE restaurante_db;

USE restaurante_db;

CREATE TABLE persona (
    id_persona INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    documento VARCHAR(20) NOT NULL
);

CREATE TABLE roles (
    id_role INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE permisos (
    id_permiso INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE roles_permisos (
    id_role INT,
    id_permiso INT,
    PRIMARY KEY (id_role, id_permiso),
    FOREIGN KEY (id_role) REFERENCES roles(id_role),
    FOREIGN KEY (id_permiso) REFERENCES permisos(id_permiso)
);

CREATE TABLE cliente (
    id_persona INT PRIMARY KEY,
    -- Agregar otros campos específicos del cliente si es necesario
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
);

CREATE TABLE mesa (
    id_mesa INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE producto (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE empleado (
    id_persona INT PRIMARY KEY,
    id_role INT NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    FOREIGN KEY (id_role) REFERENCES roles(id_role)
);

CREATE TABLE pedido (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_empleado INT NOT NULL,
    id_mesa INT NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_persona),
    FOREIGN KEY (id_mesa) REFERENCES mesa(id_mesa)
);


CREATE TABLE pedido_producto (
    id_pedido INT,
    id_producto INT,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_pedido, id_producto),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE comprobante_pago (
    id_comprobante INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    id_cliente INT NOT NULL,
    tipo_comprobante VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL,
    porcentaje_igv DECIMAL(5, 2) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_persona)
);

USE restaurante_db;

INSERT INTO roles (nombre) VALUES ('cajero');
INSERT INTO roles (nombre) VALUES ('mozo');
INSERT INTO roles (nombre) VALUES ('administrador');

INSERT INTO permisos (nombre) VALUES ('generar_pedidos');
INSERT INTO permisos (nombre) VALUES ('cobrar');
INSERT INTO permisos (nombre) VALUES ('generar_comprobante');
INSERT INTO permisos (nombre) VALUES ('crear_usuarios');

INSERT INTO persona (nombre, tipo_documento, documento) VALUES ('Juan Pérez', 'DNI', '12345678');
INSERT INTO persona (nombre, tipo_documento, documento) VALUES ('María Gómez', 'RUC', '87654321');

INSERT INTO cliente (id_persona) VALUES (1);

INSERT INTO empleado (id_persona, id_role, usuario, password) VALUES (1, 1, 'usuario1', 'password123');
INSERT INTO empleado (id_persona, id_role, usuario, password) VALUES (2, 2, 'usuario2', 'qwerty456');

INSERT INTO producto (descripcion, precio) VALUES ('Pizza Margarita', 10.99);
INSERT INTO producto (descripcion, precio) VALUES ('Hamburguesa Clásica', 8.50);
INSERT INTO producto (descripcion, precio) VALUES ('Ensalada César', 6.75);

INSERT INTO mesa (descripcion) VALUES ('Mesa 1');
INSERT INTO mesa (descripcion) VALUES ('Mesa 2');
INSERT INTO mesa (descripcion) VALUES ('Mesa 3');


INSERT INTO pedido (id_empleado, id_mesa) VALUES (1, 1);
INSERT INTO pedido_producto (id_pedido, id_producto, cantidad) VALUES (1, 1, 2);
INSERT INTO pedido_producto (id_pedido, id_producto, cantidad) VALUES (1, 2, 1);

INSERT INTO comprobante_pago (id_pedido, id_cliente, tipo_comprobante, fecha, porcentaje_igv, total) VALUES (1, 1, 'Boleta', '2023-08-06', 18.00, 32.50);

SELECT * FROM producto
