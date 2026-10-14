# Customer Rewards Service

A Spring Boot REST service that calculates loyalty points earned by each customer, grouped by calendar month, plus a period total. Purchase data is stored in an H2 relational database and loaded from SQL scripts. The reporting dates and calendar months are derived from transaction dates; no month names or reporting months are hard-coded.

## Reward rules

Points are calculated separately for every transaction:

- No points for the first $50.
- One point for each whole dollar above $50 through $100.
- Two points for each whole dollar above $100.

For example, `$120.00` earns `50 + 40 = 90` points. Fractional dollars do not earn partial points.

## Run

Requirements: Java 17 and Maven 3.9 or later.

```bash
mvn spring-boot:run
```

The application starts at `http://localhost:8080`. H2 is configured as an in-memory database and initializes from `src/main/resources/schema.sql` and `data.sql`.

## REST API

`GET /api/rewards?startDate=2026-01-01&endDate=2026-03-31`

Dates use ISO `yyyy-MM-dd` format and are inclusive. A reversed range returns HTTP 400. The response includes each customer with their month totals and the total for the requested period. For the included sample data, Alex Morgan earns 65 in January, 200 in February, and 40 in March, for 305 total points.

Example response:

```json
{
  "1": {
    "customerId": 1,
    "customerName": "Alex Morgan",
    "monthlyRewards": [
      { "month": "January 2026", "points": 65 },
      { "month": "February 2026", "points": 200 },
      { "month": "March 2026", "points": 40 }
    ],
    "totalPoints": 305
  }
}
```

## Project structure

```text
src/main/java/com/example/rewards/       Application, REST controller, service, entity, repository, DTOs
src/main/resources/application.properties H2 and SQL initialization configuration
src/main/resources/schema.sql             Purchase table schema and nonnegative amount constraint
src/main/resources/data.sql               Sample purchases for three customers across three months
src/test/java/com/example/rewards/        Calculation unit tests and REST integration tests
```

`RewardsService` computes transaction points and aggregates by `YearMonth`. `PurchaseRepository` retrieves transactions within the inclusive date window. The controller validates date ordering and Spring validates date syntax. Database SQL provides the sample data instead of application-level hard-coded transactions.

## Tests

Run the unit and integration suite with:

```bash
mvn test
```

Tests cover tier boundaries, fractional amounts, negative amount rejection, multiple customers and transactions, monthly/total aggregation, invalid and reversed dates, and an empty result interval. Build output (`target/`) and IDE output are excluded by `.gitignore`.
