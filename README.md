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
