package com.project.cdv_cinema.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String urlImage;

    private String code;

    private String scope;

    private String description;

    private String discountType;

    private double discountValue;

    private double maxDiscountAmount;

    private double minimumPurchaseAmount;

    private LocalDateTime startDate;

    private LocalDateTime expirationDate;

    private int usageLimit;

    private int used;

    private int userSpecificLimit;

    private String status;

    @ManyToMany
    @JoinTable(
            name="coupon_booking",
            joinColumns = @JoinColumn(name="coupon_id"),
            inverseJoinColumns = @JoinColumn(name="booking_id")
    )
    private List<Booking> bookings;
}
