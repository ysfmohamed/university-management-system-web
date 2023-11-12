package com.joe.universitymanagementsystem.requests.exceptions;

public class FinAidAlreadyRejected extends Exception {
    public FinAidAlreadyRejected(String email, String status) {
        super("Student with email \"" + email + "\" will not complete, because he asked previously and got rejected.\nCurrently his/her financial aid status is \"" + status + "\".");
    }
}
