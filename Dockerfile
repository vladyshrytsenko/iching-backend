FROM openjdk:21-jdk
WORKDIR /app
COPY target/iching-backend-0.0.1-SNAPSHOT.jar /app/iching-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/iching-backend.jar"]