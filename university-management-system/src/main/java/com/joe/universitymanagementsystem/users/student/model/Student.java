package com.joe.universitymanagementsystem.users.student.model;

import com.joe.universitymanagementsystem.joinentities.model.StudentEnrollment;
import com.joe.universitymanagementsystem.requests.financialrequests.FinancialAid;
import com.joe.universitymanagementsystem.users.enums.Gender;
import com.joe.universitymanagementsystem.users.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="students")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String email;

    private String firstName;
    private String lastName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer balance;

    @OneToMany(mappedBy="id.student")
    private List<StudentEnrollment> studentsEnrollment;

    @OneToMany(mappedBy = "student")
    private List<FinancialAid> studentFinancialAids;
}
