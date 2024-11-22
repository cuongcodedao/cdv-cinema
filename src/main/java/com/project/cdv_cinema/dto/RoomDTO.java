package com.project.cdv_cinema.dto;

import com.project.cdv_cinema.entity.Seat;
import com.project.cdv_cinema.entity.Theater;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
    private Long id;

    private String name;

    private String status;

    private int maxNumberOfSeat;

    private int numberOfRows;

    private int numberOfColumns;

    private List<SeatDTO> seatDTOS;

}
