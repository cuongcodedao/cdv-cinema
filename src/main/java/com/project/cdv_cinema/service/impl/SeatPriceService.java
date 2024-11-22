package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.entity.SeatPrice;
import com.project.cdv_cinema.entity.SeatType;
import com.project.cdv_cinema.repository.SeatPriceRepository;
import com.project.cdv_cinema.service.ISeatPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class SeatPriceService implements ISeatPriceService {
    private final SeatPriceRepository seatPriceRepository;
    @Override
    public double getFrice(SeatType seatType, LocalTime showtime) {
        SeatPrice newSeatPrice = seatPriceRepository.findBySeatType(seatType);
        if(newSeatPrice == null) {
            return 0.0;
        }
        double price = newSeatPrice.getBasePrice();
        if(newSeatPrice.getTimeOff().isBefore(showtime)){
            price-=newSeatPrice.getPriceOff();
        }
        return price;
    }
}
