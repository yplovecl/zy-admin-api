FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER PANGE

RUN mkdir -p /app/server/logs \
    /app/server/temp

WORKDIR /app/server

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ADD ./target/ruoyi.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
            "-jar", "app.jar"]