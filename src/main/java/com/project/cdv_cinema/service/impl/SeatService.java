package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.entity.Seat;
import com.project.cdv_cinema.entity.SeatStatus;
import com.project.cdv_cinema.exception.SeatIsLockingException;
import com.project.cdv_cinema.repository.SeatRepository;
import com.project.cdv_cinema.service.ISeatService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class SeatService implements ISeatService {

    @Autowired
    private SeatRepository seatRepository;


    @Override
    public Seat findById(Long id) {
        return seatRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
