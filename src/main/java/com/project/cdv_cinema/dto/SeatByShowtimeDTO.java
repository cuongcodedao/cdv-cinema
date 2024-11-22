package com.project.cdv_cinema.dto;


import com.project.cdv_cinema.entity.Seat;
import com.project.cdv_cinema.entity.SeatStatus;
import com.project.cdv_cinema.entity.Showtime;
import com.project.cdv_cinema.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatByShowtimeDTO {

    private ShowtimeDTO showtime;

    private SeatDTO seat;

    private String seatStatus;
}
