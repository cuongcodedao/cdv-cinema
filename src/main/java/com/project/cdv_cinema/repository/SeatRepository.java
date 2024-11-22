package com.project.cdv_cinema.repository;

import com.project.cdv_cinema.entity.Seat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface SeatRepository extends JpaRepository<Seat, Long> {

//    @Modifying
//    @Query("update Seat se set se.status = 'LOCKING' where se.id in :ids")
//    int lockSeat(@Param("ids") List<Long> ids);
}
