package com.example.geektrust.exceptions;

public class InvalidDiscountCouponException extends RuntimeException {

    public InvalidDiscountCouponException(String message) {
        super(message);
    }
}