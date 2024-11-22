package com.project.cdv_cinema.controller;

import com.project.cdv_cinema.service.impl.SeatByShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/seatbyshowtime")
public class SeatByShowtimeController {
    private final SeatByShowtimeService seatByShowtimeService;
    @GetMapping("/seats/{id}")
    public ResponseEntity<?> getSeatsByShowtimeId(@PathVariable Long id) {
        return ResponseEntity.ok(seatByShowtimeService.getSeatsByShowtimeId(id));
    }
}
