# 🧾 To-Do Application REST API

## 🧠 Overview
This is a **RESTful API** built with **Spring Boot** for managing tasks in a to-do application.  
It supports **CRUD operations (Create, Read, Update, Delete)** for tasks, uses **H2** as an in-memory database, and includes **pagination** for task listing.  
The app leverages **Spring Data JPA** for persistence and integrates **Swagger** for API documentation.

---

## 🚀 Features
- 🔹 Create, read, update, and delete tasks  
- 🔹 Pagination and sorting for task listing  
- 🔹 H2 in-memory database for data persistence  
- 🔹 Swagger API documentation for easy exploration  
- 🔹 Error handling for invalid requests or non-existent tasks  

---

## 🛠️ Technologies Used
- ☕ **Spring Boot** – Framework for building REST APIs  
- 💾 **Spring Data JPA** – ORM for database operations  
- 🧩 **H2 Database** – In-memory database for development/testing  
- 📘 **Swagger (OpenAPI)** – API documentation and testing UI  
- 🧰 **Lombok** – To reduce boilerplate code  
- 🧪 **JUnit** – For unit testing  

---

## 📋 Prerequisites
- ⚙️ Java **17** or higher  
- 🧱 Maven (for dependency management and build)  
- 🌐 Git (to clone the repository)  

---

## 🧭 Setup Instructions

```bash
git clone <repository-url>
cd <repository-directory>
mvn clean install
mvn spring-boot:run

---

## Application start at :

* http://localhost:9091/

- GET : http://localhost:9091/getTask?page=0&size=10&sortBy=taskId
- POST : http://localhost:9091/postTask
- PUT : http://localhost:9091/putTask
- DELETE : http://localhost:9091/deleteTask/Task_Id

## Project Structure :

src/main/java/com/example/to_do/
├── controller/
│   └── TaskController.java   # REST controller for handling API requests
├── model/
│   └── Task.java             # Entity class representing a Task
├── repository/
│   └── TaskRepo.java         # JPA repository for database operations
├── service/
│   └── TaskService.java      # Service layer for business logic

