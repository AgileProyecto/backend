# Etapa 1: Construcción del JAR
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen para ejecución
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar govench-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","govench-api.jar"]
