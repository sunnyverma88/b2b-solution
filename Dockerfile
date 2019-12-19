# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Shwetabh Gaurav <shwetabh.gaurav@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
#EXPOSE 8080



# The application's jar file
ARG JAR_FILE=target/b2b-solution-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} b2b-solution.jar

# Run the jar file
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/b2b-solution.jar"]

