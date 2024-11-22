package com.project.cdv_cinema.response;

import com.project.cdv_cinema.dto.SeatDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomByShowtimeResponse {
    private String name;

    private String status;

    private int maxNumberOfSeat;

    private int numberOfRows;

    private int numberOfColumns;

    private List<SeatByShowtimeResponse> seatByShowtimeResponses;


}
