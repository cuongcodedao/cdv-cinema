package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.ShowtimeDTO;
import com.project.cdv_cinema.entity.Movie;
import com.project.cdv_cinema.entity.Seat;
import com.project.cdv_cinema.entity.Showtime;
import com.project.cdv_cinema.response.MovieSelectResponse;
import com.project.cdv_cinema.response.ShowtimeResponse;

import java.util.List;

public interface IShowtimeService {
    List<ShowtimeDTO> findAllByMovieId(Long movieId);
    ShowtimeResponse getResponseById(Long id);

    Showtime findById(Long id);

    List<MovieSelectResponse> filter();
}
