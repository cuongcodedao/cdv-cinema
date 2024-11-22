package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.ImageDTO;
import com.project.cdv_cinema.dto.RoomDTO;
import com.project.cdv_cinema.dto.ShowtimeDTO;
import com.project.cdv_cinema.dto.TheaterDTO;
import com.project.cdv_cinema.entity.*;
import com.project.cdv_cinema.repository.ShowtimeRepository;
import com.project.cdv_cinema.response.*;
import com.project.cdv_cinema.service.*;
import com.project.cdv_cinema.util.LocalDateUtil;
import com.project.cdv_cinema.util.LocalTimeUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShowtimeService implements IShowtimeService{
    private final ModelMapper modelMapper;
    private final ShowtimeRepository showtimeRepository;
    private final IMovieService movieService;
    private final ITheaterService theaterService;
    private final IRoomService roomService;
    private final ISeatByShowtimeService seatByShowtimeService;

    public List<ShowtimeDTO> findAllByMovieId(Long movieId) {
        List<Showtime> showtimes = showtimeRepository.findByMovieIdOrderByShowDate(movieId);
        List<ShowtimeDTO> showtimeDTOS = showtimes.stream()
                .map(showtime -> {
                    ShowtimeDTO showtimeDTO = modelMapper.map(showtime, ShowtimeDTO.class);
                    showtimeDTO.setRoomDTO(modelMapper.map(showtime.getRoom(), RoomDTO.class));
                    showtimeDTO.setTheaterDTO(modelMapper.map(showtime.getTheater(), TheaterDTO.class));
                    showtimeDTO.setShowDate(LocalDateUtil.convertLocalDateToString(showtime.getShowDate(), DateTimeFormatter.ofPattern("dd/MM")));
                    showtimeDTO.setShowTime(LocalTimeUtil.convertLocalTime(showtime.getShowTime(), DateTimeFormatter.ofPattern("HH:mm")));
                    return showtimeDTO;
                }).toList();
        return showtimeDTOS;
    }


    @Override
    public ShowtimeResponse getResponseById(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Not found showtime"));
        Room room = showtime.getRoom();
        RoomByShowtimeResponse roomResponse = modelMapper.map(room, RoomByShowtimeResponse.class);
        roomResponse.setSeatByShowtimeResponses(seatByShowtimeService.getSeatsByShowtimeId(id));
        ShowtimeResponse showtimeResponse = new ShowtimeResponse();
        showtimeResponse.setId(showtime.getId());
        showtimeResponse.setRoom(roomResponse);
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setName(showtime.getMovie().getName());
        movieResponse.setPoster(showtime.getMovie().getPoster());
        movieResponse.setRating(showtime.getMovie().getRating());
        showtimeResponse.setMovie(movieResponse);
        showtimeResponse.setTheaterName(showtime.getTheater().getName());
        showtimeResponse.setShowDate(LocalDateUtil.convertLocalDateToString(showtime.getShowDate(), DateTimeFormatter.ofPattern("dd/MM")));
        showtimeResponse.setShowTime(LocalTimeUtil.convertLocalTime(showtime.getShowTime(), DateTimeFormatter.ofPattern("HH:mm")));
        return showtimeResponse;
    }

    @Override
    public Showtime findById(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Not found showtime"));
        return showtime;
    }

    @Override
    public List<MovieSelectResponse> filter() {
        List<Object[]> rawData = showtimeRepository.fetchMovieShowtimeData();

        // Dùng Map để gom dữ liệu
        Map<Long, MovieSelectResponse> movieMap = new HashMap<>();
        Map<Long, TheaterSelectResponse> theaterMap = new HashMap<>();
        Map<String, DateSelectedResponse> dateMap = new HashMap<>();

        for (Object[] row : rawData) {
            Long showtimeId = (Long) row[0];
            Long movieId = (Long) row[1];
            String movieName = (String) row[2];
            Long theaterId = (Long) row[3];
            String theaterName = (String) row[4];
            LocalDate showDate = (LocalDate) row[5];
            LocalTime showTime = (LocalTime) row[6];

            // Xử lý Movie
            MovieSelectResponse movie = movieMap.computeIfAbsent(movieId, id -> {
                MovieSelectResponse m = new MovieSelectResponse();
                m.setId(movieId);
                m.setName(movieName);
                m.setTheaterSelectResponses(new ArrayList<>());
                return m;
            });

            // Xử lý Theater
            TheaterSelectResponse theater = theaterMap.computeIfAbsent(theaterId, id -> {
                TheaterSelectResponse t = new TheaterSelectResponse();
                t.setId(theaterId);
                t.setTheaterName(theaterName);
                t.setDateSelectedResponses(new ArrayList<>());
                movie.getTheaterSelectResponses().add(t);
                return t;
            });


            String dateKey = theaterId + "|" + showDate.toString();
            DateSelectedResponse date = dateMap.computeIfAbsent(dateKey, key -> {
                DateSelectedResponse d = new DateSelectedResponse();
                d.setDate(LocalDateUtil.convertLocalDateToString(showDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                d.setShowtimeSelectResponses(new ArrayList<>());
                theater.getDateSelectedResponses().add(d);
                return d;
            });


            ShowtimeSelectResponse showtimeResponse = new ShowtimeSelectResponse();
            showtimeResponse.setId(showtimeId); // Nếu cần ID của showtime
            showtimeResponse.setShowtime(LocalTimeUtil.convertLocalTime(showTime, DateTimeFormatter.ofPattern("HH:mm")));
            date.getShowtimeSelectResponses().add(showtimeResponse);
        }

        return new ArrayList<>(movieMap.values());
    }



}
