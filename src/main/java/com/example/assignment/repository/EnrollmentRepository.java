package com.example.assignment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment.model.Enrollment;
import java.util.Optional;
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
    Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);
}
