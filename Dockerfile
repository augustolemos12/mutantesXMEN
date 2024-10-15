# Etapa de construcción
FROM alpine:latest as build

RUN apk update \
    && apk add openjdk17 \
    && apk add bash

WORKDIR /app

# Copiamos el código fuente al contenedor
COPY . .

# Damos permisos y construimos la aplicación
RUN chmod +x ./gradlew \
    && ./gradlew bootJar --no-daemon

# Etapa de ejecución
FROM openjdk:17-alpine

# Exponemos el puerto que la aplicación usará
EXPOSE 8080

# Copiamos el JAR generado en la etapa de construcción
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Comando de ejecución de la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
