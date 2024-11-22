package com.project.cdv_cinema.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheaterDTO {
    private int theaterId;
    private String theaterName;
    private String theaterAddress;
    private String status;
}
