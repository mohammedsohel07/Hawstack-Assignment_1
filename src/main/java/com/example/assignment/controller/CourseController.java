package com.example.assignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assignment.service.EnrollmentService;
import com.example.assignment.service.ProgressService;
import com.example.assignment.repository.LessonRepository;
import com.example.assignment.model.Lesson;
import com.example.assignment.repository.CourseRepository;
import com.example.assignment.model.Course;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final EnrollmentService enrollmentService;
    private final ProgressService progressService;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public CourseController(EnrollmentService es, ProgressService ps, LessonRepository lr, CourseRepository cr){
        this.enrollmentService = es; this.progressService = ps; this.lessonRepository = lr; this.courseRepository = cr;
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<?> enroll(@PathVariable Long courseId, @RequestBody Map<String, Long> body){
        Long userId = body.get("userId");
        if(userId==null) return ResponseEntity.badRequest().body(Map.of("error","userId required"));
        Optional<Course> c = courseRepository.findById(courseId);
        if(c.isEmpty()) return ResponseEntity.status(404).body(Map.of("error","course not found"));
        boolean already = enrollmentService.enrollUser(userId, courseId);
        if(already) return ResponseEntity.ok(Map.of("status","already enrolled"));
        return ResponseEntity.status(201).body(Map.of("status","enrolled"));
    }

    @PostMapping("/{courseId}/lessons/{lessonId}/complete")
    public ResponseEntity<?> complete(@PathVariable Long courseId, @PathVariable Long lessonId, @RequestBody Map<String, Long> body){
        Long userId = body.get("userId");
        if(userId==null) return ResponseEntity.badRequest().body(Map.of("error","userId required"));
        boolean ok = progressService.completeLesson(userId, courseId, lessonId);
        if(ok) return ResponseEntity.ok(Map.of("status","completed"));
        return ResponseEntity.status(400).body(Map.of("error","could not complete (missing enrollment / lesson mismatch)"));
    }
}