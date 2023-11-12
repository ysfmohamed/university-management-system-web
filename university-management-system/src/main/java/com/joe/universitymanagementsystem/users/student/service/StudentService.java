package com.joe.universitymanagementsystem.users.student.service;

import com.joe.universitymanagementsystem.courses.exceptions.CourseNotFound;
import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.courses.repository.CourseRepository;
import com.joe.universitymanagementsystem.joinentities.compositekeys.StudentEnrollmentKey;
import com.joe.universitymanagementsystem.joinentities.dto.CourseRegistrationRequestDto;
import com.joe.universitymanagementsystem.joinentities.model.StudentEnrollment;
import com.joe.universitymanagementsystem.joinentities.repository.StudentEnrollmentRepository;
import com.joe.universitymanagementsystem.requests.financialrequests.FinancialAid;
import com.joe.universitymanagementsystem.requests.financialrequests.FinancialAidRepository;
import com.joe.universitymanagementsystem.semesters.enums.SemesterStatus;
import com.joe.universitymanagementsystem.semesters.model.Semester;
import com.joe.universitymanagementsystem.semesters.repository.SemesterRepository;
import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.exceptions.*;
import com.joe.universitymanagementsystem.users.student.dto.NewFinancialAidRequest;
import com.joe.universitymanagementsystem.users.student.dto.NewFinancialAidResponse;
import com.joe.universitymanagementsystem.users.student.dto.NewStudentAccountRequest;
import com.joe.universitymanagementsystem.users.student.dto.NewStudentAccountResponse;
import com.joe.universitymanagementsystem.users.student.projections.RetrievedStudent;
import com.joe.universitymanagementsystem.users.student.model.Student;
import com.joe.universitymanagementsystem.users.student.projections.StudentGrade;
import com.joe.universitymanagementsystem.users.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final FinancialAidRepository financialAidRepository;
    private final SemesterRepository semesterRepository;

    public NewStudentAccountResponse createStudentAccount(NewStudentAccountRequest studentAccReq) throws UserIsBeingRevised, EmailIsAlreadyUsed {
        checkStudent(studentAccReq.getEmail());

        Student student = Student
                .builder()
                .email(studentAccReq.getEmail())
                .firstName(studentAccReq.getFirstName())
                .lastName(studentAccReq.getLastName())
                .password(studentAccReq.getPassword())
                .gender(studentAccReq.getGender())
                .balance(studentAccReq.getBalance())
                .status(Status.PENDING)
                .build();

        studentRepository.save(student);

        return NewStudentAccountResponse
                .builder()
                .email(student.getEmail())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .password(student.getPassword())
                .gender(student.getGender())
                .balance(student.getBalance())
                .status(student.getStatus())
                .build();
    }

    private void checkStudent(String email) throws UserIsBeingRevised, EmailIsAlreadyUsed {
        Student student = studentRepository.findById(email).orElse(null);

        if(student != null && student.getStatus().equals(Status.PENDING)) {
            throw new UserIsBeingRevised(email);
        }

        if(student != null && student.getStatus().equals(Status.ACCEPTED)) {
            throw new EmailIsAlreadyUsed(email);
        }
    }

    public void registerInCourse(CourseRegistrationRequestDto courseRegistrationReq) throws UserNotFound, CourseNotFound, StudentInabilityToEnrollInCourse {
        String email = courseRegistrationReq.getEmail();
        String courseId = courseRegistrationReq.getCourseId();

        Student student = studentRepository.findById(email).orElseThrow(() -> new UserNotFound(email));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFound(courseId));

        int coursePrice = course.getCreditHours() * 1650;

        if(coursePrice > student.getBalance()) {
            throw new StudentInabilityToEnrollInCourse(email, courseId, coursePrice, student.getBalance());
        }

        int studentBalanceAfterReg = student.getBalance() - coursePrice;
        student.setBalance(studentBalanceAfterReg);
        studentRepository.save(student);

        Semester unRunningSemester = semesterRepository.findBySemesterStatus(SemesterStatus.ORDINARY);

        StudentEnrollment studentPerformance =
                StudentEnrollment
                        .builder()
                        .id(new StudentEnrollmentKey(student, course, unRunningSemester))
                        .grade(0)
                        .build();

        studentEnrollmentRepository.save(studentPerformance);
    }

    public List<StudentGrade> getStudentGradesInSemester(String email, int semester) throws UserNotFound {
        studentRepository.findById(email).orElseThrow(() -> new UserNotFound(email));
        return studentEnrollmentRepository.findByIdStudentEmailAndIdSemesterSemesterNum(email, semester, StudentGrade.class);
    }

    public NewFinancialAidResponse askForFinancialAid(NewFinancialAidRequest finAidReq) throws NonAcceptedStudentAskFinAid, PendingFinAid, UserNotFound {
        String studentEmail = finAidReq.getEmail();
        Student student = studentRepository.findById(studentEmail).orElseThrow(() -> new UserNotFound(studentEmail));

        if(!student.getStatus().toString().equals(Status.ACCEPTED.toString())) {
            throw new NonAcceptedStudentAskFinAid(student.getEmail(), student.getStatus().toString());
        }

        if(!financialAidRepository.findByStudentEmailAndStatus(finAidReq.getEmail(), Status.PENDING).isEmpty()) {
            throw new PendingFinAid(finAidReq.getEmail(), Status.PENDING.toString());
        }

        FinancialAid financialAid = FinancialAid
                .builder()
                .student(student)
                .status(Status.PENDING)
                .financialAmount(finAidReq.getFinancialAmount())
                .build();

        financialAidRepository.save(financialAid);

        return NewFinancialAidResponse
                .builder()
                .id(financialAid.getId())
                .email(financialAid.getStudent().getEmail())
                .status(financialAid.getStatus())
                .financialAmount(financialAid.getFinancialAmount())
                .build();
    }

    public RetrievedStudent getStudent(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<RetrievedStudent> getStudentsByStatus(Status status) {
        return studentRepository.findByStatus(status);
    }
}
