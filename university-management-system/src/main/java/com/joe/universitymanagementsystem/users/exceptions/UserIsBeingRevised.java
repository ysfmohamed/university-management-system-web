package com.joe.universitymanagementsystem.users.exceptions;

public class UserIsBeingRevised extends Exception {
    public UserIsBeingRevised(String email) {
        super("Admin is still revising the provided \"" + email + "\" email.");
    }
}
