package com.project.cdv_cinema.response;

import com.project.cdv_cinema.entity.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatByShowtimeResponse {
    private Long id;

    private Long roomId;

    private String code;

    private Integer rowId;

    private Integer colId;

    private SeatType type;

    private String status;

    private double price;
}
