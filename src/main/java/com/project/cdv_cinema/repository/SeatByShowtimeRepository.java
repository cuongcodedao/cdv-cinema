package com.project.cdv_cinema.repository;

import com.project.cdv_cinema.entity.SeatByShowtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatByShowtimeRepository extends JpaRepository<SeatByShowtime, Long> {
    SeatByShowtime findByShowtimeIdAndSeatId(Long showtimeId, Long seatId);
    List<SeatByShowtime> findAllByShowtimeId(Long showtimeId);
}
