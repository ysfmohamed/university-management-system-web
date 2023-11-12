package com.joe.universitymanagementsystem.users.student.controller;

import com.joe.universitymanagementsystem.courses.exceptions.CourseNotFound;
import com.joe.universitymanagementsystem.joinentities.dto.CourseRegistrationRequestDto;
import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.exceptions.*;
import com.joe.universitymanagementsystem.users.student.dto.NewFinancialAidRequest;
import com.joe.universitymanagementsystem.users.student.dto.NewFinancialAidResponse;
import com.joe.universitymanagementsystem.users.student.dto.NewStudentAccountRequest;
import com.joe.universitymanagementsystem.users.student.dto.NewStudentAccountResponse;
import com.joe.universitymanagementsystem.users.student.projections.RetrievedStudent;
import com.joe.universitymanagementsystem.users.student.projections.StudentGrade;
import com.joe.universitymanagementsystem.users.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/signup")
    private ResponseEntity<NewStudentAccountResponse> createStudentAccount(@RequestBody NewStudentAccountRequest studentAccountReq) throws UserIsBeingRevised, EmailIsAlreadyUsed {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudentAccount(studentAccountReq));
    }

    @PostMapping("/register-course")
    private ResponseEntity<String> registerInCourse(@RequestBody CourseRegistrationRequestDto courseRegistrationReq) throws UserNotFound, CourseNotFound, StudentInabilityToEnrollInCourse {
        studentService.registerInCourse(courseRegistrationReq);

        return ResponseEntity.ok().body("Student with \"" + courseRegistrationReq.getEmail() + "\" has been registered to the course \"" + courseRegistrationReq.getCourseId() + "\" successfully");
    }

    @GetMapping("/{email}")
    private ResponseEntity<RetrievedStudent> getStudent(@PathVariable String email) {
        return ResponseEntity.ok().body(studentService.getStudent(email));
    }

    @GetMapping("/{email}/my-grades/semester/{semester}")
    private ResponseEntity<List<StudentGrade>> getStudentGradesInSemester(@PathVariable String email, @PathVariable int semester) throws UserNotFound {
        return ResponseEntity.ok().body(studentService.getStudentGradesInSemester(email, semester));
    }

    @GetMapping("/all")
    private ResponseEntity<List<RetrievedStudent>> getStudentsByStatus(@RequestParam Status status) {
        return ResponseEntity.ok().body(studentService.getStudentsByStatus(status));
    }

    @PostMapping("/ask-financial-aid")
    private ResponseEntity<NewFinancialAidResponse> askForFinancialAid(@RequestBody NewFinancialAidRequest finAidReq) throws NonAcceptedStudentAskFinAid, PendingFinAid, UserNotFound {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.askForFinancialAid(finAidReq));
    }
}
