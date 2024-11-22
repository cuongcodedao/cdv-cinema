package com.project.cdv_cinema.controller;

import com.project.cdv_cinema.service.impl.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }
    @GetMapping("/showtime/{showtimeId}")
    public ResponseEntity<?> findByShowtimeId(@PathVariable Long showtimeId) {
        return ResponseEntity.ok(roomService.findByShowtimeId(showtimeId));
    }
}
