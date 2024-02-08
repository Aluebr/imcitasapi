FROM openjdk:17-jdk-slim

MAINTAINER Jose Vicente Ebri

COPY target/citas-0.0.1-SNAPSHOT.jar citas-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "citas-0.0.1-SNAPSHOT.jar"]


