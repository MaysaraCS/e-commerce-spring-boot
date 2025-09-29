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
// docker run -p 8080:8080 maysara11/ecom-app
// docker run -d --name db -e POSTGRES_PASSWORD=mysecretpassword postgres:18
// docker run -d --name pgadmin -e PGADMIN_DEFAULT_EMAIL=user@domain.com -e PGADMIN_DEFAULT_PASSWORD=admin dpage/pgadmin4
// docker exec -it pgadmin ping db
// docker network prune
// docker network create my-network
//docker rm -f db pgadmin
// docker run -d --name db --network my-network -e POSTGRES_PASSWORD=mysecretpassword postgres:18
// docker run -d --name pgadmin --network my-network -e PGADMIN_DEFAULT_EMAIL=user@domain.com -e PGADMIN_DEFAULT_PASSWORD=admin dpage/pgadmin4
// docker exec -it pgadmin ping db

//Create a Docker network:
//docker network create postgres
//Start the PostgreSQL service:
//docker run —d \
//        --name postgres container \
//        -e POSTGRES USER=maysara \
//        -e POSTGRES PASSWORD= maysara\
//        —e PGDATA=/data/postgres \
//        —v postgres: /data/postgres \
//        -p 5432:5432 \
//        --network postgres \
//        --restart unless—stopped \
//postgres : 18

//Start the pgAdmin service:
//docker run -d \
//        --name pgadmin container \
//        -e PGADMIN_DEFAULT_EMAIL= pgadmin4@pgadmin.org \
//        -e PGADMIN_DEFAULT_PASSWORD= admin \
//        -e PGADMIN_CONFIG_SERVER_MODE=Fa1se \
//        —v pgadmin: /var/lib/pgadmin \
//        -p 5050:80 \
//        --network postgres \
//        --restart unless—stopped \
//dpage/pgadmin4


