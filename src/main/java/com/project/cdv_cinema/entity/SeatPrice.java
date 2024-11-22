package com.project.cdv_cinema.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double basePrice;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private LocalTime timeOff;
    private double priceOff;
}
