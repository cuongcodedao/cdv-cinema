package com.project.cdv_cinema.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodComboDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_combo_id")
    private FoodCombo foodCombo;

    private double unitPrice;

    private int quantity;

    @ManyToOne
    @JoinColumn(name ="booking_id")
    @JsonBackReference
    private Booking booking;


}
