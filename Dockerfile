FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
# Copy the JAR file
COPY target/MessagesApi-1.0.jar /app/MessagesApi-1.0.jar
# Make port available to the world outside this container
EXPOSE 8080
# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/MessagesApi-1.0.jar"]