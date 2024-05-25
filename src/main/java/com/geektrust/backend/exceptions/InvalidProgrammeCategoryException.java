package com.geektrust.backend.exceptions;

public class InvalidProgrammeCategoryException extends RuntimeException {

    public InvalidProgrammeCategoryException() {
        super();
    }

    public InvalidProgrammeCategoryException(String message) {
        super(message);
    }
}