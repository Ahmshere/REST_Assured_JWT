# Use the official Gradle image as a base image
FROM docker.io/library/gradle:7.2.0-jdk11 AS builder

# Set the working directory
WORKDIR /home/gradle/src

# Copy only the Gradle files into the container
COPY --chown=gradle:gradle build.gradle settings.gradle /home/gradle/src/

# Copy the source code into the container
COPY --chown=gradle:gradle src /home/gradle/src/src

# Build the project
RUN gradle build

# Use the official OpenJDK image as a base image
FROM docker.io/library/openjdk:11

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the current stage
COPY build/libs/GetResults01.jar /app/


# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "GetResults01.jar"]
