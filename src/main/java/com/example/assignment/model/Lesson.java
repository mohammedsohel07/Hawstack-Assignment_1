package com.example.assignment.model;
import jakarta.persistence.*;
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courseId;
    private String title;
    public Lesson() {}
    public Lesson(Long courseId, String title){this.courseId=courseId;this.title=title;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getCourseId(){return courseId;} public void setCourseId(Long c){this.courseId=c;}
    public String getTitle(){return title;} public void setTitle(String t){this.title=t;}
}