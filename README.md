# Spring Boot Backend API

This project is a Spring Boot application that implements a RESTful API with JWT authentication and role-based access control. It provides endpoints for user authentication and management of "objeto" resources.

## Features

- JWT Authentication
- Role-based access control (Admin and User roles)
- Protected routes based on user roles
- CRUD operations for "objeto" resources

## Endpoints

- **POST /auth/login**: Common login for both Admin and User roles.
- **GET /objeto**: Accessible only by Admin.
- **POST /objeto**: Accessible only by Admin.
- **PUT /objeto/:id**: Admin can edit any object; User can edit only their own object.
- **GET /objeto/:id**: Only the owner of the object can view their profile.

## Technologies Used

- Spring Boot
- Spring Security
- JPA (Java Persistence API)
- PostgreSQL (or other databases)
- Maven for dependency management

## Setup Instructions

1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd springboot-backend
   ```

2. **Configure the database**:
   Update the `src/main/resources/application.properties` file with your PostgreSQL database connection details.

3. **Run the application**:
   Use Maven to build and run the application:
   ```
   mvn spring-boot:run
   ```

4. **Access the API**:
   The API will be available at `http://localhost:8080`.

## Database Initialization

The `src/main/resources/schema.sql` file contains SQL statements to initialize the database schema.

## Dependencies

The `pom.xml` file includes necessary dependencies for Spring Boot, JPA, PostgreSQL, and JWT libraries.

## License

This project is licensed under the MIT License.