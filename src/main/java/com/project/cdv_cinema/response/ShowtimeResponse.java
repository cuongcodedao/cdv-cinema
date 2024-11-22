package com.project.cdv_cinema.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeResponse {
    private Long id;
    private String theaterName;
    private String showDate;
    private String showTime;
    private MovieResponse movie;
    private RoomByShowtimeResponse room;
}
