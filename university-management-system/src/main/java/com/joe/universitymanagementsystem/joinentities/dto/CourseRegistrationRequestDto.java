package com.joe.universitymanagementsystem.joinentities.dto;

import com.joe.universitymanagementsystem.courses.model.Course;
import com.joe.universitymanagementsystem.users.student.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationRequestDto {
    private String email;
    private String courseId;
}
