package com.project.cdv_cinema.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {
    private double price;
    private String status;
    private Long seatId;
    private Long showtimeId;
}
