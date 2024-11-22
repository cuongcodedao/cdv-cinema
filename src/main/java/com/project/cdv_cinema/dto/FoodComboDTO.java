package com.project.cdv_cinema.dto;

import com.project.cdv_cinema.entity.Booking;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodComboDTO {
    private String name;
    private double price;

}
