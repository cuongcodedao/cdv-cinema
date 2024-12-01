package com.project.cdv_cinema.dto;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponDTO {
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
}
