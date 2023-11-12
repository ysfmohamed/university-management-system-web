package com.joe.universitymanagementsystem.users.exceptions;

public class TeacherCoursesReachedThreshold extends Exception {
    public TeacherCoursesReachedThreshold(String email, String courseId) {
        super("Teacher with email \"" + email + "\" cannot be assigned to \"" + courseId + "\", because he reached his/her threshold. Teacher can be assigned to at most 3 courses.");
    }
}
