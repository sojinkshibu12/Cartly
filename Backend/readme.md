# 🛒 Cartly Backend

A modern e-commerce backend built using a microservices architecture with Spring Boot. Cartly is designed to be scalable, secure, and cloud-ready, leveraging Spring Cloud, Spring Security, Apache Kafka, PostgreSQL, and MinIO object storage.

---

## 🚀 Features

### Authentication & Authorization

* User Registration
* User Login
* Session-Based Authentication
* Spring Security Integration
* Role-Based Access Control (Admin/User)
* Protected Endpoints

### Product Management

* Create Products
* Fetch Product Catalog
* Product Image Upload
* Product Inventory Management
* Product Categorization

### Microservices Architecture

* API Gateway
* Service Discovery with Eureka
* Independent Service Deployment
* Scalable Service Communication

### Object Storage

* MinIO S3-Compatible Storage
* Product Image Upload & Retrieval

### Event-Driven Architecture

* Apache Kafka Integration
* Foundation for asynchronous service communication

---

## 🏗️ Implemented Services

| Service                | Description                                     |
| ---------------------- | ----------------------------------------------- |
| API Gateway            | Centralized entry point for all client requests |
| Authentication Service | User authentication and authorization           |
| Product Service        | Product catalog and inventory management        |
| Discovery Service      | Eureka-based service registration and discovery |

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Spring Cloud Gateway
* Spring Cloud Netflix Eureka
* Apache Kafka
* Maven

### Databases & Storage

* PostgreSQL
* MinIO (S3-Compatible Object Storage)

### Infrastructure

* Docker
* Docker Compose

---

## 📁 Project Structure

```text
cartly-backend/
│
├── api-gateway/
├── auth-service/
├── product-service/
├── discovery-service/
│
├── docker-compose.yml
└── README.md
```

---

## 🔐 Security

Cartly uses Spring Security to secure APIs and manage authentication.

### Authentication Flow

1. User registers or logs in.
2. Credentials are validated by the Authentication Service.
3. Security context is established.
4. Protected APIs are accessed through the API Gateway.
5. Role-based authorization controls access to secured resources.

---

## 📦 Storage

### PostgreSQL

Used for:

* User Data
* Product Data
* Authentication Information
* Future Order Data

### MinIO

Used for:

* Product Images
* Media Assets
* Future Document Storage

---

# 📚 API Documentation

## Authentication Service

Base URL

```text
/api/users
```

### Get CSRF Token

```http
GET /api/users/csrf
```

Response

```json
{
  "headerName": "X-CSRF-TOKEN",
  "parameterName": "_csrf",
  "token": "generated-token"
}
```

---

### Register User

```http
POST /api/users/register
```

Request

```json
{
  "userName": "john",
  "email": "john@example.com",
  "password": "password123"
}
```

Response

```json
{
  "success": true,
  "message": "Registration successful",
  "user": {}
}
```

---

### Login User

```http
POST /api/users/login
```

Request

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

Response

```json
{
  "success": true,
  "message": "Login successful",
  "user": {}
}
```

---

### Check Authentication

```http
GET /api/users/check-auth
```

Response

```json
{
  "success": true,
  "message": "Authenticated",
  "user": {}
}
```

---

### Admin Dashboard

```http
GET /api/users/admin/dashboard
```

Response

```json
{
  "message": "Admin endpoint reached",
  "principal": "admin@example.com"
}
```

---

## Product Service

Base URL

```text
/api/products
```

### Create Product

```http
POST /api/products
```

Request

```json
{
  "title": "Nike Shoes",
  "category": "men",
  "brand": "nike",
  "price": 5000,
  "salePrice": 4500,
  "totalStock": 100,
  "description": "Running shoes",
  "image": "image-url"
}
```

Response

```json
{
  "title": "Nike Shoes",
  "category": "men",
  "brand": "nike",
  "price": 5000,
  "salePrice": 4500,
  "totalStock": 100,
  "image": "image-url"
}
```

---

### Upload Product Image

```http
POST /api/products/upload
```

Content-Type

```text
multipart/form-data
```

Form Data

```text
image=<file>
```

Response

```json
{
  "success": true,
  "uri": "http://localhost:9000/cartlyminio/image.png"
}
```

---

### Fetch All Products

```http
GET /api/products
```

Response

```json
[
  {
    "id": "uuid",
    "title": "Nike Shoes",
    "category": "men",
    "brand": "nike",
    "price": 5000,
    "salePrice": 4500,
    "totalStock": 100,
    "description": "Running shoes",
    "image": "image-url"
  }
]
```

---

## ▶️ Running the Project

### Prerequisites

* Java 21+
* Maven
* Docker
* Docker Compose
* PostgreSQL
* MinIO

### Clone Repository

```bash
git clone <repository-url>
cd cartly-backend
```

### Start Infrastructure

```bash
docker compose up -d
```

### Start Services

Start the services in the following order:

1. Discovery Service
2. Authentication Service
3. Product Service
4. API Gateway

---

## 🌐 Service URLs

| Service          | URL                   |
| ---------------- | --------------------- |
| Eureka Dashboard | http://localhost:8761 |
| API Gateway      | http://localhost:8081 |
| MinIO Console    | http://localhost:9001 |

---

## 🔮 Planned Services

* Cart Service
* Order Service
* Inventory Service
* Payment Service
* Review Service
* Notification Service

---

## 🚀 Future Enhancements

* Kafka-Based Event Streaming
* Distributed Tracing
* Centralized Logging
* Circuit Breakers
* Rate Limiting
* Refresh Token Support
* OAuth2 Integration
* Kubernetes Deployment
* CI/CD Pipelines
* Prometheus & Grafana Monitoring

---

## 🎯 Learning Objectives

This project demonstrates:

* Microservices Architecture
* Spring Cloud Ecosystem
* Service Discovery Patterns
* API Gateway Patterns
* Secure Authentication & Authorization
* Event-Driven Design with Kafka
* PostgreSQL Integration
* Object Storage with MinIO
* Scalable Backend Development

---

## 👨‍💻 Author

Developed as a hands-on microservices learning and portfolio project focused on building a production-grade e-commerce platform using Spring Boot and modern cloud-native technologies.
