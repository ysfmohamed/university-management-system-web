package com.joe.universitymanagementsystem.requests.exceptions;

public class FinAidAlreadyAccepted extends Exception {
    public FinAidAlreadyAccepted(String email, String status) {
        super("Student with email \"" + email + "\" will not complete, because he asked previously and got accepted.\nCurrently his/her financial aid status is \"" + status + "\".");
    }
}
