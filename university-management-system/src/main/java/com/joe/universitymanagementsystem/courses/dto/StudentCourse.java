package com.joe.universitymanagementsystem.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface StudentCourse {
    @JsonProperty("courseId")
    public String getIdCourseCourseId();
    @JsonProperty("courseName")
    public String getIdCourseCourseName();
}
