FROM openjdk:17
EXPOSE 8080

ADD target/spring-zap-docker.jar spring-zap-docker.jar
ENTRYPOINT [ "java", "-jar", "/spring-zap-docker.jar" ]