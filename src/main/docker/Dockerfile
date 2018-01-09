FROM java:8
MAINTAINER Fabiano Góes

LABEL version="1.0"
LABEL description="This is a base image to java application"
LABEL maintainer="fabianogoes@gmail.com"
LABEL date-version="09/01/2018"

ARG JAR_FILE_NAME
VOLUME /tmp
ADD /maven/${JAR_FILE_NAME}.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]