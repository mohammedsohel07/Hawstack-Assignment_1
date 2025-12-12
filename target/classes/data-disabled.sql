-- Insert Users
INSERT INTO users (id, name, email)
VALUES (1, 'Alice', 'alice@example.com');

INSERT INTO users (id, name, email)
VALUES (2, 'Bob', 'bob@example.com');

-- Insert Courses
INSERT INTO courses (id, title)
VALUES (10, 'Java 101');

-- Insert Lessons
INSERT INTO lessons (id, course_id, title)
VALUES (100, 10, 'Introduction');

INSERT INTO lessons (id, course_id, title)
VALUES (101, 10, 'OOP Basics');

-- Insert Enrollments
INSERT INTO enrollments (id, user_id, course_id, enrolled_at)
VALUES (1000, 1, 10, CURRENT_TIMESTAMP());

INSERT INTO enrollments (id, user_id, course_id, enrolled_at)
VALUES (1001, 2, 10, CURRENT_TIMESTAMP());
