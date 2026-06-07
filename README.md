# 🛒 Cartly

<div align="center">

### A Modern E-Commerce Platform Built with Microservices

Scalable • Secure • Cloud-Native • Event-Driven

Built using **Spring Boot**, **Spring Security**, **Apache Kafka**, **PostgreSQL**, **MinIO**, and **Spring Cloud**

</div>

---

## 📖 Overview

Cartly is a modern e-commerce platform designed using a microservices architecture. The project focuses on building a scalable and production-ready backend capable of handling authentication, product management, object storage, service discovery, and future event-driven workflows.

The platform follows industry-standard backend practices including API Gateway routing, service discovery, secure authentication, distributed architecture, and cloud-native storage solutions.

---

## ✨ Features

### 🔐 Authentication & Authorization

* User Registration
* User Login
* Session-Based Authentication
* Spring Security Integration
* Role-Based Access Control
* Protected APIs

### 🛍️ Product Management

* Create Products
* View Product Catalog
* Product Categorization
* Inventory Management
* Product Image Upload
* Product Image Retrieval

### ☁️ Cloud Storage

* MinIO S3-Compatible Object Storage
* Secure Image Uploads
* Persistent Asset Management

### 🏗️ Microservices Architecture

* API Gateway
* Eureka Service Discovery
* Independent Service Deployment
* Scalable Architecture

### 📡 Event-Driven Foundation

* Apache Kafka Integration
* Asynchronous Service Communication
* Event Streaming Ready

---

## 🏛️ Architecture

### Implemented Services

| Service                   | Description                                     |
| ------------------------- | ----------------------------------------------- |
| 🚪 API Gateway            | Single entry point for all client requests      |
| 🔐 Authentication Service | Handles user authentication and authorization   |
| 📦 Product Service        | Manages product catalog and inventory           |
| 🔍 Discovery Service      | Eureka-based service registration and discovery |

### Planned Services

| Service                 | Purpose                       |
| ----------------------- | ----------------------------- |
| 🛒 Cart Service         | Shopping cart management      |
| 📋 Order Service        | Order processing and tracking |
| 📦 Inventory Service    | Stock management              |
| 💳 Payment Service      | Payment processing            |
| ⭐ Review Service        | Product reviews and ratings   |
| 🔔 Notification Service | User notifications and alerts |

---

## 🛠️ Technology Stack

### Backend

<p>
<img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge" />
<img src="https://img.shields.io/badge/Spring_Boot-Framework-green?style=for-the-badge" />
<img src="https://img.shields.io/badge/Spring_Security-Security-success?style=for-the-badge" />
<img src="https://img.shields.io/badge/Spring_Cloud-Microservices-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/Apache_Kafka-Event_Streaming-black?style=for-the-badge" />
</p>

### Database & Storage

<p>
<img src="https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/MinIO-S3_Storage-red?style=for-the-badge" />
</p>

### DevOps

<p>
<img src="https://img.shields.io/badge/Docker-Containerization-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/Docker_Compose-Orchestration-2496ED?style=for-the-badge" />
<img src="https://img.shields.io/badge/Maven-Build_Tool-C71A36?style=for-the-badge" />
</p>

---

## 📁 Project Structure

```text
cartly-backend/
│
├── api-gateway/
│
├── auth-service/
│
├── product-service/
│
├── discovery-service/
│
├── docker-compose.yml
│
└── README.md
```

---

## 🔐 Authentication APIs

### Register User

```http
POST /api/users/register
```

### Login User

```http
POST /api/users/login
```

### Check Authentication

```http
GET /api/users/check-auth
```

### Admin Dashboard

```http
GET /api/users/admin/dashboard
```

---

## 📦 Product APIs

### Create Product

```http
POST /api/products
```

### Upload Product Image

```http
POST /api/products/upload
```

### Fetch All Products

```http
GET /api/products
```

---

## 🗄️ Storage

### PostgreSQL

Used for:

* User Data
* Product Data
* Authentication Data
* Future Order Data

### MinIO

Used for:

* Product Images
* Media Assets
* Object Storage
* Future Document Storage

---

## 🚀 Getting Started

### Prerequisites

* Java 21+
* Maven
* Docker
* Docker Compose
* PostgreSQL
* MinIO

### Clone the Repository

```bash
git clone https://github.com/your-username/cartly-backend.git
cd cartly-backend
```

### Start Infrastructure

```bash
docker compose up -d
```

### Start Services

Run services in the following order:

1. Discovery Service
2. Authentication Service
3. Product Service
4. API Gateway

---

## 🌐 Local Development URLs

| Service          | URL                   |
| ---------------- | --------------------- |
| Eureka Dashboard | http://localhost:8761 |
| API Gateway      | http://localhost:8081 |
| MinIO Console    | http://localhost:9001 |

---

## 📈 Roadmap

### Phase 1 ✅

* Authentication Service
* Product Service
* Service Discovery
* API Gateway
* PostgreSQL Integration
* MinIO Integration

### Phase 2 🚧

* Cart Service
* Inventory Service
* Kafka Events

### Phase 3 🔮

* Order Service
* Payment Service
* Notification Service
* Review Service

### Phase 4 🚀

* Kubernetes Deployment
* Prometheus Monitoring
* Grafana Dashboards
* Distributed Tracing
* CI/CD Pipelines

---

## 🎯 What This Project Demonstrates

* Microservices Architecture
* Spring Cloud Ecosystem
* Secure Authentication & Authorization
* API Gateway Patterns
* Service Discovery with Eureka
* Event-Driven Architecture with Kafka
* PostgreSQL Database Design
* Object Storage with MinIO
* Scalable Backend Development
* Production-Oriented System Design

---

## 👨‍💻 Author

**Sojin K Shibu**

Backend Developer | Java & Spring Boot Enthusiast

Focused on building scalable backend systems, microservices, distributed applications, and cloud-native solutions.

---

<div align="center">

### ⭐ If you found this project interesting, consider giving it a star!

**Cartly — Building the future of scalable e-commerce systems.**

</div>
