FROM openjdk:8-jdk-alpine
LABEL maintainer="Ragavan"
EXPOSE 8081
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} stock.jar
ENTRYPOINT ["java","-jar","/stock.jar"]