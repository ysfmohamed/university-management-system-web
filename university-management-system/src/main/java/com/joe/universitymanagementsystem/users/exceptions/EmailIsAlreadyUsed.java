package com.joe.universitymanagementsystem.users.exceptions;

public class EmailIsAlreadyUsed extends Exception {
    public EmailIsAlreadyUsed(String email) {
        super("Admin accepted this registration request with the provided +\"" + email + "\" email.");
    }
}
