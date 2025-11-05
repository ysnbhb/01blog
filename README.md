# 01blog

A simple blog platform with a Spring Boot backend and an Angular frontend.  
Backend provides REST APIs (posts, comments, users, reactions, notifications, reports, file uploads). Frontend is an Angular single-page app that consumes the backend APIs.

## Key features
- Create/read/update/delete posts (with image/video uploads)
- User management, authentication (JWT)
- Comments, reactions, notifications
- Reports and admin controls
- Simple uploads storage under `uploads/`

## Technologies
- Backend
  - Java, Spring Boot
  - Maven (wrapper included)
  - Spring Security (JWT)
  - Spring Data JPA (repositories)
  - (Relational DB - configure via application.properties)
- Frontend
  - Angular (TypeScript, HTML, CSS)
  - Node / npm for dev tooling

## Prerequisites
- Java JDK 11+ (or the version supported by the project)
- Git (optional)
- Maven or use the included Maven wrapper (`./mvnw`)
- Node.js + npm
- (Optional) Angular CLI for development convenience

---

## Backend — run locally

1. Open a terminal and go to the backend folder:
   cd backend

2. Configure the database and other properties:
   - Edit `src/main/resources/application.properties` (or `target/classes/application.properties` for packaged app).
   - Set JDBC URL, username, password and any other settings (e.g. `server.port`, file upload path).

3. Run with the Maven wrapper:
   - Development run:
     ./mvnw spring-boot:run
   - Or build and run the jar:
     ./mvnw package
     java -jar target/*.jar

4. Tests:
   ./mvnw test

Default server port is 8080 unless changed in `application.properties`. Uploaded files are stored under the repository `uploads/` folder by default — ensure the application has write permission.

---

## Frontend — run locally

1. Open a terminal and go to the frontend folder:
   cd frontend

2. Install dependencies:
   npm install

3. Configure API base URL:
   - Edit `src/app/app.config.ts` (or wherever the API base is set) to point to the backend API (e.g. `http://localhost:8080/api`).

4. Run development server:
   - If Angular CLI is installed:
     ng serve --open
   - Or with npm scripts (if present):
     npm start
   - Default Angular dev server port is 4200.

5. Build for production:
   npm run build
   - Serve the `dist/` output from any static server or integrate with the backend if desired.

---

## Common notes and troubleshooting
- If JWT auth blocks requests, ensure the frontend sends the Authorization header: `Authorization: Bearer <token>`.
- If CORS errors appear, confirm backend CORS config (`middleware/CorsConfig.java`) allows the frontend origin.
- If database migrations are needed, check project code for schema setup (JPA entities and repositories).
- File upload endpoints expect multipart requests; verify frontend forms match backend field names.

---

## Project layout (high level)
- backend/
  - src/main/java/... (controllers, services, repositories, entities, middleware)
  - src/main/resources/application.properties
  - uploads/ (images, video)
- frontend/
  - src/app/ (components, routes, services)
  - src/model/
  - public/ and index.html

---