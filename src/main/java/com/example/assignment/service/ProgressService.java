package com.example.assignment.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.assignment.repository.LessonRepository;
import com.example.assignment.repository.LessonCompletionRepository;
import com.example.assignment.repository.EnrollmentRepository;
import com.example.assignment.model.Lesson;
import com.example.assignment.model.LessonCompletion;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProgressService {
    private final LessonRepository lessonRepository;
    private final LessonCompletionRepository completionRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ProgressService(LessonRepository lr, LessonCompletionRepository cr, EnrollmentRepository er){
        this.lessonRepository = lr; this.completionRepository = cr; this.enrollmentRepository = er;
    }

    @Transactional
    public boolean completeLesson(Long userId, Long courseId, Long lessonId){
        // verify lesson belongs to course
        var lessonOpt = lessonRepository.findById(lessonId);
        if(lessonOpt.isEmpty()) return false;
        Lesson lesson = lessonOpt.get();
        if(!lesson.getCourseId().equals(courseId)) return false;

        // ensure user is enrolled? (optional) we'll allow completion only if enrolled
        var enroll = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if(enroll.isEmpty()) return false;

        // idempotent: check if completion exists
        var existing = completionRepository.findByUserIdAndLessonId(userId, lessonId);
        if(existing.isPresent()) return true;
        try{
            completionRepository.save(new LessonCompletion(userId, lessonId));
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public Map<String,Object> getProgress(Long userId, Long courseId){
        // check enrollment
        var enroll = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if(enroll.isEmpty()) return null;
        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);
        List<Long> lessonIds = lessons.stream().map(Lesson::getId).collect(Collectors.toList());
        long total = lessonIds.size();
        long completed = 0;
        List<Long> completedIds = new ArrayList<>();
        if(total>0){
            var comps = completionRepository.findByUserIdAndLessonIdIn(userId, lessonIds);
            completedIds = comps.stream().map(LessonCompletion::getLessonId).collect(Collectors.toList());
            completed = completedIds.size();
        }
        double percent = total==0 ? 0.0 : (completed * 100.0) / total;
        Map<String,Object> resp = new HashMap<>();
        resp.put("userId", userId);
        resp.put("courseId", courseId);
        resp.put("totalLessons", total);
        resp.put("completedLessons", completed);
        resp.put("percent", percent);
        resp.put("completedLessonIds", completedIds);
        return resp;
    }
}