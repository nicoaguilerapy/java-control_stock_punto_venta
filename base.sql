CREATE TABLE usuarios (
  id SERIAL PRIMARY KEY,
  nombre varchar(200),
  usuario varchar(16),
  pass varchar(16),
  rango varchar(50)
);

CREATE TABLE empleados (
  id SERIAL PRIMARY KEY,
  cinruc VARCHAR(15),
  nombres VARCHAR(150),
  apellidos VARCHAR(150),
  direccion VARCHAR(300),
  fecha VARCHAR(10),
  celtel VARCHAR(15),
  correo VARCHAR(50),
  ciudad VARCHAR(50),
  idCuenta int,
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id),
  FOREIGN KEY (idCuenta) REFERENCES usuarios(id)
);

CREATE TABLE usuarioshistorial (
  id SERIAL PRIMARY KEY,
  idUsuarios int,
  fecha varchar(10),
  hora varchar(10),
  ip varchar(15),
  host varchar(50),
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE caja (
  id SERIAL PRIMARY KEY,
  concepto varchar(200),
  ingreso int,
  egreso int,
  fecha varchar(10),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE banco (
  id SERIAL PRIMARY KEY,
  concepto varchar(200),
  ingreso int,
  egreso int,
  fecha varchar(10),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE tarjeta (
  id SERIAL PRIMARY KEY,
  concepto varchar(200),
  ingreso int,
  egreso int,
  fecha varchar(10),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE acreedores (
  id SERIAL PRIMARY KEY,
  descripcion varchar(200),
  monto int,
  fecha varchar(10),
  estado varchar(10),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE clientes (
  id SERIAL PRIMARY KEY,
  cinruc VARCHAR(15),
  nombres VARCHAR(150),
  apellidos VARCHAR(150),
  direccion VARCHAR(300),
  fecha VARCHAR(10),
  celtel VARCHAR(15),
  correo VARCHAR(50),
  ciudad VARCHAR(50),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE proveedores (
  id SERIAL PRIMARY KEY,
  cinruc VARCHAR(15),
  nombres VARCHAR(150),
  apellidos VARCHAR(150),
  direccion VARCHAR(200),
  fecha VARCHAR(10),
  celtel VARCHAR(13),
  correo VARCHAR(50),
  empresanombre VARCHAR(200),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE productos (
  id SERIAL PRIMARY KEY,
  nombre varchar(150),
  codigo varchar(10),
  valor1 int,
  valor2 int,
  valor3 int,
  estado varchar(15),
  ilimitado boolean,
  iva int,
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE lotesproductos (
  id SERIAL PRIMARY KEY,
  fecha VARCHAR(15),
  facturanum VARCHAR(15),
  estado VARCHAR(20),
  gastototal int,
  ivacredito int,
  idProveedor int,
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id),
  FOREIGN KEY (idProveedor) REFERENCES proveedores(id)
);

CREATE TABLE lotesdetalles (
  id SERIAL PRIMARY KEY,
  cantidad int,
  costo int,
  idLote int,
  idProducto int,
  idUsuarios int,
  FOREIGN KEY (idLote) REFERENCES lotesproductos(id),
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id),
  FOREIGN KEY (idProducto) REFERENCES productos(id)
);

CREATE TABLE bajasproductos (
  id SERIAL PRIMARY KEY,
  fecha varchar(10),
  motivo varchar(255),
  cantidad int,
  idProducto int,
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id),
  FOREIGN KEY (idProducto) REFERENCES productos(id)
);

CREATE TABLE compras (
  id SERIAL PRIMARY KEY,
  facturanum varchar(50),
  fecha varchar(10),
  total int,
  iva int,
  estado varchar(20),
  tipo varchar(30),
  idProveedor int,
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id),
  FOREIGN KEY (idProveedor) REFERENCES proveedores(id)
);

CREATE TABLE comprasdetalles (
  id SERIAL PRIMARY KEY,
  concepto varchar(255),
  cantidad int,
  costo int,
  idCompras int,
  FOREIGN KEY (idCompras) REFERENCES compras(id)
);

CREATE TABLE ventas (
  id SERIAL PRIMARY KEY,
  fecha varchar(10),
  idClientes int,
  total int,
  estado varchar(20),
  facturanum varchar(50),
  formapago varchar(20),
  idVendedor int,
  idCobrador int,
  FOREIGN KEY (idClientes) REFERENCES clientes(id),
  FOREIGN KEY (idVendedor) REFERENCES usuarios(id),
  FOREIGN KEY (idCobrador) REFERENCES usuarios(id)
);

CREATE TABLE detallesventas (
  id SERIAL PRIMARY KEY,
  idVentas int,
  idProductos int,
  cantidad int,
  iva5 int,
  iva10 int,
  precio int,
  subtotal int,
  FOREIGN KEY (idVentas) REFERENCES ventas(id),
  FOREIGN KEY (idProductos) REFERENCES productos(id)
);

CREATE TABLE pedidos (
  id SERIAL PRIMARY KEY,
  pedido varchar(100),
  detalles varchar(600),
  estado varchar(16),
  fecha varchar(10),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE ivadebito (
  id SERIAL PRIMARY KEY,
  concepto varchar(200),
  ingreso int,
  egreso int,
  fecha varchar(10),
  idUsuarios int,
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE deudas (
  id SERIAL PRIMARY KEY,
  idClientes int,
  estado varchar(15),
  idUsuarios int,
  FOREIGN KEY (idClientes) REFERENCES clientes(id),
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE deudas_ventas (
  id SERIAL PRIMARY KEY,
  fecha varchar(10),
  iddeudas int,
  idventas int,
  idUsuarios int,
  FOREIGN KEY (iddeudas) REFERENCES deudas(id),
  FOREIGN KEY (idventas) REFERENCES ventas(id),
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE deudas_pagos (
  id SERIAL PRIMARY KEY,
  fecha varchar(10),
  iddeudas int,
  pago varchar(200),
  idUsuarios int,
  FOREIGN KEY (iddeudas) REFERENCES deudas(id),
  FOREIGN KEY (idUsuarios) REFERENCES usuarios(id)
);

CREATE TABLE datos (
  id SERIAL PRIMARY KEY,
  atributo varchar(50),
  valor varchar(50)
);

INSERT INTO usuarios (usuario, pass, rango, nombre) VALUES ('admin', 'admin', 'Administrador', 'Administrador General');
INSERT INTO empleados (id, cinruc, nombres, apellidos, direccion, fecha, celtel, correo, ciudad, idUsuarios, idCuenta) 
VALUES (DEFAULT, '0', 'Administrador', 'General', NULL, '2020/01/01', '000000000', NULL, 'Ciudad', '1', '1');

INSERT INTO proveedores (id, cinruc, nombres, apellidos, direccion, fecha, celtel, correo, empresanombre, idUsuarios) 
VALUES (DEFAULT, '0', 'Mi Empresa', 'Mi Empresa', 'Mi Empresa', '2020/01/01', '000000000000', ' ', 'Mi Empresa', '1');

INSERT INTO datos (id, atributo, valor) VALUES
(1, 'empresa', 'vacio11'),
(2, 'direccion1', 'vacio22'),
(3, 'direccion2', 'vacio33'),
(4, 'celtel', 'vacio44'),
(5, 'correo', 'vacio55');
