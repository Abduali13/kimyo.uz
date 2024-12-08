FROM openjdk:17
ADD target/kimyo-projects.jar.original app.jar
VOLUME /main.app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]