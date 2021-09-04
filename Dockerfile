FROM openjdk:11.0.12-jdk-bullseye
ADD target/weather-0.0.1-SNAPSHOT.jar .
ADD bazaDanych.mv.db  bazaDanych.mv.db
EXPOSE 8000
CMD java -jar weather-0.0.1-SNAPSHOT.jar