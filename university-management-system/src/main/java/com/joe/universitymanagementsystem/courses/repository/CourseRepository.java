package com.joe.universitymanagementsystem.courses.repository;

import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.courses.projections.TeacherCourse;
import com.joe.universitymanagementsystem.courses.projections.TeacherStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    public <T> List<T> findCourseByTeacherEmail(String email, Class<T> type);
    public long countByTeacherEmail(String email);
}
