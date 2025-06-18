# Use OpenJDK 17 as base image
FROM eclipse-temurin:17-jdk-alpine

# Set a working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/MicroUserService-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the service runs on (change as needed per service)
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
