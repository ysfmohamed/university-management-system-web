package com.joe.universitymanagementsystem.joinentities.repository;

import com.joe.universitymanagementsystem.courses.dto.StudentCourse;
import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.courses.projections.TeacherCourse;
import com.joe.universitymanagementsystem.joinentities.compositekeys.StudentEnrollmentKey;
import com.joe.universitymanagementsystem.joinentities.model.StudentEnrollment;
import com.joe.universitymanagementsystem.joinentities.projections.StudentOfCourse;
import com.joe.universitymanagementsystem.joinentities.projections.StudentOfTeacher;
import com.joe.universitymanagementsystem.joinentities.projections.StudentOfTeacherInCourse;
import com.joe.universitymanagementsystem.users.student.model.Student;
import com.joe.universitymanagementsystem.users.student.projections.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, StudentEnrollmentKey> {
    public <T> List<T> findByIdCourseInAndIdSemesterSemesterNum(List<Course> courses, int semester, Class<T> type);
    public <T> List<T> findDistinctByIdCourseInAndIdSemesterSemesterNum(List<Course> courses, int semester, Class<T> type);
    public List<StudentOfCourse> findByIdCourseCourseIdAndIdSemesterSemesterNum(String courseId, int semester);
    public <T> List<T> findByIdStudentEmailAndIdSemesterSemesterNum(String email, int semester, Class<T> type);
    public List<StudentCourse> findByIdStudentEmail(String email);

    @Transactional
    public void deleteAllByIdStudentEmailInAndIdSemesterSemesterNumAndIdCourseCourseId(List<String> emails, int semester, String courseId);

}
