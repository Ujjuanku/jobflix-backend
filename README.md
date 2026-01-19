\# JobFlix – Job Portal Backend



This repository contains the backend service for \*\*JobFlix\*\*, a job portal application built using \*\*Spring Boot\*\* and \*\*Oracle Database\*\*.



The backend exposes REST APIs for authentication, job management, and job applications, following a clean layered architecture.



---



\## 🛠️ Tech Stack



\- Java 17

\- Spring Boot

\- Spring Data JPA (Hibernate)

\- Spring Security

\- Oracle XE 21c

\- Maven



---



\## 📂 Project Structure



```text

src/main/java/com/ujjwal/job\_service

├── config

│   └── SecurityConfig.java

├── controller

│   ├── ApplicationController.java

│   ├── AuthController.java

│   └── JobController.java

├── dto

│   └── LoginRequest.java

├── entity

│   ├── Application.java

│   ├── JobPost.java

│   ├── JobSkill.java

│   └── UserAccount.java

├── repository

│   ├── ApplicationRepository.java

│   ├── JobPostRepository.java

│   ├── JobSkillRepository.java

│   └── UserAccountRepository.java

├── service

│   └── ApplicationService.java

└── JobServiceApplication.java



🔐 Security



&nbsp;   Spring Security configuration using SecurityConfig



&nbsp;   Authentication endpoint for login



&nbsp;   Separation of concerns between controller, service, and repository layers



🗄️ Database Configuration



This project uses Oracle XE 21c (PDB: XEPDB1).



Sensitive credentials are not committed.

Use environment variables for configuration.

Example (application-example.properties):



spring.application.name=job\_service



spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1

spring.datasource.username=jobportal

spring.datasource.password=\*\*\*\*\*\*\*\*

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver



spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect



▶️ Run Locally

1️⃣ Set environment variables



DB\_URL=jdbc:oracle:thin:@//localhost:1521/XEPDB1

DB\_USERNAME=jobportal

DB\_PASSWORD=your\_password



2️⃣ Run the application



mvn spring-boot:run



Backend runs on:



http://localhost:8080



🔗 Frontend Integration



This backend is designed to work with the JobFlix frontend built using React and TypeScript.



Frontend repository:

👉 https://github.com/Ujjuanku/jobflix-frontend

🚀 Features Implemented



&nbsp;   User authentication



&nbsp;   Job CRUD APIs



&nbsp;   Job application APIs



&nbsp;   Oracle DB integration using JPA



&nbsp;   Secure REST architecture



📌 Future Improvements



&nbsp;   JWT-based authentication



&nbsp;   Role-based authorization (Admin / Candidate)



&nbsp;   Pagination \& filtering



&nbsp;   Swagger/OpenAPI documentation



&nbsp;   Dockerization



👨‍💻 Author



UjjwalJha

Backend / Full Stack Developer



🔗 GitHub: https://github.com/Ujjuanku







---





