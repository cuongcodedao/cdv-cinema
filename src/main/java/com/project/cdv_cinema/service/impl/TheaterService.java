package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.entity.Theater;
import com.project.cdv_cinema.repository.TheaterRepository;
import com.project.cdv_cinema.service.ITheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService implements ITheaterService {
    private final TheaterRepository theaterRepository;
    @Override
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }
}
