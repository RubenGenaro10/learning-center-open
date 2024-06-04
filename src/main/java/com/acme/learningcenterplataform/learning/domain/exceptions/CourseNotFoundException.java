package com.acme.learningcenterplataform.learning.domain.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long aLong) {
        super("Course with id " + aLong + " not found");
    }

}
