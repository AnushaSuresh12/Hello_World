FROM openjdk:8
EXPOSE 8080
ADD target/Hello_World.jar app.jar
ENTRYPOINT [ "java", "-cp", "/app.jar","com.Anusha.Hello_World.HelloWorldApplication"]