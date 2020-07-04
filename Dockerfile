FROM openjdk:8-jre

WORKDIR /app

LABEL MAINTAINER=jpmewenemesse@maltem.com

COPY ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
