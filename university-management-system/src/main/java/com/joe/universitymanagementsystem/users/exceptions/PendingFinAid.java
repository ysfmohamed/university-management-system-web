package com.joe.universitymanagementsystem.users.exceptions;

public class PendingFinAid extends Exception {
    public PendingFinAid(String email, String status) {
        super("Student with email \"" + email + "\" asked for financial aid but its still being reviewed.\nCurrently his/her financial aid status is \"" + status + "\".");
    }
}
