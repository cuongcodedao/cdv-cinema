package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.BookingDTO;
import com.project.cdv_cinema.entity.Booking;
import com.project.cdv_cinema.exception.SeatIsLockingException;
import com.project.cdv_cinema.response.BookingResponse;

public interface IBookingService {
    BookingResponse createBooking(BookingDTO bookingDTO) throws SeatIsLockingException;
    BookingResponse paidBooking(Long id);
    BookingResponse findById(Long id);
    void cancelBooking(Long id);
}
