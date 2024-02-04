## Parqueadero App
Aplicacion parqueadero para probar de forma sencilla la implementacion de una api rest con spring boot

### Get started

-   Docker version 23.0.2 or later.
-   SpringBoot version 3.1.9
-   Java 17
-   Postgresql version 12.2

### Aplicacion java con spring boot, 

Estructura del proyecto:
```
.
├── parqueadero_db
│   └── schema-parqueadero.sql
├── parqueadero_doc
│   └── ...
├── parqueadero_app
│   ├── Dockerfile
│   └── ...
├── mail_app
│   ├── Dockerfile
│   └── ...
├── docker-compose.yaml
└── README.md
```

El archivo compose define dos aplicaciones `parqueadero_app` y `mail_app` cada una de ellas corriendo en el puerto 8080 y 8081 correspondientemente. Adicional, se despliega un servicio postgresql en el puerto 5432.

la base de datos se precarga con dos usuarios 
-   Administrador
```
    email: admin@mail.com
    pass: admin
```
-   Socio
```
    email: socio1@mail.com
    pass: socio1
```

Si lo requiere, existe un script de base de datos adicional con datos de prueba. Pero debe ejecutarse manualmente.

## Deploy with docker compose

```
$ docker compose up -d
```

Si se desea correr la aplicación de forma manual, es importante tener en cuenta que parqueadero_app esta apuntado a los host: mail_app, parqueadero_db los cuales coninciden con localhost

## Postman 
Dentro de la carpeta de doc se puede encontrar el postman con las apis habilitadas para funcionar.

La mayoría de las funcionalidades están pensadas para correr en el camino feliz.