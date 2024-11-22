package com.project.cdv_cinema.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private int total;
    private String infor;
    private Long bookingId;
}
