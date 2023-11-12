package com.joe.universitymanagementsystem.users.teacher.model;

import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.users.enums.Gender;
import com.joe.universitymanagementsystem.users.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="teachers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String specialization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="teacher")
    private List<Course> courses;
}
