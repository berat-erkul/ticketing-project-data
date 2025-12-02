# Ticketing Project - REST API

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Keycloak](https://img.shields.io/badge/Keycloak-OAuth2-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

Enterprise-grade Task and Project Management REST API built with Spring Boot, featuring OAuth2 authentication via Keycloak, comprehensive role-based access control, and production-ready architecture.

> ** Roadmap:** This monolithic application is designed to transition into a **microservices architecture** in future iterations, enabling enhanced scalability, independent deployment, and distributed system capabilities.

---

## Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [API Documentation](#-api-documentation)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [Security](#-security)
- [Performance Monitoring](#-performance-monitoring)
- [Database Schema](#-database-schema)
- [API Endpoints](#-api-endpoints)
- [Future: Microservices Migration](#-future-microservices-migration)
- [Contributing](#-contributing)
- [License](#-license)

---

##  Overview

The **Ticketing Project REST API** is a comprehensive task and project management system designed for enterprise environments. It provides robust user management, project tracking, and task assignment capabilities with a focus on security, performance, and maintainability.

### Use Cases

- **Project Management:** Create and manage multiple projects with detailed tracking
- **Task Assignment:** Assign tasks to employees with status tracking
- **Role-Based Operations:** Different capabilities for Admin, Manager, and Employee roles
- **Audit Trail:** Complete tracking of all data changes with timestamps and user information

---

##  Key Features

###  Security & Authentication
- **OAuth2 Integration** with Keycloak for enterprise-grade authentication
- **Role-Based Access Control (RBAC)** with three distinct roles: Admin, Manager, Employee
- **JWT Token Validation** for stateless authentication
- **Password Encryption** using BCrypt

###  Architecture & Design
- **RESTful API Design** following industry best practices
- **Clean Architecture** with clear separation of concerns
- **DTO Pattern** for decoupling domain models from API contracts
- **Global Exception Handling** for consistent error responses
- **Entity Auditing** with BaseEntity pattern

###  Business Features
- **User Management:** Full CRUD operations with Keycloak synchronization
- **Project Management:** Create, update, and track project lifecycle
- **Task Management:** Comprehensive task tracking with status management
- **Project Dashboard:** Real-time project status and completion tracking
- **Employee Dashboard:** View assigned tasks and update progress

###  Technical Excellence
- **Aspect-Oriented Programming (AOP)** for cross-cutting concerns
- **Custom Execution Time Monitoring** using custom annotations
- **Swagger/OpenAPI 3.0** interactive API documentation
- **Bean Validation** for input validation
- **Soft Delete Pattern** for data retention
- **ModelMapper** for object mapping

---

##  Technology Stack

### Core Framework
- **Spring Boot 3.1.5** - Application framework
- **Spring Web** - REST API development
- **Spring Data JPA** - Data persistence
- **Spring Security** - Security framework
- **Spring OAuth2 Resource Server** - OAuth2 JWT validation
- **Spring OAuth2 Client** - OAuth2 client support

### Database
- **PostgreSQL** - Primary database
- **Hibernate** - ORM framework

### Security & Authentication
- **Keycloak 22.0.4** - Identity and Access Management
- **JWT** - Token-based authentication

### Documentation & Utilities
- **SpringDoc OpenAPI 3 (2.2.0)** - API documentation
- **Lombok** - Boilerplate code reduction
- **ModelMapper 3.1.1** - Object mapping

### Build & Development
- **Maven** - Dependency management
- **Java 17** - Programming language

---

##  Architecture

### Layered Architecture

```
┌─────────────────────────────────────────────┐
│           Controllers Layer                 │
│  (ProjectController, TaskController, etc.)  │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│          Service Layer (Interfaces)         │
│  (ProjectService, TaskService, etc.)        │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│       Service Implementation Layer          │
│  (Business Logic & Keycloak Integration)    │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│          Repository Layer                   │
│  (Spring Data JPA Repositories)             │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│          Database (PostgreSQL)              │
└─────────────────────────────────────────────┘

         Cross-Cutting Concerns (AOP)
┌─────────────────────────────────────────────┐
│  • Performance Monitoring                   │
│  • Global Exception Handling                │
│  • Entity Auditing                          │
│  • Security (Keycloak Integration)          │
└─────────────────────────────────────────────┘
```

### Project Structure

```
com.cydeo
├── annotation           # Custom annotations
│   └── ExecutionTime
├── aspect              # AOP aspects
│   └── PerformanceAspect
├── config              # Configuration classes
│   ├── KeycloakProperties
│   ├── SecurityConfig
│   └── SwaggerConfig
├── controller          # REST controllers
│   ├── ProjectController
│   ├── TaskController
│   └── UserController
├── dto                 # Data Transfer Objects
│   ├── ProjectDTO
│   ├── TaskDTO
│   ├── UserDTO
│   ├── RoleDTO
│   └── ResponseWrapper
├── entity              # JPA entities
│   ├── BaseEntity
│   ├── BaseEntityListener
│   ├── Project
│   ├── Task
│   ├── User
│   └── Role
├── enums              # Enumerations
│   ├── Status
│   └── Gender
├── exception          # Exception handling
│   ├── GlobalExceptionHandler
│   ├── ExceptionWrapper
│   ├── UserNotFoundException
│   ├── UserAlreadyExistsException
│   └── ValidationException
├── mapper             # Object mapping
│   ├── MapperUtil
│   ├── RoleMapper
│   └── UserMapper
├── repository         # Data repositories
│   ├── ProjectRepository
│   ├── TaskRepository
│   ├── UserRepository
│   └── RoleRepository
└── service            # Business logic
    ├── KeycloakService
    ├── ProjectService
    ├── TaskService
    ├── UserService
    ├── RoleService
    └── impl/          # Service implementations
```

---

##  API Documentation

The API is fully documented using **Swagger/OpenAPI 3.0** with interactive documentation.

### Accessing Swagger UI

Once the application is running, navigate to:

```
http://localhost:8081/swagger-ui.html
```

### Features of API Documentation
- **Interactive Testing:** Try out API endpoints directly from the browser
- **OAuth2 Integration:** Authenticate using Keycloak within Swagger UI
- **Complete Schema Documentation:** View all request/response models
- **Role-Based Filtering:** See which endpoints are accessible to each role

---

##  Getting Started

### Prerequisites

1. **Java 17** or higher
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **PostgreSQL 12+**
   ```bash
   psql --version
   ```

4. **Keycloak Server** (Running on port 8080)
   - Download from: https://www.keycloak.org/downloads
   - Or use Docker:
   ```bash
   docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.4 start-dev
   ```

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/ticketing-project-rest.git
   cd ticketing-project-rest
   ```

2. **Create PostgreSQL Database**
   ```sql
   CREATE DATABASE ticketing-app;
   ```

3. **Configure Keycloak**
   - Access Keycloak Admin Console: `http://localhost:8080`
   - Create a new realm: `cydeo-dev`
   - Create a client: `ticketing-app`
   - Configure client settings:
     - Client Protocol: `openid-connect`
     - Access Type: `confidential`
     - Valid Redirect URIs: `http://localhost:8081/*`
   - Create client roles: `Admin`, `Manager`, `Employee`
   - Note the client secret for configuration

4. **Update Application Configuration**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   # Database Configuration
   spring.datasource.url=jdbc:postgresql://localhost:5432/ticketing-app
   spring.datasource.username=your_postgres_username
   spring.datasource.password=your_postgres_password
   
   # Keycloak Configuration
   keycloak.auth-server-url=http://localhost:8080
   keycloak.realm=cydeo-dev
   keycloak.resource=ticketing-app
   
   # Swagger OAuth2
   springdoc.swagger-ui.oauth.client-id=ticketing-app
   springdoc.swagger-ui.oauth.client-secret=your_client_secret
   ```

5. **Build the project**
   ```bash
   mvn clean install
   ```

6. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR file:
   ```bash
   java -jar target/ticketing-project-rest-0.0.1-SNAPSHOT.jar
   ```

7. **Verify Installation**
   - Application: `http://localhost:8081`
   - Swagger UI: `http://localhost:8081/swagger-ui.html`
   - Health Check: `http://localhost:8081/actuator/health` (if actuator is enabled)

---

##  Configuration

### Application Properties

| Property | Description | Default Value |
|----------|-------------|---------------|
| `server.port` | Application port | 8081 |
| `spring.datasource.url` | PostgreSQL connection URL | jdbc:postgresql://localhost:5432/ticketing-app |
| `spring.jpa.hibernate.ddl-auto` | Hibernate DDL mode | create |
| `spring.jpa.show-sql` | Show SQL queries | true |
| `spring.security.oauth2.resourceserver.jwt.issuer-uri` | JWT issuer URI | http://localhost:8080/realms/cydeo-dev |
| `keycloak.realm` | Keycloak realm name | cydeo-dev |
| `keycloak.auth-server-url` | Keycloak server URL | http://localhost:8080 |

### Environment-Specific Configuration

For production environments, consider:
- Using environment variables for sensitive data
- Setting `spring.jpa.hibernate.ddl-auto=validate`
- Enabling HTTPS
- Using external configuration management

---

##  Security

### Authentication Flow

1. **User Authentication**
   - Users authenticate via Keycloak
   - Keycloak issues JWT access token
   - Client includes JWT in Authorization header: `Bearer <token>`

2. **Token Validation**
   - Spring Security validates JWT signature
   - Extracts user roles from token claims
   - Enforces role-based access control

3. **Keycloak Integration**
   - User creation/update syncs with Keycloak
   - Password changes reflected in Keycloak
   - User deletion removes from both systems

### Role-Based Access Control

| Role | Permissions |
|------|-------------|
| **Admin** | Full access to user management endpoints |
| **Manager** | Full access to projects and tasks, can view/update projects and assign tasks |
| **Employee** | Can view assigned tasks, update task status, view own profile |

### Secured Endpoints

```java
// Admin Only
/api/v1/user/**

// Manager Only
/api/v1/project/**
/api/v1/task/**

// Employee Only
/api/v1/task/employee/**
```

---

##  Performance Monitoring

### Custom Execution Time Tracking

The application includes AOP-based performance monitoring:

```java
@ExecutionTime  // Custom annotation
@GetMapping
public ResponseEntity<ResponseWrapper> getUsers() {
    // Method execution time is automatically logged
}
```

**Log Output:**
```
Execution starts:
Time taken to execute: 245 ms - Method: UserController.getUsers()
```

### Monitored Operations
- User retrieval operations
- Complex database queries
- External service calls

---

##  Database Schema

### Entity Relationship Diagram

```
┌─────────────┐         ┌──────────────┐
│    Role     │         │    User      │
├─────────────┤         ├──────────────┤
│ id (PK)     │◄────────│ id (PK)      │
│ description │    1:N  │ firstName    │
└─────────────┘         │ lastName     │
                        │ userName     │
                        │ password     │
                        │ gender       │
                        │ phone        │
                        │ role_id (FK) │
                        └──────┬───────┘
                               │
                    ┌──────────┴────────────┐
                    │                       │
           ┌────────▼────────┐     ┌───────▼──────┐
           │    Project      │     │     Task     │
           ├─────────────────┤     ├──────────────┤
           │ id (PK)         │     │ id (PK)      │
           │ projectName     │◄────│ project_id   │
           │ projectCode     │ 1:N │ taskCode     │
           │ projectDetail   │     │ taskSubject  │
           │ projectStatus   │     │ taskDetail   │
           │ startDate       │     │ taskStatus   │
           │ endDate         │     │ assignedDate │
           │ manager_id (FK) │     │ employee_id  │
           └─────────────────┘     └──────────────┘
```

### Key Entities

1. **User**: System users with role-based permissions
2. **Role**: User roles (Admin, Manager, Employee)
3. **Project**: Projects managed by managers
4. **Task**: Tasks assigned to employees within projects

### Audit Fields (BaseEntity)

All entities inherit audit fields:
- `insertDateTime`: Record creation timestamp
- `lastUpdateDateTime`: Last modification timestamp
- `insertUserUsername`: Creator username
- `lastUpdateUserUsername`: Last modifier username
- `isDeleted`: Soft delete flag

---

##  API Endpoints

### User Management

| Method | Endpoint | Description | Role Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/user` | Get all users | Admin |
| GET | `/api/v1/user/{username}` | Get user by username | Admin |
| POST | `/api/v1/user` | Create new user | Admin |
| PUT | `/api/v1/user/{username}` | Update user | Admin |
| DELETE | `/api/v1/user/{username}` | Delete user | Admin |

### Project Management

| Method | Endpoint | Description | Role Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/project` | Get all projects | Manager |
| GET | `/api/v1/project/{code}` | Get project by code | Manager |
| POST | `/api/v1/project` | Create new project | Manager |
| PUT | `/api/v1/project/{projectCode}` | Update project | Manager |
| DELETE | `/api/v1/project/{projectCode}` | Delete project | Manager |
| GET | `/api/v1/project/manager/project-status` | Get manager's project details | Manager |
| PUT | `/api/v1/project/manager/complete/{projectCode}` | Complete project | Manager |

### Task Management

| Method | Endpoint | Description | Role Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/task` | Get all tasks | Manager |
| GET | `/api/v1/task/{taskCode}` | Get task by code | Manager |
| POST | `/api/v1/task` | Create new task | Manager |
| PUT | `/api/v1/task/{taskCode}` | Update task | Manager |
| DELETE | `/api/v1/task/{taskCode}` | Delete task | Manager |
| GET | `/api/v1/task/employee/pending-tasks` | Get employee pending tasks | Employee |
| GET | `/api/v1/task/employee/archive` | Get employee archived tasks | Employee |
| PUT | `/api/v1/task/employee/update/{taskCode}` | Employee update task | Employee |

### Response Format

All endpoints return a consistent response wrapper:

**Success Response:**
```json
{
  "message": "Users are successfully retrieved",
  "code": 200,
  "data": [ /* response data */ ]
}
```

**Error Response:**
```json
{
  "message": "User not found",
  "code": 404,
  "path": "/api/v1/user/unknown",
  "timestamp": "2024-01-05T10:30:00",
  "errorCount": 1
}
```

**Validation Error Response:**
```json
{
  "message": "Invalid Input(s)",
  "code": 400,
  "errorCount": 2,
  "validationExceptionList": [
    {
      "errorField": "userName",
      "rejectedValue": "",
      "reason": "Username cannot be empty"
    }
  ]
}
```

---

##  Future: Microservices Migration

This application is currently built as a **monolithic architecture** with a clear roadmap for transition to **microservices**. The existing modular structure and clean architecture principles facilitate this future migration.

### Planned Microservices Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                        API Gateway                                  │
│                    (Spring Cloud Gateway)                           │
└────────────┬────────────┬────────────┬──────────────┬───────────────┘
             │            │            │              │
   ┌─────────▼──────┐ ┌──▼──────┐ ┌──▼──────────┐ ┌─▼──────────────┐
   │   User Service │ │ Project │ │    Task     │ │  Auth Service  │
   │                │ │ Service │ │   Service   │ │   (Keycloak)   │
   └────────┬───────┘ └──┬──────┘ └──┬──────────┘ └────────────────┘
            │            │            │
   ┌────────▼────────────▼────────────▼────────┐
   │      Service Discovery & Config           │
   │   (Eureka / Consul + Config Server)       │
   └───────────────────────────────────────────┘
```

### Proposed Microservices

1. **User Service**
   - User management operations
   - User authentication integration
   - User profile management
   - Own PostgreSQL database

2. **Project Service**
   - Project CRUD operations
   - Project status management
   - Project-manager assignments
   - Own PostgreSQL database

3. **Task Service**
   - Task CRUD operations
   - Task assignment and tracking
   - Status updates
   - Own PostgreSQL database

4. **Authentication Service (Keycloak)**
   - Centralized authentication
   - Token issuance and validation
   - Role management

5. **API Gateway**
   - Single entry point
   - Request routing
   - Load balancing
   - Rate limiting

### Benefits of Microservices Migration

✅ **Independent Scalability:** Scale services based on demand  
✅ **Technology Flexibility:** Use different tech stacks per service  
✅ **Fault Isolation:** Failures contained to individual services  
✅ **Independent Deployment:** Deploy services without downtime  
✅ **Team Autonomy:** Different teams can own different services  
✅ **Database per Service:** Polyglot persistence support  

### Migration Strategy

**Phase 1: Preparation** (Current Phase)
- ✅ Modular monolith with clear boundaries
- ✅ Service layer abstraction
- ✅ External authentication (Keycloak)
- ✅ Stateless REST API

**Phase 2: Infrastructure Setup**
- Service discovery implementation
- API Gateway configuration
- Distributed tracing
- Centralized logging

**Phase 3: Service Extraction**
- Extract User Service
- Extract Project Service
- Extract Task Service
- Implement inter-service communication

**Phase 4: Advanced Features**
- Event-driven architecture (Kafka/RabbitMQ)
- Circuit breakers (Resilience4j)
- Distributed caching (Redis)
- Service mesh (Istio)

### Technical Considerations

- **Communication:** REST initially, migrate to gRPC for internal services
- **Data Consistency:** Implement Saga pattern for distributed transactions
- **Service Discovery:** Spring Cloud Netflix Eureka or Consul
- **Configuration Management:** Spring Cloud Config Server
- **Monitoring:** Prometheus + Grafana
- **Tracing:** Spring Cloud Sleuth + Zipkin
- **Resilience:** Resilience4j for circuit breakers and retry logic

---

## 📈 Project Status

**Current Version:** 0.0.1-SNAPSHOT  
**Status:** Active Development  
**Architecture:** Monolithic (Microservices-Ready)  

### Recent Updates

- ✅ OAuth2 integration with Keycloak
- ✅ Comprehensive API documentation with Swagger
- ✅ Role-based access control implementation
- ✅ Performance monitoring with AOP
- ✅ Global exception handling
- ✅ Entity auditing system

### Upcoming Features

- 🔄 Integration tests suite
- 🔄 Docker containerization
- 🔄 CI/CD pipeline setup
- 🔄 Performance optimization
- 🔄 Caching layer implementation
- 🔄 Microservices migration planning

---
