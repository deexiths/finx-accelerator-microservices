FROM openjdk:17-jdk-alpine
LABEL service="payload-service"
COPY build/libs/payload-service.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]