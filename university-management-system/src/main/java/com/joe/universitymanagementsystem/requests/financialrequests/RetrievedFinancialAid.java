package com.joe.universitymanagementsystem.requests.financialrequests;

import com.joe.universitymanagementsystem.users.enums.Status;

public interface RetrievedFinancialAid {
    Integer getId();
    String getStudentEmail();
    String getStudentFirstName();
    String getStudentLastName();
    Integer getFinancialAmount();
    Status getStatus();
}
