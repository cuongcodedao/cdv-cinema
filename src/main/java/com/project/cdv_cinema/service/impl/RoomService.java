package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.RoomDTO;

import com.project.cdv_cinema.dto.SeatDTO;
import com.project.cdv_cinema.entity.Room;
import com.project.cdv_cinema.entity.Showtime;
import com.project.cdv_cinema.repository.RoomRepository;
import com.project.cdv_cinema.response.RoomByShowtimeResponse;
import com.project.cdv_cinema.service.IRoomService;
import com.project.cdv_cinema.service.ISeatByShowtimeService;
import com.project.cdv_cinema.service.IShowtimeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    @Override
    public RoomDTO findById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not found room id = " + id));
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        List<SeatDTO> seats = room.getSeats().stream().map(seat -> modelMapper.map(seat, SeatDTO.class)).toList();
        roomDTO.setSeatDTOS(seats);
        return roomDTO;

    }

    @Override
    public RoomByShowtimeResponse findByShowtimeId(Long id) {
        return null;
    }


}
