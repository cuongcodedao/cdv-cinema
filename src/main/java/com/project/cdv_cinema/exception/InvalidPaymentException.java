package com.project.cdv_cinema.exception;

public class InvalidPaymentException extends Exception {
    public InvalidPaymentException() {
        super("Invalid payment");
    }
}
