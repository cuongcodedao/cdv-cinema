package com.project.cdv_cinema.controller;

import com.project.cdv_cinema.entity.SeatStatus;
import com.project.cdv_cinema.service.ISeatService;
import com.project.cdv_cinema.service.impl.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/seats")
public class SeatController {
    private final ISeatService seatService;

    @PutMapping("/locking")
    public ResponseEntity<?> lockingSeat(@RequestBody List<Long> seatIds) {
        return null;
    }
}
