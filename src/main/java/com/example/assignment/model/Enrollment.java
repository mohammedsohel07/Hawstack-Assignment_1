package com.example.assignment.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","course_id"}))
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id") private Long userId;
    @Column(name="course_id") private Long courseId;
    private Instant enrolledAt = Instant.now();
    public Enrollment() {}
    public Enrollment(Long userId, Long courseId){this.userId=userId;this.courseId=courseId;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getUserId(){return userId;} public void setUserId(Long userId){this.userId=userId;}
    public Long getCourseId(){return courseId;} public void setCourseId(Long courseId){this.courseId=courseId;}
    public Instant getEnrolledAt(){return enrolledAt;} public void setEnrolledAt(Instant t){this.enrolledAt=t;}
}