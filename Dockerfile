FROM maven:3-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine
RUN apk add --no-cache ca-certificates
COPY --from=build /target/Everything-Souvneirs-0.0.1-SNAPSHOT.jar Everything-Souvneirs.jar
EXPOSE 9191
ENV JAVA_TOOL_OPTIONS="-Djdk.tls.client.protocols=TLSv1.2"
ENTRYPOINT ["java","-Djdk.tls.client.protocols=TLSv1.2","-jar","Everything-Souvneirs.jar"]