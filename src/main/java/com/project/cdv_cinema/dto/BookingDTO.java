package com.project.cdv_cinema.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private List<Long> ticketIds;
    private List<TicketDTO> tickets;
    private List<Long> foodComboDetailIds;
    private List<FoodComboDetailDTO> foodComboDetails;
    private Long userId;
    private double totalPrice;
    private String status;
    private String paymentProvider;
    private List<Long> couponIds;
    private Long showtimeId;
    private LocalDateTime date;

}
