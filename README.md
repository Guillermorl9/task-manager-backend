# Task Manager (Backend)

Task Manager is a cross-platform web application designed to manage tasks in a simple, intuitive, and free way. It allows any user to create, organize, and track their tasks from any device, avoiding unnecessary complications and improving daily productivity.

## Table of Contents
- [Description](#description)
- [Key Features](#key-features)
- [Technologies Used](#technologies-used)
- [Architecture and Structure](#architecture-and-structure)
- [Security and Authentication](#security-and-authentication)
- [Installation and Deployment](#installation-and-deployment)
- [Motivation](#motivation)

---

## Description

Nowadays, it’s easy to lose track of everything with so many daily tasks. Task Manager was created as a simple, practical, and free alternative to more complex or paid applications. The goal is to offer an accessible tool to organize tasks and enhance productivity while maintaining a clear and straightforward user experience.

Throughout development, I placed special emphasis on the backend, focusing on REST API design, security, and modular architecture best practices.

---

## Key Features

1. Cross-platform application for task management.
2. REST API built with Java and Spring Boot.
3. Responsive frontend based on Ionic Angular.
4. Data persistence in PostgreSQL, running in a Docker container for easy deployment.
5. Traditional and Google (OAuth2) authentication.
6. Security based on JWT and a stateless policy.
7. Modular backend organization (DTOs, services, repositories, and controllers).

---

## Technologies Used

- **Backend:** Java, Spring Boot, Spring Security (JWT, OAuth2), JPA.
- **Frontend:** Ionic Angular.
- **Database:** PostgreSQL (in a Docker container).

---

## Architecture and Structure

The backend is structured around the following main entities: **User**, **Category**, **Task List**, and **Task**. For each entity, the following components are implemented:

- **DTOs:** Data Transfer Objects for secure data exchange.
- **Services:** Business logic and validations.
- **Repositories:** Data access using JPA and PostgreSQL.
- **Controllers:** REST endpoints for communication with the frontend.

This modular separation facilitates scalability and code maintenance.

---

## Security and Authentication

- **Authentication:** Supports both traditional registration and Google login via OAuth2.
- **JWT Filter:** Intercepts each HTTP request, validates the token, and authenticates the user. Only authenticated users can access protected routes.
- **JWT Utility:** Generates, validates, and extracts information from JWT tokens using a secret key.
- **Security Configuration:** Implements Spring Security with a stateless policy, meaning each request requires a valid token, with no persistent sessions on the server.

---

## Installation and Deployment

### Prerequisites

- Java 17+
- Node.js and npm
- Docker (for the database)

### Basic Steps

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/task-manager.git
    cd task-manager
    ```

2. **Start the database with Docker:**
    ```bash
    docker start taskmanager-db
    ```

3. **Backend (Spring Boot):**
    ```bash
    cd backend
    ./mvnw spring-boot:run
    ```
---

## Motivation

In a world full of complex and paid applications, Task Manager aims to be a simple and free solution for those who want to organize their tasks without complications. This project has also served as an opportunity to solidify and deepen my knowledge, especially in backend development, learning about APIs, security, and architectural best practices.
