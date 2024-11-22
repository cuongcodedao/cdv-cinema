package com.project.cdv_cinema.response;

import com.project.cdv_cinema.response.TicketResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long id;
    private Long userId;
    private double totalPrice;
    private String status;
    private String date;
    private List<TicketResponse> tickets;
    private List<FoodComboDetailResponse> foodComboDetails;
    private UserResponse user;
    private List<Long> couponIds;
    private String qrCode;
}
