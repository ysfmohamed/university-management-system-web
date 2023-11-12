package com.joe.universitymanagementsystem.semesters.model;

import com.joe.universitymanagementsystem.joinentities.model.StudentEnrollment;
import com.joe.universitymanagementsystem.semesters.enums.SemesterStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "semesters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;

    @OneToMany(mappedBy="id.semester")
    private List<StudentEnrollment> studentsEnrollment;

    private int semesterNum;

    @Enumerated(EnumType.STRING)
    private SemesterStatus semesterStatus;
}
