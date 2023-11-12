package com.joe.universitymanagementsystem.joinentities.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface StudentOfCourse {
    @JsonProperty("studentFirstName")
    public String getIdStudentFirstName();
    @JsonProperty("studentLastName")
    public String getIdStudentLastName();
    @JsonProperty("studentEmail")
    public String getIdStudentEmail();
}
