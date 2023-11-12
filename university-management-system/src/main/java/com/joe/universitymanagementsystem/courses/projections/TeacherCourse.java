package com.joe.universitymanagementsystem.courses.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface TeacherCourse {
    @JsonProperty("courseId")
    public String getIdCourseCourseId();
    @JsonProperty("courseName")
    public String getIdCourseCourseName();
    @JsonProperty("specialization")
    public String getIdCourseCourseSpecialization();
    @JsonProperty("maxStudents")
    public Integer getIdCourseMaxStudents();
    @JsonProperty("creditHours")
    public Integer getIdCourseCreditHours();

}
