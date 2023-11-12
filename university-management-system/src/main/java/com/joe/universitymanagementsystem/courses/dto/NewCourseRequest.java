package com.joe.universitymanagementsystem.courses.dto;

import lombok.Data;

@Data
public class NewCourseRequest {
    private String courseId;
    private String courseName;
    private Integer creditHours;
    private Integer maxStudents;
    private String courseSpecialization;
}
