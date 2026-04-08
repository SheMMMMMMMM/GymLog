# GymLog – Workout Tracking Backend

GymLog is a backend web application built with **Java and Spring Boot** for managing users, authentication, and workout tracking.

This project was created as a personal backend development project to strengthen practical skills in **REST API design, Spring Boot architecture, authentication, database integration, and clean layered architecture**.

---

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Git
- Postman

---

## Features

### Authentication & User Management
- User registration
- User login
- Token-based authentication
- User profile update
- Centralized exception handling
- DTO-based request and response structure

### Workout Management *(In Progress)*
- Workout module is currently under active development
- Initial entity, repository, and service layers implemented
- REST endpoints are being expanded
- Planned functionality:
  - Create workout
  - Update workout
  - Delete workout
  - Get workouts by date
  - User workout history

---

## Roadmap
- [x] Authentication module
- [x] User management
- [ ] Workout CRUD
- [ ] Input validation
- [ ] Swagger / OpenAPI documentation
- [ ] Unit and integration tests

---

## Project Architecture

The project follows a **layered backend architecture**:

- **Controller** – REST API endpoints
- **Service** – business logic
- **Repository** – database access layer
- **DTO** – request / response models
- **Entity** – JPA entities
- **Exception** – custom exceptions and global handler
- **Filter / Token** – authentication flow

---

## Project Structure

```text
src/main/java/com/max/gymlog
├── config
├── controller
├── dto
├── entity
├── exception
├── filter
├── repository
├── service
