# Bank Application

A simple full-stack banking application with Spring Boot (Java) backend, H2 in-memory database, and Vue.js frontend. Supports:

- Creating bank accounts
- Listing accounts with balances
- Transferring funds between accounts
- Viewing transaction history
- Swagger API docs
- Automated unit and controller tests

---

## Table of Contents

1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Getting Started](#getting-started)
    - [Backend](#backend)
    - [Frontend](#frontend)
4. [API Documentation](#api-documentation)
5. [Database Console](#database-console)
6. [Automated Tests](#automated-tests)
7. [Data Seeding](#data-seeding)
8. [Github links](#github-links)
9. [Implemented User Stories](#implemented-user-stories)
10. [Author](#author)

---

## Features

- **Accounts**: create & list accounts with holder name and balance
- **Transfers**: move funds between two accounts, with validation (same-account, insufficient funds)
- **History**: view all transactions (timestamped)
- **Swagger UI**: interactive API documentation
- **H2 Console**: inspect in-memory database
- **Tests**: JUnit + Mockito service & controller tests

---

## Prerequisites

- Java 17 (set `JAVA_HOME`)
- Maven (or use the bundled `mvnw`)
- Node.js & npm

---

## Getting Started

Clone the repo and open two terminals—one for backend, one for frontend.

### Backend

```bash
cd bankapp-JAVA-Backend
./mvnw clean spring-boot:run 

-Runs on port 8080
-Auto-creates tables & seeds sample data via src/main/resources/sql-scripts/add-data.sql
```
### Frontend

```bash
cd bankapp-vue-frontend
npm install           # only first time
npm run serve

-Vue dev server on port 8081 (or as printed in console)
-Navigate to http://localhost:8081/
```

## API Documentation
Once the backend is running, view:

Swagger UI:
http://localhost:8080/swagger-ui.html

Raw OpenAPI JSON:
http://localhost:8080/api-docs

You can “Try it out” directly from Swagger.

## Database Console
Inspect the H2 in-memory database:

1-Open http://localhost:8080/h2-console

2-JDBC URL: jdbc:h2:mem:bankdb

3-User: sa (no password)

4-Run queries, for example:

SELECT * FROM accounts; 
SELECT * FROM transactions;

## Automated Tests

```bash
From the backend folder, run:
./mvnw clean test

You should see:
Tests run: XX, Failures: 0, Errors: 0, Skipped: 0

Tests included:
-AccountServiceTest
-TransactionServiceTest
-AccountRestControllerTest
-TransactionRestControllerTest
```

## Data Seeding
The file src/main/resources/sql-scripts/add-data.sql seeds 10 demo accounts and one sample transaction. IDs are auto-generated to avoid conflicts with UI-driven inserts.

## Implemented User Stories

| ID  | User Story                                                                                 | Status  |
|-----|--------------------------------------------------------------------------------------------|---------|
| US1 | As a user, I can create new bank accounts with holder name and initial balance.            | ✅ Done |
| US2 | As a user, I can see a list of all existing accounts, with balances.                       | ✅ Done |
| US3 | As a user, I can make a transfer between two accounts.                                     | ✅ Done |
| US4 | As a user, I can view a history of all transactions.                                       | ✅ Done |
| US5 | All endpoints are documented in Swagger UI.                                                | ✅ Done |
| US6 | I can run automated tests against account and transaction services.                        | ✅ Done |

# Github Links
https://github.com/KamiDiego/bankapp-JAVA-backend
https://github.com/KamiDiego/bankapp-vue-frontend

# Author
Diego Báez – Solo implementation


