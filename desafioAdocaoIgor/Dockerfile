FROM maven:3.8-openjdk-17-slim AS builder
WORKDIR /app
COPY ./src /app/src
COPY ./pom.xml /app/.
COPY ./mvnw.cmd /app/.

RUN mvn -B -f pom.xml clean package

FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","application.jar"]