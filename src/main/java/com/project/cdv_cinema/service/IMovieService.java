package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.MovieDTO;
import com.project.cdv_cinema.entity.Movie;

import java.util.List;


public interface IMovieService {
    List<MovieDTO> findAllWithPoster();
    MovieDTO findById(Long id);
    List<Movie> findAll();

}
