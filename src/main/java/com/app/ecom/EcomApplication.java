package com.app.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}

}

// docker commands
// ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=<docker hub username>/<your-image-name>"
//mvn clean package -DskipTests
//docker build -t ecom-app .
//docker run -p 8080:8080 ecom-app