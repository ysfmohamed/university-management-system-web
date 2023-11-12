package com.joe.universitymanagementsystem.users.exceptions;

public class TeacherIsNotRegisteredInCourse extends Exception {
    public TeacherIsNotRegisteredInCourse(String email, String courseId) {
        super("Teacher with the given \"" + email + "\" email doesn't registered on this course \"" + courseId + "\"");
    }
}
