FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/bookingsystem-0.0.1-SNAPSHOT.jar

# cd app
WORKDIR app

# cp target/spring-boot-web.jar app/app.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

# java -jar app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]