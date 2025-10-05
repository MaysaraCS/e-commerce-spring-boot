How to run this project (Windows/PowerShell)

This repository is a multi-module Spring Boot microservices workspace with the following modules:
- configdemo/configdemo
- product/product
- user/user
- order/order (skeleton in this repo)

There is also a docker-compose.yml for local infrastructure (Postgres + pgAdmin). Follow the steps below depending on what you want to run.

Prerequisites
- Java 17+ installed (java -version)
- Maven 3.8+ installed (mvn -v)
- Docker Desktop (optional, for running Postgres/pgAdmin)

Option A: Run infrastructure (Postgres) with Docker
1) From the project root (Microservices directory), start Postgres and pgAdmin:
   docker compose up -d

   This will expose:
   - Postgres on localhost:5432 with username: maysara, password: maysara
   - pgAdmin on http://localhost:5050 (default login: pgadmin4@pgadmin.org / admin)

2) In pgAdmin, create the required databases if they don’t exist yet:
   - product
   - userdb (only if you plan to switch the user service to Postgres; by default, it is configured for MongoDB and will not use Postgres)

Option B: Run services directly with Maven (without Docker)
You can run each service independently. Open a terminal at the project root (Microservices) and use -pl to target a module.

A1) Run the config demo service
- This service demonstrates Spring profiles and exposes a build info endpoint.

Command (from project root):
   mvn -pl configdemo/configdemo -am spring-boot:run

By default, the active profile is dev (see configdemo/configdemo/src/main/resources/application.yml).

Verify:
- Open: http://localhost:8080/build-info
  Expected response (values depend on profile):
  Build Id: 101, Build Version: 1.2.3, Build Name: dev production-build

A2) Run the product service (uses Postgres)
- Ensure Postgres is running (Option A) and that the product database exists.
- product service configuration: product/product/src/main/resources/application.yml
  - JDBC URL: jdbc:postgresql://localhost:5432/product
  - Username/Password: maysara / maysara
  - Port: 8081

Command (from project root):
   mvn -pl product/product -am spring-boot:run

Verify:
- Health: http://localhost:8081/actuator/health (if actuator is present) or check application logs for "Started".
- Your domain endpoints (if any) will be available on port 8081.

A3) Run the user service (default config expects MongoDB)
- By default, user service connects to MongoDB at mongodb://localhost:5051/userdb (see user/user/src/main/resources/application.yml).
- If you have MongoDB running on localhost:5051, you can start the service.
- Alternatively, you can switch to one of the commented configurations in application.yml (H2 or Postgres) if preferred.

Command (from project root):
   mvn -pl user/user -am spring-boot:run

Verify:
- Health/logs to confirm it started on port 8082.

Using IntelliJ IDEA or another IDE
- Import the Microservices directory as a Maven project.
- Locate each module’s main class:
  - configdemo: com.info.configdemo.ConfigDemoApplication
  - product: Main application class under product/product (package depends on your code)
  - user: Main application class under user/user
- Right-click the main class and Run.

Switching profiles for configdemo
- Current default active profile is dev. To run with prod profile:

From command line:
   mvn -pl configdemo/configdemo -Dspring-boot.run.profiles=prod -am spring-boot:run

Or set environment variable before running:
   set SPRING_PROFILES_ACTIVE=prod
   mvn -pl configdemo/configdemo -am spring-boot:run

Troubleshooting
- Port already in use: Stop other apps using 8080, 8081, 8082, or change server.port in the corresponding application.yml.
- Database connection errors for product service:
  - Ensure Docker Postgres is up (docker compose ps)
  - Ensure database "product" exists and credentials match
- MongoDB not available for user service:
  - Start MongoDB on localhost:5051, or adjust spring.data.mongodb.uri in user/user/src/main/resources/application.yml, or switch to the commented H2/Postgres config.

Common PowerShell commands from project root
- Build all modules: mvn -DskipTests package
- Run a specific module: mvn -pl <modulePath> -am spring-boot:run
  Examples:
    mvn -pl configdemo/configdemo -am spring-boot:run
    mvn -pl product/product -am spring-boot:run
    mvn -pl user/user -am spring-boot:run

Docker quick commands
- Start services: docker compose up -d
- Stop and remove: docker compose down
- See logs: docker compose logs -f

Notes
- Controller to try in configdemo: GET /build-info
- Profiles override build.* values using application-dev.yml or application-prod.yml.
