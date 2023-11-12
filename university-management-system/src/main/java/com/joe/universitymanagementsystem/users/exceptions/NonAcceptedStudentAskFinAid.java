package com.joe.universitymanagementsystem.users.exceptions;

import com.joe.universitymanagementsystem.users.enums.Status;

public class NonAcceptedStudentAskFinAid extends Exception {
    public NonAcceptedStudentAskFinAid(String email, String status) {
        super("Student with email \"" + email + "\" cannot request a financial aid, because his/her registration is not accepted.\nCurrently student status is \"" + status + "\".");
    }
}
