package com.joe.universitymanagementsystem.courses.dto;

import lombok.Data;

@Data
public class UpdateCourseRequest {
    private String courseName;
    private Integer creditHours;
    private Integer maxStudents;
    private String courseSpecialization;
}
