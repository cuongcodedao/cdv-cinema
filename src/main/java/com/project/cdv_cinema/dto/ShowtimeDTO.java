package com.project.cdv_cinema.dto;

import lombok.*;

import java.time.LocalTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeDTO {
    private Long id;
    private RoomDTO roomDTO;
    private MovieDTO movieDTO;
    private String showDate;
    private String showTime;
    private TheaterDTO theaterDTO;
}
