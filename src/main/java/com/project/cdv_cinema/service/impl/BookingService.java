package com.project.cdv_cinema.service.impl;


import com.google.gson.Gson;
import com.project.cdv_cinema.DataGenerate.QrCodeGenerator;
import com.project.cdv_cinema.dto.BookingDTO;
import com.project.cdv_cinema.dto.FoodComboDetailDTO;
import com.project.cdv_cinema.dto.TicketDTO;
import com.project.cdv_cinema.entity.*;
import com.project.cdv_cinema.exception.SeatIsLockingException;
import com.project.cdv_cinema.repository.BookingRepository;
import com.project.cdv_cinema.repository.TicketRepository;
import com.project.cdv_cinema.response.*;
import com.project.cdv_cinema.service.*;
import com.project.cdv_cinema.util.LocalDateUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;
    private final ITicketService ticketService;
    private final ISeatByShowtimeService seatByShowtimeService;
    private final IFoodComboDetailService foodComboDetailService;
    private final IUserService userService;
    private final ModelMapper modelMapper;
    private final ShowtimeService showtimeService;
    private final Gson gson;

    @Override
    @Transactional
    public BookingResponse createBooking(BookingDTO bookingDTO) throws SeatIsLockingException {
        List<Long> seats = new ArrayList<>();
        for(TicketDTO ticketDTO : bookingDTO.getTickets()) {
            seats.add(ticketDTO.getSeatId());
        }
        if(!seatByShowtimeService.lockingSeats(seats, bookingDTO.getShowtimeId())) throw new SeatIsLockingException();
        Booking booking = new Booking();
        User user = userService.findById(bookingDTO.getUserId());
        if(user == null) throw new EntityNotFoundException("User not found");
        double totalPrice = 0.0;
        List<Ticket> tickets = new ArrayList<>();

        for(TicketDTO ticketDTO : bookingDTO.getTickets()) {
            seats.add(ticketDTO.getSeatId());
            Ticket ticket = ticketService.createTicket(ticketDTO);
            ticket.setBooking(booking);
            tickets.add(ticket);
            totalPrice += ticketDTO.getPrice();
        }
        List<FoodComboDetail> foodComboDetails = new ArrayList<>();
        for(FoodComboDetailDTO foodComboDetailDTO : bookingDTO.getFoodComboDetails()){
            FoodComboDetail foodComboDetail = foodComboDetailService.createFoodComboDetail(foodComboDetailDTO);
            foodComboDetail.setBooking(booking);
            totalPrice += foodComboDetail.getUnitPrice()*foodComboDetail.getQuantity();
            foodComboDetails.add(foodComboDetail);
        }
        booking.setShowtime(showtimeService.findById(bookingDTO.getShowtimeId()));
        booking.setTickets(tickets);
        booking.setFoodComboDetails(foodComboDetails);
        booking.setUser(user);
        booking.setDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.PENDING);
        booking.setTotalPrice(totalPrice);
        bookingRepository.save(booking);
        return toBookingResponse(booking);
    }

    @Override
    public BookingResponse paidBooking(Long idBooking) {
        Booking booking = bookingRepository.findById(idBooking).orElseThrow(EntityNotFoundException::new);
        booking.setStatus(BookingStatus.PAID);
        for(Ticket ticket : booking.getTickets()){
            ticket.setStatus(TicketStatus.CONFIRMED);
            seatByShowtimeService.updateSeatStatus(ticket.getShowtime().getId(), ticket.getSeat().getId(), SeatStatus.RESERVED);
        }
        BookingResponse bookingResponse = toBookingResponse(booking);
        String urlQRCode = QrCodeGenerator.QRCodeGeneratorAndSaving(gson.toJson(bookingResponse), booking.getId());
        booking.setQrCode(urlQRCode);
        bookingRepository.save(booking);
        return toBookingResponse(booking);
    }

    public BookingResponse toBookingResponse(Booking booking) {
        BookingResponse bookingResponse = modelMapper.map(booking, BookingResponse.class);
        bookingResponse.setDate(booking.getDate().toString());
        List<TicketResponse> ticketResponses = booking.getTickets().stream()
                .map(ticket -> {
                    TicketResponse ticketResponse = modelMapper.map(ticket, TicketResponse.class);
                    ticketResponse.setSeat(modelMapper.map(ticket.getSeat(), SeatResponse.class));
                    ticketResponse.setShowtime(modelMapper.map(ticket.getShowtime(), ShowtimeResponse.class));
                    ticketResponse.getShowtime().setShowDate(LocalDateUtil.convertLocalDateToString(ticket.getShowtime().getShowDate(), DateTimeFormatter.ofPattern("dd/MM")));
                    return ticketResponse;
                })
                .collect(Collectors.toList());

        bookingResponse.setTickets(ticketResponses);

        List<FoodComboDetailResponse> foodComboDetails = booking.getFoodComboDetails().stream()
                .map(detail -> {
                    FoodComboDetailResponse response = modelMapper.map(detail, FoodComboDetailResponse.class);
                    response.setFoodCombo(modelMapper.map(detail.getFoodCombo(), FoodComboResponse.class));
                    return response;
                })
                .collect(Collectors.toList());

        bookingResponse.setFoodComboDetails(foodComboDetails);
        bookingResponse.setUser(modelMapper.map(booking.getUser(), UserResponse.class));
        return bookingResponse;
    }



    @Override
    public BookingResponse findById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return toBookingResponse(booking);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        booking.setStatus(BookingStatus.CANCELLED);
        for(Ticket ticket : booking.getTickets()){
            ticket.setStatus(TicketStatus.CANCELLED);
            seatByShowtimeService.unLockingSeat(ticket.getSeat().getId(), ticket.getShowtime().getId());
            seatByShowtimeService.updateSeatStatus(ticket.getShowtime().getId(), ticket.getSeat().getId(), SeatStatus.AVAILABLE);
        }
        bookingRepository.save(booking);
    }
}
