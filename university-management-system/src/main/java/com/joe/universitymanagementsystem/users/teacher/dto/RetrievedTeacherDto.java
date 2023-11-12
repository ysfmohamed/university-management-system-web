package com.joe.universitymanagementsystem.users.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrievedTeacherDto {
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String specialization;
}
