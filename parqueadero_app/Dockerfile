FROM gradle:8.5.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /home/gradle/src/build/libs/*.jar parqueadero.jar
ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar parqueadero.jar" ]
EXPOSE 8080