package com.project.cdv_cinema.exception;

public class SeatIsLockingException extends Exception {
    public SeatIsLockingException() {
        super("Seat is locking!");
    }
}
