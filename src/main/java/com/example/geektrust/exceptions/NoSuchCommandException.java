package com.example.geektrust.exceptions;

public class NoSuchCommandException extends RuntimeException {
    public NoSuchCommandException() {
        super();
    }

    public NoSuchCommandException(String msg) {
        super(msg);
    }
}

