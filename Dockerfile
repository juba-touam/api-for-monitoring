FROM ubuntu
RUN apt-get update && apt-get install openjdk-17-jdk curl vim -y
WORKDIR /opt
ADD target/api-for-monitoring-*.jar api-monitoring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/api-monitoring.jar"]
