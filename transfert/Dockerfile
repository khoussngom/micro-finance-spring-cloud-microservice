FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/transfert-0.0.1-SNAPSHOT.jar transfert.jar
EXPOSE 8099
ENTRYPOINT ["java", "-jar", "transfert.jar"]