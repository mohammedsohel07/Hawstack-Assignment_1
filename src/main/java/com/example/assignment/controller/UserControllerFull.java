package com.example.assignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assignment.repository.UserRepository;
import com.example.assignment.repository.CourseRepository;
import com.example.assignment.repository.LessonRepository;
import com.example.assignment.model.User;
import com.example.assignment.model.Course;
import com.example.assignment.model.Lesson;
import com.example.assignment.service.ProgressService;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserControllerFull {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final ProgressService progressService;

    public UserControllerFull(UserRepository u, CourseRepository c, LessonRepository l, ProgressService p){
        this.userRepository=u; this.courseRepository=c; this.lessonRepository=l; this.progressService=p;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User u){
        User saved = userRepository.save(u);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/seedCourse")
    public ResponseEntity<?> createCourse(@RequestBody Map<String,String> body){
        String title = body.get("title");
        if(title==null) return ResponseEntity.badRequest().body(Map.of("error","title required"));
        Course c = courseRepository.save(new Course(title));
        return ResponseEntity.status(201).body(c);
    }

    @PostMapping("/seedLesson")
    public ResponseEntity<?> createLesson(@RequestBody Map<String,Object> body){
        Number courseIdN = (Number) body.get("courseId");
        String title = (String) body.get("title");
        if(courseIdN==null || title==null) return ResponseEntity.badRequest().body(Map.of("error","courseId and title required"));
        Long courseId = courseIdN.longValue();
        // ensure course exists
        if(courseRepository.findById(courseId).isEmpty()) return ResponseEntity.status(404).body(Map.of("error","course not found"));
        Lesson l = lessonRepository.save(new Lesson(courseId, title));
        return ResponseEntity.status(201).body(l);
    }

    @GetMapping("/{userId}/courses/{courseId}/progress")
    public ResponseEntity<?> getProgress(@PathVariable Long userId, @PathVariable Long courseId){
        var p = progressService.getProgress(userId, courseId);
        if(p==null) return ResponseEntity.status(404).body(Map.of("error","not enrolled or not found"));
        return ResponseEntity.ok(p);
    }
}