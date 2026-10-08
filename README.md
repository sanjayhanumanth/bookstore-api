# Bookstore API

Spring Boot 3.3 · Java 21 · SQLite · Swagger UI (springdoc)

## Run
Requires JDK 21 and Maven 3.9+.

    mvn spring-boot:run

## URLs
- Swagger UI:  http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Endpoints
| Method | Path              | Description                        |
|--------|-------------------|------------------------------------|
| GET    | /api/books        | List books (`?author=` to filter)  |
| GET    | /api/books/{id}   | Get one book                       |
| POST   | /api/books        | Create a book                      |
| PUT    | /api/books/{id}   | Update a book                      |
| DELETE | /api/books/{id}   | Delete a book                      |

The SQLite database file `bookstore.db` is created in the working directory on first run
and seeded with three books.
