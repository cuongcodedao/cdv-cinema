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
public class MovieSelectResponse {
    private Long id;
    private String name;
    private List<TheaterSelectResponse> theaterSelectResponses;
}
