# Course Project - Order and User System

## Overview
This project is a microservices-based system for managing orders and users. It includes two main services:
- **Order Service**: Handles order creation, updates, and management.
- **User Service**: Manages user registration, authentication, and interaction with the Order Service.

## Technologies
- **Java 21** with Spring Boot
- **RabbitMQ** for messaging between services
- **PostgreSQL** as the database
- **Docker** for containerization
- **Maven** for build management

## Architecture
- Microservices architecture with `order_system` and `user_system`.
- Communication between services via RabbitMQ.
- Persistent storage using PostgreSQL.

## Getting Started

### Prerequisites
- **Java 21**
- **Maven**
- **Docker** and **Docker Compose**

### Build and Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd course-project
   ```
2. Build the Docker images:
   ```bash
   docker-compose build
   ```
3. Start the services:
   ```bash
   docker-compose up
   ```
### Access the services:
- Order Service: http://localhost:8081
- User Service: http://localhost:8082
- RabbitMQ Management UI: http://localhost:15672
- Default login: guest
- Default password: guest

## Verify the Setup
1. Ensure all services are running:
   ```bash
   docker ps
   ```
2. Check logs for each service:
   ```bash
   docker-compose logs -f order_system
   docker-compose logs -f user_system
   ```
3. Test APIs for order_system and user_system using tools like Postman or cURL.

## Configuration
Environment variables for services can be adjusted in the docker-compose.yml file:

### PostgreSQL
POSTGRES_USER <br>
POSTGRES_PASSWORD <br>
POSTGRES_DB
### RabbitMQ
RABBITMQ_USERNAME <br>
RABBITMQ_PASSWORD <br>

Spring application configurations are defined in application.yml for both services.

## Testing
To run tests for each service:

1. Navigate to the respective service directory:
```bash
cd order_system
```
2. Run:
```bash
mvn test
```
Repeat these steps for the user_system directory.

## Features
### Order Service
- Create, update, delete, and retrieve orders.
- Temporary storage for processed orders using an internal cache.
### User Service
- User registration and authentication (JWT-based).
- Secure password storage and validation.
- Integration with the Order Service via RabbitMQ for user-related operations.
  
