package com.joe.universitymanagementsystem.joinentities.compositekeys;

import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.semesters.model.Semester;
import com.joe.universitymanagementsystem.users.student.model.Student;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollmentKey implements Serializable {
    @ManyToOne
    @MapsId("email")
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name="semesterId")
    private Semester semester;

    public StudentEnrollmentKey(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
