# 🚲 Bike Rental Service — Backend (Spring Boot)

A fully functional backend service for a Bike Rental Application, built using Spring Boot, JWT Authentication, Role-based Authorization, MySQL, Swagger, and a BusinessResult + ApiEnvelope response architecture.

This project follows a clean, scalable domain-driven structure suitable for production and resume portfolios.

## 📌 Features

## 👤 Authentication & Authorization
* Register & login users (JWT-based)
* Login returns JWT token for all secure APIs
* Role-based authorization:
  * Admin → Add / Update / Delete bikes
  * ustomer → Book bikes, view available bikes
* Custom JWT filter with token validation (expired, invalid, empty token)
		
## 🏍️ Bike Management
* Public API to list available bikes
* Admin APIs:
  * Add bike
  * Update bike
  * Update bike status
  * Delete bike
  * Fetch by ID
			
## 🧾 Booking System
* User can book bikes for a time range
* Checks:
  * Time validity
  * Bike availability
  * Avoid booking conflicts (overlap checks)
* Price calculation based on rental hours
* Booking statuses:
  * REQUESTED → CONFIRMED → COMPLETED
* PaymentStatus placeholder for future integration

## 📦 Response Architecture
* A professional, consistent API response style:
* BusinessResult<T> → Service-level result wrapper
* ApiEnvelope<T> → REST controller global envelope
* Centralized exception handling (global)

## 🗄️ Database
* MySQL schema with Flyway scripts (optional)
* Proper relationships:
  * User ↔ Booking ↔ Bike

## 🐳 Docker Support (Optional)
* MySQL Docker setup
* Initialization SQL scripts

## 🛠️ Tech Stack
| Layer | Technology |
|---|---|
| Backend | Spring Boot 3, Spring Web, Spring Security 6 |
| Auth | JWT (Json Web Token), Custom JWT Filter |
| Database | MySQL 8 (JPA/Hibernate) |
| Tools | Lombok |
| Build | Maven |
| DevOps | Docker, Docker Compose |
| IDE (Dev) | Eclipse |

## ⚙️ Setup & Installation
1. Clone the repository
   ```
   	git clone https://github.com/<your-username>/Bike-Rental-Service.git
   	cd Bike-Rental-Service
   ```
3. Configure the database  
   * Update your application.properties:  
   * spring.datasource.password= //database password here//  
4. Run MySQL using Docker (optional)
   ```
   	docker-compose up -d
   ```
6. Start the server using Eclipse
   ```
   	Run bikeRentalApplication
   ```
