FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/patient-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 4000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]