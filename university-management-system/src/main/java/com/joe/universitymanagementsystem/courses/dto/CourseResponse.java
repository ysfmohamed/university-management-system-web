package com.joe.universitymanagementsystem.courses.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CourseResponse {
    private String courseId;
    private String courseName;
    private Integer creditHours;
    private Integer maxStudents;
    private String courseSpecialization;
}
