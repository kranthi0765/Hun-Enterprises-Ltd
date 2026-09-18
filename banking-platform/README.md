# Banking microservices

A starter banking platform built with Java 21 and Spring Boot 3.4. It contains independently deployable customer, account, and transaction services behind a Spring Cloud Gateway.

## Services

| Service | Port | Responsibility |
|---|---:|---|
| API Gateway | 8080 | Routes public API requests |
| Customer Service | 8081 | Customer registration and lookup |
| Account Service | 8082 | Account creation and balance management |
| Transaction Service | 8083 | Deposits, withdrawals, and transaction history |

The starter uses in-memory storage so it can run immediately. Replace the repositories with PostgreSQL and add authentication, messaging, idempotency, and audit controls before production use.

## Run

```bash
mvn clean package
mvn -pl services/customer-service spring-boot:run
mvn -pl services/account-service spring-boot:run
mvn -pl services/transaction-service spring-boot:run
mvn -pl services/api-gateway spring-boot:run
```

Or run all services with Docker Compose after installing Docker:

```bash
docker compose up --build
```

## Example API

```bash
curl -X POST http://localhost:8080/customers \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alex Morgan","email":"alex@example.com"}'

curl -X POST http://localhost:8080/accounts \
  -H 'Content-Type: application/json' \
  -d '{"customerId":"<customer-id>","currency":"USD"}'

curl -X POST http://localhost:8080/transactions/deposit \
  -H 'Content-Type: application/json' \
  -d '{"accountId":"<account-id>","amount":250.00}'
```

This is a development scaffold, not a compliant banking system. Never use floating point for money, expose internal services publicly, or deploy without authentication, authorization, encryption, validation, persistence, and security review.
