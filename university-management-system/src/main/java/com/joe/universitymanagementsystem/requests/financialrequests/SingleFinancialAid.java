package com.joe.universitymanagementsystem.requests.financialrequests;

import com.joe.universitymanagementsystem.users.enums.Status;

public interface SingleFinancialAid {
    String getStudentEmail();
    String getStudentFirstName();
    String getStudentLastName();
    Integer getFinancialAmount();
    Status getStatus();
}
