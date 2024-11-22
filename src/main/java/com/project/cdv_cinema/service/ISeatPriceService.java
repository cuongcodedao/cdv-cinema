package com.project.cdv_cinema.service;

import com.project.cdv_cinema.entity.SeatPrice;
import com.project.cdv_cinema.entity.SeatType;

import java.time.LocalTime;

public interface ISeatPriceService {
    double getFrice(SeatType seatType, LocalTime showtime);
}
