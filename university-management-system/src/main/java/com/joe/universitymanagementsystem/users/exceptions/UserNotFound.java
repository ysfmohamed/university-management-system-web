package com.joe.universitymanagementsystem.users.exceptions;

public class UserNotFound extends Exception {
    public UserNotFound(String email) {
        super("User with the given \"" + email + "\" email doesn't exist");
    }
}
