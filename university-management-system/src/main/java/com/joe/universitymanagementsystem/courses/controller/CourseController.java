package com.joe.universitymanagementsystem.courses.controller;

import com.joe.universitymanagementsystem.courses.dto.NewCourseRequest;
import com.joe.universitymanagementsystem.courses.dto.CourseResponse;
import com.joe.universitymanagementsystem.courses.dto.StudentCourse;
import com.joe.universitymanagementsystem.courses.dto.UpdateCourseRequest;
import com.joe.universitymanagementsystem.courses.exceptions.CourseIsAlreadyExist;
import com.joe.universitymanagementsystem.courses.exceptions.CourseNotFound;
import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.courses.projections.TeacherCourse;
import com.joe.universitymanagementsystem.courses.service.CourseService;
import com.joe.universitymanagementsystem.users.exceptions.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    private ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    private ResponseEntity<Course> getCourse(@PathVariable String courseId) throws CourseNotFound {
        return ResponseEntity.ok().body(courseService.getCourse(courseId));
    }

    @PostMapping("/add")
    private ResponseEntity<CourseResponse> addCourse(@RequestBody NewCourseRequest course) throws CourseIsAlreadyExist {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(course));
    }

    @PutMapping("/{courseId}/update")
    private ResponseEntity<CourseResponse> updateCourse(@RequestBody UpdateCourseRequest course, @PathVariable String courseId) throws CourseNotFound {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseService.updateCourse(course, courseId));
    }

    @GetMapping("/teacher/{email}")
    private ResponseEntity<List<TeacherCourse>> getTeacherCourses(@PathVariable String email) throws UserNotFound {
        return ResponseEntity.ok().body(courseService.getTeacherCourses(email));
    }

    @GetMapping("/student/{email}")
    private ResponseEntity<List<StudentCourse>> getStudentCourses(@PathVariable String email) throws UserNotFound {
        return ResponseEntity.ok().body(courseService.getStudentCourses(email));
    }

    @GetMapping("/teacher/{email}/semester/{semester}")
    private ResponseEntity<List<TeacherCourse>> getTeacherCoursesInSemester(@PathVariable String email, @PathVariable int semester) throws UserNotFound {
        return ResponseEntity.ok().body(courseService.getTeacherCoursesInSemester(email, semester));
    }

    @GetMapping("/student/{email}/semester/{semester}")
    private ResponseEntity<List<StudentCourse>> getStudentCoursesInSemester(@PathVariable String email, @PathVariable int semester) throws UserNotFound {
        return ResponseEntity.ok().body(courseService.getStudentCoursesInSemester(email, semester));
    }
}
