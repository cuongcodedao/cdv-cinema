package com.project.cdv_cinema.controller;

import com.project.cdv_cinema.service.IShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/showtimes")
public class ShowtimeController {
    private final IShowtimeService showtimeService;

    @GetMapping("/movie/{id}")
    public ResponseEntity<?> showtimes(@PathVariable Long id) {
        return ResponseEntity.ok(showtimeService.findAllByMovieId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showtimeById(@PathVariable Long id) {
        return ResponseEntity.ok(showtimeService.getResponseById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterShowtime(){
        return ResponseEntity.ok(showtimeService.filter());
    }
}
