package com.joe.universitymanagementsystem.users.teacher.dto;

import com.joe.universitymanagementsystem.users.enums.Gender;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTeacherAccountRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String specialization;
    private Gender gender;
}
