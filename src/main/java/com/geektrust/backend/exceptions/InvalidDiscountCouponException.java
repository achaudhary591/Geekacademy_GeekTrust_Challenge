package com.geektrust.backend.exceptions;

public class InvalidDiscountCouponException extends RuntimeException {

    public InvalidDiscountCouponException() {
        super();
    }

    public InvalidDiscountCouponException(String message) {
        super(message);
    }
}