CREATE TABLE IF NOT EXISTS usuario (
	id uuid NOT NULL,
	rol varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	pass TEXT NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);


CREATE TABLE IF NOT EXISTS parqueadero (
	id uuid NOT NULL,
	nombre varchar(255) NOT NULL,
	usuario_id uuid NOT NULL,
	costo double precision NOT NULL,
	capacidad integer NOT NULL,
	CONSTRAINT parqueadero_pk PRIMARY KEY (id),
	CONSTRAINT parqueadero_fk0 FOREIGN KEY (usuario_id) 
		REFERENCES usuario(id)
) WITH (
  OIDS=FALSE
);


CREATE TABLE IF NOT EXISTS vehiculo (
	id uuid NOT NULL,
	placa varchar(10) NOT NULL,
	CONSTRAINT vehiculo_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);


CREATE TABLE IF NOT EXISTS vehiculos_parqueados (
	id uuid NOT NULL,
	vehiculo_id uuid NOT NULL,
	parqueadero_id uuid NOT NULL,
	entry_date TIMESTAMP NOT NULL,
	exit_date TIMESTAMP DEFAULT NULL,
	total double precision NOT NULL,
	CONSTRAINT vehiculos_parqueados_pk PRIMARY KEY (id),
	CONSTRAINT vehiculos_parqueados_fk0 FOREIGN KEY (vehiculo_id)
    	REFERENCES vehiculo(id) MATCH SIMPLE        
        ON DELETE CASCADE,
	CONSTRAINT vehiculos_parqueados_fk1 FOREIGN KEY (parqueadero_id)
    	REFERENCES parqueadero(id) MATCH SIMPLE        
        ON DELETE CASCADE
) WITH (
  OIDS=FALSE
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into usuario (id, rol, email, pass) values (uuid_generate_v4(), 'ADMINISTRATOR', 'admin@mail.com', '$2a$10$XUCGuP06Pi94k5E19HaX9uu6gQWWB/wWV3VXuGbTkITn7M7MdHq4e');
insert into usuario (id, rol, email, pass) values (uuid_generate_v4(), 'SOCIO', 'socio1@mail.com', '$2a$10$pq/FdhP8b8bNRnqeKH2dFOhIwMn3cCzpuk4zVCvkCG06GrXlq71bK');