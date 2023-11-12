package com.joe.universitymanagementsystem.users.student.dto;

import com.joe.universitymanagementsystem.users.enums.Gender;
import com.joe.universitymanagementsystem.users.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewStudentAccountResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Gender gender;
    private Status status;
    private Integer balance;
}
