FROM openjdk:11-slim as build
VOLUME /tmp
ADD /target/zequiz-api-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java" ,"-jar", "/app.jar"," -Dspring.profiles.active=dev"]


