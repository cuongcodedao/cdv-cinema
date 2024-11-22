package com.project.cdv_cinema.response;

import com.project.cdv_cinema.response.ShowtimeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Long id;
    private double price;
    private String status;
    private SeatResponse seat;
    private ShowtimeResponse showtime;
}
