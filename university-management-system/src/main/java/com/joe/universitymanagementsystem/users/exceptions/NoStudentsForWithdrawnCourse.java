package com.joe.universitymanagementsystem.users.exceptions;

public class NoStudentsForWithdrawnCourse extends Exception {
    public NoStudentsForWithdrawnCourse(String email, String courseId) {
        super("Teacher with the given \"" + email + "\" doesn't have students enrolled in the \"" + courseId +
                "\" course he/she wants to withdraw from");
    }
}
