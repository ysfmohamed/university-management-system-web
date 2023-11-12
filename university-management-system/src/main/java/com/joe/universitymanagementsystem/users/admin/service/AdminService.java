package com.joe.universitymanagementsystem.users.admin.service;

import com.joe.universitymanagementsystem.requests.exceptions.FinAidAlreadyAccepted;
import com.joe.universitymanagementsystem.requests.exceptions.FinAidAlreadyRejected;
import com.joe.universitymanagementsystem.requests.financialrequests.FinancialAid;
import com.joe.universitymanagementsystem.requests.financialrequests.FinancialAidRepository;
import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.enums.UserType;
import com.joe.universitymanagementsystem.users.student.repository.StudentRepository;
import com.joe.universitymanagementsystem.users.teacher.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final FinancialAidRepository financialAidRepository;

    public void acceptUserRegistration(String email, String type) {
        if(type.equals(UserType.student.toString())) {
            studentRepository.updateStudentStatus(email, Status.ACCEPTED);
        }
        else {
            teacherRepository.updateTeacherStatus(email, Status.ACCEPTED);
        }
    }

    public void rejectUserRegistration(String email, String type) {
        if(type.equals(UserType.student.toString())) {
            studentRepository.updateStudentStatus(email, Status.REJECTED);
        }
        else {
            teacherRepository.updateTeacherStatus(email, Status.REJECTED);
        }
    }

    public String acceptFinancialAidRequest(Integer id) throws FinAidAlreadyAccepted, FinAidAlreadyRejected {
        FinancialAid financialAid = financialAidRepository.findById(id).get();

        String studentEmail = financialAid.getStudent().getEmail();
        Status financialAidStatus = financialAid.getStatus();
        String studentFirstName = financialAid.getStudent().getFirstName();
        String studentLastName = financialAid.getStudent().getLastName();

        if(financialAid.getStatus().toString().equals(Status.ACCEPTED.toString())) {
            throw new FinAidAlreadyAccepted(studentEmail, financialAidStatus.toString());
        }

        if(financialAid.getStatus().toString().equals(Status.REJECTED.toString())) {
            throw new FinAidAlreadyRejected(studentEmail, financialAidStatus.toString());
        }

        // TODO find first then save
        financialAidRepository.updateFinAidStatus(id, Status.ACCEPTED);

        Integer studentBalance = financialAid.getStudent().getBalance();
        Integer financialAidAmount = financialAid.getFinancialAmount();

        Integer newBalance = studentBalance + financialAidAmount;

        studentRepository.updateStudentBalance(studentEmail, newBalance);

        // TODO don't return string
        return "Student \"" + studentFirstName +  " " + studentLastName + "\"  balance increased from " + studentBalance + " to " + newBalance + ".";
    }

    public String rejectFinancialAidRequest(Integer id) {
        FinancialAid financialAid = financialAidRepository.findById(id).get();

        String studentFirstName = financialAid.getStudent().getFirstName();
        String studentLastName = financialAid.getStudent().getLastName();

        financialAidRepository.updateFinAidStatus(id, Status.REJECTED);

        return "Financial aid request is rejected for student \"" + studentFirstName +  " " + studentLastName + "\"";
    }
}
