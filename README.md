# Coatic
- Proyecto de Ejemplo del curso FullStack con JAVA
- PoloTic Misiones

## ⌨🖱 Instalación
- Si queremos correr la aplicación en un entorno local debemos tener en cuenta lo siguiente: 

1. Clonar el repositorio utilizando GIT o descargando el archivo ZIP:

    `https://github.com/thiagobrnal/Coatic.git`

2. Instalar las dependencias de Maven utilizando nuestro IDE preferido o a través del comando:

    `mvn install`

3. Crear/Configurar el archivo _application.properties_ en src/main/resources/

```properties
spring.datasource.url=jdbc:mariadb://<host_DB>:<puerto_DB>/concesionaria
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```
NOTA: Reemplazar los valores borrando los <>.

4. Ejecutar nuestra aplicación ejecutando el siguiente comando:
    `mvn spring-boot:run`


