package com.joe.universitymanagementsystem.joinentities.model;

import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.joinentities.compositekeys.StudentEnrollmentKey;
import com.joe.universitymanagementsystem.users.student.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students_enrollment")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollment {
    @EmbeddedId
    private StudentEnrollmentKey id;

    private int grade;
}
