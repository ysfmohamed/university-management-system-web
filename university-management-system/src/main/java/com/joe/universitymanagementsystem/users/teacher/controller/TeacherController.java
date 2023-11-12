package com.joe.universitymanagementsystem.users.teacher.controller;

import com.joe.universitymanagementsystem.courses.exceptions.CourseNotFound;
import com.joe.universitymanagementsystem.joinentities.dto.CourseRegistrationRequestDto;
import com.joe.universitymanagementsystem.joinentities.projections.StudentOfTeacherInCourse;
import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.exceptions.*;
import com.joe.universitymanagementsystem.users.teacher.dto.NewTeacherAccountRequest;
import com.joe.universitymanagementsystem.users.teacher.dto.NewTeacherAccountResponse;
import com.joe.universitymanagementsystem.users.teacher.projections.RetrievedTeacher;
import com.joe.universitymanagementsystem.users.teacher.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/signup")
    private ResponseEntity<NewTeacherAccountResponse> createTeacherAccount(@RequestBody NewTeacherAccountRequest teacherAccountReq) throws UserIsBeingRevised, EmailIsAlreadyUsed  {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacherAccount(teacherAccountReq));
    }

    @PutMapping("/register-course")
    private ResponseEntity<String> registerInCourse(@RequestBody CourseRegistrationRequestDto courseRegistrationReq)  throws TeacherCoursesReachedThreshold, NonAcceptedUser, UserNotFound, CourseNotFound{
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(teacherService.registerInCourse(courseRegistrationReq));
    }

    @DeleteMapping("{email}/withdraw-course/{courseId}")
    private ResponseEntity<String> withdrawFromCourse(@PathVariable String email, @PathVariable String courseId) throws UserNotFound, CourseNotFound, TeacherIsNotRegisteredInCourse {
        return ResponseEntity.ok().body(teacherService.withdrawFromCourse(email, courseId));
    }

    @GetMapping("/{email}")
    private ResponseEntity<RetrievedTeacher> getTeacher(@PathVariable String email) {
        return ResponseEntity.ok().body(teacherService.getTeacher(email));
    }

    @GetMapping("/{email}/my-students/semester/{semester}")
    private ResponseEntity<List<StudentOfTeacherInCourse>> getTeacherStudentsInSemester(@PathVariable String email, @PathVariable Integer semester) throws UserNotFound {
        return ResponseEntity.ok().body(teacherService.getTeacherStudentsInSemester(email, semester));
    }

    @GetMapping("/all")
    private ResponseEntity<List<RetrievedTeacher>> getTeachersByStatus(@RequestParam Status status) {
        return ResponseEntity.ok().body(teacherService.getTeachersByStatus(status));
    }
}
