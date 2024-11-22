package com.project.cdv_cinema.repository;

import com.project.cdv_cinema.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieIdOrderByShowDate(Long movieId);
    List<Showtime> findAllByMovieId(Long movieId);
    List<Showtime> findAllByTheaterId(Long theaterId);
    List<Showtime> findAllByShowDateEquals(LocalDate showdate);

    @Query("SELECT s.id AS showtimeId, m.id AS movieId, m.name AS movieName, "
            + "t.id AS theaterId, t.name AS theaterName, "
            + "s.showDate AS showDate, s.showTime AS showTime "
            + "FROM Movie m "
            + "JOIN m.showtimes s "
            + "JOIN s.theater t "
            + "WHERE s.showDate >= CURRENT_DATE OR (s.showDate = CURRENT_DATE AND s.showTime >= CURRENT_TIME)")
    List<Object[]> fetchMovieShowtimeData();


}
