package com.example.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDockerApplication.class, args);
	}

	@GetMapping("/")
	public String get(){
		return "Welcome to Spring Boot Application Dockerized";
	}

}
