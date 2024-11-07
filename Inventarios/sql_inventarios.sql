drop table if exists proveedores;
drop table if exists tipo_documentos;
drop table if exists cabecera_pedido;
drop table if exists estados_pedidos;
drop table if exists detalle_pedido;
drop table if exists historial_stock;
drop table if exists detalle_ventas;
drop table if exists cabecera_ventas;
drop table if exists productos;
drop table if exists unidades_medida;
drop table if exists categorias_unidad_medida;
drop table if exists categorias;



create table categorias(
	codigo_cat serial not null,
	nombre varchar(100),
	categoria_padre int,
	constraint categorias_pk primary key(codigo_cat),
	constraint categorias_fk foreign key(categoria_padre) references categorias(codigo_cat)	
);


insert into categorias(nombre, categoria_padre)
values
('Materia prima',null),
('Proteina',1),
('Salsas', 1),
('Punto de venta', null),
('Bebidas',4),
('Con alcohol',5),
('Sin alchol',5);


create table categorias_unidad_medida(
	codigo_udm char(1) not null,
	nombre varchar(100) not null,

	constraint categorias_unidad_medida_pk primary key(codigo_udm)
);

insert into categorias_unidad_medida(codigo_udm, nombre)
values
('U', 'Unidades'),
('V', 'Volumen'),
('P', 'Peso');

create table unidades_medida(
	codigo_nombre varchar(50) not null,
	descripcion varchar(100) not null,
	id_categoria_unidad_medida char(1) not null,

	constraint unidades_medida_pk primary key(codigo_nombre),
	constraint unidades_medida_categorias_unidad_medida_fk foreign key (id_categoria_unidad_medida) references categorias_unidad_medida(codigo_udm)
);

insert into unidades_medida(codigo_nombre ,descripcion, id_categoria_unidad_medida)
values
('ml','mililitros','V'),
('l','litros','V'),
('u','unidad','U'),
('d','docena','U'),
('g','gramos','P'),
('kg','kilogramos','P'),
('lb','libras','P');

create table productos(
	codigo serial not null,
	nombre varchar(100) not null,
	id_unidades_medida varchar(50) not null,
	precio_venta money not null,
	tiene_iva BOOLEAN not null,
	coste money not null,
	id_categorias int not null,
	stock int,

	constraint productos_pk primary key(codigo),
	constraint productos_unidades_medida_fk foreign key(id_unidades_medida) references unidades_medida(codigo_nombre),
	constraint productos_categorias_fk foreign key(id_categorias) references categorias(codigo_cat)
	
);





insert into productos(nombre, id_unidades_medida, precio_venta, tiene_iva, coste, id_categorias, stock)
values
('Coca cola pequeña', 'u', 0.5804,true,0.323,7,0),
('Salsa de tomate', 'kg', 0.95,true,0.823,3,0);


create table tipo_documentos(
	codigo_tipo_doc char(1) not null,
	descripcion varchar(50) not null,

	constraint tipo_documentos_pk primary key(codigo_tipo_doc)
);

insert into tipo_documentos(codigo_tipo_doc,descripcion)
values
('C','Cedula'),
('R','RUC');


create table proveedores(
	id varchar(13) not null,
	id_tipo_documetos char(1),
	nombre varchar(50) not null,
	telefono char(10) not null,
	correo varchar(30) not null,
	direccion varchar(50) not null,

	constraint proveedores_pk primary key(id),
	constraint proveedores_tipo_documentos_fk foreign key(id_tipo_documetos) references tipo_documentos(codigo_tipo_doc)
);

INSERT INTO proveedores (id, id_tipo_documetos, nombre, telefono, correo, direccion)
VALUES 
('1234567890123', 'C', 'Juan Pérez', '0987654321', 'juan.perez@example.com', 'Calle Falsa 123'),
('9876543210987', 'R', 'Empresa XYZ', '0981234567', 'contacto@empresa.xyz', 'Avenida Real 456');


create table estados_pedidos(
	codigo char(1) not null,
	descripcion varchar(50) not null ,

	constraint estados_pedidos_pk primary key(codigo)
);

insert into estados_pedidos(codigo, descripcion)
values
('S', 'Solicitado'),
('R', 'Recibido');

create table cabecera_pedido(
	numero serial not null,
	id_proveedor varchar(13) not null,
	fecha TIMESTAMP without TIME ZONE not null,
	id_estado_pedido char(1) not null,

	constraint cabecera_pedido_pk primary key(numero),
	constraint cabecera_pedido_estados_pedidos_fk foreign key (id_estado_pedido) references estados_pedidos (codigo)
);

insert into cabecera_pedido(id_proveedor, fecha, id_estado_pedido)
values
('1234567890123', '6/11/2024 19:54:00', 'S'),
('1234567890123', '6/12/2024 19:54:00', 'R');


create table detalle_pedido(
	codigo serial not null,
	id_cabecera_pedido int not null,
	id_producto int not null,
	cantidad_solicitada int not null,
	subtotal money not null,
	cantidad_recibida int,

	constraint detalle_pedido_pk primary key(codigo),
	constraint detalle_pedido_productos_fk foreign key(id_producto) references productos(codigo)
);


insert into detalle_pedido(id_cabecera_pedido,id_producto,cantidad_solicitada,subtotal,cantidad_recibida)
values
(1,1,100,37.29,100),
(1,2,50,11.29,50),
(2,1,10,3.29,10);


create table historial_stock(
	codigo serial not null, 
	fecha TIMESTAMP without TIME ZONE not null,
	referencia varchar(50),
	id_producto int not null,
	cantidad int,

	constraint historial_stock_pk primary key(codigo),
	constraint  historial_stock_productos_fk foreign key(id_producto) references productos(codigo)
);


create table cabecera_ventas(
	codigo serial not null,
	fecha TIMESTAMP without TIME ZONE not null,
	total_sin_iva money not null,
	iva money not null,
	total money not null,
	
	constraint cabecera_ventas_pk primary key(codigo)
	
);

insert into cabecera_ventas( fecha, total_sin_iva, iva ,total)
values('6/11/2024 19:54:00',3.36,0.39,3.65);


create table detalle_ventas(
	codigo serial not null,
	id_cabecera_ventas int not null,
	id_producto int not null,
	cantidad int not null,
	precio_venta money not null,
	sub_total money not null,
	sub_total_con_iva money not null,

	constraint detalle_ventas_pk primary key(codigo),
	constraint detalle_ventas_cabecera_ventas_fk foreign key(id_cabecera_ventas) references cabecera_ventas(codigo),
	constraint detalle_ventas_Productos_fk foreign key(id_producto) references productos(codigo)
);

insert into detalle_ventas(id_cabecera_ventas, id_producto,cantidad,precio_venta,sub_total,sub_total_con_iva )
values
(1,1,5,0.58,2.9,3.25),
(1,2,5,0.36,0.36,0.4);


