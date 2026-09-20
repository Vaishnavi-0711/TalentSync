# TalentSync

TalentSync is a microservices-based recruitment platform developed using
Java and Spring Boot.

## Architecture

TalentSync consists of:

- Eureka Server
- API Gateway
- Recruitment Service
- Job Service
- Application Service
- MySQL databases
- JWT authentication
- WebClient inter-service communication
- Load balancing

## Services

| Service | Port |
|---|---:|
| Eureka Server | 8761 |
| API Gateway | 8080 |
| Recruitment Service | 8001 |
| Job Service | 8002 |
| Job Service - Instance 2 | 8004 |
| Application Service | 8003 |

## Technologies

- Java 21
- Spring Boot
- Spring Cloud
- Spring Security
- JWT
- Eureka
- Spring Cloud Gateway
- WebClient
- JPA / Hibernate
- MySQL
- Maven
- Postman
- JUnit / Mockito

## Main Features

- User registration
- User login
- JWT authentication
- Job management
- Application management
- Job validation during application
- Service discovery using Eureka
- API Gateway
- Load balancing
- MySQL persistence

## Testing

The project was tested using:

- Postman
- JUnit
- MySQL Workbench
- Eureka Dashboard

JUnit result:

**5/5 tests passed**

## Running the Project

Start the services in this order:

1. Eureka Server
2. Recruitment Service
3. Job Service - 8002
4. Job Service - 8004
5. Application Service
6. API Gateway

Then access the services through their respective ports.

## Note

Database credentials and JWT secrets should be configured locally and should not
be committed to the repository.
