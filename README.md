🧠 Overview

This is a RESTful API built with Spring Boot for managing tasks in a to-do application.
It supports CRUD operations (Create, Read, Update, Delete) for tasks, uses H2 as an in-memory database, and includes pagination for task listing.
The app leverages Spring Data JPA for persistence and integrates Swagger for API documentation.

🚀 Features

🔹 Create, read, update, and delete tasks

🔹 Pagination and sorting for task listing

🔹 H2 in-memory database for data persistence

🔹 Swagger API documentation for easy exploration

🔹 Error handling for invalid requests or non-existent tasks



🛠️ Technologies Used

☕ Spring Boot – Framework for building REST APIs

💾 Spring Data JPA – ORM for database operations

🧩 H2 Database – In-memory database for development/testing

📘 Swagger (OpenAPI) – API documentation and testing UI

🧰 Lombok – To reduce boilerplate code

🧪 JUnit – For unit testing


🔹Application starts at:
👉 http://localhost:9091

🗄️ Access the H2 Console (optional)

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (leave blank)

📚 Access Swagger UI  - URL: http://localhost:8080/swagger-ui/index.html


  🔹GET : http://localhost:9091/getTask?page=0&size=5&sortBy=taskId

  🔹POST : http://localhost:9091/postTask

  🔹PUT : http://localhost:9091/putTask

  🔹DELETE : http://localhost:9091/deleteTask/task_Id

🧱 Project Structure :

src/main/java/com/example/to_do/

├── controller/

│   └── TaskController.java   # REST controller for handling API requests

├── model/

│   └── Task.java             # Entity class representing a Task

├── repository/

│   └── TaskRepo.java         # JPA repository for database operations

├── service/

│   └── TaskService.java      # Service layer for business logic



🧪 Testing

🧱 Unit tests (planned) for controller methods using JUnit and Mockito

🧩 To run tests:

mvn test

(or run directly from your IDE)


