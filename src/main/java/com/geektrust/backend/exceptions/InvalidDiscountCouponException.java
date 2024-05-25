package com.geektrust.backend.exceptions;

public class InvalidDiscountCouponException extends RuntimeException {

    public InvalidDiscountCouponException(String message) {
        super(message);
    }
}