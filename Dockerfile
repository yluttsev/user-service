FROM amazoncorretto:17-alpine
COPY build/libs/user-service.jar .
ENTRYPOINT ["java", "-jar", "user-service.jar"]