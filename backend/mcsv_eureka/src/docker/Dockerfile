FROM openjdk:21-jdk-slim as build

ADD ../target/mcsv_eureka-0.0.1-SNAPSHOT.jar /home/mcsv_eureka.jar

EXPOSE 8761

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/mcsv_eureka.jar"]
