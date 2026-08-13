# Employee Management System

## Objective

The **Employee Management System** is a Spring Boot REST API application developed to manage employee information using **Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL**.

The application provides REST APIs to perform CRUD operations on employee records.

---

## Features

* Add a new employee
* Retrieve all employees
* Retrieve an employee by ID
* Update an existing employee
* Delete an employee
* Request and Response DTOs
* DTO validation
* Spring Data JPA repository
* Hibernate ORM
* PostgreSQL database integration
* RESTful API architecture
* Exception handling for employee lookup
* Maven project management

---

## Technologies Used

| Technology      | Purpose                  |
| --------------- | ------------------------ |
| Java            | Programming language     |
| Spring Boot     | Application framework    |
| Spring Web      | REST API development     |
| Spring Data JPA | Database access          |
| Hibernate       | ORM implementation       |
| PostgreSQL      | Relational database      |
| Maven           | Dependency management    |
| Lombok          | Reduces boilerplate code |
| IntelliJ IDEA   | Development environment  |
| Git & GitHub    | Version control          |

---

## Project Architecture

The project follows a layered architecture:

```text
Client / Postman
       |
       ↓
Controller Layer
       |
       ↓
Service Interface
       |
       ↓
Service Implementation
       |
       ↓
Repository Layer
       |
       ↓
Hibernate / JPA
       |
       ↓
PostgreSQL Database
```

### Main Layers

#### Controller

Handles HTTP requests and sends responses to the client.

```text
EmployeeController
```

#### Service

Contains the business logic.

```text
EmployeeService
EmployeeServiceImpl
```

#### Repository

Communicates with the database using Spring Data JPA.

```text
EmployeeRepository
```

#### Model

Contains the JPA entity representing the database table.

```text
Employee
```

#### DTO

Used to transfer data between the client and application.

```text
EmployeeRequestDTO
EmployeeResponseDTO
```

---

## Project Structure

```text
employee-management-system
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.bridglabz.employeemanagementsystem
│   │   │       │
│   │   │       ├── controller
│   │   │       │   └── EmployeeController.java
│   │   │       │
│   │   │       ├── dto
│   │   │       │   ├── EmployeeRequestDTO.java
│   │   │       │   └── EmployeeResponseDTO.java
│   │   │       │
│   │   │       ├── model
│   │   │       │   └── Employee.java
│   │   │       │
│   │   │       ├── repository
│   │   │       │   └── EmployeeRepository.java
│   │   │       │
│   │   │       └── service
│   │   │           ├── EmployeeService.java
│   │   │           └── EmployeeServiceImpl.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## Employee Entity

The `Employee` entity represents the employee table in PostgreSQL.

Typical employee fields include:

```text
id
name
email
department
salary
phone
```

The entity is mapped to the database using JPA annotations.

Example:

```java
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Employee fields
}
```

Hibernate uses this entity to map Java objects to database records.

---

## Request DTO

`EmployeeRequestDTO` is used when the client sends employee data to the application.

Example request:

```json
{
    "name": "Aryan",
    "email": "aryan@gmail.com",
    "department": "IT",
    "salary": 50000,
    "phone": "9876543210"
}
```

Validation annotations can be applied to the request DTO to validate incoming data before it reaches the service layer.

---

## Response DTO

`EmployeeResponseDTO` is used when sending employee information back to the client.

Using a response DTO prevents the application from directly exposing the entity to the client.

The flow is:

```text
Client
   ↓
EmployeeRequestDTO
   ↓
Service
   ↓
Employee Entity
   ↓
Database
   ↓
Employee Entity
   ↓
EmployeeResponseDTO
   ↓
Client
```

---

# REST API Endpoints

Base URL:

```text
http://localhost:8080/employee
```

---

## 1. Add Employee

### Endpoint

```text
POST /employee
```

### Request Body

```json
{
    "name": "Aryan",
    "email": "aryan@gmail.com",
    "department": "IT",
    "salary": 50000,
    "phone": "9876543210"
}
```

### Purpose

Creates a new employee record in the database.

### Flow

```text
POST Request
     ↓
EmployeeController
     ↓
EmployeeRequestDTO
     ↓
EmployeeService
     ↓
EmployeeServiceImpl
     ↓
Employee Entity
     ↓
EmployeeRepository
     ↓
PostgreSQL
```

---

## 2. Get All Employees

### Endpoint

```text
GET /employee
```

### Purpose

Retrieves all employees from the database.

### Flow

```text
GET Request
     ↓
Controller
     ↓
Service
     ↓
Repository.findAll()
     ↓
List<Employee>
     ↓
EmployeeResponseDTO
     ↓
Client
```

---

## 3. Get Employee By ID

### Endpoint

```text
GET /employee/{id}
```

### Example

```text
GET /employee/1
```

### Purpose

Retrieves a specific employee using their ID.

The repository method:

```java
employeeRepository.findById(id)
```

returns:

```java
Optional<Employee>
```

The service uses `orElseThrow()` to handle the case where the employee does not exist.

---

## 4. Update Employee

### Endpoint

```text
PUT /employee/{id}
```

### Example

```text
PUT /employee/1
```

### Request Body

```json
{
    "name": "Aryan Pujari",
    "email": "aryan@gmail.com",
    "department": "Development",
    "salary": 60000,
    "phone": "9876543210"
}
```

### Purpose

Updates an existing employee.

The existing employee is first retrieved using its ID:

```java
Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found"));
```

The fields are then updated and the entity is saved:

```java
employeeRepository.save(employee);
```

Because the entity already has an existing ID, JPA/Hibernate performs an `UPDATE` rather than creating another employee record.

---

## 5. Delete Employee

### Endpoint

```text
DELETE /employee/{id}
```

### Example

```text
DELETE /employee/1
```

### Purpose

Deletes an employee from the database using their ID.

---

# Service Interface

The service interface defines the operations supported by the employee service.

```java
public interface EmployeeService {

    EmployeeResponseDTO addEmployee(EmployeeRequestDTO requestDTO);

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO);

    String deleteEmployee(Long id);
}
```

The controller depends on the `EmployeeService` interface rather than directly depending on `EmployeeServiceImpl`.

```text
Controller
    ↓
EmployeeService
    ↓
EmployeeServiceImpl
```

This keeps the application loosely coupled.

---

# Repository

The repository uses Spring Data JPA:

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

Because `JpaRepository` already provides common database operations, we do not need to manually implement methods such as:

```text
save()
findById()
findAll()
delete()
deleteById()
existsById()
```

Spring Data JPA provides their implementation automatically.

---

# Optional and findById()

`findById()` returns:

```java
Optional<Employee>
```

instead of directly returning an `Employee`.

Example:

```java
Employee employee = employeeRepository.findById(id)
        .orElseThrow(() ->
                new RuntimeException("Employee not found"));
```

The `Optional` can contain:

```text
Employee object
```

or:

```text
Optional.empty()
```

Common methods include:

```text
orElse()
orElseGet()
orElseThrow()
```

For this project, `orElseThrow()` is useful because an employee that doesn't exist should result in an appropriate error instead of continuing with a null object.

---

# Database Configuration

PostgreSQL is used as the database.

The connection is configured in:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.application.name=employee-management-system

spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

server.port=8080
```

The actual `application.properties` file is excluded from Git because it contains database credentials.

---

# Database

Create the PostgreSQL database:

```sql
CREATE DATABASE employee_db;
```

Hibernate can automatically create/update the employee table based on the entity when:

```properties
spring.jpa.hibernate.ddl-auto=update
```

is used.

---

# Server Port

The application runs on:

```text
http://localhost:8080
```

because the configuration contains:

```properties
server.port=8080
```

The Spring Boot application uses port `8080`, while PostgreSQL normally uses port `5432`.

```text
Spring Boot → localhost:8080
PostgreSQL   → localhost:5432
```

---

# How to Run the Project

## Prerequisites

Make sure the following are installed:

* Java
* Maven
* PostgreSQL
* IntelliJ IDEA or another Java IDE
* Git

---

## Step 1: Clone the Repository

```bash
git clone git@github.com:Ariyan9907/employee-management-system.git
```

---

## Step 2: Open the Project

Open the project in IntelliJ IDEA.

Wait for Maven to download all required dependencies.

---

## Step 3: Create PostgreSQL Database

Create:

```sql
CREATE DATABASE employee_db;
```

---

## Step 4: Configure Database Credentials

Create/update:

```text
src/main/resources/application.properties
```

and configure your PostgreSQL username and password.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

---

## Step 5: Run the Application

Run the Spring Boot main application class.

The application will start on:

```text
http://localhost:8080
```

---

# Testing

The REST APIs can be tested using:

* Postman
* IntelliJ HTTP Client
* cURL
* Browser for GET requests

Example:

```text
GET http://localhost:8080/employee
```

Example:

```text
GET http://localhost:8080/employee/1
```

---

# Git Workflow

Initialize the repository:

```bash
git init
```

Add the remote repository:

```bash
git remote add origin git@github.com:Ariyan9907/employee-management-system.git
```

Check the remote:

```bash
git remote -v
```

Create a commit:

```bash
git add .
git commit -m "[Ariyan Pujari] created employee management system"
```

Rename the branch:

```bash
git branch -M main
```

Push to GitHub:

```bash
git push -u origin main
```

---

# Git Ignore

Sensitive configuration files should not be committed.

The project ignores:

```text
target/
.idea/
*.iml
.vscode/
src/main/resources/application.properties
```

This prevents the PostgreSQL password and other local configuration values from being pushed to GitHub.

---

# Key Concepts Learned

This project demonstrates the following Spring Boot concepts:

* Spring Boot application structure
* REST controllers
* `@RestController`
* `@RequestMapping`
* `@GetMapping`
* `@PostMapping`
* `@PutMapping`
* `@DeleteMapping`
* `@RequestBody`
* `@PathVariable`
* Dependency Injection
* Service layer
* Service interfaces
* Repository layer
* Spring Data JPA
* `JpaRepository`
* JPA entities
* Hibernate
* DTO pattern
* Request DTO
* Response DTO
* Bean validation
* `Optional`
* `orElseThrow()`
* CRUD operations
* PostgreSQL integration
* Maven
* Git and GitHub

---

# Outcome

The Employee Management System provides a complete backend foundation for managing employee records through REST APIs.

The application follows a clean layered architecture:

```text
Controller
    ↓
Service Interface
    ↓
Service Implementation
    ↓
Repository
    ↓
JPA / Hibernate
    ↓
PostgreSQL
```

The project can be extended in the future with:

* Global exception handling
* Custom `404 Not Found` responses
* Pagination and sorting
* Search employees
* Department-based filtering
* Authentication and authorization
* Spring Security
* Unit and integration testing
* API documentation using Swagger/OpenAPI
* Production database configuration
* Docker deployment
