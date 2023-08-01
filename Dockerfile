FROM gradle:jdk11 AS gradleimage
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build

FROM openjdk:11
COPY --from=gradleimage /home/gradle/src/build/libs/*.jar app/Authefication.jar
WORKDIR /app
ENTRYPOINT java -jar Authefication.jar