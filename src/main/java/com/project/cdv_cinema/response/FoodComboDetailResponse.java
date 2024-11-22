package com.project.cdv_cinema.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodComboDetailResponse {
    private Long id;
    private FoodComboResponse foodCombo;
    private double unitPrice;
    private int quantity;
}
