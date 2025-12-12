# Assignment Spring Boot - Full Implementation

## Run
mvn spring-boot:run

H2 Console: http://localhost:8080/h2-console  
JDBC URL: jdbc:h2:mem:assignmentdb  
User: sa (no password)

## Seeded Data (from data.sql)
Users:  
- Alice (id = 1)  
- Bob (id = 2)

Course:  
- Java 101 (id = 10)

Lessons:  
- Lesson 100  
- Lesson 101

## Example Flow (after starting the application)

### Enroll a user into a course
curl -X POST -H "Content-Type: application/json" -d '{"userId":1}' http://localhost:8080/courses/10/enroll

### Complete a lesson
curl -X POST -H "Content-Type: application/json" -d '{"userId":1}' http://localhost:8080/courses/10/lessons/100/complete

### Get user progress
curl http://localhost:8080/users/1/courses/10/progress
