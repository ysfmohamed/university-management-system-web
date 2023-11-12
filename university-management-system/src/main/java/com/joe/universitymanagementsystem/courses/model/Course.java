package com.joe.universitymanagementsystem.courses.model;

import com.joe.universitymanagementsystem.joinentities.model.StudentEnrollment;
import com.joe.universitymanagementsystem.users.teacher.model.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    Questions:
    1- Is there a possibility to change the courseId value in the future?
* */
@Entity
@Table(name="courses")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private String courseId;

    private String courseName;

    private Integer creditHours;

    private Integer maxStudents;

    private String courseSpecialization;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "id.course")
    private List<StudentEnrollment> studentsEnrollment;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
