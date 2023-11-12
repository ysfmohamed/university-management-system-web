package com.joe.universitymanagementsystem.users.student.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface StudentGrade {
    @JsonProperty("firstName")
    public String getIdStudentFirstName();
    @JsonProperty("lastName")
    public String getIdStudentLastName();
    @JsonProperty("email")
    public String getIdStudentEmail();
    @JsonProperty("grade")
    public String getGrade();
    @JsonProperty("courseId")
    public String getIdCourseCourseId();
    @JsonProperty("courseName")
    public String getIdCourseCourseName();
    @JsonProperty("semester")
    public String getIdSemesterSemesterNum();
}
