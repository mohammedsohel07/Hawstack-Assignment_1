package com.example.assignment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment.model.LessonCompletion;
import java.util.List;
import java.util.Optional;
public interface LessonCompletionRepository extends JpaRepository<LessonCompletion,Long>{
    Optional<LessonCompletion> findByUserIdAndLessonId(Long userId, Long lessonId);
    long countByUserIdAndLessonIdIn(Long userId, List<Long> lessonIds);
    List<LessonCompletion> findByUserIdAndLessonIdIn(Long userId, List<Long> lessonIds);
}
