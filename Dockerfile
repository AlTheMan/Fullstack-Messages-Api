FROM eclipse-temurin:17-jdk-jammy


FROM maven:3.8.1-openjdk-17-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f app/pom.xml install


FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/MessageApi-1.0.jar /app/MessageApi-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/MessageApi-1.0.jar"]




#WORKDIR /app
# Copy the JAR file
#COPY target/MessagesApi-1.0.jar /app/MessagesApi-1.0.jar
# Make port available to the world outside this container
#EXPOSE 8080
# Run the JAR file
#ENTRYPOINT ["java", "-jar", "/app/MessagesApi-1.0.jar"]