package com.project.cdv_cinema.service.impl;

import com.project.cdv_cinema.dto.TicketDTO;
import com.project.cdv_cinema.entity.Seat;
import com.project.cdv_cinema.entity.Showtime;
import com.project.cdv_cinema.entity.Ticket;
import com.project.cdv_cinema.entity.TicketStatus;
import com.project.cdv_cinema.repository.TicketRepository;
import com.project.cdv_cinema.service.ISeatService;
import com.project.cdv_cinema.service.IShowtimeService;
import com.project.cdv_cinema.service.ITicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;
    private final IShowtimeService showtimeService;
    private final ISeatService seatService;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public Ticket createTicket(TicketDTO ticket) {
        Showtime showtime = showtimeService.findById(ticket.getShowtimeId());
        Seat seat = seatService.findById(ticket.getSeatId());
        Ticket newTicket = new Ticket();
        newTicket.setSeat(seat);
        newTicket.setShowtime(showtime);
        newTicket.setStatus(TicketStatus.PENDING);
        newTicket.setPrice(ticket.getPrice());
        return ticketRepository.save(newTicket);
    }
}
