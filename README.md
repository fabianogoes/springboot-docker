# Sample Application with Build generate Docker Image

**/src/main/dockerDockerfile**

```
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
```

**pom.xml build plugin**

```xml
	<build>
		<finalName>${project.artifactId}</finalName>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>

			<plugin>
				<groupId>io.fabric8</groupId>
				<artifactId>docker-maven-plugin</artifactId>
				<version>0.23.0</version>
				<configuration>
					<dockerDirectory>src/main/docker</dockerDirectory>
					<buildArgs>
						<JAR_FILE_NAME>${project.build.finalName}</JAR_FILE_NAME>
					</buildArgs>
					<verbose>true</verbose>
					<images>
						<image>
							<name>${docker-image-prefix}/${project.artifactId}</name>
							<build>
								<dockerFile>Dockerfile</dockerFile>
								<assembly>
									<descriptorRef>artifact</descriptorRef>
								</assembly>
							</build>
						</image>
					</images>
				</configuration>
			</plugin>

		</plugins>
	</build>
```

**run maven build**

```
mvn clean package docker:build
```

**image generated**

```
docker images

REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
logistic/springboot-docker   latest              b73c46b5bce3        About an hour ago   672MB
java                         8                   d23bdf5b1b1b        11 months ago       643MB
```

**run application by docker container**

```
docker run -p 8080:8080 -d logistic/springboot-docker
```

> view application running in: http://localhost:8080/

**verify container running**

```
docker ps

CONTAINER ID        IMAGE                        COMMAND                  CREATED             STATUS              PORTS                    NAMES
eed110c0a999        logistic/springboot-docker   "java -Djava.secur..."   4 seconds ago       Up 2 seconds        0.0.0.0:8080->8080/tcp   amazing_meitner
```

**stop dpcker container**

```
docker stop eed110c0a999
```

**remove docker container**

```
docker rm -f eed110c0a999
```


### References

* [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
* [RUNNING SPRING BOOT IN A DOCKER CONTAINER](https://springframework.guru/running-spring-boot-in-a-docker-container/#comment-2306)
* [Using Docker containers for your Spring boot applications](https://g00glen00b.be/docker-spring-boot/)
* [Dockerize Spring Boot Application](https://medium.com/sudharao/dockerize-spring-boot-application-deploy-spring-boot-application-to-docker-containerize-spring-e039a1aa743a)
* [Dockerize a Spring Boot application](https://thepracticaldeveloper.com/2017/12/11/dockerize-spring-boot/)
* [Dockerizing a Spring Boot Application by baeldung](http://www.baeldung.com/dockerizing-spring-boot-application)
* [fabric8io/docker-maven-plugin](https://dmp.fabric8.io/#example)
* [Docker Documentation](https://docs.docker.com/)





