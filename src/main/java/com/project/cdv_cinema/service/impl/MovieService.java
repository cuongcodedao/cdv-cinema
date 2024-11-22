package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.ImageDTO;
import com.project.cdv_cinema.dto.MovieDTO;
import com.project.cdv_cinema.entity.Movie;
import com.project.cdv_cinema.repository.MovieRepository;
import com.project.cdv_cinema.service.IMovieService;
import com.project.cdv_cinema.util.LocalDateUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MovieDTO> findAllWithPoster() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOS = movies.stream()
                .map(movie -> {
                    MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                    movieDTO.setReleaseDate(LocalDateUtil.convertLocalDateToString(movie.getReleaseDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    movieDTO.setImagePoster(movieDTO.getImages().stream()
                            .filter(ImageDTO::isPoster)
                            .map(ImageDTO::getUrlImage)
                            .findFirst().get());
                    return movieDTO;

                })
                .collect(Collectors.toList());
        return movieDTOS;
    }


    public MovieDTO findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Movie with + " + id + " not found"));
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        movieDTO.setReleaseDate(LocalDateUtil.convertLocalDateToString(movie.getReleaseDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        movieDTO.setImagePoster(movieDTO.getImages().stream()
                .filter(ImageDTO::isPoster)
                .map(ImageDTO::getUrlImage)
                .findFirst().get());
        return movieDTO;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
