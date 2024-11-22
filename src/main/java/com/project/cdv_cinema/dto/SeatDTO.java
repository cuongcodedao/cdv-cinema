package com.project.cdv_cinema.dto;

import com.project.cdv_cinema.entity.Room;
import com.project.cdv_cinema.entity.SeatStatus;
import com.project.cdv_cinema.entity.SeatType;
import com.project.cdv_cinema.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatDTO {
    private Long id;

    private Long roomId;

    private String code;

    private Integer rowId;

    private Integer colId;

    private SeatType type;

}
