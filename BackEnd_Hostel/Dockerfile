# Use OpenJDK 22 as the base image
FROM openjdk:22-jdk

# Copy the JAR file into the container
ADD target/backend.jar backend.jar

# Expose the backend port
#EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/backend.jar"]