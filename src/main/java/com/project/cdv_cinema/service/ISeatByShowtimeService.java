package com.project.cdv_cinema.service;


import com.project.cdv_cinema.entity.SeatStatus;
import com.project.cdv_cinema.response.SeatByShowtimeResponse;

import java.util.List;

public interface ISeatByShowtimeService {
    void unLockingSeats(List<Long> ids,Long showtimeId);
    void unLockingSeat(Long ids, Long showtimeId);
    boolean lockingSeats(List<Long> seatIds, Long showtimeId);
    void updateSeatStatus(Long showtimeId, Long seatId, SeatStatus seatStatus);
    List<SeatByShowtimeResponse> getSeatsByShowtimeId(Long showtimeId);
}
