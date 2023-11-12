package com.joe.universitymanagementsystem.courses.exceptions;

public class CourseNotFound extends Exception {
    public CourseNotFound(String courseId) {
        super("Course with the given \"" + courseId + "\" course id doesn't exist");
    }
}
