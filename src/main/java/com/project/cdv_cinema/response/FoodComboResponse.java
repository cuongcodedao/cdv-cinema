package com.project.cdv_cinema.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodComboResponse {
    private Long id;
    private String name;
    private double price;
}
