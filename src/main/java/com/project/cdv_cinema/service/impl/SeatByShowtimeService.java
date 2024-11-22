package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.SeatByShowtimeDTO;
import com.project.cdv_cinema.dto.SeatDTO;
import com.project.cdv_cinema.entity.SeatByShowtime;
import com.project.cdv_cinema.entity.SeatStatus;
import com.project.cdv_cinema.entity.Showtime;
import com.project.cdv_cinema.repository.SeatByShowtimeRepository;
import com.project.cdv_cinema.repository.SeatRepository;
import com.project.cdv_cinema.repository.ShowtimeRepository;
import com.project.cdv_cinema.response.SeatByShowtimeResponse;
import com.project.cdv_cinema.service.ISeatByShowtimeService;
import com.project.cdv_cinema.service.ISeatPriceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SeatByShowtimeService implements ISeatByShowtimeService {
    private final SeatByShowtimeRepository seatByShowtimeRepository;
    private final ModelMapper modelMapper;

    private final ISeatPriceService seatPriceService;

    private static final String SEAT_LOCK_PREFIX = "seat_lock:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    private static final long LOCK_TIMEOUT = 425L;

    @Transactional
    public boolean lockingSeats(List<Long> seatIds, Long showtimeId) {
        List<Long> lockedSeats = new ArrayList<>();
        List<SeatByShowtime> seatByShowtimes = new ArrayList<>();

        for (Long seatId : seatIds) {
            String lockKey = SEAT_LOCK_PREFIX + seatId + "-" + showtimeId;
            String lockValue = UUID.randomUUID().toString();


            SeatByShowtime seatByShowtime = seatByShowtimeRepository.findByShowtimeIdAndSeatId(showtimeId, seatId);
            if (seatByShowtime != null && seatByShowtime.getStatus()==SeatStatus.AVAILABLE) {
                Boolean lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, LOCK_TIMEOUT, TimeUnit.SECONDS);
                if (lockAcquired != null && lockAcquired) {
                    seatByShowtime.setStatus(SeatStatus.LOCKING);
                    lockedSeats.add(seatId);
                    seatByShowtimes.add(seatByShowtime);
                } else {
                    unLockingSeats(lockedSeats, showtimeId);
                    return false;
                }
            } else {
                unLockingSeats(lockedSeats, showtimeId);
                return false;
            }
        }

        seatByShowtimeRepository.saveAll(seatByShowtimes);
        return true;
    }


    public void unLockingSeats(List<Long> seatIds, Long showtimeId) {
        for (Long seatId : seatIds) {
            String lockKey = SEAT_LOCK_PREFIX + seatId + "-" + showtimeId;
            redisTemplate.delete(lockKey);
        }
        seatIds.clear();
    }

    @Override
    public void unLockingSeat(Long id, Long showtimeId) {
        String lockKey = SEAT_LOCK_PREFIX + id + "-" + showtimeId;
        redisTemplate.delete(lockKey);
    }

    @Override
    @Transactional
    public void updateSeatStatus(Long showtimeId, Long seatId, SeatStatus seatStatus) {
        SeatByShowtime seatByShowtime = seatByShowtimeRepository.findByShowtimeIdAndSeatId(showtimeId, seatId);
        seatByShowtime.setStatus(seatStatus);
        seatByShowtimeRepository.save(seatByShowtime);
    }

    @Override
    public List<SeatByShowtimeResponse> getSeatsByShowtimeId(Long showtimeId) {
        List<SeatByShowtime> seatByShowtimes = seatByShowtimeRepository.findAllByShowtimeId(showtimeId);
        for(SeatByShowtime seatByShowtime: seatByShowtimes){
            if(seatByShowtime.getStatus()==SeatStatus.LOCKING && !Boolean.TRUE.equals(redisTemplate.hasKey(SEAT_LOCK_PREFIX + seatByShowtime.getId()+"-" + showtimeId))){
                seatByShowtime.setStatus(SeatStatus.AVAILABLE);
                seatByShowtimeRepository.save(seatByShowtime);
            }
        }
        List<SeatByShowtimeResponse> seatByShowtimeResponses = seatByShowtimes.stream().map(s -> {
            SeatByShowtimeResponse seat = modelMapper.map(s.getSeat(), SeatByShowtimeResponse.class);
            seat.setStatus(String.valueOf(s.getStatus()));
            seat.setPrice(seatPriceService.getFrice(s.getSeat().getType(), s.getShowtime().getShowTime()));
            return seat;
        }).toList();
        return seatByShowtimeResponses;
    }
}
