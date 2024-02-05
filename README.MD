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

El archivo compose define dos aplicaciones `parqueadero_app` y `mail_app` cada una de ellas corriendo en el puerto **8080** y **8081** correspondientemente. Adicional, se despliega un servicio postgresql en el puerto **5432**.

En la sección **parqueadero_doc** encontrará el modelo de base de datos junto con la **coleccion postman** para el consumo de las apis.

El esquema de la base de datos se construira de forma automatica utilizando el scritp **schema-parqueadero.sql** precargando dos usuarios 
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

Si lo requiere, existe un script de base de datos adicional **datos_prueba.sql** con datos de prueba. Debe ejecutarse manualmente, los datos de acceso a la bd son los siguientes:
```
-   POSTGRES_DB=parqueadero
-   POSTGRES_USER=postgres
-   POSTGRES_PASSWORD=admin
```
## Deploy with docker compose

```
$ docker compose up -d
```

Si se desea correr la aplicación de forma manual, es importante tener en cuenta que parqueadero_app esta apuntado a los host: **mailapp**, **parqueadero_db** los cuales coninciden con localhost

## Postman 
Dentro de la carpeta de **parqueadero_doc** podrá encontrar el postman con las apis habilitadas para funcionar.

Recuerde que se está utilizando roles y por tanto debe usar el usuario correcto para acceder a ellas. A continuación un resumen de las operaciones:
-   Administrador
```
-   login
-   create user
-   create parqueadero
-   read parqueadero
-   listar todos los parqueaderos
-   actualizar parqueadero
-   eliminar parqueadero
-   enviar email

```
- Socio
```
-   registrar entrada de vehiculos
-   registrar salida de vehiculos
-   listar parqueaderos por socio
-   indicador 4
```
-   Ambos (Administrador y socio)
```
-   listar vehiculos en un parqueadero
-   indicador 1
-   indicador 2
-   indicador 3
-   indicador 5
```


## Importante
No olvide firmar todas las peticiones con el **bearer token** generado en la operacion **login**.

Para su facilidad, en la **colección postman**, se utiliza una variable global para manejar este valor.