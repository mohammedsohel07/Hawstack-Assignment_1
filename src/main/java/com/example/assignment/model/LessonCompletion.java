package com.example.assignment.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name = "lesson_completions", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","lesson_id"}))
public class LessonCompletion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id") private Long userId;
    @Column(name="lesson_id") private Long lessonId;
    private Instant completedAt = Instant.now();
    public LessonCompletion() {}
    public LessonCompletion(Long userId, Long lessonId){this.userId=userId;this.lessonId=lessonId;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getUserId(){return userId;} public void setUserId(Long u){this.userId=u;}
    public Long getLessonId(){return lessonId;} public void setLessonId(Long l){this.lessonId=l;}
    public Instant getCompletedAt(){return completedAt;} public void setCompletedAt(Instant t){this.completedAt=t;}
}