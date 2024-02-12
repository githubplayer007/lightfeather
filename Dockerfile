FROM amazoncorretto:17.0.0-alpine3.14
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]