package com.project.cdv_cinema.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateSelectedResponse {
    private String date;
    private List<ShowtimeSelectResponse> showtimeSelectResponses;
}
