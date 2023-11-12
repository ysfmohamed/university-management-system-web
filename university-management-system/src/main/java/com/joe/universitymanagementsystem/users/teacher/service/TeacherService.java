package com.joe.universitymanagementsystem.users.teacher.service;

import com.joe.universitymanagementsystem.courses.exceptions.CourseNotFound;
import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.courses.repository.CourseRepository;
import com.joe.universitymanagementsystem.joinentities.dto.CourseRegistrationRequestDto;
import com.joe.universitymanagementsystem.joinentities.projections.StudentOfCourse;
import com.joe.universitymanagementsystem.joinentities.projections.StudentOfTeacher;
import com.joe.universitymanagementsystem.joinentities.projections.StudentOfTeacherInCourse;
import com.joe.universitymanagementsystem.joinentities.repository.StudentEnrollmentRepository;
import com.joe.universitymanagementsystem.semesters.enums.SemesterStatus;
import com.joe.universitymanagementsystem.semesters.model.Semester;
import com.joe.universitymanagementsystem.semesters.repository.SemesterRepository;
import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.exceptions.*;
import com.joe.universitymanagementsystem.users.student.repository.StudentRepository;
import com.joe.universitymanagementsystem.users.teacher.dto.NewTeacherAccountRequest;
import com.joe.universitymanagementsystem.users.teacher.dto.NewTeacherAccountResponse;
import com.joe.universitymanagementsystem.users.teacher.model.Teacher;
import com.joe.universitymanagementsystem.users.teacher.projections.RetrievedTeacher;
import com.joe.universitymanagementsystem.users.teacher.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final SemesterRepository semesterRepository;

    public NewTeacherAccountResponse createTeacherAccount(NewTeacherAccountRequest teacherAccReq) throws UserIsBeingRevised, EmailIsAlreadyUsed {
        checkTeacher(teacherAccReq.getEmail());

        Teacher teacher = Teacher
                .builder()
                .email(teacherAccReq.getEmail())
                .firstName(teacherAccReq.getFirstName())
                .lastName(teacherAccReq.getLastName())
                .password(teacherAccReq.getPassword())
                .gender(teacherAccReq.getGender())
                .specialization(teacherAccReq.getSpecialization())
                .status(Status.PENDING)
                .build();

        teacherRepository.save(teacher);

        return NewTeacherAccountResponse
                .builder()
                .email(teacher.getEmail())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .password(teacher.getPassword())
                .gender(teacher.getGender())
                .specialization(teacher.getSpecialization())
                .status(teacher.getStatus())
                .build();
    }

    private void checkTeacher(String email) throws UserIsBeingRevised, EmailIsAlreadyUsed {
        Optional<Teacher> teacher = teacherRepository.findById(email);

        if(teacher.isPresent() && teacher.get().getStatus().equals(Status.PENDING)) {
            throw new UserIsBeingRevised(email);
        }

        if(teacher.isPresent() && teacher.get().getStatus().equals(Status.ACCEPTED)) {
            throw new EmailIsAlreadyUsed(email);
        }
    }

    public List<StudentOfTeacherInCourse> getTeacherStudentsInSemester(String email, Integer semester) throws UserNotFound {
        teacherRepository.findById(email).orElseThrow(() -> new UserNotFound(email));

        List<Course> teacherCourses = courseRepository.findCourseByTeacherEmail(email, Course.class);
        return studentEnrollmentRepository.findByIdCourseInAndIdSemesterSemesterNum(teacherCourses, semester, StudentOfTeacherInCourse.class);
    }

    public String registerInCourse(CourseRegistrationRequestDto courseRegistrationReq) throws TeacherCoursesReachedThreshold, NonAcceptedUser, UserNotFound, CourseNotFound {
        String email = courseRegistrationReq.getEmail();
        String courseId = courseRegistrationReq.getCourseId();

        long numberOfCourses = courseRepository.countByTeacherEmail(email);

        if(numberOfCourses == 3) {
            throw new TeacherCoursesReachedThreshold(email, courseId);
        }

        Teacher teacher = teacherRepository.findById(email).orElseThrow(() -> new UserNotFound(email));

        if(teacher.getStatus().toString().equals(Status.PENDING.toString())) {
            throw new NonAcceptedUser("You cannot register in course, your registration request is still being reviewed.");
        }

        if(teacher.getStatus().toString().equals(Status.REJECTED.toString())) {
            throw new NonAcceptedUser("You cannot register in course, your registration request is rejected.");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));

        course.setTeacher(teacher);

        courseRepository.save(course);

        return "Teacher with email \"" + email + "\" has been assigned to course \"" + courseId + "\" successfully.";
    }

    public String withdrawFromCourse(String email, String courseId) throws UserNotFound, CourseNotFound, TeacherIsNotRegisteredInCourse {
        Teacher teacher = teacherRepository.findById(email).orElseThrow(() -> new UserNotFound(email));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));
        Semester semester = semesterRepository.findBySemesterStatus(SemesterStatus.ORDINARY);

        if(course.getTeacher() == null || !course.getTeacher().getEmail().equals(teacher.getEmail())) {
            throw new TeacherIsNotRegisteredInCourse(email, courseId);
        }

        List<StudentOfCourse> courseStudents = studentEnrollmentRepository.findByIdCourseCourseIdAndIdSemesterSemesterNum(courseId, semester.getSemesterNum());

        if(!courseStudents.isEmpty()) {
            List<String> studentEmails = courseStudents.stream().map(courseStudent -> courseStudent.getIdStudentEmail()).collect(Collectors.toList());
            refundStudents(studentEmails, course);

            studentEnrollmentRepository.deleteAllByIdStudentEmailInAndIdSemesterSemesterNumAndIdCourseCourseId(studentEmails, semester.getSemesterNum(), courseId);
        }

        course.setTeacher(null);
        courseRepository.save(course);

        return "Teacher with the given +\"" + email + "\" has been withdrawn from the course successfully and all his/her students have been refunded.";
    }

    private void refundStudents(List<String> studentEmails, Course course) {
        int refundedValue = course.getCreditHours() * 1650;
        studentRepository.updateStudentsBalance(studentEmails, refundedValue);
    }

    public RetrievedTeacher getTeacher(String email) {
        return teacherRepository.findByEmail(email);
    }

    public List<RetrievedTeacher> getTeachersByStatus(Status status) {
        return teacherRepository.findByStatus(status);
    }
}
