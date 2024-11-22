package com.project.cdv_cinema.service;

import com.project.cdv_cinema.entity.Seat;
import com.project.cdv_cinema.exception.SeatIsLockingException;

import java.util.List;

public interface ISeatService {
    Seat findById(Long id);
}
