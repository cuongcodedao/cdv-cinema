package com.project.cdv_cinema.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate showDate;

    private LocalTime showTime;

    @OneToMany(mappedBy = "showtime")
    private List<SeatByShowtime> seatByShowtimeStatuses;

    @OneToMany(mappedBy = "showtime")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "showtime")
    private List<Booking> bookings;


    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="theater_id")
    private Theater theater;
}
