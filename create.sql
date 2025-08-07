CREATE DATABASE proyecto_seguridad;
USE proyecto_seguridad;


create table actividad_horario (actividad_id bigint not null, horario_id bigint not null, primary key (actividad_id, horario_id)) engine=InnoDB;
create table actividades (activo bit, duracion float(53), id bigint not null auto_increment, descripcion varchar(255) not null, titulo varchar(255) not null, primary key (id)) engine=InnoDB;
create table actividades_instructores (actividad_id bigint not null, instructor_id bigint not null, primary key (actividad_id, instructor_id)) engine=InnoDB;
create table actividades_usuarios (actividad_id bigint not null, usuario_id bigint not null, primary key (actividad_id, usuario_id)) engine=InnoDB;
create table direcciones (numero integer, id bigint not null auto_increment, localidad_id bigint not null, calle varchar(255) not null, primary key (id)) engine=InnoDB;
create table horarios (hora time(6), id bigint not null auto_increment, dia enum ('DOMINGO','JUEVES','LUNES','MARTES','MIERCOLES','SABADO','VIERNES'), primary key (id)) engine=InnoDB;
create table localidades (id bigint not null auto_increment, provincia_id bigint, nombre varchar(255), primary key (id)) engine=InnoDB;
create table movimientos (egreso datetime(6), id bigint not null auto_increment, ingreso datetime(6), usuario_id bigint, tipo_ingreso enum ('ENTRADA','SALIDA'), primary key (id)) engine=InnoDB;
create table provincias (id bigint not null auto_increment, nombre varchar(255) not null, primary key (id)) engine=InnoDB;
create table publicaciones (id bigint not null auto_increment, usuario_id bigint, descripcion varchar(255) not null, imagen varchar(255), titulo varchar(255) not null, tipo_publicacion enum ('COMUNICADO','NOTICIA') not null, primary key (id)) engine=InnoDB;
create table usuarios (activo bit, fecha_nacimiento date not null, ingreso_activo bit, direccion_id bigint, id bigint not null auto_increment, ultimo_egreso datetime(6), ultimo_ingreso datetime(6), apellido varchar(255) not null, dni varchar(255) not null, email varchar(255), foto varchar(255), nombre varchar(255) not null, password varchar(255), pin varchar(255), telefono varchar(255), username varchar(255), rol enum ('ADMIN','DEV','STAFF','USER') not null, sexo enum ('FEMENINO','MASCULINO') not null, primary key (id)) engine=InnoDB;
alter table actividad_horario add constraint FK4msy4cfb4qntm3dbddh2htwom foreign key (horario_id) references horarios (id);
alter table actividad_horario add constraint FKd7p0j53nlhrkb09pd62ne9d1n foreign key (actividad_id) references actividades (id);
alter table actividades_instructores add constraint FKd8jej551d3q5vlwxlmsisqywl foreign key (instructor_id) references usuarios (id);
alter table actividades_instructores add constraint FK1xy1d7xc8n8l8bt4do4pwdgh5 foreign key (actividad_id) references actividades (id);
alter table actividades_usuarios add constraint FKvnv9n7b1m9iijwxro9u615ls foreign key (usuario_id) references usuarios (id);
alter table actividades_usuarios add constraint FK7vd2ywyg797d9koibeooumk32 foreign key (actividad_id) references actividades (id);
alter table direcciones add constraint FKhhtl5u75eacbbsrt3xtv433ci foreign key (localidad_id) references localidades (id);
alter table localidades add constraint FKpsu514uguuo384j979ub9dp4y foreign key (provincia_id) references provincias (id);
alter table movimientos add constraint FK2ofduyqqwmyyf07vue5g6iv7m foreign key (usuario_id) references usuarios (id);
alter table publicaciones add constraint FKcuualw35fb3065r7mjiijb898 foreign key (usuario_id) references usuarios (id);
alter table usuarios add constraint FK8lfvsnntkwwg6r7kluhmte1nn foreign key (direccion_id) references direcciones (id);
