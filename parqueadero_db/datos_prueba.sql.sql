CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV001');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV002');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV003');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV004');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV005');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV006');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV007');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV008');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV009');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV010');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV011');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV012');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV013');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV014');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV015');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV016');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV017');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV018');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV019');
insert into public.vehiculo (id, placa) values (uuid_generate_v4(), 'DCV020');

INSERT INTO public.parqueadero (id,nombre,usuario_id,costo,capacidad) VALUES
	 (uuid_generate_v4(),'park1', (select u.id from usuario u where u.email like 'socio1@mail.com'), 1500.0, 6);
INSERT INTO public.parqueadero (id,nombre,usuario_id,costo,capacidad) VALUES
	 (uuid_generate_v4(),'park2',(select u.id from usuario u where u.email like 'socio1@mail.com'),1500.0,6);
INSERT INTO public.parqueadero (id,nombre,usuario_id,costo,capacidad) VALUES
	 (uuid_generate_v4(),'park3',(select u.id from usuario u where u.email like 'socio1@mail.com'),1500.0,6);
INSERT INTO public.parqueadero (id,nombre,usuario_id,costo,capacidad) VALUES
	 (uuid_generate_v4(),'park4',(select u.id from usuario u where u.email like 'socio1@mail.com'),1500.0,6);
INSERT INTO public.parqueadero (id,nombre,usuario_id,costo,capacidad) VALUES
	 (uuid_generate_v4(),'park5',(select u.id from usuario u where u.email like 'socio1@mail.com'),1500.0,6);
INSERT INTO public.parqueadero (id,nombre,usuario_id,costo,capacidad) VALUES
	 (uuid_generate_v4(),'park6',(select u.id from usuario u where u.email like 'socio1@mail.com'),1500.0,6);
	
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		((select id from public.vehiculo v where v.placa like 'DCV001')),
	    ((select id from public.parqueadero p where p.nombre like 'park1')),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
	  
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV001'),
	    (select id from public.parqueadero p where p.nombre like 'park1'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);

INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV002'),
	    (select id from public.parqueadero p where p.nombre like 'park2'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV002'),
	    (select id from public.parqueadero p where p.nombre like 'park2'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV002'),
	    (select id from public.parqueadero p where p.nombre like 'park2'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV001'),
	    (select id from public.parqueadero p where p.nombre like 'park3'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV003'),
	    (select id from public.parqueadero p where p.nombre like 'park3'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV004'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV004'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV004'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV004'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV004'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV004'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV005'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
  INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV005'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV005'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);
INSERT INTO public.vehiculos_parqueados (id,vehiculo_id,parqueadero_id,entry_date,exit_date,total) VALUES
	 (	uuid_generate_v4(),
		(select id from public.vehiculo v where v.placa like 'DCV005'),
	    (select id from public.parqueadero p where p.nombre like 'park4'),
	    '2024-02-03 19:40:07.914',
	  	'2024-02-03 19:40:28.698',
	  	2500.0);

	