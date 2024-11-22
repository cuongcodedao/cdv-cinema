package com.project.cdv_cinema.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodComboDetailDTO {
    private Long foodComboId;
    private int quantity;
    private double unitPrice;
    private Long bookingId;
}
