package com.joe.universitymanagementsystem.users.exceptions;

public class StudentInabilityToEnrollInCourse extends Exception {
    public StudentInabilityToEnrollInCourse(String email, String courseId, int coursePrice, int studentBalance) {
        super("Student with the given \"" + email + "\" email cannot register in the \"" + courseId + "\" because his balance is less than the course price.\nStudent Balance = \"" + studentBalance + "\" and Course Price = \"" + coursePrice + "\"");

    }
}
