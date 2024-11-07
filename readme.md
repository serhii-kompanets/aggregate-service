# User Aggregator Service

This project is a Spring Boot application that aggregates user data from multiple databases and provides a single REST API endpoint (`/users`) for fetching users' data. The service supports PostgreSQL and MySQL as data sources and is documented using OpenAPI.

## Features

- Aggregates user data from multiple databases (PostgreSQL and MySQL).
- Provides a single REST endpoint (`GET /users`) to fetch user information.
- Uses OpenAPI for API documentation.
- Supports Docker Compose for easy setup and management of databases.

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose (for local database setup)

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd user-aggregator-service
```

### 2. Start databases using docker-compose

```bash
- if docker.version < 4.32.0
docker-compose up -d
- else if docker.version > 4.32.0
docker compose up -d
```

### 3. Init databases
in the resource folder execute the scripts for DB

### 4. Build project
```bash
mvn clean install
```

### 5. Execute method
```bash
GET http://localhost:8080/api/users
```

### 5. Documentation
OpenAPI URL
http://localhost:8080/api/v3/api-docs