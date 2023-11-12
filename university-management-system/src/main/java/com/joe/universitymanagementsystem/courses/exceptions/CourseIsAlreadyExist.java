package com.joe.universitymanagementsystem.courses.exceptions;

public class CourseIsAlreadyExist extends Exception {
    public CourseIsAlreadyExist(String courseId) {
        super("The \"" + courseId + "\" you are trying to add is already exist.");
    }
}
