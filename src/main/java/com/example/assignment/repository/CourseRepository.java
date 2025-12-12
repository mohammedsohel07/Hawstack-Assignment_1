package com.example.assignment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment.model.Course;
public interface CourseRepository extends JpaRepository<Course,Long>{}
