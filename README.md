# Full-Stack Banking System
 
A full-stack banking application with a **Spring Boot** REST API backend and an **Angular** frontend. Supports secure JWT authentication with role-based access — admins manage customers and accounts, while registered users can view their profile, manage their own accounts, transfer money, and view transaction history.
 
## Features
 
### Admin Dashboard
- Create, view, and search customers (by ID, email, or phone)
- Create bank accounts linked to customers (Savings, Current, Salary, Fixed Deposit)
- View account summaries and customers with the highest balances
- Create login credentials for existing customers (link a `User` account to a `Customer` profile)
### User Dashboard
- View personal profile and KYC status
- View all linked bank accounts and total balance
- Transfer money between accounts
- View full transaction history (deposits, withdrawals, transfers)
### Authentication & Security
- JWT-based stateless authentication
- Role-based access control (`ROLE_ADMIN`, `ROLE_USER`)
- Public self-registration (creates a customer profile and linked login together)
- Auto-logout on token expiry (401/403 responses)
## Tech Stack
 
**Backend**
- Java 17, Spring Boot 3
- Spring Security + JWT (jjwt)
- Spring Data JPA / Hibernate
- MySQL
- Log4j2 (separate `app.log` and `exceptions.log`)
**Frontend**
- Angular 21 (standalone components)
- Angular SSR (client-rendered routes)
- RxJS
## Project Structure
 
```
Full-Stack-Banking-System/
├── Backend/          # Spring Boot REST API
│   └── src/main/java/com/example/
│       ├── controller/    # REST controllers
│       ├── service/       # Business logic orchestration
│       ├── bo/             # Core business rules & validation
│       ├── repository/    # Spring Data JPA repositories
│       ├── entity/         # JPA entities
│       ├── dto/             # Request/response DTOs
│       ├── security/       # JWT utilities & filters
│       ├── config/         # Security & app configuration
│       └── exception/      # Custom exceptions & global handler
└── Frontend/          # Angular application
    └── src/app/
        ├── components/    # Feature components (admin + user)
        ├── services/       # HTTP services
        ├── guards/         # Route guards (auth, role-based)
        └── interceptors/   # JWT auth interceptor
```
 
## Getting Started
 
### Prerequisites
- Java 17+
- Node.js 18+ and npm
- MySQL 8+
### Backend Setup
 
1. Create a MySQL database:
```sql
   CREATE DATABASE saving_account;
```
2. Configure `Backend/src/main/resources/application.properties`:
```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/saving_account
   spring.datasource.username=root
   spring.datasource.password=your_password
   jwt.secret=your_secret_key_at_least_32_characters_long
   jwt.expiration=86400000
```
3. Run the application:
```bash
   cd Backend
   ./mvnw spring-boot:run
```
   The API will start on `http://localhost:8080`. A default admin account (`admin` / `admin123`) is seeded on first run.
 
### Frontend Setup
 
1. Install dependencies:
```bash
   cd Frontend
   npm install
```
2. Start the development server:
```bash
   ng serve
```
3. Open `http://localhost:4200` in your browser.
## API Overview
 
| Endpoint | Method | Access | Description |
|---|---|---|---|
| `/auth/login` | POST | Public | Log in and receive a JWT |
| `/auth/register-customer` | POST | Public | Self-register a new customer + linked user login |
| `/auth/register` | POST | Public | Create a login for an existing customer (admin use) |
| `/customers` | GET / POST | Admin | List / create customers |
| `/customers/{id}` | GET | Admin | Get customer by ID |
| `/customers/email/{email}` | GET | Admin | Find customer by email |
| `/customers/phone/{phone}` | GET | Admin | Find customer by phone |
| `/customers/{id}/accounts` | POST | Admin | Add an account to a customer |
| `/customers/account-summary/{accountNumber}` | GET | Admin | Get account summary |
| `/customers/max-balance` | GET | Admin | Get customers with the highest balance |
| `/user/profile` | GET | User | Get the logged-in user's profile |
| `/user/accounts` | GET | User | Get the logged-in user's accounts |
| `/user/transactions` | GET | User | Get the logged-in user's transaction history |
| `/user/transfer` | POST | User | Transfer money to another account |
 
All protected endpoints require an `Authorization: Bearer <token>` header.
 
## License
 
This project is for educational purposes.
 
