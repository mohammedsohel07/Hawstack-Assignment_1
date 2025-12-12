package com.example.assignment.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.assignment.repository.EnrollmentRepository;
import com.example.assignment.model.Enrollment;
import org.springframework.dao.DataIntegrityViolationException;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    public EnrollmentService(EnrollmentRepository r){this.enrollmentRepository=r;}
    @Transactional
    public boolean enrollUser(Long userId, Long courseId){
        // check if already enrolled
        var exists = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if(exists.isPresent()) return true;
        try{
            enrollmentRepository.save(new Enrollment(userId, courseId));
            return false;
        } catch (DataIntegrityViolationException e){
            // likely unique constraint violation -> treat as already enrolled (idempotent)
            return true;
        }
    }
}