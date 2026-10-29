FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace
COPY gradlew .
COPY gradle gradle
RUN chmod 755 gradlew
COPY settings.gradle build.gradle ./
COPY src src
RUN ./gradlew --no-daemon bootJar
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/app.jar app.jar
USER 10001:10001
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
