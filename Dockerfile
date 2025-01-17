FROM ubuntu:lastest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

COPY . .

RUN ap-get install maven -y

RUN mvn clear install 

EXPOSE 8080

COPY --from=build /target/todolist-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]