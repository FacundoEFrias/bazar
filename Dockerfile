# Paso 1: Usar una imagen oficial de Java 21
FROM eclipse-temurin:21-jdk-alpine

# Paso 2: Crear el directorio donde vivirá la app dentro del contenedor
WORKDIR /app

# Paso 3: Copiar el archivo ejecutable JAR compilado por Maven
COPY target/bazar-0.0.1-SNAPSHOT.jar app.jar

# Paso 4: Exponer el puerto en el que corre Spring Boot
EXPOSE 8080

# Paso 5: Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]