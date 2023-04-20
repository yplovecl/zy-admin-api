#FROM anapsix/alpine-java:8_server-jre_unlimited
FROM eclipse-temurin:17-jdk-alpine

RUN mkdir -p /app/logs /app/temp /app/uploads

WORKDIR /app

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ADD ./target/ruoyi.jar ./app.jar

ENTRYPOINT ["java", "-Dserver.port=${SERVER_PORT}", "-jar", "app.jar", "--spring.profiles.active=prod"]