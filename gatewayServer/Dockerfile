FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
RUN apk add --no-cache curl
COPY --from=build /app/target/gatewayServer-0.0.1-SNAPSHOT.jar gatewayServer.jar
EXPOSE 8888
ENV JAVA_OPTS="-Xmx256m -Xms128m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar gatewayServer.jar"]
