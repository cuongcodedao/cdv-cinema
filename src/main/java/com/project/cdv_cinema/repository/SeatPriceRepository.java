package com.project.cdv_cinema.repository;

import com.project.cdv_cinema.entity.SeatPrice;
import com.project.cdv_cinema.entity.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatPriceRepository extends JpaRepository<SeatPrice, Long> {
    SeatPrice findBySeatType(SeatType seatType);
}
