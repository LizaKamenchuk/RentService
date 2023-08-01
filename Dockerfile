FROM gradle:jdk11 AS gradleimage
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build -x test

FROM openjdk:11
COPY --from=gradleimage /home/gradle/src/build/libs/RentService-1.0-SNAPSHOT.jar app/RentService.jar
WORKDIR /app
ENTRYPOINT java -jar RentService.jar


