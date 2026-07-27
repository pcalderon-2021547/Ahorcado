# Ahorcado

Juego del ahorcado con **Spring Boot API** + **Cliente Web JSP**.

## Requisitos

- Java 21 (JDK)
- Docker (o MySQL 8 con base de datos `DB_Ahorcado`)
- Apache Tomcat 9

## Cómo correrlo

### 1. Base de datos (MySQL 8 con Docker)

```bash
docker run -d --name mysql-ahorcado -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=DB_Ahorcado -p 3306:3306 mysql:8
docker cp Ahorcado/DB_Ahorcdo.sql mysql-ahorcado:/tmp/db.sql
docker exec mysql-ahorcado mysql -uroot -padmin DB_Ahorcado -e "source /tmp/db.sql"
```

### 2. API (Spring Boot)

Desde una terminal:

```bash
cd ApiAhorcado
set JAVA_HOME=C:\Program Files\Java\jdk-21
.\mvnw spring-boot:run -DskipTests
```

O desde VS Code con la extensión de Spring Boot: abre `ApiAhorcado/` y usa el launcher configurado.

La API arranca en `http://localhost:8768`.

### 3. Cliente Web (JSP + Tomcat)

El WAR ya está construido en `Ahorcado/Ahorcado/dist/Ahorcado1.war`.

**Con Tomcat:**
```bash
cp Ahorcado/Ahorcado/dist/Ahorcado1.war /ruta/a/tomcat/webapps/
cd /ruta/a/tomcat/bin
./startup.sh
```

El cliente queda en `http://localhost:8080/Ahorcado1/`.

### Usuarios de prueba

| Email | Contraseña |
|---|---|
| carlosmendoza@gmail.com | carlitos23 |
| analopez@kinal.edu.gt | pepe23 |
| pedrogonzalez@kinal.edu.gt | holi |

## Endpoints de la API

- `GET /api/palabras` — listar palabras
- `GET /api/palabras/{id}` — buscar por ID
- `POST /api/palabras` — crear palabra
- `PUT /api/palabras/{id}` — actualizar palabra
- `DELETE /api/palabras/{id}` — eliminar palabra
