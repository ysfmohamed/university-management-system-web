package com.joe.universitymanagementsystem.users.student.dto;

import com.joe.universitymanagementsystem.users.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewFinancialAidResponse {
    private Integer id;
    private String email;
    private Integer financialAmount;
    private Status status;
}
