package com.example.geektrust.exceptions;

public class InvalidProgrammeCategoryException extends RuntimeException {

    public InvalidProgrammeCategoryException() {
        super();
    }

    public InvalidProgrammeCategoryException(String message) {
        super(message);
    }
}