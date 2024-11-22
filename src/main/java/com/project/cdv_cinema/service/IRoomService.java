package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.RoomDTO;
import com.project.cdv_cinema.response.RoomByShowtimeResponse;


public interface IRoomService {
    RoomDTO findById(Long id);
    RoomByShowtimeResponse findByShowtimeId(Long id);
}
