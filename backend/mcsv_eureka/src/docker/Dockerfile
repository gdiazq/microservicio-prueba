#inicia con la imagen base que contiene Java runtime
FROM openjdk:21-jdk-slim as build

# se agregar el jar del microservicio al contenedor
ADD ../target/mcsv_eureka-0.0.1-SNAPSHOT.jar /home/mcsv_eureka.jar

# Declarar el puerto que se debe exponer
EXPOSE 8761

#se ejecuta el microservicio
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/mcsv_eureka.jar"]
