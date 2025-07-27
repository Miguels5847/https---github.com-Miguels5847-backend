# Spring Boot Backend - Gestión de Estudiantes

Este proyecto es una API RESTful desarrollada con Spring Boot para la gestión de usuarios y estudiantes, con autenticación JWT y control de acceso basado en roles (ADMIN y ESTUDIANTE). Incluye pruebas automatizadas básicas y está lista para desplegarse o integrarse con un frontend.

---

## Características

- **Autenticación JWT**: Inicio de sesión seguro para usuarios.
- **Roles**: Soporte para roles ADMIN y ESTUDIANTE.
- **Gestión de estudiantes**: CRUD completo (crear, listar, editar, eliminar) solo accesible por ADMIN.
- **Validaciones**: Validación de datos en endpoints protegidos.
- **Pruebas automatizadas**: Incluye pruebas unitarias para login, validación y CRUD.
- **Configuración flexible**: Uso de JPA y base de datos relacional (por defecto PostgreSQL, pero puedes cambiarla).
- **Documentación clara**: Este README y comentarios en el código.

---

## Endpoints principales

- **POST /auth/login**: Login para cualquier usuario (ADMIN o ESTUDIANTE).
- **GET /estudiante**: Listar todos los estudiantes (solo ADMIN).
- **POST /estudiante**: Crear estudiante (solo ADMIN).
- **PUT /estudiante/{id}**: Editar estudiante (solo ADMIN).
- **DELETE /estudiante/{id}**: Eliminar estudiante (solo ADMIN).
- **GET /estudiante/{id}**: Obtener estudiante por ID (solo ADMIN).
- **GET /estudiante/usuario/{usuarioId}**: Obtener estudiante por ID de usuario (opcional, solo si lo usas).

---

## Tecnologías utilizadas

- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL (puedes usar H2, MySQL, etc.)
- Maven
- JUnit 5 y Spring Boot Test (para pruebas)
- Lombok (opcional)

---

## Estructura del proyecto

```
src/
  main/
    java/com/example/
      controller/      # Controladores REST
      model/           # Entidades JPA
      repository/      # Repositorios JPA
      security/        # Seguridad y JWT
      service/         # Lógica de negocio
    resources/
      application.properties  # Configuración de la base de datos y JWT
      schema.sql              # (Opcional) Script de inicialización de BD
  test/
    java/com/example/controller/  # Pruebas automatizadas
pom.xml
README.md
```

---

## Pruebas automatizadas

- **Backend**:
  - Prueba de login fallido (espera 401).
  - Prueba de validación (crear estudiante sin nombre, espera 400).
  - Prueba CRUD (crear y obtener estudiante, espera 200 y datos correctos).
- **Cómo ejecutar**:
  - Desde terminal: `mvn test`
  - Desde VS Code o tu IDE: botón "Run Test" en cada archivo de test.

---

## Configuración y ejecución

1. **Clona el repositorio**

   ```sh
   git clone https://github.com/Miguels5847/backend.git
   cd springboot-backend
   ```

2. **Configura la base de datos**  
   Edita `src/main/resources/application.properties` con tus datos de PostgreSQL (o la base que uses).

3. **Instala dependencias y ejecuta**

   ```sh
   mvn spring-boot:run
   ```

   La API estará disponible en `http://localhost:8080`.

4. **Ejecuta las pruebas**
   ```sh
   mvn test
   ```

---

## Notas adicionales

- El endpoint `/estudiante` y sus variantes están protegidos y solo accesibles por usuarios con rol ADMIN.
- El login devuelve un JWT que debe usarse en el header `Authorization: Bearer <token>` para acceder a los endpoints protegidos.
- El código incluye validaciones básicas y manejo de errores estándar.
- Puedes modificar la configuración de roles, base de datos y seguridad según tus necesidades.

---

## Licencia

MIT License.

---

**Desarrollado para prácticas universitarias de Ingeniería Web.**
