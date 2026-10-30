# TalentSync

**TalentSync** is a microservices-based recruitment platform developed using Java 21 and Spring Boot. It connects recruiters and candidates through job management and job application features, with secure authentication and service-to-service communication.

## 1. Project Overview

TalentSync is designed to simplify recruitment through independently deployable microservices.

### Main Objectives
- Allow recruiters to create and manage job postings.
- Allow candidates to browse available jobs and submit applications.
- Secure APIs using JWT authentication.
- Enable communication between microservices.
- Improve scalability through service discovery and load balancing.

## 2. System Architecture

The project consists of the following components:

- **Eureka Server:** Service registration and discovery.
- **API Gateway:** Central entry point for client requests and security enforcement.
- **Recruitment Service:** User registration, login, and JWT generation.
- **Job Service:** Job creation, retrieval, updating, and deletion.
- **Application Service:** Candidate job application management.
- **MySQL:** Persistent storage for service data.

### Architecture Flow

Client → API Gateway → Microservices → MySQL

Eureka Server handles service discovery, while WebClient enables communication between the Application Service and Job Service.

## 3. Microservices and Ports

| Service | Port |
|---|---:|
| Eureka Server | 8761 |
| Recruitment Service | 8001 |
| Job Service – Instance 1 | 8002 |
| Job Service – Instance 2 | 8004 |
| API Gateway | 8003 |
| Application Service | 8080 |

## 4. Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot | Backend development |
| Spring Cloud | Microservices infrastructure |
| Spring Cloud Gateway | API routing and security |
| Netflix Eureka | Service discovery |
| Spring Security | Authentication and authorization |
| JWT | Token-based authentication |
| WebClient | Inter-service communication |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| MySQL | Database |
| Maven | Dependency and build management |
| Postman | API testing |
| JUnit / Mockito | Unit testing |
| STS | Development environment |

## 5. Main Features

- User registration and login.
- Password encryption using BCrypt.
- JWT generation and validation.
- Role-based access control for recruiters and candidates.
- Job creation, retrieval, updating, and deletion.
- Candidate job applications.
- Job ID validation before application submission.
- Service discovery using Eureka.
- Centralized API routing through API Gateway.
- Load balancing across multiple Job Service instances.
- Inter-service communication using WebClient.
- Persistent data storage using MySQL.

## 6. API Endpoints

All endpoints below are accessed through the API Gateway at `http://localhost:8003`.

### Recruitment Service

| Method | Endpoint | Description |
|---|---|---|
| POST | `/recruitment-service/auth/register` | Register a user |
| POST | `/recruitment-service/auth/login` | Authenticate a user |

### Job Service

| Method | Endpoint | Description |
|---|---|---|
| POST | `/job-service/jobs` | Create a job |
| GET | `/job-service/jobs` | Retrieve all jobs |
| GET | `/job-service/jobs/{id}` | Retrieve a job by ID |
| PUT | `/job-service/jobs/{id}` | Update a job |
| DELETE | `/job-service/jobs/{id}` | Delete a job |

### Application Service

| Method | Endpoint | Description |
|---|---|---|
| POST | `/application-service/applications` | Submit an application |
| GET | `/application-service/applications` | Retrieve all applications |
| GET | `/application-service/applications/{id}` | Retrieve an application |
| PUT | `/application-service/applications/{id}` | Update an application |
| DELETE | `/application-service/applications/{id}` | Delete an application |

## 7. Authentication and Authorization

TalentSync uses Spring Security and JWT for securing protected APIs.

### Authentication Flow

1. A user registers with their details and role.
2. The password is encrypted using BCrypt.
3. The user logs in with valid credentials.
4. The Recruitment Service generates a JWT.
5. The client sends the JWT in the Authorization header.
6. The API Gateway validates the token before allowing access.

### JWT Claims

The generated JWT contains:

- `sub`: User email.
- `userId`: User ID.
- `role`: User role.

### Role-Based Access Control

| Operation | Recruiter | Candidate |
|---|---|---|
| View jobs | Allowed | Allowed |
| Create jobs | Allowed | Denied |
| Update jobs | Allowed | Denied |
| Delete jobs | Allowed | Denied |

Unauthorized requests are rejected by the API Gateway.

## 8. Database Configuration

The project uses MySQL with separate databases for the microservices:

- `talentsync_recruitment`
- `talentsync_job`
- `talentsync_application`

Spring Data JPA and Hibernate are used for database operations and entity management.

Database credentials should be configured locally and must not be committed to GitHub.

## 9. Inter-Service Communication

The Application Service communicates with the Job Service using Spring WebClient.

Before accepting an application, the Application Service checks whether the referenced job exists.

If the job ID is invalid, the application is rejected and is not saved.

The WebClient is configured with Spring Cloud LoadBalancer for service discovery-based communication.

## 10. Load Balancing

Two Job Service instances are configured:

- Instance 1: Port 8002
- Instance 2: Port 8004

Both instances register with Eureka under the same service name.

The API Gateway uses service discovery and load balancing to distribute requests between available instances.

## 11. Testing and Verification

The project was tested using Postman, MySQL Workbench, STS, and the Eureka Dashboard.

### Security Testing
- Access without JWT.
- Invalid JWT.
- Modified JWT.
- Expired JWT.
- JWT claim inspection.
- Recruiter and candidate authorization.

### CRUD Testing
- Job creation, retrieval, updating, and deletion.
- Application creation, retrieval, updating, and deletion.
- Invalid job ID handling.
- Verification of database records.

### Microservices Testing
- Eureka service registration.
- API Gateway routing.
- Inter-service communication.
- Multiple Job Service instances.
- Load-balancing verification.

### Unit Testing

**Reported result: 5/5 tests passed.**

## 12. Running the Project

### Prerequisites
- Java 21
- Maven
- MySQL
- Spring Tool Suite or another Java IDE
- Postman

### Startup Order

Start the services in the following order:

1. Eureka Server.
2. Recruitment Service.
3. Job Service – Instance 1 (8002).
4. Job Service – Instance 2 (8004).
5. Application Service.
6. API Gateway.

### Access Points

- Eureka Dashboard: `http://localhost:8761`
- API Gateway: `http://localhost:8003`

Verify that all service instances are registered and showing UP in the Eureka Dashboard.

## 13. Security Considerations

- Never commit database passwords or JWT secrets.
- Store sensitive configuration in environment variables.
- Use HTTPS in production.
- Keep JWT secrets secure and rotate exposed secrets.
- Apply authorization rules to all protected operations.

## 14. Future Enhancements

- Email notifications for application updates.
- Resume upload and document management.
- Advanced job search and filtering.
- Recruiter and candidate dashboards.
- Deployment using Docker and cloud infrastructure.
- Automated integration testing.

## 15. Author

**TalentSync Project**

Developed as a Java Spring Boot microservices project.

---

**TalentSync – Connecting Recruiters and Candidates Through Microservices.**
