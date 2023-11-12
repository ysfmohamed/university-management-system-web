package com.joe.universitymanagementsystem.requests.financialrequests;

import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.student.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="financial_aid_requests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialAid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Integer financialAmount;

    @Enumerated(EnumType.STRING)
    private Status status;
}
