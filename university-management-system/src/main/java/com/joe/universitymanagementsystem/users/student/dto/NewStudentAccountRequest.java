package com.joe.universitymanagementsystem.users.student.dto;

import com.joe.universitymanagementsystem.users.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewStudentAccountRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Gender gender;
    private Integer balance;
}
