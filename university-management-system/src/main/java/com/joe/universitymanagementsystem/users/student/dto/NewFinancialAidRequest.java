package com.joe.universitymanagementsystem.users.student.dto;

import lombok.Data;

@Data
public class NewFinancialAidRequest {
    private String email;
    private Integer financialAmount;
}
