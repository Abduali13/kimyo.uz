FROM openjdk:17
ADD target/third-project.jar app.jar
VOLUME /main.app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]