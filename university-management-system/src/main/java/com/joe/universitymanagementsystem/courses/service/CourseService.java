package com.joe.universitymanagementsystem.courses.service;

import com.joe.universitymanagementsystem.courses.dto.NewCourseRequest;
import com.joe.universitymanagementsystem.courses.dto.CourseResponse;
import com.joe.universitymanagementsystem.courses.dto.StudentCourse;
import com.joe.universitymanagementsystem.courses.dto.UpdateCourseRequest;
import com.joe.universitymanagementsystem.courses.exceptions.CourseIsAlreadyExist;
import com.joe.universitymanagementsystem.courses.exceptions.CourseNotFound;
import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.courses.projections.TeacherCourse;
import com.joe.universitymanagementsystem.courses.repository.CourseRepository;
import com.joe.universitymanagementsystem.joinentities.repository.StudentEnrollmentRepository;
import com.joe.universitymanagementsystem.users.exceptions.UserNotFound;
import com.joe.universitymanagementsystem.users.student.model.Student;
import com.joe.universitymanagementsystem.users.student.repository.StudentRepository;
import com.joe.universitymanagementsystem.users.teacher.model.Teacher;
import com.joe.universitymanagementsystem.users.teacher.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    public CourseResponse addCourse(NewCourseRequest course) throws CourseIsAlreadyExist {
        if(courseRepository.findById(course.getCourseId()).isPresent()) {
            throw new CourseIsAlreadyExist(course.getCourseId());
        }

        Course newCourse = Course
                .builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .creditHours(course.getCreditHours())
                .maxStudents(course.getMaxStudents())
                .courseSpecialization(course.getCourseSpecialization())
                .build();

        courseRepository.save(newCourse);

        return CourseResponse
                .builder()
                .courseId(newCourse.getCourseId())
                .courseName(newCourse.getCourseName())
                .creditHours(newCourse.getCreditHours())
                .maxStudents(newCourse.getMaxStudents())
                .courseSpecialization(newCourse.getCourseSpecialization())
                .build();
    }

    public CourseResponse updateCourse(UpdateCourseRequest course, String courseId) throws CourseNotFound {
        Course retrievedCourse = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));

        retrievedCourse.setCourseName(course.getCourseName());
        retrievedCourse.setCourseSpecialization(course.getCourseSpecialization());
        retrievedCourse.setMaxStudents(course.getMaxStudents());
        retrievedCourse.setCreditHours(course.getCreditHours());

        courseRepository.save(retrievedCourse);

        return CourseResponse
                .builder()
                .courseId(retrievedCourse.getCourseId())
                .courseName(retrievedCourse.getCourseName())
                .maxStudents(retrievedCourse.getMaxStudents())
                .courseSpecialization(retrievedCourse.getCourseSpecialization())
                .creditHours(retrievedCourse.getCreditHours())
                .build();
    }

    public List<StudentCourse> getStudentCourses(String email) throws UserNotFound {
        studentRepository.findById(email).orElseThrow(() -> new UserNotFound(email));
        return studentEnrollmentRepository.findByIdStudentEmail(email);
    }

    public List<StudentCourse> getStudentCoursesInSemester(String email, int semester) throws UserNotFound {
        studentRepository.findById(email).orElseThrow(() -> new UserNotFound(email));
        return studentEnrollmentRepository.findByIdStudentEmailAndIdSemesterSemesterNum(email, semester, StudentCourse.class); // todo
    }

    public List<TeacherCourse> getTeacherCourses(String email) throws UserNotFound {
        teacherRepository.findById(email).orElseThrow(() -> new UserNotFound(email));

        return courseRepository.findCourseByTeacherEmail(email, TeacherCourse.class);
    }

    public List<TeacherCourse> getTeacherCoursesInSemester(String email, int semester) throws UserNotFound {
        Teacher teacher = teacherRepository.findById(email).orElseThrow(() -> new UserNotFound(email));

        List<Course> teacherCourses = teacher.getCourses();

        return studentEnrollmentRepository.findDistinctByIdCourseInAndIdSemesterSemesterNum(teacherCourses, semester, TeacherCourse.class);
    }

    public Course getCourse(String courseId) throws CourseNotFound {
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
