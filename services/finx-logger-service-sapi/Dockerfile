FROM amazoncorretto:17-alpine-jdk
LABEL service="logger-service"

ENV APP_HOME=/app
WORKDIR $APP_HOME

RUN apk update && \
    apk add --no-cache curl && \
    apk add --no-cache busybox-extras
COPY build/libs/logger-service.jar ./app.jar
RUN addgroup -S spring && adduser -S spring -G spring
RUN chown -R spring:spring $APP_HOME
RUN chmod -R 555 $APP_HOME && \
    chmod +x $APP_HOME/app.jar
USER spring
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "./app.jar"]