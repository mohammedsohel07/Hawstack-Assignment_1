# Assignment Spring Boot - Full Implementation

## Run
mvn spring-boot:run

H2 console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:assignmentdb
User: sa (no password)

## Example flow (after starting):

# Seeded users/courses/lessons available from data.sql: Alice(id=1), Bob(id=2), Course Java 101(id=10), lessons 100/101

Enroll:
curl -X POST -H "Content-Type: application/json" -d '{"userId":1}' http://localhost:8080/courses/10/enroll

Complete lesson:
curl -X POST -H "Content-Type: application/json" -d '{"userId":1}' http://localhost:8080/courses/10/lessons/100/complete

Get progress:
curl http://localhost:8080/users/1/courses/10/progress
