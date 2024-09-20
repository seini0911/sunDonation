
FROM maven:3.8.6-openjdk-17 AS build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-alpine3.12
# Copy the JAR file from the build context into the Docker image
ARG JAR_FILE=target/*.jar
COPY target/donations-0.0.1-SNAPSHOT.jar application.jar
# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "application.jar"]