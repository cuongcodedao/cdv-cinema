package com.project.cdv_cinema.service;

import com.project.cdv_cinema.dto.TicketDTO;
import com.project.cdv_cinema.entity.Ticket;

public interface ITicketService {
    Ticket createTicket(TicketDTO tk);
}
